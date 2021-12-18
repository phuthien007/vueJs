package com.backend.controller;

import com.backend.dto.CustomerDto;
import com.backend.entity.CustomerEntity;
import com.backend.exception.BadRequestException;
import com.backend.mapper.CustomerMapper;
import com.backend.service.CustomerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(allowedHeaders = "*", exposedHeaders = "*")
@RequestMapping(path = "/api/")
public class CustomerController {

    private final CustomerService CustomerSer;
    private final CustomerMapper CustomerMapper;

    public CustomerController(CustomerService CustomerSer, CustomerMapper CustomerMapper) {
        this.CustomerSer = CustomerSer;
        this.CustomerMapper = CustomerMapper;
    }

    // get all element in Customer
    @GetMapping("customer")
    public List<CustomerDto> findAll(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            HttpServletResponse response
    ) {
        Page<CustomerEntity> results = CustomerSer.getAllData(PageRequest.of(page, 10));
        response.addHeader("totalElements", String.valueOf(results.getTotalElements()));
        response.addHeader("totalPages", String.valueOf(results.getTotalPages()));
        response.addHeader("page", String.valueOf(results.getNumber()));
        response.addHeader("numberOfElements", String.valueOf(results.getNumberOfElements()));
        return results.stream().map(CustomerEntity -> CustomerMapper.toDTO(CustomerEntity))
                .collect(Collectors.toList());
    }

    // get element by id
    @GetMapping("customer/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDto findDataById(@PathVariable Long id) {
        return CustomerMapper.toDTO(CustomerSer.findDataById(id));
    }

    // add a new element
//    @PostMapping("customer")
//    @ResponseBody
//    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDto addNewData(@Validated @RequestBody CustomerDto Customer) {
        if (Customer.getId() != null) {
            Customer.setId(null);
        }
        if (Customer.getName() == null || Customer.getAddress() == null
                || Customer.getAddress() == null || Customer.getPhone() == null) {
            throw new BadRequestException("Invalid Input, value missing");
        }
        return CustomerMapper.toDTO(CustomerSer.addNewData(CustomerMapper.toEntity(Customer)));
    }

    // update a element with id
    @PutMapping("customer/{id}")
    public CustomerDto updateData(@PathVariable Long id, @Validated @RequestBody CustomerDto Customer) {
        return CustomerMapper.toDTO(CustomerSer.updateData(id, CustomerMapper.toEntity(Customer)));
    }

    // delete a element by id
    @DeleteMapping("customer/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Boolean> deleteData(@PathVariable Long id) {
        return CustomerSer.deleteData(id);
    }

}
