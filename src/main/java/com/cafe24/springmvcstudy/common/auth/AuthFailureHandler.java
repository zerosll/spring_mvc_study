package com.cafe24.springmvcstudy.common.auth;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 로그인 실패 핸들러
 *
 */
@Log4j2
@Component
public class AuthFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    //protected Logger logger = LoggerFactory.getLogger(AuthFailureHandler.class);
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {

        log.debug("onAuthenticationFailure 실행 [{}]",request.getParameter("userId"));
        // 실패 시 response를 json 형태로 결과값 전달
        /*JsonObject jsonObject = new JsonObject();
        Gson gson = new Gson();

        jsonObject.addProperty("success", false);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().print(gson.toJson(jsonObject));
        response.getWriter().flush();*/

        request.setAttribute("userId", request.getParameter("userId"));
        request.getRequestDispatcher("/login?error=true").forward(request, response);
    }

}