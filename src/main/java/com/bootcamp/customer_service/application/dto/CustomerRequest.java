package com.bootcamp.customer_service.application.dto;

import com.bootcamp.customer_service.domain.valueobject.CustomerId;
import lombok.*;
import jakarta.validation.constraints.*;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequest {

    @NotBlank
    private String name;

    @Email
    private String email;

    @NotBlank
    private String phone;

    @NotBlank
    private String imei;

    @NotBlank
    private String documentType; // DNI, CEX, PASSPORT

    @NotBlank
    private String documentNumber;

    @NotBlank
    private String customerType; // PERSONAL, BUSINESS

    private String personalType; // STANDARD, VIP
    private String businessType; // STANDARD, PYME

    @NotBlank
    private String password;

    private Set<CustomerId> representatives; // solo para BUSINESS
}
