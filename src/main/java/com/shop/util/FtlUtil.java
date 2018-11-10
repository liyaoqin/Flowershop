package com.shop.util;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Locale;
import java.util.Map;

public class FtlUtil {
    public static Template getTemplate(HttpServletRequest request, HttpServletResponse response, String fileName1,String fileName2, Map map){
        try {
            Configuration cfg = new Configuration(Configuration.getVersion());
            cfg.setOutputEncoding("UTF-8");
            cfg.setDefaultEncoding("UTF-8");// 编码设置1
            cfg.setEncoding(Locale.CHINA, "UTF-8");
            //设定读取ftl模板文件的目录
            //cfg.setClassForTemplateLoading(this.getClass(), "/template");     //读取src目录下
            cfg.setServletContextForTemplateLoading(request.getSession().getServletContext(), "WEB-INF/ftl"); //读取webroot目录下
            String indexPath = request.getSession().getServletContext().getRealPath(fileName2);
            Writer out = new OutputStreamWriter(new FileOutputStream(indexPath),"UTF-8");
            //在模板文件目录中找到名称为name的文件,并加载为模板
            Template template = cfg.getTemplate(fileName1);
            template.process(map,out);
        } catch (Exception e) {
            e.printStackTrace();
        }
            return null;
    }
}
