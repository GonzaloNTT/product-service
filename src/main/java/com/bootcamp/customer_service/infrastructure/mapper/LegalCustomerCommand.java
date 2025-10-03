package com.bootcamp.customer_service.infrastructure.mapper;

import com.bootcamp.customer_service.domain.enums.LegalCustomerType;
import com.bootcamp.customer_service.domain.valueobject.CustomerId;
import lombok.Getter;

import java.util.Set;

@Getter
public class LegalCustomerCommand {
    private final String documentType;
    private final String documentNumber;
    private final String email;
    private final String phone;
    private final LegalCustomerType type;
    private final String imei;
    private final String password;
    private final Set<CustomerId> representatives;

    public LegalCustomerCommand(String documentType,
                                String documentNumber,
                                String email,
                                String phone,
                                LegalCustomerType type,
                                String imei, String password, Set<CustomerId> representatives) {
        this.documentType = documentType;
        this.documentNumber = documentNumber;
        this.email = email;
        this.phone = phone;
        this.type = type;
        this.imei = imei;
        this.password = password;
        this.representatives = representatives;
    }
}