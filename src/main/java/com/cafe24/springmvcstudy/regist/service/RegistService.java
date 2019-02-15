package com.cafe24.springmvcstudy.regist.service;

import com.cafe24.springmvcstudy.regist.vo.RegistVo;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RegistService {

    private Map<String , RegistVo> registVoMap;

    public RegistService(){
        registVoMap = new HashMap<>() ;
    }

    public void regist(RegistVo vo){
        registVoMap.put(vo.getEmail(), vo);
    }

}
