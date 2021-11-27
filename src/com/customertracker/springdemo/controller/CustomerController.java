package com.customertracker.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.customertracker.springdemo.entity.Customer;
import com.customertracker.springdemo.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	//inject customer service
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/list")
	public String listCustomers(Model theModel) {

		//get the customers from the service
		List<Customer> theCustomers = customerService.getCustomers();
		
		//add the customers to the model
		theModel.addAttribute("customers", theCustomers);
		
		return "list-customers";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		//create model attribute to bind the form data
		Customer theCustomer = new Customer();
		
		theModel.addAttribute("customer", theCustomer);
		
		return "customer-form";
	}
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer theCustomer) {
		
		//save the customer
		customerService.saveCustomer(theCustomer);
		
		return"redirect:/customer/list";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int theId, Model theModel) {
		
		//get the customer from the service
		Customer theCustomer = customerService.getCustomers(theId);
		
		//set customer as the model attribute
		theModel.addAttribute("customer", theCustomer);
		
		//send over to form
		return "customer-form";
	}
	
	@GetMapping("/showAlertForDelete")
	public String showAlertForDelete(@RequestParam("customerId") int theId, Model theModel) {
		
		//delete the customer
		customerService.deleteCustomer(theId);
		
		return "redirect:/customer/list";
	}
	
	@GetMapping("/search")
	public String searchCustomers(@RequestParam("theSearchName") String theSearchName,
					Model theModel) {
		
		//search customers from the service
		List<Customer> theCustomers = customerService.searchCustomer(theSearchName);
		
		//add the model to attribute
		theModel.addAttribute("customers", theCustomers);
		
		return "list-customers";
	}
	
}
