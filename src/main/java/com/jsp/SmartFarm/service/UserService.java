package com.jsp.SmartFarm.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.jsp.SmartFarm.dao.UserDao;
import com.jsp.SmartFarm.entity.User;
import com.jsp.SmartFarm.exception.EmailAlreadyRegisteredException;
import com.jsp.SmartFarm.exception.EmailNotSendException;
import com.jsp.SmartFarm.exception.PasswordMismatchException;
import com.jsp.SmartFarm.exception.UserNotFound;
import com.jsp.SmartFarm.util.ResponseStructure;

import jakarta.mail.internet.MimeMessage;


@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired 
	private JavaMailSender javaMailSender;
	
//	public String sendSimpleMail(User user)
//    {
// 
//        // Try block to check for exceptions
//        try {
// 
//            // Creating a simple mail message
//            SimpleMailMessage mailMessage = new SimpleMailMessage();
// 
//            // Setting up necessary details
//            mailMessage.setFrom("pratikreddy12345@gmail.com");
//            mailMessage.setTo(user.getEmail());
//            mailMessage.setText("Welcome to your Application");
//            mailMessage.setSubject("Registration");
// 
//            // Sending the mail
//            javaMailSender.send(mailMessage);
//            return "Mail Sent Successfully...";
//        }
// 
//        // Catch block to handle the exceptions
//        catch (Exception e) {
//            return "Error while Sending Mail";
//        }
//    }
	
//	++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	
//	for save 
	public ResponseEntity<ResponseStructure<User>> saveUser(User user) {
		
		ResponseStructure<User> m = new ResponseStructure<User>();
		
		if (userDao.fetchUserByEmail(user.getEmail()) == null) {
			User db = userDao.saveDao(user);
			m.setData(db);
			m.setMessage("User Registered Successfully...");
			m.setStatus(HttpStatus.CREATED.value());
			
			try {
				
				MimeMessage message = javaMailSender.createMimeMessage();
				MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
				
				messageHelper.setFrom("pratikreddy12345@gmail.com");
				messageHelper.setTo(user.getEmail());
				messageHelper.setSubject("Welcome Aboard! Your Journey with SmartFarm Begins Now 🌱");
				
				String emailText = 
							"<html><body><div style='text-align: center;'>"
						+	"<img src='' alt='Banner'>"
						+	"<p>Dear <strong><em>" + db.getFirstName() + " " + db.getLastName()
						+	",</em></strong></p>\n"
						+	"<p>Welcome to the SmartFarm Portal! 🌾 We're absolutely thrilled to have you join our growing community of dedicated farmers who are passionate about sustainable agriculture and driving progress in the industry.</p>\n"
						+	"<p>Your decision to register marks the beginning of an exciting journey filled with exploration, empowerment, and support. Here at SmartFarm, we're committed to providing you with the tools, resources, and connections you need to thrive in your farming endeavors.</p>\n"
						+	"<p>Whether you're seeking valuable insights, connecting with fellow farmers, or tapping into the expertise of industry leaders, our platform is here to serve you every step of the way. Feel free to dive in, explore all that we have to offer, and share your own experiences with the community.</p>\n\n"
						+	"<p>Together, we are cultivating a brighter, more sustainable future for agriculture, and we're thrilled to have you on board to be a part of it.</p>\n\n"
						+	"<p>Once again, welcome to SmartFarm! We can't wait to see the impact you'll make within our community.</p>\n\n"
						+	"<p><strong>Warm regards,</strong><br>SmartFarm Team</p> </body></html>";
				
				messageHelper.setText(emailText, true);
				ClassPathResource banner = new ClassPathResource("");
				messageHelper.addInline("emailBanner", banner);
				
				javaMailSender.send(message);
				
				return new ResponseEntity<ResponseStructure<User>>(m, HttpStatus.CREATED);
				
			} catch (Exception e) {
				System.out.println(e);
				throw new EmailNotSendException("Failed to send the Mail...");
			}
			
		} else {
			throw new EmailAlreadyRegisteredException("Email Already Registered ! Try New Email or Go for Login");
		}
	}
	
