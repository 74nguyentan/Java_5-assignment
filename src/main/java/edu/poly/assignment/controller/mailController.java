package edu.poly.assignment.controller;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class mailController {
@Autowired
private JavaMailSender javaMailSender;

@GetMapping("/gmail")
public String senmail() {
//	SimpleMailMessage msg = new SimpleMailMessage();
//	
//	msg.setTo("exfick@gmail.com" , "74nguyentan@gmail.com");
//	msg.setSubject("Spring java 5");
//	msg.setText("hoc hoc hoc");
//	
//	javaMailSender.send(msg);
	return "layouts/gmail";
}

@GetMapping("/sendgmail")
public String send(ModelMap model, @RequestParam("from") String from, @RequestParam("to") String to, @RequestParam("subject") String subject,@RequestParam("body") String body) {
	
	try {
	MimeMessage mail = javaMailSender.createMimeMessage();  // createMime... tạo mail
	MimeMessageHelper helper = new MimeMessageHelper(mail);
	
	helper.setFrom(from,from);
	helper.setTo(to);
	//helper.setCc(cc);  // danh sách email cùng nhận
	//helper.setBcc(bcc);  // danh sách email cùng nhận ẩn danh
	helper.setReplyTo(from,from); // cung cấp thông tin người nhận phản hồi lại người gửi
	helper.setSubject(subject);
	helper.setText(body,true);
	// helper.addAttachment(name, file);  // file đính kèm
	
	javaMailSender.send(mail);  // gửi mail
	
	model.addAttribute("message", "gui mail  thanh cong");
	
	} catch (Exception e) {
		e.printStackTrace();
		
		model.addAttribute("message","gui mail that bai");
	}
	
	return "layouts/gmail";
}
}
