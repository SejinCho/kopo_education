package kr.ac.kopo.library;


import java.io.Reader;
import java.io.Writer;

public class FileClose {

	public static void close(Reader r) {
		if(r != null) {
			try {
				r.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void close(Writer w) {
		if(w != null) {
			try {
				w.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void close(Reader br, Reader fr) {
		FileClose.close(br);
		FileClose.close(fr);
	}
	
	public static void close(Writer bw, Writer fw) {
		FileClose.close(bw);
		FileClose.close(fw);
	}
	
}
