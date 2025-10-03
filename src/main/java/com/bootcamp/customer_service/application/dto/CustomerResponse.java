package com.bootcamp.customer_service.application.dto;

import lombok.*;
import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponse {

    private String id;
    private String name;
    private String email;
    private String phone;
    private String documentType;
    private String documentNumber;
    private String customerType;
    private String personalType;
    private String businessType;
    private Instant createdAt;
}
