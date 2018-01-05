package com.laboratory.utils.operateExcel;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.apache.poi.xssf.usermodel.XSSFCell;
//import org.apache.poi.xssf.usermodel.XSSFRow;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * read excel
 * Created by Lpan on 2017/12/11.
 */
public class InputExcel {

    public static void main(String args[]){
        readExcel("");
        //readexcel07plus();
    }

    /**
     * Workbook、Sheet、Row、Cell等为接口；
     * HSSFWorkbook、HSSFSheet、HSSFRow、HSSFCell为97-2003版本对应的处理实现类；
     */
    public static void readExcel(String path){
        try {
            //
            FileInputStream fileInputStream = new FileInputStream(path);
            HSSFWorkbook workbool = new HSSFWorkbook(fileInputStream);
            //HSSFSheet sheet = workbool.getSheet("B2B支持银行");
            HSSFSheet sheet = workbool.getSheetAt(0);
            String sheetName = sheet.getSheetName();        //sheet名称
            int rowNum = sheet.getLastRowNum();//获取sheet表中行数
            int numberOfCells = sheet.getRow(7).getPhysicalNumberOfCells();     //获取总列数
            Map<String,Object> baseMap = new HashMap<String,Object>();
            HSSFRow row1 = sheet.getRow(1);         //获取第二行
            baseMap.put(row1.getCell(0).toString(),row1.getCell(1));

            HSSFRow row2 = sheet.getRow(2);         //获取第三行
            baseMap.put(row2.getCell(0).toString(),row2.getCell(1));
            baseMap.put(row2.getCell(2).toString(),"");
            baseMap.put(row2.getCell(3).toString(),"");
            baseMap.put(row2.getCell(4).toString(),row2.getCell(5));
            baseMap.put(row2.getCell(6).toString(),row2.getCell(7));
            baseMap.put(row2.getCell(8).toString(),row2.getCell(9));

            HSSFRow row3 = sheet.getRow(3);
            baseMap.put(row3.getCell(0).toString(),row3.getCell(1));
            baseMap.put(row3.getCell(2).toString(),row3.getCell(3));
            baseMap.put(row3.getCell(4).toString(),row3.getCell(5));
            baseMap.put(row3.getCell(6).toString(),row3.getCell(7));
            baseMap.put(row3.getCell(8).toString(),row3.getCell(9));

            HSSFRow row4 = sheet.getRow(4);
            baseMap.put(row4.getCell(0).toString(),row4.getCell(1));
            baseMap.put(row4.getCell(2).toString(),row4.getCell(3));
            baseMap.put(row4.getCell(4).toString(),row4.getCell(5));
            baseMap.put(row4.getCell(6).toString(),row4.getCell(7));
            baseMap.put(row4.getCell(8).toString(),row4.getCell(9));

            
            for(int x=8;x<rowNum;x++){
                //获取字段名的行
                Map<String,Object> rowMap = new HashMap<String, Object>();
                HSSFRow row6= sheet.getRow(6);
                for (int y = 0 ; y < row6.getPhysicalNumberOfCells(); y++){        //循环列
                    HSSFRow xrow = sheet.getRow(x);
                    rowMap.put(row6.getCell(y).toString(),xrow.getCell(y));
                    //保存一条数据(map)

                }


                //获取每一行
                HSSFRow row = sheet.getRow(x);
                HSSFCell rowCell1 = row.getCell( 0);
                String serializable = rowCell1 == null ? "空" : rowCell1.getDateCellValue().toString();
                SimpleDateFormat sdf1= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
                try {
                    Date date=sdf1.parse(serializable);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Workbook、Sheet、Row、Cell等为接口；
     * HSSFWorkbook、HSSFSheet、HSSFRow、HSSFCell为97-2003版本对应的处理实现类；
     * XSSFWorkbook、XSSFSheet、XSSFRow、XSSFCell为2007+版本对应的处理实现类；
     */
    public static void readexcel07plus(){
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream("D:\\aa\\20170207.xlsx");
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(fileInputStream);
            XSSFSheet sheet = xssfWorkbook.getSheet("B2B支持银行");
            int rowNum = sheet.getLastRowNum();//获取sheet表中行数
            for(int x=0;x<rowNum;x++){
                //获取每一行
                XSSFRow row = sheet.getRow(x);
                XSSFCell rowCell0 = row.getCell((short) 0);
                XSSFCell rowCell1 = row.getCell((short) 1);
                XSSFCell rowCell2 = row.getCell((short) 2);
                XSSFCell rowCell3 = row.getCell((short) 3);
                XSSFCell rowCell4= row.getCell((short) 4);
                String rowcell0 = rowCell1==null?"空":rowCell0.getStringCellValue();
                String rowcell1 = rowCell1==null?"空":rowCell1.getStringCellValue();
                String rowcell2 = rowCell1==null?"空":rowCell2.getStringCellValue();
                String rowcell3= rowCell1==null?"空":rowCell3.getStringCellValue();
                String rowcell4 = rowCell1==null?"空":rowCell4.getStringCellValue();
                System.out.println(rowcell0);
                System.out.println(rowCell1 + "\t" + rowCell2 + "\t" + rowCell3+"\t"+rowCell4);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
