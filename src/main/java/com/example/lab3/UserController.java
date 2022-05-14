package com.example.lab3;


import com.example.lab3.models.UserEntity;
import com.example.lab3.models.UserShowSettings;
import org.springframework.beans.factory.annotation.Autowired;
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

        UserShowSettings userShowSettings = userService.getUsers(pageNumber,pageSize, pagesCount, totalCount);

        return userShowSettings;
    }

    @RequestMapping(
            value = "api/user/create",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE

    )
    @ResponseBody
    public UserEntity createUser(@RequestBody(required = false) UserEntity user){

        userService.createUser(user);

        return user;
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

        UserEntity user = userList.get(id);

        if(userUpdatedValues.getEmail() != null && userUpdatedValues.getName() != null){
            return null;
        }
        user.setId(user.getId());
        user.setName(userUpdatedValues.getName());
        user.setEmail(userUpdatedValues.getEmail());

        return user;
    }

}
