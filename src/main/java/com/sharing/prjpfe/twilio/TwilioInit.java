package com.sharing.prjpfe.twilio;

import com.twilio.Twilio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TwilioInit {
    private TwilioConfig twilioConfig;
    @Autowired
    public TwilioInit(TwilioConfig twilioConfig) {
        this.twilioConfig = twilioConfig;
        Twilio.init(twilioConfig.getAccountSid(),twilioConfig.getAuthToken());
    }
}

