package com.laboratory.utils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * map verify
 * Created by Lpan on 2017/12/22.
 */
public class MapUtils {

    public static boolean isEmptyS(Map<String,String> map){
        Boolean flag = false;
        if(null != map && !map.isEmpty() && map.size() > 0){
            flag = true;
        }
        return flag;
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
    public static Map getValue(Object thisObj)
    {
        Map map = new HashMap();
        Class c;
        try
        {
            c = Class.forName(thisObj.getClass().getName());
            Method[] m = c.getMethods();
            for (int i = 0; i < m.length; i++)
            {
                String method = m[i].getName();
                if (method.startsWith("get"))
                {
                    try{
                        Object value = m[i].invoke(thisObj);
                        if (value != null && !"".equals(value))
                        {
                            if(value instanceof Date){
                                value = ((Date) value).getTime();
                            }
                            String key=method.substring(3);
                            String keys =  key.substring(0,1).toLowerCase();
                            String ss=keys+key.substring(1,key.length());
                            map.put(ss, value);
                        }
                    }catch (Exception e) {
                        // TODO: handle exception
                        System.out.println("error:"+method);
                    }
                }
            }
        }
        catch (Exception e)
        {
            // TODO: handle exception
            e.printStackTrace();
        }
        return map;
    }


}

