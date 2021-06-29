package com.example.h2demo.Controller;

import com.example.h2demo.Domain.Customer;
import com.example.h2demo.Repository.CustomerRepository;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerController {

    private final CustomerRepository repository;

    public CustomerController(CustomerRepository repository){
        this.repository = repository;
    }

    /*
    localhost:8080/create

    {
        "firstName": "John",
        "lastName": "Doe"
    }

    {
        "firstName": "John2",
        "lastName": "Doe2"
    }
    */

    @PostMapping("/create")
    public Customer create(@RequestBody Customer customer) {
        return this.repository.save(customer);
    }

    //localhost:8080/list
    @GetMapping("/list")
    public Iterable<Customer> list() {
        return this.repository.findAll();
    }

    //localhost:8080/read/lastname/Doe
    @GetMapping("/read/lastname/{lastName}")
    public Iterable<Customer> read(@PathVariable String lastName) {
        return this.repository.findByLastName(lastName);
    }
    /*
    localhost:8080/delete

    {
        "id": 1
    }
    */

    @DeleteMapping("delete")
    public void delete(@RequestBody Customer customer) {
        this.repository.delete(customer);
    }
    /*
    //localhost:8080/update/id/1
        {
            "firstName": "John3",
            "lastName": "Doe3"
        }
    */
    @PatchMapping("/update/id/{id}")
    public void patchUpdate(@RequestBody Customer customerInput,@PathVariable Long id) {
        Customer customer = this.repository.findById(id).get();
        customer.setFirstName(customerInput.getFirstName());
        customer.setLastName(customerInput.getLastName());
        this.repository.save(customer);
    }

    /*
    //localhost:8080/update/id/1
        {
            "firstName": "John4",
            "lastName": "Doe4"
        }
    */
    @PutMapping("/update/id/{id}")
    public void putUpdate(@RequestBody Customer customerInput,@PathVariable Long id) {
        Customer customer = this.repository.findById(id).get();
        customer.setFirstName(customerInput.getFirstName());
        customer.setLastName(customerInput.getLastName());
        this.repository.save(customer);
    }

}
