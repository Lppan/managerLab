package com.laboratory.utils;

import org.apache.log4j.Logger;

import java.io.File;
import java.util.Map;
import java.util.Properties;

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
     * @param fileMap    检查(创建)目录的必要字段
     *                   fileType：文件类型 ，具体的实验内容
     *                   projectName：项目名称
     * @return
     */
    public static String checkDirectory(Map<String,String> fileMap){
        Properties props = System.getProperties();
        String operateName = props.getProperty("os.name").substring(0,3);
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        String ymd = sdf.format(new Date());
//        String year = ymd.substring(0,4);
//        String month = ymd.substring(5,7);
//        String today = ymd.substring(ymd.length()-2,ymd.length());
        StringBuffer bfpath = new StringBuffer();
        if (null != fileMap && !fileMap.isEmpty()){
            String fileType = null;
            if (fileMap.containsKey("fileType")){
                fileType = fileMap.get("fileType");
            }
            String projectName = null;
            if (fileMap.containsKey("projectName")){
                projectName = fileMap.get("projectName");
            }
            String path = null;
            String [] splitpath = null;
            if (operateName.toLowerCase().startsWith("win")){
                if (!fileType.equals("") && null != fileType){
                    path = LabConstant.filePath.FILE_DIRECTORY_PATH_WIN +"\\\\"+projectName+"\\\\"+fileType;
                } else if (fileType.equals("") || null == fileType){
                    path = LabConstant.filePath.FILE_DIRECTORY_PATH_WIN +"\\"+projectName;
                }
                splitpath = path.split("\\\\");
            }else{
                if (null != fileType && !fileType.equals("")){
                    path = LabConstant.filePath.FILE_DIRECTORY_PATH_WIN +"/"+projectName+"/"+fileType;
                }else if (null == fileType && fileType.equals("")){
                    path = LabConstant.filePath.FILE_DIRECTORY_PATH_WIN +"/"+projectName;
                }
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
        }
        return bfpath.toString();
    }
}
