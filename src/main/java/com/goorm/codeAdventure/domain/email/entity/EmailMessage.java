package com.goorm.codeAdventure.domain.email.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmailMessage {
    private String to;//수신자
    private String subject;//메일의 제목
    private String message;//메일의 본문을 어떤식으로 보낼건지
}
