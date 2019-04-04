package com.cafe24.springmvcstudy.common.util;

import com.cafe24.springmvcstudy.storage.FileInfo;
import com.cafe24.springmvcstudy.storage.FileInfoRepository;
import com.cafe24.springmvcstudy.common.exception.StorageException;
import com.cafe24.springmvcstudy.common.properties.UploadProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Component
public class FileUploadUtil {

    private final UploadProperties uploadProperties;
    private final FileInfoRepository fileInfoRepository;

    @Autowired
    public FileUploadUtil(UploadProperties uploadProperties, FileInfoRepository fileInfoRepository) {
        this.uploadProperties = uploadProperties;
        this.fileInfoRepository = fileInfoRepository;
    }


    public Long saveFile(MultipartFile file) {

        String uploadPath = uploadProperties.getPath();
        if (file.isEmpty()) {
            throw new StorageException("Failed to store empty file");
        }
        //디렉토리가 존재하지 않는다면 생성
        File destdir = new File(uploadPath);
        if(!destdir.exists()){
            destdir.mkdirs();
        }
        try {
            String fileName = file.getOriginalFilename();
            String newFileName = newFileName(file);
            InputStream is = file.getInputStream();

            Files.copy(is, Paths.get(uploadPath + "/" + newFileName(file)),
                    StandardCopyOption.REPLACE_EXISTING);

            return setFileInfo(file, newFileName);

        } catch (IOException e) {

            String msg = String.format("Failed to store file", file.getName());
            throw new StorageException(msg, e);
        }

    }
    private String newFileName(MultipartFile file) {
        String uuid = UUID.randomUUID().toString() + Long.toString(System.nanoTime()) ;
        String fileName = file.getOriginalFilename();
        String fileTitle = fileName.substring(0,fileName.lastIndexOf("."));
        String ext = fileName.substring(fileName.lastIndexOf("."));

        return uuid + ext;
    }
    private Long setFileInfo(MultipartFile file, String newFileName){
        String fileName = file.getOriginalFilename();
        FileInfo info = FileInfo.builder()
                        .newName(newFileName)
                        .orgName(fileName)
                        .ext(fileName.substring(fileName.lastIndexOf(".")))
                        .build();
        FileInfo ret = fileInfoRepository.save(info);
        return  ret.getSeq();
    }
}
