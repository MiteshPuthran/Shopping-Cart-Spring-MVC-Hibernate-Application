package com.my.spring.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.my.spring.dao.UserDAO;
import com.my.spring.exception.UserException;
import com.my.spring.pojo.User;
import com.my.spring.validator.UserValidator;

@Controller
@RequestMapping("/user/*")
public class UserController {

	
	@Autowired
	@Qualifier("userDao")
	UserDAO userDao;

	@Autowired
	@Qualifier("userValidator")
	UserValidator validator;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	protected String goToUserHome(HttpServletRequest request) throws Exception {
		return "user-home";
	}
	
	
	@RequestMapping(value = "/user/login", method = RequestMethod.POST)
	protected String loginUser(HttpServletRequest request) throws Exception {

		HttpSession session = (HttpSession) request.getSession();
		
		try {

			System.out.print("loginUser");

			User u = userDao.get(request.getParameter("username"), request.getParameter("password"));
			
			if(u == null){
				System.out.println("UserName/Password does not exist");
				session.setAttribute("errorMessage", "UserName/Password does not exist");
				return "error";
			}
			
			else if(u.getUsertype().equals("Buyer")){
				session.setAttribute("user", u);
				return "buyer-home";
			}
			
			else if(!(u.getUsertype().equals("Buyer"))&&!(u.getUsertype().equals("Seller"))){
				session.setAttribute("errorMessage", "UserName/Password does not exist");
				return "error";
			}
			
			else{
				session.setAttribute("user", u);
				return "user-home";
			}
			
			//return "user-home";

		} catch (UserException e) {
			System.out.println("Exception: " + e.getMessage());
			session.setAttribute("errorMessage", "error while login");
			return "error";
		}

	}
	
	@RequestMapping(value = "/user/register", method = RequestMethod.GET)
	protected ModelAndView registerUser() throws Exception {
		System.out.print("registerUser");

		ModelAndView mav = new ModelAndView("register-user");
		Map<String,String> usertype = new LinkedHashMap<String,String>();
		usertype.put("Buyer", "Buyer");
		usertype.put("Seller", "Seller");
		
		mav.addObject("usertype", usertype);
        mav.addObject("user", new User());
        return mav;
		//return new ModelAndView("", "user", new User());

	}
	
	@RequestMapping(value = "/user/register", method = RequestMethod.POST)
	protected ModelAndView registerNewUser(HttpServletRequest request,  @ModelAttribute("user") User user, BindingResult result) throws Exception {

		validator.validate(user, result);

		if (result.hasErrors()) {
			ModelAndView mav = new ModelAndView("register-user");
			Map<String,String> usertype = new LinkedHashMap<String,String>();
			usertype.put("Buyer", "Buyer");
			usertype.put("Seller", "Seller");
			
			mav.addObject("usertype", usertype);
	        mav.addObject("user", user);
	        return mav;
			//return new ModelAndView("register-user", "user", user);
		}

		try {

			System.out.print("registerNewUser");

			User u = userDao.register(user);
			
			request.getSession().setAttribute("user", u);
			Email email= new SimpleEmail();
	           email.setHostName("smtp.googlemail.com");
	           email.setSmtpPort(465);
	           email.setAuthentication("puthranmitesh@gmail.com", "9892098500");
	           email.setSSLOnConnect(true);
	           email.setFrom(user.getEmail().getEmailAddress());
	           email.setSubject("Sign Up Successful");
	           email.setMsg("Welcome to the NEU Store\n\n Your account has been successfully created.");
	           email.addTo(user.getEmail().getEmailAddress());
	           email.send();
			
			return new ModelAndView("account-success", "user", u);

		} catch (UserException e) {
			System.out.println("Exception: " + e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while login");
		}
	}

		protected Map referenceData(HttpServletRequest request) throws Exception {

		Map referenceData = new HashMap();

		Map<String,String> usertype = new LinkedHashMap<String,String>();
		usertype.put("Buyer", "Buyer");
		usertype.put("Seller", "Seller");
		referenceData.put("usertype", usertype);
		
		return referenceData;
	}
}
