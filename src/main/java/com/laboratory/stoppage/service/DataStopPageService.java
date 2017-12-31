package com.laboratory.stoppage.service;

import com.laboratory.labport.model.ResponseModel;
import com.laboratory.stoppage.dao.DataStopPageMapper;
import com.laboratory.stoppage.model.DataStopPage;
import com.laboratory.utils.LabConstant;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;


/**
 * Created by shipan on 2017/12/29.
 */
@Service
@Transactional
public class DataStopPageService {

    private Logger logger = Logger.getLogger(DataStopPageService.class);

    @Autowired
    private DataStopPageMapper dataStopPageMapper;

    public ResponseModel addDataStopPage(Map<String,Object> paramMap){
        ResponseModel responseModel = new ResponseModel();
        if(null != paramMap && !paramMap.isEmpty()){
            int selective = dataStopPageMapper.insertSelective(paramMap);
            responseModel= parseResponseModel(selective);
        }else{
            responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
            responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MUST_MESSAGE);
        }
        return responseModel;
    }

    public ResponseModel deleteDataaById(Map<String,Object> paramMap){
        ResponseModel responseModel = new ResponseModel();
        if(null != paramMap && !paramMap.isEmpty() && paramMap.containsKey("id")){
            Integer id = Integer.valueOf(paramMap.get("id").toString());
            int i = dataStopPageMapper.deleteByPrimaryKey(id);
            responseModel = parseResponseModel(i);
        }else{
            responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
            responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MESSAGE);
        }
        return responseModel;
    }

    public ResponseModel selectDataById(Map<String,Object> paramMap){
        ResponseModel responseModel = new ResponseModel();
        if(null != paramMap && !paramMap.isEmpty() && paramMap.containsKey("id")){
            Integer id = Integer.valueOf(paramMap.get("id").toString());
            DataStopPage dataStopPage = dataStopPageMapper.selectByPrimaryKey(id);
            responseModel = parseResponseModel(dataStopPage);
        }else{
            responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
            responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MESSAGE);
        }
        return responseModel;
    }

    public ResponseModel updateDataStopPage(Map<String,Object> paramMap){
        ResponseModel responseModel = new ResponseModel();
        if (null != paramMap && !paramMap.isEmpty()){
            int selective = dataStopPageMapper.updateByPrimaryKeySelective(paramMap);
            responseModel = parseResponseModel(selective);
        }else{
            responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
            responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MUST_MESSAGE);
        }
        return responseModel;
    }

    public ResponseModel getDebugType(){
        ResponseModel responseModel = new ResponseModel();
        Map<String, Object> debugType = dataStopPageMapper.getDebugType();
        responseModel = parseResponseModel(debugType);
        return responseModel;
    }

    public ResponseModel getDebugInfoByType(Map<String,Object> param){
        ResponseModel responseModel = new ResponseModel();
        if (null != param && !param.isEmpty() && param.containsKey("parentId")){
            Integer parentId = Integer.valueOf(param.get("parentId").toString());
            Map<String, Object> DebuginfoByType = dataStopPageMapper.getDebugInfoByType(parentId);
            if(null != DebuginfoByType && !DebuginfoByType.isEmpty()){
                responseModel = parseResponseModel(DebuginfoByType);
            }else{
                responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
                responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MESSAGE);
            }
        }else{
            responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
            responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MUST_MESSAGE);
        }
        return responseModel;
    }


    public ResponseModel parseResponseModel(Object param){
        ResponseModel responseModel = new ResponseModel();
            if (param instanceof Integer){
                int intValue = ((Integer) param).intValue();
                if(intValue > 0){
                    responseModel.setStatus(LabConstant.operateModel.OPERATE_SUCCESS_STATUS);
                    responseModel.setMessage(LabConstant.operateModel.OPERATE_SUCCESS_MESSAGE);
                }else{
                    responseModel.setStatus(LabConstant.operateModel.OPERATE_FAILED_STATUS);
                    responseModel.setMessage(LabConstant.operateModel.OPERATE_FAILED_MESSAGE);
                }
            }else if (param instanceof DataStopPage){
                DataStopPage dataStopPage = (DataStopPage)param;
                if (null != dataStopPage){
                    responseModel.setStatus(LabConstant.operateModel.OPERATE_SUCCESS_STATUS);
                    responseModel.setMessage(LabConstant.operateModel.OPERATE_SUCCESS_MESSAGE);
                    responseModel.setData(dataStopPage);
                }else{
                    responseModel.setStatus(LabConstant.operateModel.OPERATE_FAILED_STATUS);
                    responseModel.setMessage(LabConstant.operateModel.OPERATE_FAILED_MESSAGE);
                }
            }else if(param instanceof Map){
                responseModel.setStatus(LabConstant.operateModel.OPERATE_SUCCESS_STATUS);
                responseModel.setMessage(LabConstant.operateModel.OPERATE_SUCCESS_MESSAGE);
                responseModel.setData(param);
            }else{
                responseModel.setStatus(LabConstant.operateModel.OPERATE_FAILED_STATUS);
                responseModel.setMessage(LabConstant.operateModel.OPERATE_FAILED_MESSAGE);

            }
            return responseModel;
    }

}
