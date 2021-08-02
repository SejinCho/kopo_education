package kr.ac.kopo.controller;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class HandlerMapping {
	private Map<String, Controller> mappings ;
	
	public HandlerMapping() {
		this("D:\\workspace\\web-workspace\\Sejin-Bank\\WebContent\\bean.properties");
	}
	
	public HandlerMapping(String propLoc) {
		mappings = new HashMap<String, Controller>();
		Properties prop = new Properties();
		try {
			
			InputStream is = new FileInputStream(propLoc);
			prop.load(is);
			
			Set<Object> keys = prop.keySet();
			for(Object key : keys) {
				String className = prop.getProperty(key.toString());
				Class<?> clz = Class.forName(className);
				mappings.put(key.toString(), (Controller) clz.newInstance());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Controller getController(String uri) {
		return mappings.get(uri);
	}
	
}
