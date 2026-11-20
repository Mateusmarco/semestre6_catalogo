package com.tads20262.catalago.resource;


import com.tads20262.catalago.dto.UserDTO;
import com.tads20262.catalago.dto.UserMapper;
import com.tads20262.catalago.entity.User;

import com.tads20262.catalago.resource.exceptions.DuplicateTupleException;
import com.tads20262.catalago.service.UserService;
import com.tads20262.catalago.serviceinterterface.User_Interface_Service;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/Users")
@RequiredArgsConstructor
public class UserResource {
private final User_Interface_Service userService;
private final UserMapper userMapper;

public ResponseEntity save(@RequestBody UserDTO dto){
    try{
        User user = userMapper.mapToUser(dto);
        userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    } catch (DuplicateTupleException e) {
        Map<String, String> jsonResultado = Map.of("error",e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(jsonResultado);
    }
}


}
