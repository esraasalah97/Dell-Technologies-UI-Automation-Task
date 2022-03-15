package com.saucedemo.DellTechnologiesTask.reader;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.saucedemo.DellTechnologiesTask.Constant;

public class ExcelReader {
	
	  public  XSSFWorkbook getExcelFile(String fileName) throws IOException{
		    FileInputStream inputStream=new FileInputStream(new File(Constant.resourcesPath+fileName+".xlsx"));  
		    return new XSSFWorkbook(inputStream);		    
            }
	  public Sheet getSheet(String fileName,String sheetName) throws IOException {
		 XSSFWorkbook reader=getExcelFile(fileName);
		 return  reader.getSheet(sheetName); 
	  }
}
