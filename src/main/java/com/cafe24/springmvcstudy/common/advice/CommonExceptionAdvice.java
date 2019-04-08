package com.cafe24.springmvcstudy.common.advice;

import com.cafe24.springmvcstudy.common.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@ControllerAdvice
public class CommonExceptionAdvice {
    @ExceptionHandler(Exception.class)
    public ModelAndView common(Exception e) {

        log.info(e.toString());

        ModelAndView mav = new ModelAndView();
        mav.setViewName("/error/error_common");
        mav.addObject("exception", e);  //예외를 뷰에 던져서 주자.

        return mav;
    }
    @ExceptionHandler(ResourceNotFoundException.class)
    public ModelAndView resourceNotFound(ResourceNotFoundException e) {

        log.info(e.toString());

        ModelAndView mav = new ModelAndView();
        mav.setViewName("/error/resource_not_found");
        mav.addObject("exception", e);  //예외를 뷰에 던져서 주자.

        return mav;
    }
}
