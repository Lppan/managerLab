package com.laboratory.system.dataDictionary.service;

import com.laboratory.labport.model.ResponseModel;
import com.laboratory.system.dataDictionary.dao.DataInfoMapper;
import com.laboratory.system.dataDictionary.model.DataInfo;
import com.laboratory.utils.DateConversionUtils;
import com.laboratory.utils.LabConstant;
import com.laboratory.utils.MapUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by shipan on 2018/1/1.
 */
@Service
@Transactional
public class DataInfoService {

    private Logger logger = Logger.getLogger(DataInfoService.class);

    @Autowired
    private DataInfoMapper dataInfoMapper;

    public ResponseModel deleteByPrimaryKey(Integer id){
        ResponseModel responseModel = new ResponseModel();
        if(null != id && !"".equals(id)){
            int primaryKey = dataInfoMapper.deleteByPrimaryKey(id);
            if(primaryKey > 0){
                responseModel.setStatus(LabConstant.operateModel.OPERATE_SUCCESS_STATUS);
                responseModel.setMessage(LabConstant.operateModel.OPERATE_SUCCESS_MESSAGE);
            }else{
                responseModel.setStatus(LabConstant.operateModel.OPERATE_FAILED_STATUS);
                responseModel.setMessage(LabConstant.operateModel.OPERATE_FAILED_MESSAGE);
            }
        }else{
            responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
            responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MUST_MESSAGE+"id不能为空");
        }
        return responseModel;
    }

    public ResponseModel insertSelective(Map<String,Object> paramMap){
        ResponseModel responseModel = checkProjectMust(paramMap);
        if (null != responseModel.getStatus()){
            return responseModel;
        }else{
            int selective = dataInfoMapper.insertSelective(paramMap);
            if(selective > 0){
                responseModel.setStatus(LabConstant.operateModel.OPERATE_SUCCESS_STATUS);
                responseModel.setMessage(LabConstant.operateModel.OPERATE_SUCCESS_MESSAGE);
            }else{
                responseModel.setStatus(LabConstant.operateModel.OPERATE_FAILED_STATUS);
                responseModel.setMessage(LabConstant.operateModel.OPERATE_FAILED_MESSAGE);
            }
        }
        return responseModel;
    }

    public ResponseModel selectByPrimaryKey(Integer id){
        ResponseModel responseModel = new ResponseModel();
        if(null != id && !"".equals(id)){
            DataInfo dataInfo = dataInfoMapper.selectByPrimaryKey(id);
            Map projectMap = MapUtils.getValue(dataInfo);
            if(null != dataInfo){
                responseModel.setStatus(LabConstant.operateModel.OPERATE_SUCCESS_STATUS);
                responseModel.setMessage(LabConstant.operateModel.OPERATE_SUCCESS_MESSAGE);
                responseModel.setData(projectMap);
            }else{
                responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
                responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MESSAGE);
            }
        }else{
            responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
            responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MUST_MESSAGE+"id 不能为空");
        }
        return responseModel;
    }

    public ResponseModel updateByPrimaryKeySelective(Map<String,Object> dataMap){
        ResponseModel responseModel = checkProjectMust(dataMap);
        if (null != responseModel.getStatus()){
            return responseModel;
        }else{
            int keySelective = dataInfoMapper.updateByPrimaryKeySelective(dataMap);
            if(keySelective >0){
                responseModel.setStatus(LabConstant.operateModel.OPERATE_SUCCESS_STATUS);
                responseModel.setMessage(LabConstant.operateModel.OPERATE_SUCCESS_MESSAGE);
            }else{
                responseModel.setStatus(LabConstant.operateModel.OPERATE_FAILED_STATUS);
                responseModel.setMessage(LabConstant.operateModel.OPERATE_FAILED_MESSAGE);
            }
        }
        return responseModel;
    }

    //查询项目实验内容 一级、二级、三级菜单
    public ResponseModel selectDataDictionary(Map<String,Object> paramMap){
        ResponseModel responseModel = new ResponseModel();
        if (null != paramMap && !paramMap.isEmpty()){
            if (paramMap.containsKey("parentId") && null != paramMap.get("parentId") || !"".equals(paramMap.get("parentId"))){
                if (paramMap.containsKey("type") && null != paramMap.get("type") || !"".equals(paramMap.get("type"))){
                    List<DataInfo> dataInfos = dataInfoMapper.selectDataDictionary(paramMap);
                    if (null != dataInfos && dataInfos.size() > 0){
                        responseModel.setStatus(LabConstant.operateModel.OPERATE_SUCCESS_STATUS);
                        responseModel.setMessage(LabConstant.operateModel.OPERATE_SUCCESS_MESSAGE);
                        responseModel.setData(dataInfos);
                    }else{
                        responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
                        responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MESSAGE);
                    }
                }else{
                    responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
                    responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MUST_MESSAGE+":类型不能为空");
                    return responseModel;
                }
            }else{
                responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
                responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MUST_MESSAGE+":父id不能为空");
                return responseModel;
            }
        }else{
            responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
            responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MUST_MESSAGE);
        }
        return responseModel;
    }

    public ResponseModel checkProjectMust(Map<String,Object> paramMap){
        ResponseModel responseModel = new ResponseModel();
        if (null != paramMap && !paramMap.isEmpty()){
            if (paramMap.containsKey("experimentName") && null == paramMap.get("experimentName") || "".equals(paramMap.get("experimentName"))){
                responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
                responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MUST_MESSAGE+":内容不能为空");
                return responseModel;
            }else if (paramMap.containsKey("parentId") && null == paramMap.get("parentId") || "".equals(paramMap.get("parentId"))){
                responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
                responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MUST_MESSAGE+":父id不能为空");
                return responseModel;
            }else if (paramMap.containsKey("type") && null == paramMap.get("type") || "".equals(paramMap.get("type"))){
                responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
                responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MUST_MESSAGE+":类型不能为空");
                return responseModel;
            }
        }else{
            responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
            responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MUST_MESSAGE);
        }
        return responseModel;
    }
}
