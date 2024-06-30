package org.eu.luolei.interceptor;

import com.auth0.jwt.interfaces.Claim;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.eu.luolei.utils.JwtUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 令牌验证
        String token = request.getHeader("Authorization");
        try{
            Map<String, Claim> claims = JwtUtils.parseToken(token);
            // 放行
            return true;
        }catch (Exception e){
            response.setStatus(401);
            // 不放行
            return false;
        }
    }
}
