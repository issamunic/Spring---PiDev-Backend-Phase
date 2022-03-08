package tn.esprit.spring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
	
	@Autowired
	JwtRequestFilter JwtRequestFilter;
	
	@GetMapping("/forAdmin")
	@PreAuthorize("hasRole('admin')")
	public String forAdmin() {
		return "hello admin : "+JwtRequestFilter.getCurrentUser().getLogin();
	}
	
	@GetMapping("/forEmploye")
	@PreAuthorize("hasRole('employe')")
	public String forEmploye() {
		return "hello employe";
	}
	
	@GetMapping("/forCompany")
	@PreAuthorize("hasRole('company')")
	public String forCompany() {
		return "hello company";
	}
	
	@GetMapping("/forAdminEmploye")
	@PreAuthorize("hasAnyRole('company','admin')")
	public String forCompanyAndAdmin() {
		return "hello company , admin";
	}
}
