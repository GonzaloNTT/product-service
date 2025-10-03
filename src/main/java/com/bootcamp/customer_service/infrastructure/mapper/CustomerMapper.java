package com.bootcamp.customer_service.infrastructure.mapper;

import com.bootcamp.customer_service.application.dto.CustomerRequest;
import com.bootcamp.customer_service.domain.enums.LegalCustomerType;
import com.bootcamp.customer_service.domain.enums.NaturalCustomerType;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public NaturalCustomerCommand toNaturalCustomerCommand(CustomerRequest dto) {
        return new NaturalCustomerCommand(
                dto.getDocumentType(),
                dto.getDocumentNumber(),
                dto.getEmail(),
                dto.getPhone(),
                NaturalCustomerType.valueOf(dto.getPersonalType()),
                dto.getImei(),
                dto.getPassword()
        );
    }
    public LegalCustomerCommand toLegalCustomerCommand(CustomerRequest dto) {
        return new LegalCustomerCommand(
                dto.getDocumentType(),
                dto.getDocumentNumber(),
                dto.getEmail(),
                dto.getPhone(),
                LegalCustomerType.valueOf(dto.getBusinessType()),
                dto.getImei(),
                dto.getPassword(),
                dto.getRepresentatives()
        );
    }
}