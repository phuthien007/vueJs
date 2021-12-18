package com.backend.controller;


import com.backend.config.jwt.JwtUtil;
import com.backend.dto.AuthRequest;
import com.backend.dto.UserDto;
import com.backend.entity.CustomerEntity;
import com.backend.entity.UserEntity;
import com.backend.exception.BadRequestException;
import com.backend.mapper.UserMapper;
import com.backend.service.CustomerService;
import com.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(allowedHeaders = "*", exposedHeaders = "*")
@RequestMapping(path = "/api/")
public class UserController {

    private final UserService UserSer;
    private final UserMapper UserMapper;
    private final BCryptPasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public UserController(UserService UserSer, UserMapper UserMapper,
              BCryptPasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.UserSer = UserSer;
        this.UserMapper = UserMapper;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    // get all element in User
    @GetMapping("user")
    public List<UserDto> findAll(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            HttpServletResponse response
    ) {
        Page<UserEntity> results = UserSer.getAllData(PageRequest.of(page, 10));
        response.addHeader("totalElements", String.valueOf(results.getTotalElements()));
        response.addHeader("totalPages", String.valueOf(results.getTotalPages()));
        response.addHeader("page", String.valueOf(results.getNumber()));
        response.addHeader("numberOfElements", String.valueOf(results.getNumberOfElements()));
        return results.stream().map(UserEntity -> UserMapper.toDTO(UserEntity))
                .collect(Collectors.toList());
    }

    // get element by id
    @GetMapping("user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto findDataById(@PathVariable Long id) {
        return UserMapper.toDTO(UserSer.findDataById(id));
    }

    // add a new element

    @Autowired
    private CustomerService CustomerSer;


    private CustomerEntity addNewData(CustomerEntity Customer) {
        if (Customer.getId() != null) {
            Customer.setId(null);
        }
        if (Customer.getName() == null || Customer.getAddress() == null
                || Customer.getAddress() == null || Customer.getPhone() == null) {
            throw new BadRequestException("Invalid Input, value missing");
        }
        return CustomerSer.addNewData(Customer);
    }



    @PostMapping("user")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto addNewData(@Validated @RequestBody UserEntity User) {
        if (User.getId() != null) {
            User.setId(null);
        }
        if (User.getUsername() == null || User.getEmail() == null
            || User.getPassword() == null || User.getCustomer() == null) {
            throw new BadRequestException("Invalid Input, value missing");
        }
        User.setPassword(passwordEncoder.encode(User.getPassword()));
        CustomerEntity c = addNewData(User.getCustomer());
        User.setCustomer(c);
        return UserMapper.toDTO(UserSer.addNewData(User));
    }

    // update a element with id
    @PutMapping("user/{id}")
    public UserDto updateData(@PathVariable Long id, @Validated @RequestBody UserDto User) {
        return UserMapper.toDTO(UserSer.updateData(id, UserMapper.toEntity(User)));
    }

    // delete a element by id
    @DeleteMapping("user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Boolean> deleteData(@PathVariable Long id) {
        return UserSer.deleteData(id);
    }

    // login

    @PostMapping("login")
    public String login(@Validated @RequestBody AuthRequest authRequest){
        try{
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
            UserDetails userDetails = UserSer.loadUserByUsername(authRequest.getUsername());
            if(userDetails == null){
                throw new BadRequestException("Username: "+ authRequest.getUsername() + " was not exist");
            }
            String token = jwtUtil.generateToken(authRequest.getUsername());
            return "Bearer "+ token;

        }
        catch (Exception e){
            throw new BadRequestException(e.getMessage());
        }
    }
}
