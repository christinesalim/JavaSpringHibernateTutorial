package com.luv2code.springdemo.dao;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.util.SortUtils;


@Repository
public class CustomerDAOImpl implements CustomerDAO {

	//Need to inject session factory
	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public List<Customer> getCustomers() {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
				
		// create a query  ... sort by last name
		Query<Customer> theQuery = 
				currentSession.createQuery("from Customer order by lastName",
											Customer.class);
		
		// execute query and get result list
		List<Customer> customers = theQuery.getResultList();
				
		// return the results		
		return customers;
	}


	@Override
	public void saveCustomer(Customer theCustomer) {
		
		//Get the current hibernate session
		Session currSession = sessionFactory.getCurrentSession();
		
		//Save or update the customer
		currSession.saveOrUpdate(theCustomer);	
		
	}


	@Override
	public Customer getCustomer(int theId) {
		//get the current hibernate session
		Session currSession = sessionFactory.getCurrentSession();
		
		//get the customer using the primary key
		Customer theCustomer = currSession.get(Customer.class, theId);
				
		return theCustomer;
	}


	@Override
	public void deleteCustomer(int theId) {
		//Get the current hibernate session
		Session currSession = sessionFactory.getCurrentSession();
		
		//Delete the object with the primary key passed in
		Query theQuery = 
			currSession.createQuery("delete from Customer where id=:customerId");
		theQuery.setParameter("customerId", theId);		
		
		theQuery.executeUpdate();
		
	}


	@Override
	public List<Customer> searchCustomers(String theSearchName) {
		//Get the current hibernate session
		Session currSession = sessionFactory.getCurrentSession();

        Query theQuery = null;
        
		
		//
        // only search by name if theSearchName is not empty
        //
        if (theSearchName != null && theSearchName.trim().length() > 0) {
            // search for firstName or lastName ... case insensitive
		
			//Create the query: search by name if the theSearchName field is not empty
			theQuery = null;
			theQuery =currSession.createQuery("from Customer where lower(firstName) like :theName or lower(lastName) like :theName", Customer.class);
	        theQuery.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");
	    }
	    else {
	        // theSearchName is empty ... so just get all customers
	        theQuery =currSession.createQuery("from Customer", Customer.class);            
	    }
		
		//Execute the query
		List<Customer> customers = theQuery.getResultList();
						
		return customers;
	
		
	}


	@Override
	public List<Customer> getCustomers(int theSortField) {
	
		//Get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//Determine the sort field
		String fieldName = null;
		
		switch(theSortField) {
			case SortUtils.FIRST_NAME:
				fieldName = "firstName";
				break;
			case SortUtils.LAST_NAME:
				fieldName = "lastName";
				break;
			case SortUtils.EMAIL:
				fieldName = "email";
				break;
			default:
				//If no match then default to lastName sort
				fieldName = "lastName";
		}
		
		//Create query
		String queryString = "from Customer order by " + fieldName;
		Query<Customer> query = currentSession.createQuery(queryString, Customer.class);
		
		//Execute query and get result
		List<Customer> customers = query.getResultList();
		
		//Return the result
		return customers;
	}


}
