package com.my.spring.controller;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.my.spring.dao.AdvertDAO;
import com.my.spring.dao.CategoryDAO;
import com.my.spring.dao.UserDAO;
import com.my.spring.exception.AdvertException;
import com.my.spring.pojo.Advert;
import com.my.spring.pojo.Cart;
import com.my.spring.pojo.Category;
import com.my.spring.pojo.User;

@Controller
@RequestMapping("/advert/*")
public class AdvertController {
		
		@Autowired
		@Qualifier("advertDao")
		AdvertDAO advertDao;
		
		@Autowired
		@Qualifier("categoryDao")
		CategoryDAO categoryDao;
		
		@Autowired
		@Qualifier("userDao")
		UserDAO userDao;
		
		@Autowired
		ServletContext servletContext;

		@RequestMapping(value = "/advert/add", method = RequestMethod.POST)
		public ModelAndView addCategory(@ModelAttribute("advert") Advert advert, BindingResult result) throws Exception {

			try {			
				
				User u = userDao.get(advert.getPostedBy());
				advert.setUser(u);
				advert = advertDao.create(advert);
				
	            
	            for(Category c: advert.getCategories()){
	            	c = categoryDao.get(c.getTitle());
	            	c.getAdverts().add(advert);
	            	categoryDao.update(c); //to maintain many to many relationship
				
	            }
	            if (advert.getFilename().trim() != "" || advert.getFilename() != null) {
					File directory;
					String check = File.separator; // Checking if system is linux
													// based or windows based by
													// checking seprator used.
					String path = null;
					if (check.equalsIgnoreCase("\\")) {
						path = servletContext.getRealPath("").replace("build\\", ""); // gives real path as Lab9/build/web/
																					  // so we need to replace build in the path
																							}

					if (check.equalsIgnoreCase("/")) {
						path = servletContext.getRealPath("").replace("build/", "");
						path += "/"; // Adding trailing slash for Mac systems.
					}
					directory = new File(path + "\\" + advert.getFilename());
					boolean temp = directory.exists();
					if (!temp) {
						temp = directory.mkdir();
					}
					if (temp) {
						// We need to transfer to a file
						CommonsMultipartFile photoInMemory = advert.getPhoto();

						String fileName = photoInMemory.getOriginalFilename();
						// could generate file names as well

						File localFile = new File(directory.getPath(), fileName);

						// move the file from memory to the file

						photoInMemory.transferTo(localFile);
						advert.setFilename(localFile.getPath());
						System.out.println("File is stored at" + localFile.getPath());
						System.out.print("registerNewUser");
						Advert a = advertDao.create(advert);

					} else {
						System.out.println("Failed to create directory!");
					}
					
				}
				
	            return new ModelAndView("advert-success", "advert", advert);
	            
			} catch (AdvertException e) {
				System.out.println(e.getMessage());
				return new ModelAndView("error", "errorMessage", "error while login");
			}
			
			
		}
		
		@RequestMapping(value = "/advert/list", method = RequestMethod.GET)
		public ModelAndView addCategory(HttpServletRequest request) throws Exception {

			ModelAndView mav = new ModelAndView("advert-list");
			List<Advert> adverts = advertDao.list();
			mav.addObject("adverts", adverts);
	        mav.addObject("cart", new Cart());
	        return mav;
			
		}
		
		@RequestMapping(value = "/advert/sellerlist", method = RequestMethod.GET)
		public ModelAndView addCategories(HttpServletRequest request) throws Exception {

			try {			
				
				List<Advert> adverts = advertDao.list();
				return new ModelAndView("seller-advert-list", "adverts", adverts);
				
			} catch (AdvertException e) {
				System.out.println(e.getMessage());
				return new ModelAndView("error", "errorMessage", "error while login");
			}
			
			
		}

		@RequestMapping(value="/advert/add", method = RequestMethod.GET)
		public ModelAndView initializeForm(HttpServletRequest request) throws Exception {		
			ModelAndView mv = new ModelAndView();
			mv.addObject("categories", categoryDao.list());			
			mv.addObject("advert", new Advert());
			mv.setViewName("advert-form");
			return mv;
		}


}