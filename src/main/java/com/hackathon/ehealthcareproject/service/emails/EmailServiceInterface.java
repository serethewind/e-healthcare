package com.hackathon.ehealthcareproject.service.emails;

import com.hackathon.ehealthcareproject.dto.email.EmailDetails;

public interface EmailServiceInterface {

    String sendSimpleMessage(EmailDetails emailDetails);

    String sendMessageWithAttachment(EmailDetails emailDetails);
}
