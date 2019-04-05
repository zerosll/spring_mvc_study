package com.cafe24.springmvcstudy.storage;

import com.cafe24.springmvcstudy.common.util.FileUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class FileStorageService {
    private final FileUtil fileUtil;
    private final FileInfoRepository fileInfoRepository;

    public String saveFileToLocal(MultipartFile file) {
            return fileUtil.saveFile(file);
    }

    public FileInfo saveFileInfoToDB(MultipartFile file, String newFileName){
        String fileName = file.getOriginalFilename();
        FileInfo info = FileInfo.builder()
                .newName(newFileName)
                .orgName(fileName)
                .size(file.getSize())
                .type(file.getContentType())
                .location(fileUtil.getFilePath())
                .ext(fileName.substring(fileName.lastIndexOf(".")))
                .build();
//        FileInfo ret = fileInfoRepository.save(info);
//        log.info("[{}]newFileName : {}", ret.getSeq(), newFileName);
        return info;
    }

    public void executeFileDownload(HttpServletRequest request, HttpServletResponse response, Long fileSeq) throws Exception {
        FileInfo info = fileInfoRepository.findById(fileSeq).orElseThrow(IllegalArgumentException::new);

        File file = fileUtil.getFile(info);

        fileUtil.download(request, response, file, info.getOrgName());
    }
}
