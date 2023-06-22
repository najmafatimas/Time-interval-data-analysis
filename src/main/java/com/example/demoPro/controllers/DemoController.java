package com.example.demoPro.controllers;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.annotation.PreDestroy;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demoPro.dto.XlsRepo;
import com.example.demoPro.entities.XlsDemo;

@RestController
public class DemoController {

	@Autowired
	private XlsRepo xlsRepo;
	
	private static String filePath="C:/Users/Voc/OneDrive/Documents/Menu.xlsx";
	private static File filePath1=new File(filePath);
	
	public void loadMenuDocument(File filepath2){
        try {
            Workbook workbook = WorkbookFactory.create(filepath2);
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.rowIterator();
            rowIterator.next();
            while(rowIterator.hasNext()){
                Row row = rowIterator.next();
                XlsDemo model = new XlsDemo();
                model.setItemName(row.getCell(0).toString());
                model.setPrice(row.getCell(1).toString());
               
                xlsRepo.save(model);
            }
            System.out.println("loaded successfull");
            workbook.close();

        } catch (Exception e){
           System.out.println(e.getMessage());
        }
    }
	@PreDestroy
	public void preDestroy()
	{
		System.out.println("hiiiiiii destroy");
	}
	@PostMapping("/post-items")
	public String addItems(@RequestParam MultipartFile d)
	{
		String filepath2="C:/Users/Voc/OneDrive/Documents/"+"1"+d.getOriginalFilename();
		if(d!=null)
		{
		try {
			d.transferTo(new File(filepath2));
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			loadMenuDocument(new File(filepath2));
			return "Added successfull";
		}
		return "unsuccessfull";
	}
}
