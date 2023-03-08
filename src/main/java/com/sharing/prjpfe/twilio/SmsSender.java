package com.sharing.prjpfe.twilio;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SmsSender {
    private TwilioConfig twilioConfig;
    @Autowired
    public SmsSender(TwilioConfig twilioConfig) {
        this.twilioConfig = twilioConfig;
    }
    public void sendSms(SmsRequest smsRequest){
        PhoneNumber to=new PhoneNumber(smsRequest.getPhoneNumber());
        PhoneNumber from =new PhoneNumber(twilioConfig.getTrialNumber());
        String message = smsRequest.getMessage();
        MessageCreator Creator = Message.creator(to,from,message);
        Creator.create();
    }
}
