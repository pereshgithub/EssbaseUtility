package com.indrasol.essbase.utils;

import com.essbase.api.metadata.IEssDimension;
import com.essbase.api.metadata.IEssMember;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import com.essbase.api.base.EssException;
import com.essbase.api.base.IEssBaseObject;
import com.essbase.api.metadata.IEssCubeOutline;

@Component
public class EssbaseUtil {

	public static List<IEssDimension> getDimensionsAsList(IEssCubeOutline cubeOutLine) throws EssException {
		List<IEssDimension> dimenionsList = new ArrayList<IEssDimension>();
		IEssBaseObject[] dimensions = cubeOutLine.getDimensions().getAll();
		for (int i = 0; i < dimensions.length; i++) {
			IEssDimension dimension = (IEssDimension) dimensions[i];
			dimenionsList.add(dimension);
		//	System.out.println("Dimensions:" + dimension.getName());
		}
		return dimenionsList;
	}

	public static List<IEssMember> getMembers(IEssDimension dimension) throws EssException {
		List<IEssMember> membersList = new ArrayList<IEssMember>();
		IEssBaseObject[] members = dimension.getDimensionRootMember().getChildMembers().getAll();
		for (int i = 0; i < members.length; i++) {
			IEssMember member = (IEssMember) members[i];
			membersList.add(member);
		//	System.out.println("member:" + member.getName());
		}
		return membersList;
	}

	public static List<IEssMember> getSubMembers(IEssMember member) throws EssException {
		List<IEssMember> membersList = new ArrayList<IEssMember>();
		IEssBaseObject[] members = member.getChildMembers().getAll();
		for (int i = 0; i < members.length; i++) {
			IEssMember subMember = (IEssMember) members[i];
			membersList.add(subMember);
		//	System.out.println("Submember:" + subMember.getName());
		}
		return membersList;
	}

	

	public static void createExcel(List<IEssDimension> dimensionList) throws EssException, InvalidFormatException {
		
		Workbook workbook = new XSSFWorkbook();
		CreationHelper createHelper = workbook.getCreationHelper();
		Sheet sheet = workbook.createSheet("Employee");
		DataFormatter formatter = new DataFormatter();
        		
		FileOutputStream fileOut = null;
		int dimrowNum = 1;
		int dimcellNum = 1;
		
		int memcellNum = 1;
		int submemrowNum = 1;
		int submemcellNum = 1;
		IEssDimension dim = null;
		IEssMember member = null;
		IEssMember subMember = null;
		try { 
			fileOut = new FileOutputStream("./ESSBASEDetails.xls");
			//workbook.write(fileOut);
			Row row = null;
			for(int i=0; i< dimensionList.size(); i++) {
				  dim = (IEssDimension)dimensionList.get(i);
				 row = sheet.createRow(dimrowNum++);
				 row.createCell(0).setCellValue(dim.toString());
				 System.out.println("Dimensions List: "+dim.toString());
				// int memrowNum = dimrowNum++;
				// memcellNum = dimcellNum++;
			
				 List<IEssMember> memberList = EssbaseUtil.getMembers(dim);
					
					for(int j=0; j<memberList.size(); j++) {
						
						 member = (IEssMember)memberList.get(j);
						 row = sheet.createRow(dimrowNum++);
						row.createCell(1).setCellValue(member.toString());
						
						System.out.println("Members List: "+member.toString());
						
					
						List<IEssMember> subMembersList = EssbaseUtil.getSubMembers(member); 
						
						for(int k=0; k < subMembersList.size(); k++) { 
							
							subMember = (IEssMember) subMembersList.get(k);
							row = sheet.createRow(dimrowNum++);
							row.createCell(2).setCellValue(subMember.toString());
							System.out.println("subMember List: "+subMember.toString());
						}

					}
			
				} 
			workbook.write(fileOut);
			} catch (FileNotFoundException e) { e.printStackTrace(); }
			  catch (IOException e) { e.printStackTrace(); }
			finally {
						try { fileOut.close();fileOut.flush(); workbook.close(); }
						catch (IOException e) {  e.printStackTrace(); }

					} 
		}
	 

}
