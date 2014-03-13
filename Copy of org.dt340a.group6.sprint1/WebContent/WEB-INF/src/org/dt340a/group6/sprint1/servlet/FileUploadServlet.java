package org.dt340a.group6.sprint1.servlet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.dt340a.group6.sprint1.fileImport.FileReader;
import org.dt340a.group6.sprint1.persistence.PersistenceUtil;
import org.dt340a.group6.sprint1.validation.validateExcelFile;

@WebServlet ("/upload")
@MultipartConfig
public class FileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		uploadFile(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		uploadFile(request, response);
	}

	/**
	 * uploadFile method used to upload file to server.
	 *
	 ***/
	private void uploadFile(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
//		String fileName = "/home/ubuntu/temp/";
	     String fileName = "";
		for(Part part : request.getParts()) {
			String name = part.getName();
			InputStream is = request.getPart(name).getInputStream();
			fileName = fileName+getUploadedFileName(part);
			FileOutputStream fos = new FileOutputStream(fileName);
			int data = 0;
			while((data = is.read()) != -1) {
				fos.write(data);
			}
			fos.close();
			is.close();
			response.sendRedirect("success.html");
		}
/*
 * 
 */
		FileReader filereader = new FileReader(fileName);
		if(new validateExcelFile(filereader).isXLSValid()){
			PersistenceUtil.persistAll(fileName);
			System.out.println("Upload complete @ "
					+new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()));
		}
		else{
			System.out.println("The Excel file was not valid");
		}
	}
	
	/**
	 * Method used to get uploaded file name.
	 * This will parse following string and get filename 
	 * Content-Disposition: form-data; name="content"; filename="a.txt"
	 **/
	private String getUploadedFileName(Part p) {
		String file = "", header = "Content-Disposition";
		String[] strArray = p.getHeader(header).split(";");
		for(String split : strArray) {
			if(split.trim().startsWith("filename")) {
				file = split.substring(split.indexOf('=') + 1);
				file = file.trim().replace("\"", "");
				System.out.println("File name : "+file);
			}
		}
		return file;
	}
	
}
