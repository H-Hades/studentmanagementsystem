package com.hyh.serviceImpl;

import com.hyh.dao.IClazzDao;
import com.hyh.model.Clazz;
import com.hyh.service.IClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IClazzServiceImpl  implements IClazzService {
    @Autowired
    private IClazzDao icd;

    @Override
    public List<Clazz> getAllClazz() {

        return icd.selectAllClazz();
    }

    @Override
    public void modifyClazzNumberDesc(Integer cid) {
        icd.updateClazzNumberDesc(cid);
    }

    @Override
    public void modifyClazzNumberInc(Integer cid) {
        icd.updateClazzNumberInc(cid);
    }
}
