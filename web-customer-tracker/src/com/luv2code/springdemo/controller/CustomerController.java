package com.luv2code.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.CustomerService;
import com.luv2code.springdemo.util.SortUtils;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	//need to inject the customer service
	@Autowired
	private CustomerService customerService;
	
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		//Create the model attribute to bind form data
		Customer theCustomer = new Customer();
		
		theModel.addAttribute("customer", theCustomer);			
		
		return "customer-form";
	}
	
	@PostMapping("/saveCustomer")
	public String saveCustomer (@ModelAttribute("customer") Customer theCustomer) {
		
		//Save the customer using our service
		customerService.saveCustomer(theCustomer);
		
		//Display the customer list page
		return "redirect:/customer/list";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int theId,
			Model theModel) {
		//get the customer from the customer service
		Customer theCustomer = customerService.getCustomer(theId);
						
		//set customer as the model attribute to pre-populate the form
		theModel.addAttribute("customer", theCustomer);
		
		//send over to our form
		return "customer-form";
	}
	
	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("customerId") int theId,
			Model theModel) {
				
		//delete the customer
		customerService.deleteCustomer(theId);
		
		//Display the customer list page
		return "redirect:/customer/list";
	}
	
	@GetMapping("/search")
	public String searchCustomers(@RequestParam("theSearchName") String theSearchName,
			Model theModel) {
			
		//search customers from the service
		List<Customer> theCustomers = customerService.searchCustomers(theSearchName);
		
		//Add the customers found to the model
		theModel.addAttribute("customers", theCustomers);
		return "list-customers";
		
	}
	
	@GetMapping("/list")
	public String listCustomers(@RequestParam(required=false) String sort,
			Model theModel) {
		//Get the customers from the service
		List<Customer> theCustomers = null;
		
		//Check for sort field
		if (sort != null) {
			int sortField = Integer.parseInt(sort);
			theCustomers = customerService.getCustomers(sortField);
		}
		else {
			//no sort field provided so default to last name sorting
			theCustomers = customerService.getCustomers(SortUtils.LAST_NAME);
		}
		theModel.addAttribute("customers", theCustomers);	
		return "list-customers";
	}
}
