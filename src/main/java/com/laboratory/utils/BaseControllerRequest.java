package com.laboratory.utils;

import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by shipan on 2017/12/27.
 */
public class BaseControllerRequest {
    public static JSONObject getJSONParams(HttpServletRequest request){
        StringBuilder sb = new StringBuilder();
        BufferedReader in = null;
        String line = null;
        String json = null;
        JSONObject params = null;
        try {
            in = new BufferedReader(new InputStreamReader(request.getInputStream(),"utf-8"));
            while((line = in.readLine())!=null){
                sb.append(line);
            }
            json = sb.toString();
           if (null != json &&  !"".equals(json)){
               params = JSONObject.fromObject(json);
           }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(in != null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return params;
    }

    public void setJSONResult(HttpServletResponse response, JSONObject json) throws Exception{
        String result = json.toString();
        //result = AESUtils.encrypt(AESUtils.key, AESUtils.iv, result);
        response.getWriter().write(result);
    }

    public static Map<String, String> getMapParams(HttpServletRequest request){
        Map<String, String> param = new HashMap<String, String>();
        Enumeration enumeration = request.getParameterNames();
        while(enumeration.hasMoreElements()){
            String paramName = (String) enumeration.nextElement();
            String[] values = request.getParameterValues(paramName);
            param.put(paramName, values[0]);
        }
        return param;
    }
}
