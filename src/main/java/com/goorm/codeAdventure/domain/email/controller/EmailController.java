package com.goorm.codeAdventure.domain.email.controller;

import com.goorm.codeAdventure.domain.email.entity.EmailMessage;
import com.goorm.codeAdventure.domain.email.service.EmailService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;

    @PostMapping("/send-mail")
    @Operation(summary = "이메일 전송 API", description = "메일 메시지를 특정 사용자 이메일 주소로 전송합니다.")
    public ResponseEntity<EmailMessage> sendMail() {
        EmailMessage emailMessage = EmailMessage.builder()
                .to("cmwmss59@gmail.com")
                .subject("테스트 메일 제목")
                .message("""
                        <!DOCTYPE html>
                        <html lang="en" >
                        <head>
                            <meta charset="UTF-8">
                            <title>메일 테스트</title>
                        </head>
                        <body>
                        <div>
                            <p>안녕하세요, <span ></span>손님</p>
                            <p>메일 테스트입니다.</p>
                        </div>
                        <div>
                            <img src="gift.jpg" height="630" width="450" alt="이미지 오류"/>
                        </div>
                        </body>
                        </html>">테스트 메일 본문<div></body></html>""")
                .build();
        emailService.sendMail(emailMessage);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}