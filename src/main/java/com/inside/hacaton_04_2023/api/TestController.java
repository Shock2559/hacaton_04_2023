package com.inside.hacaton_04_2023.api;

import com.inside.hacaton_04_2023.dao.TestDAO;
import com.inside.hacaton_04_2023.entity.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api/test", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class TestController {

    private TestDAO testDAO;

    @Autowired
    public void setTestDAO(TestDAO testDAO) {this.testDAO = testDAO;}

    @CrossOrigin
    @GetMapping("/all_test")
    public Iterable<Test> getAllTest() {
        Iterable<Test> response = testDAO.findAll();

        return response;
    }

    @CrossOrigin
    @PostMapping("/test_by_id")
    public Test getTestById(@RequestBody int id) {
        Test response = testDAO.myTestById(id);

        return response;
    }

}
