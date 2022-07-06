package com.project.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;

import com.project.entities.user;

import java.util.List;

@RestController
@RequestMapping(value = "/template")
public class controller {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final MongoTemplate mongoTemplate;

    public controller(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List < user > getAllUsers() {
        logger.info("Getting all Employees.");
        return mongoTemplate.findAll(user.class);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public List<user> getEmployee(@PathVariable("id") String emai) {
        logger.info("Getting user with ID: {}.", emai);
        Query query = new Query();
        query.addCriteria(Criteria.where("email").is(emai));
        List<user> users = mongoTemplate.find(query,user.class);
       // user userModel = mongoTemplate.findById(email, user.class);
        return users;
    }
    @RequestMapping(value = "/{id}/{id1}", method = RequestMethod.GET)
    public List<user> getEmployee(@PathVariable("id") String field,@PathVariable("id1") String em) {
        logger.info("Getting user with ID: {}.", field);
        Query query = new Query();
        query.addCriteria(Criteria.where(field+".bio").is(em));
        List<user> users = mongoTemplate.find(query,user.class);
       // user userModel = mongoTemplate.findById(email, user.class);
        return users;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public user add(@RequestBody user userModel) {
        logger.info("Saving Employee.");
        return mongoTemplate.save(userModel);
    }
}