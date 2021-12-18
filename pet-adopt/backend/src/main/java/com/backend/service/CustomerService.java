package com.backend.service;

import com.backend.entity.CustomerEntity;
import com.backend.exception.BadRequestException;
import com.backend.repository.CustomerRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CustomerService {

    private final CustomerRepository customerRep;

    public CustomerService(CustomerRepository customerRep) {
        this.customerRep = customerRep;
    }

    @Cacheable(cacheNames = "customer")
    public Page<CustomerEntity> getAllData(Pageable pageable) {
        return customerRep.findAll(pageable);
    }

    @Cacheable(cacheNames = "customer")
    public CustomerEntity findDataById(Long id) throws BadRequestException {
        return customerRep.findById(id).
                orElseThrow(() -> new BadRequestException("Data not found by ID: " + id));
    }

    public CustomerEntity addNewData(CustomerEntity customer) {
        try {
            return customerRep.save(customer);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @CachePut(cacheNames = "customer")
    public CustomerEntity updateData(Long id, CustomerEntity newCustomer) {
        try {
            CustomerEntity oldCustomer = customerRep.getById(id);
            if (newCustomer.getName() != null) {
                oldCustomer.setName(newCustomer.getName());
            }
            if (newCustomer.getAddress() != null) {
                oldCustomer.setAddress(newCustomer.getAddress());
            }
            if (newCustomer.getPhone() != null) {
                oldCustomer.setPhone(newCustomer.getPhone());
            }

            return customerRep.save(oldCustomer);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @CacheEvict(cacheNames = "customer", allEntries = true)
    public Map<String, Boolean> deleteData(Long id) {
        try {
            Map<String, Boolean> result = new HashMap<>();
            customerRep.deleteById(id);
            result.put("Deleted", Boolean.TRUE);
            return result;
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

}
