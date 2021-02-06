package com.iter.adm.email;

import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.iter.adm.user.dao.IUserDAO;
import com.iter.adm.user.model.UserResponse;

@RestController
@EnableScheduling
public class EmailResource {
	
	@Autowired
	JavaMailSenderImpl messageSenderImpl;
	
	@Autowired
	IUserDAO userDao;
	
	@Autowired
	IMiscDAO miscDao;
	
	@PostMapping(path="/sendemail")
	public String sendEmail(@RequestBody EmailRequest request) {	
		int otp = generateOTP(request.getToEmailAddress());
		if(otp != 0) {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom(request.getFromEmailAddress());
			message.setTo(request.getToEmailAddress());
			message.setSubject(request.getSubjet());
			message.setText(getMessageBody(otp));
			messageSenderImpl.send(message);
			return "Success";
		}
		return "Failure";
		
	}
	
	@Scheduled(cron = "0 0/5 * * * ?")
	@PostMapping(path="/purgedata")
	public String purgeOTP() {
		System.out.println("###############################" + new Date().getTime());
		String result = miscDao.purgeOTP();
		return result;
	}
	
	public String getMessageBody(int otp) {
		return "Yuor One Time password for password reset is : " + otp;
		
	}
	
	public int generateOTP(String emailAddress) {
		int generatedOTP = new Random().nextInt(900000) + 100000;
		UserResponse response  = userDao.updateOTP(emailAddress, generatedOTP);
		if(response.getResult().equalsIgnoreCase("Success")) {
			return generatedOTP;
		}
		return 0;
	}

}
