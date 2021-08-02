package kr.ac.kopo.util;

import java.io.File;
import java.util.UUID;

import com.oreilly.servlet.multipart.FileRenamePolicy;

public class SejinBankFileNamePolicy implements FileRenamePolicy {

	@Override
	public File rename(File f) {
		String name = f.getName();
		String ext = "";
		int dot = name.lastIndexOf(".");
		if (dot != -1) {
			ext = name.substring(dot); 
		} else {
			ext = "";
		}
		String str = "kopo" + UUID.randomUUID(); //32비트의 임의의 값을 출력 (파일명을 다르게 해주기 위함)
		File renameFile = new File(f.getParent(), str + ext);
		return renameFile;
	}
}
