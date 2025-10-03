package com.bootcamp.customer_service.application.dto;

import lombok.*;
import jakarta.validation.constraints.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NonCustomerRequest {

    @NotBlank
    private String name;

    @Email
    private String email;

    @NotBlank
    private String phone;

    @NotBlank
    private String documentType; // DNI, CEX, PASSPORT

    @NotBlank
    private String documentNumber;

    private String imei; // obligatorio para Yanki

    @NotBlank
    private String productType; // YANKI, BOOTCOIN
}
