package com.laboratory.stoppage.service;

import com.laboratory.labport.model.ResponseModel;
import com.laboratory.stoppage.dao.StopPageMapper;
import com.laboratory.stoppage.model.StopPage;
import com.laboratory.utils.LabConstant;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * Created by shipan on 2017/12/28.
 */
@Service
@Transactional
public class StopPageService {
    private Logger logger = Logger.getLogger(StopPageService.class);

    @Autowired
    private StopPageMapper stopPageMapper;

    public ResponseModel insert(Map<String,Object> stopMapper){
        ResponseModel responseModel = new ResponseModel();
        if(null != stopMapper && !stopMapper.isEmpty()){
            int selective = stopPageMapper.insertSelective(stopMapper);
            if(selective > 0){
                responseModel.setStatus(LabConstant.operateModel.OPERATE_SUCCESS_STATUS);
                responseModel.setMessage(LabConstant.operateModel.OPERATE_SUCCESS_MESSAGE);
            }else{
                responseModel.setStatus(LabConstant.operateModel.OPERATE_FAILED_STATUS);
                responseModel.setMessage(LabConstant.operateModel.OPERATE_FAILED_MESSAGE);
            }
        }else{
            responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
            responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MESSAGE);
        }

        return responseModel;
    }

    public ResponseModel deleteByPrimaryKey(Integer id){
        ResponseModel responseModel = new ResponseModel();
        int key = stopPageMapper.deleteByPrimaryKey(id);
        return responseModel;
    }


    public StopPage selectByPrimaryKey(Integer id){
        return stopPageMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(StopPage stopPage){
        return stopPageMapper.updateByPrimaryKeySelective(stopPage);
    }

    public int updateByPrimaryKey(StopPage stopPage){
        return stopPageMapper.updateByPrimaryKey(stopPage);
    }
}
