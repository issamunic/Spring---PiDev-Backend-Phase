package tn.esprit.spring.mail.mailsender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Service
public class SimpleEmailExampleController implements ISimpleEmailExampleController{
	@Autowired
    public JavaMailSender emailSender;

   // @ResponseBody
   // @RequestMapping("/sendSimpleEmail")
    public String sendSimpleEmail(String Mail , String Subject , String Text) {

        // Create a Simple MailMessage.
        SimpleMailMessage message = new SimpleMailMessage();
        
        message.setTo(Mail);
        message.setSubject(Subject);
        message.setText(Text);

        // Send Message!
        this.emailSender.send(message);

        return "Email Sent!";
    }
}
