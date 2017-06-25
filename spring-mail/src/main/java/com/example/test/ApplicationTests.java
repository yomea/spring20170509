package com.example.test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.example.SpringMailApplication;

import freemarker.template.Configuration;
import freemarker.template.Template;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringMailApplication.class)
public class ApplicationTests {
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private Configuration config; 
	
	@Autowired
	private TemplateEngine templateEngine;

	private String from = "951645267@qq.com";
	private String to = "hong_mail123@sina.com";

	@Test
	public void sendSimpleMail() throws Exception {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(from);// 设置发送方
		message.setTo(to);// 设置接收方
		message.setSubject("主题：简单邮件");// 设置邮件主题
		message.setText("测试邮件内容");// 设置内容
		mailSender.send(message);// 发送文件
	}

	/**
	 * 发送带附件的邮件
	 * 
	 * @throws Exception
	 */
	@Test
	public void sendAttachmentsMail() throws Exception {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
		helper.setFrom(from);
		helper.setTo(to);
		helper.setSubject("主题：有附件");
		helper.setText("有附件的邮件");

		FileSystemResource file1 = new FileSystemResource(new File("C:\\Users\\may\\Pictures\\gif\\大e.gif"));
		FileSystemResource file2 = new FileSystemResource(new File("C:\\Users\\may\\Desktop\\needRead\\ant.pdf"));
		helper.addAttachment("附件-1.png", file1);
		helper.addAttachment("附件-2.pdf", file2);
		mailSender.send(mimeMessage);
	}

	/**
	 * 内容中嵌入图片等静态资源
	 * @throws Exception
	 */
	//这里需要注意的是addInline函数中资源名称feiq需要与正文中cid:feiq对应起来
	@Test
	public void sendInlineMail() throws Exception {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
		helper.setFrom(from);
		helper.setTo(to);
		helper.setSubject("主题：嵌入静态资源");
		
//	    StringBuffer sb = new StringBuffer();
//	    sb.append("<h1>大标题-h1</h1>").append("<p style='color:red;'>红色字</p>")
//	      .append("<p style='text-align:right'>右对齐</p>");
//	    helper.setText(sb.toString(), true);
	   //注意:调用setText时需要在第二个参数传入true，这样才会使用HTML格式发送邮件    
	        
	   helper.setText("<html><body><p><img src=\"cid:pic\"></body></html>",true);
	   FileSystemResource pic = new FileSystemResource(new File("C:\\Users\\may\\Pictures\\Saved Pictures\\赤座灯里_岁纳京子.jpg"));
	   helper.addInline("pic", pic);
	   mailSender.send(mimeMessage);
	}
	
	
	/**
	 * 使用thymeleaf模板
	 * @throws Exception
	 */
	@Test
	public void sendThymeleafTemplateMail() throws Exception {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
		helper.setFrom(from);
		helper.setTo(to);
		helper.setSubject("主题：模板邮件");
		
		Context context = new Context();
		
		context.setVariable("src", "cid:pic");
		
		String text = templateEngine.process("test2", context);
		
		helper.setText(text, true);
		
		FileSystemResource pic = new FileSystemResource(new File("C:\\Users\\may\\Pictures\\Saved Pictures\\赤座灯里_岁纳京子.jpg"));
		
		helper.addInline("pic", pic);
		
		mailSender.send(mimeMessage);
	}
	
	
	/**
	 * 使用Freemarker模板
	 * @throws Exception
	 */
	@Test
	public void sendFreemarkerTemplateMail() throws Exception {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
		helper.setFrom(from);
		helper.setTo(to);
		helper.setSubject("主题：模板邮件");
		Map<String, Object> model = new HashMap<>();
		
		model.put("src", "cid:pic");
		
		Template template = config.getTemplate("test.ftlh");
		
		String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
		
		helper.setText(text, true);
		
		FileSystemResource pic = new FileSystemResource(new File("C:\\Users\\may\\Pictures\\Saved Pictures\\赤座灯里_岁纳京子.jpg"));
		
		helper.addInline("pic", pic);
		
		mailSender.send(mimeMessage);
	}
	
}