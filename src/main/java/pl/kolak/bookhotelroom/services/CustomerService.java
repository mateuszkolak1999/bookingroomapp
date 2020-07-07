package pl.kolak.bookhotelroom.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.kolak.bookhotelroom.models.Customer;
import pl.kolak.bookhotelroom.models.CustomerDetails;
import pl.kolak.bookhotelroom.repositories.CustomerRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> customerList(){
        return customerRepository.findAll();
    }

    public List<Customer> findAll(int page, int limit){
        PageRequest pageRequest = PageRequest.of(page, limit);
        Page<Customer> customers = customerRepository.findAll(pageRequest);
        return customers.getContent();
    }

    public Customer customerById(Long id){
        return customerRepository.findById(id).get();
    }

    @Transactional
    public void addCustomer(Customer customer, CustomerDetails customerDetails){
        customer.setCustomerDetails(customerDetails);
        customerRepository.save(customer);
    }

    @Transactional
    public void delete(Long id){
        Optional<Customer> customer = customerRepository.findById(id);
        customer.ifPresent(c -> customerRepository.delete(c));
    }

    @Transactional
    public void modify(Long id, Customer customer){
        customer.setId(id);
        Customer customerFind = customerRepository.findById(id).get();
        customer.getCustomerDetails().setId(customerFind.getCustomerDetails().getId());
        customerRepository.save(customer);
    }

}
