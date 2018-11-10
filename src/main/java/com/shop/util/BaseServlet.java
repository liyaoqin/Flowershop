package com.shop.util;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.lang.reflect.Method;

public class BaseServlet extends HttpServlet {

   protected void service(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
      request.setCharacterEncoding("UTF-8");
      response.setContentType("text/html;charset=utf-8");
      String methodName=request.getParameter("method");
      if(methodName == null || methodName.isEmpty()) {
         methodName = "execute";
      }
      try {
         Class c=this.getClass();
         Method method=c.getMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);
            String desPath= (String) method.invoke(this,request,response);
            if(desPath!=null&&!desPath.isEmpty()){
               request.getRequestDispatcher(desPath).forward(request,response);
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
}
