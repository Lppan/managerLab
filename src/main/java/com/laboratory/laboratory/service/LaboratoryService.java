package com.laboratory.laboratory.service;

import com.laboratory.laboratory.dao.LaboratoryMapper;
import com.laboratory.laboratory.model.Laboratory;
import com.laboratory.labport.model.ResponseModel;
import com.laboratory.utils.LabConstant;
import com.laboratory.utils.PageUtils;
import net.sf.json.JSONObject;
import org.apache.commons.collections.map.HashedMap;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public ResponseModel insertSelective(Laboratory laboratory){
        ResponseModel responseModel = this.checkLaboratoyMust(laboratory);
        if ("".equals(responseModel.getStatus())){
            int selective = laboratoryMapper.insertSelective(laboratory);
            if (selective > 0){
                responseModel.setStatus(LabConstant.operateModel.OPERATE_SUCCESS_STATUS);
                responseModel.setMessage(LabConstant.operateModel.OPERATE_SUCCESS_MESSAGE);
            }else{
                responseModel.setStatus(LabConstant.operateModel.OPERATE_FAILED_STATUS);
                responseModel.setMessage(LabConstant.operateModel.OPERATE_FAILED_MESSAGE);
            }
        }
        return responseModel;
    }


    public Laboratory selectByPrimaryKey(Integer id){
        return laboratoryMapper.selectByPrimaryKey(id);
    }

    public ResponseModel updateByPrimaryKeySelective(Laboratory laboratory){
        ResponseModel responseModel = this.checkLaboratoyMust(laboratory);
        if (!"".equals(responseModel.getStatus())){
            int selective = laboratoryMapper.updateByPrimaryKeySelective(laboratory);
            if (selective > 0){
                responseModel.setStatus(LabConstant.operateModel.OPERATE_SUCCESS_STATUS);
                responseModel.setMessage(LabConstant.operateModel.OPERATE_SUCCESS_MESSAGE);
            }else{
                responseModel.setStatus(LabConstant.operateModel.OPERATE_FAILED_STATUS);
                responseModel.setMessage(LabConstant.operateModel.OPERATE_FAILED_MESSAGE);
            }
        }
        return responseModel;
    }

    public int updateByPrimaryKey(Laboratory record){
        return laboratoryMapper.updateByPrimaryKey(record);
    }

    public ResponseModel selectLaboratoryAllByPage(Map<String, Object> paramMap, HttpServletRequest request) {
        ResponseModel responseModel = new ResponseModel();
        if (null != paramMap){
            int count = laboratoryMapper.selectLaboratoryCount(paramMap);
            paramMap.put("count",count);
            PageUtils.calculate(paramMap);
            List<Laboratory> laboratories = laboratoryMapper.selectLaboratoryAllByPage(PageUtils.Page(request, paramMap));
            logger.info("查询结果："+laboratories.toString());
            if (null != laboratories && laboratories.size() > 0){
                responseModel.setStatus(LabConstant.operateModel.OPERATE_SUCCESS_STATUS);
                responseModel.setMessage(LabConstant.operateModel.OPERATE_SUCCESS_MESSAGE);
                responseModel.setData(JSONObject.fromObject(laboratories));
            }else{
                responseModel.setStatus(LabConstant.operateModel.OPERATE_FAILED_STATUS);
                responseModel.setMessage(LabConstant.operateModel.OPERATE_FAILED_MESSAGE);
            }
        }else{
            responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
            responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MUST_MESSAGE);
        }
        return responseModel;
    }

    private ResponseModel checkLaboratoyMust(Laboratory laboratory) {
        ResponseModel responseModel = new ResponseModel();
        if (null == laboratory.getLabName() && "".equals(laboratory.getLabName())) {
            responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
            responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MUST_MESSAGE + "实验室名称不能为空");
        }
        if (null == laboratory.getStatus() && "".equals(laboratory.getStatus())) {
            responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
            responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MUST_MESSAGE + "实验室状态不能为空");
        }
        if (null == laboratory.getLabPerson() && "".equals(laboratory.getLabPerson())) {
            responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
            responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MUST_MESSAGE + "实验人不能为空");
        }
        if (null == laboratory.getLabFunction() && "".equals(laboratory.getLabFunction())) {
            responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
            responseModel.setMessage(LabConstant.operateModel.OPERATE_EMPTY_MUST_MESSAGE + "实验室功能不能为空");
        }
        return responseModel;
    }

    public ResponseModel updateStatusUp(Integer id) {
        ResponseModel responseModel = new ResponseModel();
        if (null != id && !"".equals(id)){
            Map<String,Object> paramMap = new HashMap<String,Object>();
            paramMap.put("id",id);
            paramMap.put("status","1");
            int update = laboratoryMapper.updateStatus(paramMap);
            if (update > 0){
                responseModel.setStatus(LabConstant.operateModel.OPERATE_SUCCESS_STATUS);
                responseModel.setMessage(LabConstant.operateModel.OPERATE_SUCCESS_MESSAGE);
            }else{
                responseModel.setStatus(LabConstant.operateModel.OPERATE_FAILED_STATUS);
                responseModel.setMessage(LabConstant.operateModel.OPERATE_FAILED_MESSAGE);
            }
        }else{
            responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
            responseModel.setMessage(LabConstant.operateModel.OPERATE_SUCCESS_MESSAGE);
        }
        return responseModel;
    }

    public ResponseModel updateStatusDown(Integer id) {
        ResponseModel responseModel = new ResponseModel();
        if (null != id && !"".equals(id)){
            Map<String,Object> paramMap = new HashMap<String,Object>();
            paramMap.put("id",id);
            paramMap.put("status","1");
            int update = laboratoryMapper.updateStatus(paramMap);
            if (update > 0){
                responseModel.setStatus(LabConstant.operateModel.OPERATE_SUCCESS_STATUS);
                responseModel.setMessage(LabConstant.operateModel.OPERATE_SUCCESS_MESSAGE);
            }else{
                responseModel.setStatus(LabConstant.operateModel.OPERATE_FAILED_STATUS);
                responseModel.setMessage(LabConstant.operateModel.OPERATE_FAILED_MESSAGE);
            }
        }else{
            responseModel.setStatus(LabConstant.operateModel.OPERATE_EMPTY_STATUS);
            responseModel.setMessage(LabConstant.operateModel.OPERATE_SUCCESS_MESSAGE);
        }
        return responseModel;
    }
}