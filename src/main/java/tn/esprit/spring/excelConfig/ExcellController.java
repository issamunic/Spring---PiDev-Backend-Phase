package tn.esprit.spring.excelConfig;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entities.User;
import tn.esprit.spring.serviceInterface.IUserService;

@RestController
@CrossOrigin
public class ExcellController {
	
	@Autowired
	IUserService userService;
	
	@GetMapping("/exportUsersExcel")
    public String exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=listUsers_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);
         
        List<User> listUsers = userService.retrieveAllUsers();
         
        UserExcelExporter excelExporter = new UserExcelExporter(listUsers);
         
        excelExporter.export(response); 
        
        return "data exported excel";
    }  
}