//	++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	
//	send OTP
	
	public ResponseEntity<ResponseStructure<Integer>> sendOtp(String email) {
		
		User db = userDao.fetchUserByEmail(email);
		if (db != null) {
			Random random = new Random();
			int otp = 100000 + random.nextInt(900000);
			ResponseStructure<Integer> m = new ResponseStructure<Integer>();
			
			try {
				MimeMessage message = javaMailSender.createMimeMessage();
				MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
				
				messageHelper.setFrom("pratikreddy12345@gmail.com");
				messageHelper.setTo(email);
				messageHelper.setSubject("Forget Password OTP Verification");
				
				String emailText = 
							"<html><body><div style='text-align: center;'><img src='' alt='Banner' style='display: black; margin: 0 auto;'></div>"
						+	"<p>Dear <strong><em>" + db.getFirstName() + " " + db.getLastName()
						+	",</em></strong></p>\n"
						+	"<p>Thank you for your request. To complete your verification process, please use the following OTP</p>\n"
						+	"<p><strong>OTP :</strong> " + otp + "</p>\n\n"
						+	"<p>Please enter this code on the verification page to proceed. If you did not initiate this request,\n please disregard this email.</p>\n\n"
						+	"<p>Thank you.</p>\n\n"
						+	"<p><strong>Warm regards,</strong><br>SmartFarm Team</p> </body></html>";
				
				messageHelper.setText(emailText, true);
				ClassPathResource banner = new ClassPathResource("");
				messageHelper.addInline("fqBanner", banner);
				
				javaMailSender.send(message);
				
				m.setData(otp);
				m.setMessage("OTP Sent Successfully");
				m.setStatus(HttpStatus.OK.value());
				
				return new ResponseEntity<ResponseStructure<Integer>>(m, HttpStatus.OK);
				
			} catch (Exception e) {
				System.out.println(e);
				throw new EmailNotSendException("Failed to Send the OTP Mail!");
			}
		} else {
			throw new UserNotFound("User not Found with E-mail = " + email);
		}
	}
	
//	++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

//	for fetch
	public ResponseEntity<ResponseStructure<User>> fetchUser(int id) {
		User db = userDao.fetchDao(id);
		
		if (db != null) {
			
			ResponseStructure<User> m = new ResponseStructure<User>();
			m.setData(db);
			m.setMessage("user fetch successfully...");
			m.setStatus(HttpStatus.FOUND.value());
			
			return new ResponseEntity<ResponseStructure<User>>(m, HttpStatus.FOUND);
		} else {
			throw new UserNotFound("User not found with id : " + id);
		}
	}
	
//	++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	
//	for delete
	public ResponseEntity<ResponseStructure<User>> deleteUser(int id) {
		
		User db = userDao.deleteDao(id);
		
		if (db != null) {
			ResponseStructure<User> m = new ResponseStructure<User>();
			m.setData(db);
			m.setMessage("user Data delete successfully...");
			m.setStatus(HttpStatus.FOUND.value());
			
			return new ResponseEntity<ResponseStructure<User>>(m, HttpStatus.FOUND);
		} else {
			throw new UserNotFound("User not found which you want to delete with id : " + id);
		}
	}
	
//	++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	
//	for update
	public ResponseEntity<ResponseStructure<User>> updateUser(User user) {
		
		User db = userDao.fetchDao(user.getId());
		
		if (db != null) {
			ResponseStructure<User> m = new ResponseStructure<User>();
			m.setData(userDao.updateDao(user));
			m.setMessage("user Data update successfully...");
			m.setStatus(HttpStatus.FOUND.value());
			
			return new ResponseEntity<ResponseStructure<User>>(m, HttpStatus.FOUND);
			
		} else {
			throw new UserNotFound("User details update successfully with id : " + user.getId());
		}
	}
	
//	++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	
//	for login
	
	public ResponseEntity<ResponseStructure<User>> loginUser(String email, String password) {
		User db = userDao.fetchUserByEmail(email);
		if (db != null) {
			ResponseStructure<User> m = new ResponseStructure<User>();
			if (db.getPwd().equals(password)) {
				m.setData(db);
				m.setMessage("Login Successfull!");
				m.setStatus(HttpStatus.ACCEPTED.value());
				return new ResponseEntity<ResponseStructure<User>>(m, HttpStatus.ACCEPTED);
			} else {
				throw new PasswordMismatchException("Incorrect Password! Please make sure you have entered the correct password.");
			}
		} else {
			throw new UserNotFound("User not Found with E-mail = " + email);
		}
	}

}
