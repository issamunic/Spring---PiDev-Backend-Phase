package tn.esprit.spring.twillo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.twilio.Twilio;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class TwilioInitializer {
	private final static Logger LOGGER = LoggerFactory.getLogger(TwilioInitializer.class);
    private final TwilioConfiguration twilioConfiguration;
    @Autowired
    public TwilioInitializer(TwilioConfiguration twilioConfiguration) {
        this.twilioConfiguration = twilioConfiguration;
        Twilio.init(
                twilioConfiguration.getAccountSid(),
                twilioConfiguration.getAuthToken()
        );
        LOGGER.info("Twilio initialized ... with account sid {} ", twilioConfiguration.getAccountSid());
    }
}
