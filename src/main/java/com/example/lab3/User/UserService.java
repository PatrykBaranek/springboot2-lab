package com.example.lab3.User;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


@Service
public class UserService {

    @PostConstruct
    private void onCreate(){
//        Type targetClassType = new TypeToken<List<UserEntity>>() {}.getType();
//        userList = new Gson().fromJson("usersJson/users.json", targetClassType);
    }

    @PreDestroy
    private void onDestroy() throws FileNotFoundException {
        String usersJsonToSave = new Gson().toJson(userList);

        File file = ResourceUtils.getFile("classpath:resources/static/users.json");
        try (FileOutputStream fos = new FileOutputStream(file)){
            PrintWriter pw = new PrintWriter(fos);
            pw.write(usersJsonToSave);
            pw.close();
        }catch (IOException e){
            e.printStackTrace();
        }
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
