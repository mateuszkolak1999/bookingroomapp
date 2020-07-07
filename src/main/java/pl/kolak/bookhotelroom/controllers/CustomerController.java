package pl.kolak.bookhotelroom.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.kolak.bookhotelroom.exceptions.CustomerDoNotAddedException;
import pl.kolak.bookhotelroom.exceptions.CustomerDoNotUpdateException;
import pl.kolak.bookhotelroom.models.Customer;
import pl.kolak.bookhotelroom.models.CustomerDetails;
import pl.kolak.bookhotelroom.services.CustomerService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerController {
    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customers/all")
    public List<Customer> customerList(){
        return customerService.customerList();
    }

    @GetMapping(value = "/customers")
    public List<Customer> bookingGet(@RequestParam(value = "page", defaultValue = "0") int page,
                                     @RequestParam(value = "limit", defaultValue = "30") int limit) {
        return customerService.findAll(page, limit);
    }

    @GetMapping("/customer/{id}")
    public Customer customer(@PathVariable (name = "id") long id){
        return customerService.customerById(id);
    }

    @PostMapping("/customer/add")
    public ResponseEntity<Customer> addCustomer(@RequestBody @Valid Customer customer, BindingResult result){
        if(!result.hasErrors()){
            CustomerDetails customerDetails = customer.getCustomerDetails();
            customerService.addCustomer(customer,customerDetails);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}")
                    .buildAndExpand(customer.getId()).toUri();
            return ResponseEntity.created(location).body(customer);
        }else{
            throw new CustomerDoNotAddedException();
        }
    }

    @DeleteMapping("/customer/{id}/delete")
    public void delete(@PathVariable(name = "id") Long id){
        customerService.delete(id);
    }

    @PutMapping("/customer/{id}/update")
    public void update(@PathVariable(name = "id") Long id,
                       @RequestBody @Valid Customer customer, BindingResult result){
        if(!result.hasErrors()){
            customerService.modify(id,customer);
        }else
            throw new CustomerDoNotUpdateException();
    }
}
