package com.backend.service;

import com.backend.entity.UserEntity;
import com.backend.exception.BadRequestException;
import com.backend.repository.UserRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository UserRep;
    private final CustomerService customerService;

    public UserService(UserRepository UserRep, CustomerService customerService) {
        this.UserRep = UserRep;
        this.customerService = customerService;
    }

    @Cacheable(cacheNames = "user")
    public Page<UserEntity> getAllData(Pageable pageable) {
        return UserRep.findAll(pageable);
    }

    @Cacheable(cacheNames = "user")
    public UserEntity findDataById(Long id) throws BadRequestException {
        return UserRep.findById(id).
                orElseThrow(() -> new BadRequestException("Data not found by ID: " + id));
    }

    public UserEntity addNewData(UserEntity User) {
        try {
            return UserRep.save(User);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @CachePut(cacheNames = "user")
    public UserEntity updateData(Long id, UserEntity newUser) {
        try {
            UserEntity oldUser = UserRep.getById(id);
            if (newUser.getCustomer() != null) {
                oldUser.setCustomer(newUser.getCustomer());
            }
            if (newUser.getPassword() != null) {
                oldUser.setPassword(newUser.getPassword());
            }
            if (newUser.getForgotPasswordToken() != null) {
                oldUser.setForgotPasswordToken(newUser.getForgotPasswordToken());
            }
            return UserRep.save(oldUser);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @CacheEvict(cacheNames = "user", allEntries = true)
    public Map<String, Boolean> deleteData(Long id) {
        try {
            Map<String, Boolean> result = new HashMap<>();
            UserRep.deleteById(id);
            result.put("Deleted", Boolean.TRUE);
            return result;
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = UserRep.findByUsername(username);
        return  new UserDetailsImpl(user);
    }
}
