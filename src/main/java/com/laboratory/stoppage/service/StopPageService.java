package com.laboratory.stoppage.service;

import com.laboratory.labport.model.ResponseModel;
import com.laboratory.stoppage.dao.StopPageMapper;
import com.laboratory.stoppage.model.StopPage;
import com.laboratory.utils.LabConstant;
import com.laboratory.utils.PageUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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

    public ResponseModel insert(StopPage stopPage){
        ResponseModel responseModel = new ResponseModel();
        if(null != stopPage){
            int selective = stopPageMapper.insertSelective(stopPage);
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

    public ResponseModel showDebugList(Map<String, Object> paramMap) {
        ResponseModel responseModel = new ResponseModel();
        if (null != paramMap){
            paramMap.put("count",stopPageMapper.getDebugCount(paramMap));
            List<StopPage> debugList = stopPageMapper.showList(paramMap);
            logger.info("故障查询结果："+debugList.toString());
            PageUtils.calculate(paramMap);
            if (null != debugList && debugList.size() > 0){
                responseModel.setStatus(LabConstant.operateModel.OPERATE_SUCCESS_STATUS);
                responseModel.setMessage(LabConstant.operateModel.OPERATE_SUCCESS_MESSAGE);
                responseModel.setData(JSONArray.fromObject(debugList));
                responseModel.setPageMap(paramMap);
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
}
