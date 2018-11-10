package com.shop.web;

import com.shop.util.BaseServlet;
import com.shop.domain.User;
import com.shop.service.UserService;
import com.shop.service.UserServiceImpl;
import com.shop.util.DigestUtils;
import com.shop.util.FtlUtil;
import org.apache.commons.beanutils.BeanUtils;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@WebServlet("/user")
public class UserServlet extends BaseServlet {

    public String regist(HttpServletRequest request, HttpServletResponse response) {
        Map<String,Object> root = new HashMap<String,Object>();
        UserService userService=new UserServiceImpl();
        String code = request.getParameter("code");
        System.out.println("code=" + code);
        String word = (String) this.getServletContext().getAttribute("checkCode");
        try {
            if (code.equals(word)) {
                Map<String, String[]> parameterMap = request.getParameterMap();
                User u = new User();
                BeanUtils.populate(u, parameterMap);
                u.setPassword(DigestUtils.md5DigestAsHex(u.getPassword().getBytes()));
                User existuser=null;
                existuser = userService.findUser(u);
                if(existuser!=null){
                    root.put("msg","该用户名已存在！");
                    root.put("mhref","regist.html");
                    FtlUtil.getTemplate(request,response,"show.ftl","show.html",root);
                    response.sendRedirect("show.html");
                    return null;
                }
                u.setUid(UUID.randomUUID().toString());
                userService.addUser(u);
                root.put("msg","注册成功！");
                root.put("mhref","login.html");
                FtlUtil.getTemplate(request,response,"show.ftl","show.html",root);
                response.sendRedirect("show.html");
            } else {
                root.put("msg","验证码错误！");
                root.put("mhref","regist.html");
                FtlUtil.getTemplate(request,response,"show.ftl","show.html",root);
                response.sendRedirect("show.html");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public String login(HttpServletRequest request, HttpServletResponse response){
        Map<String,Object> root = new HashMap<String,Object>();
        UserService userService=new UserServiceImpl();
        String code = request.getParameter("code");
        System.out.println("code=" + code);
        String word = (String) this.getServletContext().getAttribute("checkCode");
        try {
            if(code.equals(word)){
                Map<String, String[]> parameterMap = request.getParameterMap();
                User u = new User();
                BeanUtils.populate(u, parameterMap);
                u.setPassword(DigestUtils.md5DigestAsHex(u.getPassword().getBytes()));
                User existuser=null;
                existuser = userService.loginUser(u);
                if(existuser==null){
                    root.put("msg","用户名或密码错误，请重新登录！");
                    root.put("mhref","login.html");
                    FtlUtil.getTemplate(request,response,"show.ftl","show.html",root);
                    response.sendRedirect("show.html");
                    return null;
                }
                HttpSession session=request.getSession();
                session.setAttribute("user", u);
                response.sendRedirect("index.html");
            }else {
                root.put("msg","验证码错误！");
                root.put("mhref","login.html");
                FtlUtil.getTemplate(request,response,"show.ftl","show.html",root);
                response.sendRedirect("show.html");
            }
           return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public String exit(HttpServletRequest request, HttpServletResponse response){
        HttpSession session=request.getSession();
        session.invalidate();
        try {
            response.sendRedirect("index.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
