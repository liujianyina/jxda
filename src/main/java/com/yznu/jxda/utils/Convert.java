package com.yznu.jxda.utils;

/**
 * Created by 刘剑银 on 2017/8/11.
 * github: https://github.com/liujianyina
 * e-mail: 18523245126@sina.cn
 */

import org.apache.poi.xwpf.converter.core.BasicURIResolver;
import org.apache.poi.xwpf.converter.core.FileImageExtractor;
import org.apache.poi.xwpf.converter.xhtml.XHTMLConverter;
import org.apache.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

/**
 * 转换工具类
 */

public class Convert {

    /**
     * 将docx文件转化为HTML
     *
     * @param uploadPath 传文件的目录
     * @param filename   上传文件的文件名
     */
    public static void word07ToHtml(String uploadPath, String filename, String targetName) {

        OutputStreamWriter outputStreamWriter = null;
        try {

            //源文件的位置
            XWPFDocument document = new XWPFDocument(new FileInputStream(uploadPath + "/" + filename));
            XHTMLOptions options = XHTMLOptions.create();

            // 存放图片的文件夹
            options.setExtractor(new FileImageExtractor(new File("target/classes/static/temp")));

            // html中图片的路径
            options.URIResolver(new BasicURIResolver("/temp"));

            outputStreamWriter = new OutputStreamWriter(new FileOutputStream("target/classes/templates/temp/" + targetName), "utf-8");
            XHTMLConverter xhtmlConverter = (XHTMLConverter) XHTMLConverter.getInstance();
            xhtmlConverter.convert(document, outputStreamWriter, options);

            outputStreamWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
