package com.start.contract.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.start.contract.dto.Contract;
import com.start.contract.dto.Customer;
import com.start.contract.repo.CustomerRepository;


@RestController
public class ContractController {
	
	public static final String PUBLIC_CONTRACT_BASE_URI="svc/v1/contracts/public/";
	public static final String ADMIN_CONTRACT_BASE_URI="svc/v1/contracts/admin/";
	
	@RequestMapping(value="svc/v1/contracts/public/{accountNumber}")
	public Contract getContract(@PathVariable final int accountNumber){
		Contract contract=new Contract();
		contract.setName("philp");
		contract.setId(accountNumber);
		return contract;
	}
	
	@RequestMapping(value="/svc/v1/private/accounts/{accountNumber}")
	public Contract getAdminContract(@PathVariable final int accountNumber){
		Contract contract=new Contract();
		contract.setName("ADMIN");
		contract.setId(accountNumber);
		return contract;
	}
	
	@Autowired
    CustomerRepository repository;
     
    @RequestMapping(value="/save",  method=RequestMethod.GET)
    public String process(){
        repository.save(new Customer("Jack", "Smith"));
        repository.save(new Customer("Adam", "Johnson"));
        repository.save(new Customer("Kim", "Smith"));
        repository.save(new Customer("David", "Williams"));
        repository.save(new Customer("Peter", "Davis"));
        return "Done";
    }
     
    @RequestMapping(value="/save", method=RequestMethod.POST, consumes={"application/json","application/xml"})
    public String processPost(@RequestBody Customer input){
        repository.save(input);

        return "Post Done";
    }
     
    @RequestMapping("/findall")
    public String findAll(){
        String result = "<html>";
         
        for(Customer cust : repository.findAll()){
            result += "<div>" + cust.toString() + "</div";
        }
         
        return result + "</html>";
    }
     
    @RequestMapping("/findbyid")
    public String findById(@RequestParam("id") long id){
        String result = "";
        result = repository.findOne(id).toString();
        return result;
    }
     
    @RequestMapping("/findbylastname")
    public String fetchDataByLastName(@RequestParam("lastname") String lastName){
        String result = "<html>";
         
        for(Customer cust: repository.findByLastName(lastName)){
            result += "<div>" + cust.toString() + "</div>"; 
        }
         
        return result + "</html>";
    }

}
