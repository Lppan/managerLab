package com.laboratory.laboratory.service;

import com.laboratory.laboratory.dao.LaboratoryMapper;
import com.laboratory.laboratory.model.Laboratory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by shipan on 2018/1/7.
 */
@Service
@Transactional
public class LaboratoryService {

    private Logger logger = Logger.getLogger(LaboratoryService.class);

    @Autowired
    private LaboratoryMapper laboratoryMapper;

    public int deleteByPrimaryKey(Integer id){
       return laboratoryMapper.deleteByPrimaryKey(id);
    }

    public int insert(Laboratory record){
        return laboratoryMapper.insert(record);
    }

    public int insertSelective(Laboratory record){
        return laboratoryMapper.insertSelective(record);
    }

    public Laboratory selectByPrimaryKey(Integer id){
        return laboratoryMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(Laboratory record){
        return laboratoryMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(Laboratory record){
        return laboratoryMapper.updateByPrimaryKey(record);
    }





}