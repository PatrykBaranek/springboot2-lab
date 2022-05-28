package com.example.lab3.User;


import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("api/users")
    @ResponseBody
    public UserShowSettings getUsers(
            @RequestParam(defaultValue = "1", required = false) int pageNumber,
            @RequestParam(defaultValue = "2", required = false) int pageSize,
            @RequestParam(defaultValue = "10",required = false) int pagesCount,
            @RequestParam(defaultValue = "95",required = false) int totalCount){

        UserShowSettings userShowSettings = userService.getUsers(pageNumber, pageSize, pagesCount, totalCount);

        return userShowSettings;
    }

    @RequestMapping(
            value = "api/user/create",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE

    )
    @ResponseBody
    public UserEntity createUser(@RequestBody UserEntity user){

         UserEntity newUser = userService.createUser(user);

        return newUser;
    }

    @GetMapping("api/users/{id}")
    @ResponseBody
    public UserEntity getUser(@PathVariable int id){

        UserEntity user = userService.getUser(id);

        return user;
    }

    @RequestMapping(
            value = "api/users/{id}/update",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public UserEntity updateUser(
            @PathVariable int id,
            @RequestBody UserEntity userUpdatedValues
            ){

        UserEntity user = userService.updateUser(id, userUpdatedValues);

        return user;
    }

    @DeleteMapping("api/users/{id}/delete")
    @ResponseBody
    public DeleteResult deleteUser(
            @PathVariable int id
    ){

        DeleteResult deletedResult = userService.deleteUser(id);

        return deletedResult;
    }

}
