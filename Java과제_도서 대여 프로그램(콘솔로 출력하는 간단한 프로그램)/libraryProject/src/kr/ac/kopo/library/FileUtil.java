package kr.ac.kopo.library;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import kr.ac.kopo.library.member.MemberDTO;


public class FileUtil {
	private String fileName = "";
	private String filePath = "C:\\자바복습\\libraryProject\\src\\kr\\ac\\kopo\\library\\";
	File file = null;

	public FileUtil() {
		super();
	}

	public FileUtil(String fileName) {
		super();
		this.fileName = fileName;
		filePath += this.fileName;
		this.file = new File(filePath);
	}

	
	//파일 존재 여부 
	public boolean fileExists() {
		boolean result = false;
		boolean fileExists = file.exists();
		if(! fileExists) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		result = true;
		return result;
	}
		
	
	//파일 쓰기
	public boolean fileWriter(Object obj) {
		boolean result = false;
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
			oos.writeObject(obj);
			oos.flush();
			result = true;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	
	//파일 읽기
	public Object fileReader() {
		List<Object> itemList = new ArrayList<Object>();
		ObjectInputStream ois = null;
		boolean flag = true;
		try {
	
			ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)));
			itemList = (List<Object>) ois.readObject();

			return itemList ;
		} catch (Exception e) {
			//e.printStackTrace();
		} finally {
			try {
				if(ois != null)
				ois.close();
			} catch (IOException e) {
				//e.printStackTrace();
			}
		}
		return null;
		
	}
	
	
	
}
