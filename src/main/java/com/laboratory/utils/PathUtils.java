package com.laboratory.utils;

import org.apache.log4j.Logger;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * path
 * Created by shipan on 2018/1/2.
 */
public class PathUtils {
    private static String webRootPath;
    private static String rootClassPath;

    private static Logger logger = Logger.getLogger(PathUtils.class);

    @SuppressWarnings("rawtypes")
    public static String getPath(Class clazz) {
        String path = clazz.getResource("").getPath();
        return new File(path).getAbsolutePath();
    }

    public static String getPath(Object object) {
        String path = object.getClass().getResource("").getPath();
        return new File(path).getAbsolutePath();
    }

    public static String getRootClassPath() {
        if (rootClassPath == null) {
            try {
                String path = PathUtils.class.getClassLoader().getResource("").toURI().getPath();
                rootClassPath = new File(path).getAbsolutePath();
            } catch (Exception e) {
                String path = PathUtils.class.getClassLoader().getResource("").getPath();
                rootClassPath = new File(path).getAbsolutePath();
                logger.info("获取路径异常：" + e.toString());
            }
        }
        return rootClassPath;
    }

    /**
     * 根据路径判断是否存在真实路径 如果不存在就创建一个
     * @param filetype
     * @return
     */
    public static String checkDirectory(String filetype,String operate){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String ymd = sdf.format(new Date());
        String year = ymd.substring(0,4);
        String month = ymd.substring(5,7);
        String today = ymd.substring(ymd.length()-2,ymd.length());
        StringBuffer bfpath = new StringBuffer();
        String path = null;
        String [] splitpath = null;

        if(null != operate && !"".equals(operate) && "WIN".equals(operate)){
            path = LabConstant.filePath.FILE_DIRECTORY_PATH_WIN +"\\"+year+"\\"+month+"\\"+today+"\\"+filetype;
            splitpath = path.split("\\\\");
        }else if (null != operate && !"".equals(operate) && "LINUX".equals(operate)){
            path = LabConstant.filePath.FILE_DIRECTORY_PATH_WIN +"/"+year+"/"+month+"/"+today+"/"+filetype;
            splitpath = path.split("/");
        }
        for(int i=0 ; i < splitpath.length ; i++){
            bfpath = bfpath.append(splitpath[i]).append("/");
            File file = new File(bfpath.toString());
            //if (splitpath.length-1!=i){             //path到文件时，不需要创建文件夹
            if (!file.exists()){
                file.mkdir();
                //file.createNewFile();         //创建文件
                logger.info("创建目录："+bfpath);
            }
            // }
        }
        return bfpath.toString();
    }
}
