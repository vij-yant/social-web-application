package com.project.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.entities.user;
import com.project.repo.UserRepository;

@Service
public class userServiceImp implements userService{
	    @Autowired
	    private UserRepository userRepo;
	   
	  
	    public ResponseObjectService adduser(user inputUser) {
	    	 ResponseObjectService responseObj = new ResponseObjectService();
	         Optional<user> optUser = userRepo.findByusername(inputUser.getUsername());
	         if (optUser.isPresent()) {
	             responseObj.setStatus("fail");
	             responseObj.setMessage("Email address " + inputUser.getEmail() + " existed");
	             responseObj.setPayload(null);
	             return responseObj;
	         } else {
	             inputUser.setPassword(inputUser.getPassword());
	             // user follows himself so he could get his posts in newsfeed as well
	             user user = userRepo.save(inputUser);
	             responseObj.setPayload(user);
	             responseObj.setStatus("success");
	             responseObj.setMessage("success");
	             return responseObj;
	         }
	    }

	    
	    
	    
	    

}
