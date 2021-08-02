

function doWrite() {
	
	let f = document.writeForm

	//파일 확장자 체크
	if(checkExt(f.attachfile1, 0)){
		return false
	}
	if(checkExt(f.attachfile2, 1)){
		return false
	}
	if(checkExt(f.attachfile3, 2)){
		return false
	}
	
	return true
}

function checkExt(obj, idx) {
	let forbidName = ['exe','java','jsp','js','class']
	let fileName = obj.value
	
	let ext = fileName.substring(fileName.lastIndexOf('.')+1)
	
	let error = document.querySelectorAll('.error_next_box');
	
	for(let i=0; i<forbidName.length ; i++ ) {
		if(forbidName[i] == ext) {
			error[idx].innerHTML = "확장자는 파일 업로드 정책에 위배됩니다.";
			error[idx].style.display = "block";
			return true
		}
	}
	error[idx].style.display = "none";
	return false
}
