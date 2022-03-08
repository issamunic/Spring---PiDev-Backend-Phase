package tn.esprit.spring.twillo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/sms")
public class SmsController {
	private final SmsService smsService;
    @Autowired
    public SmsController(SmsService smsService) {
        this.smsService = smsService;
    }
    @PostMapping
    public void sendSms(@RequestBody SmsRequest smsRequest) {
        smsService.sendSms(smsRequest);
    }
}
