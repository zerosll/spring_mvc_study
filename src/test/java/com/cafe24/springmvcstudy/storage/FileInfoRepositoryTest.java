package com.cafe24.springmvcstudy.storage;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class FileInfoRepositoryTest {

    @Autowired FileInfoRepository fileInfoRepository;

    @Test
    public void test(){
        FileInfo fileInfo = FileInfo.builder()
                .ext(".jpg")
                .link("d:/uploads")
                .newName(UUID.randomUUID().toString() + Long.toString(System.nanoTime()))
                .orgName("abc")
                .size(111111l)
                .build();
        FileInfo fileInfo1 = fileInfoRepository.save(fileInfo);

        assertThat(fileInfo1.getSeq())
                .isGreaterThan(0l);
        assertThat(fileInfo1.getOrgName()).isNotEmpty().isEqualTo("abc");

    }

}