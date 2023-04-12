package com.inside.hacaton_04_2023.api;

import com.inside.hacaton_04_2023.dao.LoginAndPasswordDAO;
import com.inside.hacaton_04_2023.dao.UserDAO;
import com.inside.hacaton_04_2023.dao.UserDopDataDAO;
import com.inside.hacaton_04_2023.dao.UserSkillsDAO;
import com.inside.hacaton_04_2023.entity.LoginAndPassword;
import com.inside.hacaton_04_2023.entity.User;
import com.inside.hacaton_04_2023.entity.UserDopData;
import com.inside.hacaton_04_2023.entity.UserSkills;
import com.inside.hacaton_04_2023.restClasses.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/auth", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthorizationController {

    private LoginAndPasswordDAO loginAndPasswordDAO;
    private UserDAO userDAO;
    private UserDopDataDAO userDopDataDAO;
    private UserSkillsDAO userSkillsDAO;

    @Autowired
    public void setLoginAndPasswordDAO(LoginAndPasswordDAO loginAndPasswordDAO) {
        this.loginAndPasswordDAO = loginAndPasswordDAO;
    }

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Autowired
    public void setUserDopDataDAO(UserDopDataDAO userDopDataDAO) { this.userDopDataDAO = userDopDataDAO; }

    @Autowired
    public void setUserSkillsDAO(UserSkillsDAO userSkillsDAO) { this.userSkillsDAO = userSkillsDAO; }

    @CrossOrigin
    @PostMapping("/check_auth")
    public Object postCheckAuth(@RequestBody AuthRequest request) {
        LoginAndPassword loginAndPassword = loginAndPasswordDAO.getLoginAndPasswordByLoginAndPassword(request.getLogin(), request.getPassword());

        if(loginAndPassword != null) {
            return userDAO.getUserByLoginAndPassword(loginAndPassword);
        }

        return new ErrorRequest("invalid username or password");
    }

    @CrossOrigin
    @PostMapping("/create_user")
    public Object postCreateUser(@RequestBody CreateUserRequest request) {

        User newUser = new User();
        newUser.setName(request.getName());
        newUser.setEmail(request.getEmail());
        newUser.setNumberPhone(request.getNumberPhone());
        newUser.setEmployer(request.isEmployer());

        LoginAndPassword checkLoginAndPassword = loginAndPasswordDAO.getLoginAndPasswordByLogin(request.getLogin());
        if(checkLoginAndPassword != null) {
            return new ErrorRequest("A user with this username already exists");
        }

        LoginAndPassword loginAndPassword = new LoginAndPassword();
        loginAndPassword.setLogin(request.getLogin());
        loginAndPassword.setPassword(request.getPassword());
        loginAndPasswordDAO.save(loginAndPassword);

        newUser.setLoginAndPassword(loginAndPassword);

        userDAO.save(newUser);

        return newUser;
    }

    @CrossOrigin
    @PostMapping("/get_user_dop_data")
    public Object getUserDopData(@RequestBody int id) {

        User user = userDAO.getUserById(id);

        if(user != null) {

            UserDopData userDopData = userDopDataDAO.getUserDopDataByUser(user);
            List<UserSkills> list = userSkillsDAO.findUserSkillsByUserDopData(userDopData);
            userDopData.userSkills = new ArrayList<>();

            for(int i = 0; i < list.size(); i++) {
                userDopData.userSkills.add(new UserSkillsResponse(list.get(i).getName()));
            }

            return userDopData;
        }

        return new ErrorRequest("User not found");
    }


    @CrossOrigin
    @PostMapping("/update_user")
    public Object postUpdateUser(@RequestBody UpdateUserRequest request) {

        User user = userDAO.getUserById(request.getId());

        if(user != null) {

            UserDopData userDopData = userDopDataDAO.getUserDopDataByUser(user);

            if(userDopData == null) {
                userDopData = new UserDopData();
                userDopDataDAO.save(userDopData);
            }

            userDopData.setPost(request.getPost());
            userDopData.setLocation(request.getLocation());
            userDopData.setUser(user);
            userDopData.setImg(request.getImg());
            userDopDataDAO.save(userDopData);

            List<UserSkills> list = userSkillsDAO.findUserSkillsByUserDopData(userDopData);

            if(list != null) {
                for(int i = 0; i < list.size(); i++) {
                    userSkillsDAO.delete(list.get(i));
                }
            }

            for(int i = 0; i < request.getUserSkills().size(); i++) {
                UserSkills userSkill = new UserSkills();
                userSkill.setName(request.getUserSkills().get(i));
                userSkill.setUserDopData(userDopData);
                userSkillsDAO.save(userSkill);
            }

            user.setName(request.getName());
            user.setEmail(request.getEmail());
            user.setNumberPhone(request.getNumberPhone());
            user.setEmployer(request.isEmployer());
            user.getLoginAndPassword().setLogin(request.getLogin());
            user.getLoginAndPassword().setPassword(request.getPassword());

            userDAO.save(user);


            return user;
        }

        return new ErrorRequest("User not found");
    }

}
