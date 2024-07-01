package com.example.demo.service.impl;

import com.example.demo.Repository.UserRepository;
import com.example.demo.model.GetUsersResponse;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    public List<GetUsersResponse> getUsers() {
        List<GetUsersResponse> responses = new ArrayList<>();
        userRepository.findAll().forEach(user -> {
            responses.add(new GetUsersResponse(user.getName()));
        });
        return responses;
    }
}
