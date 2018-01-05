package com.laboratory.labport.service;

import com.laboratory.utils.operateExcel.InputExcel;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * importExcel service
 * Created by Lpan on 2018/1/5.
 */
@Service
@Transactional
public class ImportExcelFileService {
        private Logger logger = Logger.getLogger(ImportExcelFileService.class);

    /**
     *  根据文件路径读取excel文件
     * @param path
     */
    public void readExcelFileOne(String path){
        if (null != path && !path.equals("")){
            //读取exvel文件 并将数据存储到数据库
            InputExcel.readExcel(path);
        }
    }




}
