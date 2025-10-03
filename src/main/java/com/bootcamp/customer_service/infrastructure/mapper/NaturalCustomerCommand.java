package com.bootcamp.customer_service.infrastructure.mapper;

import com.bootcamp.customer_service.domain.enums.DocumentType;
import com.bootcamp.customer_service.domain.enums.LegalCustomerType;
import com.bootcamp.customer_service.domain.enums.NaturalCustomerType;
import com.bootcamp.customer_service.domain.valueobject.CustomerId;
import lombok.Getter;

import java.util.List;
import java.util.Set;

@Getter
public class NaturalCustomerCommand {
    private final String documentType;
    private final String documentNumber;
    private final String email;
    private final String phone;
    private final NaturalCustomerType type;
    private final String imei;
    private final String password;

    public NaturalCustomerCommand(String documentType,
                                        String documentNumber,
                                        String email,
                                        String phone,
                                        NaturalCustomerType type,
                                        String imei, String password) {
        this.documentType = documentType;
        this.documentNumber = documentNumber;
        this.email = email;
        this.phone = phone;
        this.type = type;
        this.imei = imei;
        this.password = password;

    }
}