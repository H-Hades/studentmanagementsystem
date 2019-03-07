package com.hyh.serviceImpl;

import com.hyh.dao.IStudentDao;
import com.hyh.model.User;
import com.hyh.service.IClazzService;
import com.hyh.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class IStudentServiceImpl implements IStudentService {

    @Autowired
    private IStudentDao isd;
    @Autowired
    private IClazzService ics;
    @Override
    public User queryStudent(String name, String pwd) {
        return isd.selectStudentByNameAndPwd(name,pwd);
    }

    @Override
    public List<User> queryAllStudent() {
        return isd.selectAll();
    }

    @Override
    @Transactional
    public void removeStudent(Integer uid, Integer cid) {
        isd.deleteStudent(uid);
        ics.modifyClazzNumberDesc(cid);
    }

    @Override
    public User queryStudentById(Integer uid) {
        return isd.selectById(uid);
    }

    @Override
    @Transactional
    public void saveStudent(User stu) {
        User user = isd.selectById(stu.getId());
        System.out.println(user);
        System.out.println(stu);
        isd.updateStudent(stu);
        if(!user.getClazz().getId().equals(stu.getClazz().getId())){
            ics.modifyClazzNumberDesc(user.getClazz().getId());
            ics.modifyClazzNumberInc(stu.getClazz().getId());
        }
    }

    @Override
    public void addStudent(User stu) {
        isd.insertStudent(stu);
        ics.modifyClazzNumberInc(stu.getClazz().getId());
    }
}
