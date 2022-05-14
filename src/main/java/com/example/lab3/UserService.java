package com.example.lab3;

import com.example.lab3.models.UserEntity;
import com.example.lab3.models.UserShowSettings;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;


@Service
public class UserService {

    @PostConstruct
    private void onCreate(){

    }

    @PreDestroy
    private void onDestroy(){

    }


    private List<UserEntity> userList = new ArrayList<UserEntity>();

    public UserShowSettings getUsers(int pageNumber, int pagesCount, int pageSize, int totalCount){

        UserShowSettings userShowSettings = new UserShowSettings(pageNumber, pagesCount, pageSize, totalCount, userList);

        return userShowSettings;
    }

    public UserEntity createUser(UserEntity user){
        userList.add(user);

        return user;
    }

    public UserEntity getUser(int id){
        if(!userList.contains(id)){
            return null;
        }
        UserEntity user = userList.get(id);

        return user;
    }



}
