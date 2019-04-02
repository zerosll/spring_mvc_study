package com.cafe24.springmvcstudy.regist;

import com.cafe24.springmvcstudy.regist.RegistVo;
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

    public Map<String, RegistVo> getRegistVoMap(){
        return registVoMap;
    }

}
