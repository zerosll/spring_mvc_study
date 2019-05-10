package com.cafe24.springmvcstudy.auth;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Log4j2
@Controller
public class AuthController {

    //Logger logger = LoggerFactory.getLogger(AuthController.class);

    @RequestMapping("/login")
    public String loginForm(HttpServletRequest req, Model model) {
        String referer = req.getHeader("Referer");
        String prevPage = (String) req.getSession().getAttribute("prevPage");
        if (prevPage != null) {
            req.getSession().setAttribute("prevPage", referer);
        }

        String userId = (String) req.getAttribute("userId");
        model.addAttribute("userId", userId);
        String error = (String) req.getParameter("error");
        if(error != null)
        log.error("error : {}", error);

        return "auth/login";
    }
}
