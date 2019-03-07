package com.hyh.dao;

import com.hyh.model.Clazz;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface IClazzDao {
    @Select("select * from clazz ")
    List<Clazz> selectAllClazz();

    @Update("update clazz set number=number+1 where id=#{cid}")
    int updateClazzNumberInc(Integer cid);
    @Update("update clazz set number=number-1 where id=#{cid}")
    int updateClazzNumberDesc(Integer cid);

}
