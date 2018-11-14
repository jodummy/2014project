package ya.rain.bow.beans;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

// 파일 업로드, 다운로드 처리
public class FileUpDown {
	
	private MultipartHttpServletRequest multipartRequest;
	private String filePath;
	private Map<String, List<String>> fileNames;
	private String oldNmae;
	private HttpServletResponse resp;
	private byte[] fileByte;

	public FileUpDown(MultipartHttpServletRequest multipartRequest, String filePath) {
		super();
		this.multipartRequest = multipartRequest;
		this.filePath = filePath;
		fileNames = new HashMap<String, List<String>>();
		upload();
	}
	
	public FileUpDown(HttpServletResponse resp, String filePath, String oldNmae) {
		super();
		this.resp = resp;
		this.filePath = filePath;
		this.oldNmae = oldNmae;
		dowonload();
	}

	public Map<String, List<String>> getFileName() {
		return fileNames;
	}
	
	public byte[] getFileByte() {
		return fileByte;
	}

	// 파일 업로드 처리
	private void upload() {
		Iterator<String> itr = multipartRequest.getFileNames();
		List<String> oldNames = new ArrayList<String>();
		List<String> saveNames = new ArrayList<String>();
		while (itr.hasNext()) { // 받은 파일들을 모두 돌린다.
			MultipartFile mpf = multipartRequest.getFile(itr.next());
			String oldFileName = mpf.getOriginalFilename(); // 파일명
			String extName = oldFileName.substring(oldFileName.lastIndexOf("."));
			long currTime = System.currentTimeMillis();
			SimpleDateFormat currFileNm = new SimpleDateFormat("yyyyMMddhhmmss"); //14글자
			String saveFileName = currFileNm.format(currTime) + extName;
			String fileFullPath = filePath + "/" + saveFileName; // 파일 전체 경로
			try {
				// 파일 저장
				mpf.transferTo(new File(fileFullPath)); // 파일저장 실제로는 service에서 처리
				oldNames.add(oldFileName);
				saveNames.add(saveFileName);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		fileNames.put("oldNames", oldNames);
		fileNames.put("saveNames", saveNames);
	}
	
	private void dowonload() {
		try {
			fileByte = FileUtils.readFileToByteArray(new File(filePath));
			resp.setContentType("application/octet-stream");
			resp.setContentLength(fileByte.length); // 다운로드시 변경할 파일명
			resp.setHeader("Content-Disposition",
					"attachment; fileName=\"" + URLEncoder.encode(oldNmae, "UTF-8") + "\";");
			resp.setHeader("Content-Transfer-Encoding", "binary");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
