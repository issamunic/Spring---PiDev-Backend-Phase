package tn.esprit.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import tn.esprit.spring.entities.CodeInvitationCompany;
import tn.esprit.spring.serviceInterface.ICodeInvitationCompanyService;

@RestController
@Api(tags = "CodeInvitationCompany Manager")
@RequestMapping("/CodeInvitationCompany")
public class CodeInvitationCompanyRestController {
	
	@Autowired
	ICodeInvitationCompanyService CodeInvitationCompanyService;
    //http://localhost:8087/SpringMVC/swagger-ui/index.html
	
	
	@ApiOperation(value = "getAllCodeInvitationCompanys")
	@GetMapping("/getAll")
	@ResponseBody
	public List<CodeInvitationCompany> getAll() {
		return CodeInvitationCompanyService.getAll();
	}
	
	
	@ApiOperation(value = "addCodeInvitationCompany")
	@PostMapping("/add")
	@ResponseBody
	public CodeInvitationCompany add(@RequestBody CodeInvitationCompany CodeInvitationCompany) {
		return CodeInvitationCompanyService.add(CodeInvitationCompany);
	}
	
	@ApiOperation(value = "getById")
	@GetMapping("/getById/{CodeInvitationCompany-id}")
	@ResponseBody
	public CodeInvitationCompany getById(@PathVariable("CodeInvitationCompany-id") Long id) {
		System.out.println("********************"+id+"**********");
		return CodeInvitationCompanyService.getById(id);
	}
	
	@ApiOperation(value = "updateCodeInvitationCompany")
	@PutMapping("/update")
	@ResponseBody
	public CodeInvitationCompany update(@RequestBody CodeInvitationCompany CodeInvitationCompany) {
		return CodeInvitationCompanyService.update(CodeInvitationCompany);
	}
	
	
	@ApiOperation(value = "deleteCodeInvitationCompany")
	@DeleteMapping("/delete/{CodeInvitationCompany-id}")
	@ResponseBody
	public void delete(@PathVariable("CodeInvitationCompany-id") Long id) {
		CodeInvitationCompanyService.delete(id);
	}
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@ApiOperation(value = "addCodeInvitationCompanyFinal")
	@PostMapping("/addFinal")
	@ResponseBody
	public CodeInvitationCompany addFinal(@RequestBody CodeInvitationCompany CodeInvitationCompany) {
		return CodeInvitationCompanyService.addFinal(CodeInvitationCompany);
	}
	
	@ApiOperation(value = "getByCode")
	@GetMapping("/getByCode/{CodeInvitationCompany}")
	@ResponseBody
	public CodeInvitationCompany getById(@PathVariable("CodeInvitationCompany") String CodeInvitationCompany) {
		return CodeInvitationCompanyService.getByCode(CodeInvitationCompany);
	}
	
	
}
