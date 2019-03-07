package com.hyh.service;

import com.hyh.model.Clazz;

import java.util.List;

public interface IClazzService {
    List<Clazz> getAllClazz();
    void modifyClazzNumberDesc(Integer cid);
    void modifyClazzNumberInc(Integer cid);
}
