package com.luv2code.springdemo.mvc;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloWorldController {
	//need a controller method to show the initial HTML form
	@RequestMapping("/showForm")
	public String showForm() {
		return "helloworld-form";
	}
	
	//need a controller to process the HTML form	
	@RequestMapping("/processForm")
	public String processForm() {
		return "helloworld";
	}
	
	//New controller method to read form data and add it to a model
	@RequestMapping("/processFormVersionTwo")
	public String letsShout(HttpServletRequest request, Model model) {
		//read request parameter from the HTML form
		String name = request.getParameter("studentName");
		//convert the data to all caps
		name = name.toUpperCase();
		
		//create the message
		String result = "Yo! " + name;
		
		//Add message to model
		model.addAttribute("message", result);
				
		return "helloworld";
	}
	
	//New controller method to read form data and add it to a model
	@RequestMapping("/processFormVersionThree")
	public String processFormVersionThree(
			@RequestParam("studentName") String name, 
			Model model
		) 
	{
		
		//convert the data to all caps
		name = name.toUpperCase();
		
		//create the message
		String result = "Hello! " + name;
		
		//Add message to model
		model.addAttribute("message", result);
				
		return "helloworld";
	}

}

