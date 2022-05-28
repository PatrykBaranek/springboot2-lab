package com.example.lab3.User;

import com.example.lab3.User.UserEntity;
import com.example.lab3.User.UserShowSettings;
import org.apache.catalina.User;
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
        user.setId(userList.size() + 1);
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

    public UserEntity updateUser(int id, UserEntity userUpdatedValues){

        UserEntity user = userList.stream()
                .filter(userInList -> userInList.getId().equals(id))
                .findAny()
                .orElse(null);

        if(user == null){
            return null;
        }

        user.setName(userUpdatedValues.getName());
        user.setEmail(userUpdatedValues.getEmail());


        return user;
    }

    public DeleteResult deleteUser(int id){

        UserEntity userToDelete = userList.stream()
                .filter(u -> u.getId().equals(id))
                .findAny()
                .orElse(null);

        if(userToDelete == null){
            return new DeleteResult(false);
        }

        userList.remove(userToDelete);

        return new DeleteResult(true);
    }



}
