package com.cafe24.springmvcstudy.storage;

import com.cafe24.springmvcstudy.common.util.FileUploadUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
@RequiredArgsConstructor
public class FileStorageService {
    private final FileUploadUtil fileUploadUtil;
    private final FileInfoRepository fileInfoRepository;

    public void saveFileToLocal(MultipartFile file) {
        fileUploadUtil.saveFile(file);
    }

    @Transactional
    public FileInfo saveFileInfoToDB(MultipartFile file){
        String fileName = file.getOriginalFilename();
        String newFileName = fileUploadUtil.newFileName(file);
        FileInfo info = FileInfo.builder()
                .newName(newFileName)
                .orgName(fileName)
                .ext(fileName.substring(fileName.lastIndexOf(".")))
                .build();
//        FileInfo ret = fileInfoRepository.save(info);
//        log.info("[{}]newFileName : {}", ret.getSeq(), newFileName);
        return info;
    }
}
