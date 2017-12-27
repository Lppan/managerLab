package com.laboratory.utils;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by shipan on 2017/12/27.
 */
public class GetRequestParam {
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
