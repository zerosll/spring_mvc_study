package com.cafe24.springmvcstudy.common.auth;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 로그인 성공 핸들러
 *
 */
@Component
public class AuthSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {

        /*HttpSession session = request.getSession();
        AtomicReference<String> redirectUrl = new AtomicReference<>("");
        if (session != null) {
            redirectUrl.set((String) session.getAttribute("prevPage"));
        }
        // 성공 시 response를 json형태로 반환
        JsonObject jsonObject = new JsonObject();
        Gson gson = new Gson();

        jsonObject.addProperty("success", true);
        jsonObject.addProperty("prevPage", redirectUrl.get());
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().print(gson.toJson(jsonObject));
        response.getWriter().flush();*/


       HttpSession session = request.getSession();
        if (session != null) {
            String redirectUrl = (String) session.getAttribute("prevPage");
            if (redirectUrl != null) {
                session.removeAttribute("prevPage");
                getRedirectStrategy().sendRedirect(request, response, redirectUrl);
            } else {
                super.onAuthenticationSuccess(request, response, authentication);
            }
        } else {
            super.onAuthenticationSuccess(request, response, authentication);
        }



    }


}
