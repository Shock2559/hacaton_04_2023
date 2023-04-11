package com.inside.hacaton_04_2023.api;

import com.inside.hacaton_04_2023.dao.LoginAndPasswordDAO;
import com.inside.hacaton_04_2023.dao.UserDAO;
import com.inside.hacaton_04_2023.entity.LoginAndPassword;
import com.inside.hacaton_04_2023.entity.User;
import com.inside.hacaton_04_2023.restClasses.AuthRequest;
import com.inside.hacaton_04_2023.restClasses.CreateUserRequest;
import com.inside.hacaton_04_2023.restClasses.ErrorRequest;
import com.inside.hacaton_04_2023.restClasses.UpdateUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/auth", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthorizationController {

    private LoginAndPasswordDAO loginAndPasswordDAO;
    private UserDAO userDAO;

    @Autowired
    public void setLoginAndPasswordDAO(LoginAndPasswordDAO loginAndPasswordDAO) {
        this.loginAndPasswordDAO = loginAndPasswordDAO;
    }

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

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
    @PostMapping("/update_user")
    public Object postUpdateUser(@RequestBody UpdateUserRequest request) {

        User user = userDAO.getUserById(request.getId());

        if(user != null) {
            user.setName(request.getName());
            user.setEmail(request.getEmail());
            user.setNumberPhone(request.getNumberPhone());
            user.setEmployer(request.isEmployer());
            user.getLoginAndPassword().setLogin(request.getLogin());
            user.getLoginAndPassword().setPassword(request.getPassword());

            return user;
        }

        return new ErrorRequest("User not found");
    }

}
