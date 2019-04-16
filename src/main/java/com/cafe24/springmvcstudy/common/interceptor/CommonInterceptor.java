package com.cafe24.springmvcstudy.common.interceptor;

import com.cafe24.springmvcstudy.category.Category;
import com.cafe24.springmvcstudy.category.CategoryService;
import com.cafe24.springmvcstudy.common.annotation.IncludeCategory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Slf4j
public class CommonInterceptor implements HandlerInterceptor {

    @Autowired
    private CategoryService categoryService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.debug("============================= Before Method");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.debug("============================= After Method");
        HandlerMethod method = (HandlerMethod) handler;

        if (method.hasMethodAnnotation(IncludeCategory.class)) {
            List<Category> categoryList = categoryService.getCategory();
            categoryService.getCategory("test");
            categoryService.getCategory("test", "gogo");
            categoryService.getCategory("test", "gogo", "11");
            modelAndView.addObject("categoryList", categoryList);
            log.info("================================ Model에 카테고리 포함");
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.debug("============================= Method Completed");
    }
}
