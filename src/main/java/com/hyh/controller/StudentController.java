package com.hyh.controller;

import com.hyh.model.Clazz;
import com.hyh.model.User;
import com.hyh.service.IClazzService;
import com.hyh.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class StudentController {
    // http://ww.tulun.com/login?name=zhangsan&pwd=123&sdf=3452345

    @Autowired
    private IStudentService iss;
    @Autowired
    private IClazzService ics;


    /**
     * 访问主页面
     * @return
     */
    @RequestMapping(value = {"/login"},
            method=RequestMethod.GET)
    public String login(){
        return "login";  // 返回一个视图名称 => jsp视图页面上 => 视图解析器
    }

    /**
     * 处理登录页面用户的登录操作
     * @param name
     * @param pwd
     * @param session
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam("name") String name,@RequestParam("pwd") String pwd, HttpSession session){
        User stu = iss.queryStudent(name, pwd);
        if(stu == null){
            return "redirect:/login";  // redirect  forword
        } else{
            session.setAttribute("student", stu);
            return "redirect:/main";
        }
    }

    /**
     * 学生信息显示的主页面
     * 当前登录用户是：
     * 学生信息显示：
     * 编号  学生姓名  密码   年龄   性别   班级      操作
     *  1     张三    111    20    男  查看班级信息  修改 删除
     * @return
     */

    @RequestMapping("/main")
    public String main(Model model, HttpServletRequest request){
        List<User> stuList = iss.queryAllStudent();
        System.out.println(stuList);
        System.out.println();
        model.addAttribute("stuList", stuList);

        /*获取assets/file目录下的所有文件名字在页面进行显示，也可以封装在service层当中，此处为了简便，直接
         * 在web层实现*/
        File root = new File(request.getServletContext().getRealPath("/assets/file"));
        File[] files = root.listFiles();
        Map<String, String> fileMap = new HashMap<>();
        for (File file : files) {
            try {
                fileMap.    put(file.getName(), URLEncoder.encode(file.getName(), "utf-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        model.addAttribute("fileMap", fileMap);

        return "main";
    }

    /**
     * 删除学生操作
     * @return
     */
    @RequestMapping("/delete/uid/{uid}")
    public String delete(@PathVariable Integer uid){
        User user = iss.queryStudentById(uid);
        System.out.println(user);
        System.out.println(user.getClazz());
        System.out.println(user.getClazz().getId());
        iss.removeStudent(uid, user.getClazz().getId());

        return "redirect:/main";
    }

    /**
     * 返回学生信息修改页面
     * @return
     */
    @RequestMapping("/modify/{uid}") //{uid}占位符
    public String modify(@PathVariable Integer uid, Model model){
        User stu = iss.queryStudentById(uid);
        model.addAttribute("stu", stu);  // no BindingResult

        List<Clazz> clazzList = ics.getAllClazz();
        model.addAttribute("clazzList", clazzList);

        return "modify";
    }

    /**
     * Spring MVC的数据绑定机制：
     * 可以把请求的查询参数自动绑定到action方法的实体对象参数上
     * @param stu
     * @return
     */
    @RequestMapping(value = "/modify",method = RequestMethod.POST)
    public String modify(User stu){ //
        iss.saveStudent(stu);
        return "redirect:/main";
    }

    @RequestMapping("/add")
    public String add(Model model){
        List<Clazz> clazzList = ics.getAllClazz();
        model.addAttribute("stu", new User());
        model.addAttribute("clazzList", clazzList);
        return "add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(User student){  // clazz.id
        // 增加学生
        iss.addStudent(student);
        return "redirect:/main";
    }
    /**
     * 处理用户上传头像的请求
     * @param uid
     * @param file
     * @param request
     * @return
     */
    @RequestMapping(value = "/upload")
    public String upload(Integer uid,
                         MultipartFile file,
                         HttpServletRequest request){
        // 把文件放在assets的img里面
        User stu = iss.queryStudentById(uid);

        // 这里存储文件，必须用绝对路径
        String path = request.getServletContext().getRealPath("/assets/img");

        try {
            // 把文件写入相应的磁盘目录里面
            // MultipartFile如何把文件写入目录中
//            File imageFile = new File(path, file.getOriginalFilename());
            //1图片存储的路径
            String pic_path="";
            //2原名称
            String originalFilename = file.getOriginalFilename();
            //3新名称(uuid随机数加上后缀名)
            String newfileName= UUID.randomUUID()+originalFilename.substring(originalFilename.lastIndexOf("."));
            //新的图片
            File newfile=new File(pic_path+newfileName);
            file.transferTo(newfile);

            // 把文件名字写入数据库
            stu.setImg(file.getOriginalFilename());
            System.out.println(stu.getImg());
            iss.saveStudent(stu);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/main";
    }


    @RequestMapping(value = "/uploadfile",method = RequestMethod.POST)
    public String uploadFile(@RequestParam MultipartFile[] files, HttpServletRequest request){
        // 这里存储文件，在assets/file路径下面
        String path = request.getServletContext().getRealPath("/assets/file");

        try {

            for (MultipartFile file : files) {
                if(!file.getOriginalFilename().isEmpty()){
                    file.transferTo(new File(path, file.getOriginalFilename()));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/main";
    }

    /**
     * 文件下载的功能
     * @param fileName
     * @param session
     * @param response
     * @return
     */
    @RequestMapping("/download")
    public String downLoadFile(String fileName, HttpSession session, HttpServletResponse response){
        String dataDirectory = session.getServletContext().getRealPath("/assets/file/");
        File file = new File(dataDirectory + fileName);
        System.out.println("filepath:" + fileName);
        System.out.println("realpath:" + file.getAbsolutePath());

        if(file.exists()){
            // application/text/html text/json
            response.setContentType("application/octet-stream");
            try {
                response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "utf-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            response.setHeader("Content-Length", String.valueOf(file.length()));

            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try{
                bis = new BufferedInputStream(new FileInputStream(file));
                byte[] bytes = new byte[bis.available()];
                bis.read(bytes);
                response.getOutputStream().write(bytes);
            }catch(IOException e){

            }finally {
                try{
                    if(bis != null){
                        bis.close();
                    }
                }catch(IOException e){

                }
            }
        }

        return "redirect:/main";
    }

}
