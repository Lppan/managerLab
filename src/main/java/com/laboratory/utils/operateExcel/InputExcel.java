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
import java.util.Properties;

/**
 * read excel
 * Created by Lpan on 2017/12/11.
 */
public class InputExcel {

    public static void main(String args[]){
        //readExcel();
        readexcel07plus();
    }

    /**
     * Workbook、Sheet、Row、Cell等为接口；
     * HSSFWorkbook、HSSFSheet、HSSFRow、HSSFCell为97-2003版本对应的处理实现类；
     */
    public static void readExcel(){
        try {
            //
            FileInputStream fileInputStream = new FileInputStream("D:\\aa\\20170207.xls");
            HSSFWorkbook workbool = new HSSFWorkbook(fileInputStream);
            //HSSFSheet sheet = workbool.getSheet("B2B支持银行");
            HSSFSheet sheet = workbool.getSheetAt(2);
            String sheetName = sheet.getSheetName();        //sheet名称
            int rowNum = sheet.getLastRowNum();//获取sheet表中行数
            int numberOfCells = sheet.getRow(0).getPhysicalNumberOfCells();     //获取总列数
            for(int x=0;x<rowNum;x++){
                //获取每一行
                HSSFRow row = sheet.getRow(x);
                HSSFCell rowCell1 = row.getCell((short) 0);
                HSSFCell rowCell2 = row.getCell((short) 1);
                HSSFCell rowCell3 = row.getCell((short) 2);
                HSSFCell rowCell4= row.getCell((short) 3);
                CellType cellType = rowCell2.getCellTypeEnum();
                if(cellType.equals(CellType.NUMERIC)){      //数字类型的
                    double numericCellValue = rowCell2.getNumericCellValue();
                    System.out.println(numericCellValue);
                }
                String rowcell1 = rowCell1==null?"空": rowCell1.getStringCellValue();
                String rowcell2 = rowCell1==null?"空":rowCell2.getStringCellValue();
                String rowcell3= rowCell1==null?"空":rowCell3.getStringCellValue();
               String rowcell4 = rowCell1==null?"空":rowCell4.getStringCellValue();
                System.out.println(rowCell1 + "\t" + rowCell2 + "\t" + rowCell3+"\t"+rowCell4);
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
