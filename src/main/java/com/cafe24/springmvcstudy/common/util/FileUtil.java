package com.cafe24.springmvcstudy.common.util;

import com.cafe24.springmvcstudy.common.exception.ResourceNotFoundException;
import com.cafe24.springmvcstudy.storage.FileInfo;
import com.cafe24.springmvcstudy.storage.FileInfoRepository;
import com.cafe24.springmvcstudy.common.exception.StorageException;
import com.cafe24.springmvcstudy.common.properties.UploadProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Slf4j
@Component
public class FileUtil {

    private final UploadProperties uploadProperties;
    /** 다운로드 버퍼 크기 */
    private static final int BUFFER_SIZE = 8192; // 8kb

    /** 문자 인코딩 */
    private static final String CHARSET = "utf-8";

    @Autowired
    public FileUtil(UploadProperties uploadProperties) {
        this.uploadProperties = uploadProperties;

    }

    public String getFilePath() {
        return uploadProperties.getPath();
    }

    public String saveFile(MultipartFile file) {

        String uploadPath = uploadProperties.getPath();
        if (file.isEmpty()) {
            throw new ResourceNotFoundException("Failed to store empty file");
        }
        //디렉토리가 존재하지 않는다면 생성
        File destdir = new File(uploadPath);
        if(!destdir.exists()){
            destdir.mkdirs();
        }
        try {
            String newFileName = newFileName(file);
            InputStream is = file.getInputStream();

            Files.copy(is, Paths.get(uploadPath + File.separator + newFileName),
                    StandardCopyOption.REPLACE_EXISTING);

            return newFileName;
        } catch (IOException e) {

            String msg = String.format("Failed to store file", file.getName());
            throw new StorageException(msg, e);
        }

    }
    public String newFileName(MultipartFile file) {
        String uuid = UUID.randomUUID().toString() + Long.toString(System.nanoTime()) ;
        String fileName = file.getOriginalFilename();
        String fileTitle = fileName.substring(0,fileName.lastIndexOf("."));
        String ext = fileName.substring(fileName.lastIndexOf("."));

        return uuid + ext;
    }


    public static File getFile(FileInfo info) {
        File file = new File(info.getLocation()+"/"+info.getNewName());

        if(file.exists()) {
            return file;
        }

        log.error("File Not FOUND !!!!!!!!! : {}", info.getLocation()+"/"+info.getNewName());
        return null;

    }

    public static void download(HttpServletRequest request, HttpServletResponse response, File file, String realName) throws Exception {
        InputStream is = null;
        BufferedInputStream fin = null;
        BufferedOutputStream outs = null;
        try {
            String mimetype = request.getSession().getServletContext().getMimeType(file.getName());
			    /*
			     프로그램 파일 또는 실행파일일 경우 에러처리
			     */
            if (file == null) {
                throw new StorageException("파일이 없습니다.");
            }if (!file.exists()) {
                throw new StorageException("파일이 없습니다.");
            }if (file.length() <= 0) {
                throw new StorageException("파일이 없습니다.");
            }if (file.isDirectory()) {
                throw new StorageException("디렉토리 입니다.");
            }

            String mime = "binary/octet-stream;";

            byte[] buffer = new byte[BUFFER_SIZE];

            response.setContentType(mime + "; charset=" + CHARSET);

            //String userAgent = request.getHeader("User-Agent");
            response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(realName, "UTF-8").replaceAll("\\+", "%20") + ";");

            long filesize = file.length();

            // 파일 사이즈가 정확하지 않을때는 아예 지정하지 않는다.
            if (filesize > 0) {
                response.setHeader("Content-Length", "" + filesize);
            }

            //파일 다운로드 작업
            is = new FileInputStream(file);
            fin = new BufferedInputStream(is);
            outs = new BufferedOutputStream(response.getOutputStream());
            int read = 0;
            while ((read = fin.read(buffer)) != -1) {
                outs.write(buffer, 0, read);
            }

        }catch(StorageException e){
            throw e;
        }catch(Exception e){
            e.printStackTrace();
            throw new StorageException("파일 다운로드 에러");
        }finally {
            if(is!=null){
                is.close();
            }
            if(outs!=null){
                outs.close();
            }
            if(fin!=null){
                fin.close();
            }
        } // end of try/catch
    }
}
