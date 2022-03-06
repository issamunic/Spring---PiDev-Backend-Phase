package tn.esprit.spring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.serviceInterface.IUserService;

@RestController
@CrossOrigin
public class JwtController {

    @Autowired
    private JwtService jwtService;
    
    @Autowired
    IUserService userService;

    /*@PostMapping({"/authenticate"})
    public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        return jwtService.createJwtToken(jwtRequest);
    }*/
    
    @PostMapping({"/authenticate"})
    public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception {
    	String login=jwtRequest.getUserName();
    	if(userService.testActiveAccount(login)) {
    		return jwtService.createJwtToken(jwtRequest);
    	}
        return null;
    }
}