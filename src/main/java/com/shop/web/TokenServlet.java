package com.shop.web;

import com.shop.domain.User;
import com.shop.util.FlowerResult;
import com.shop.util.JsonUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/token")
public class TokenServlet extends HttpServlet {
    protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        User user = (User) request.getSession().getAttribute("user");
        if(user!=null){
                user.setPassword(null);
                response.getWriter().print(JsonUtils.objectToJson(FlowerResult.ok(user)));
        }else {
                response.getWriter().print(JsonUtils.objectToJson( FlowerResult.build(400, "您好")));
        }
    }
}
