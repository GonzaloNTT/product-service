package com.bootcamp.customer_service.domain.aggregate;

import com.bootcamp.customer_service.domain.enums.NaturalCustomerType;
import com.bootcamp.customer_service.domain.valueobject.CustomerId;
import com.bootcamp.customer_service.domain.valueobject.Document;
import com.bootcamp.customer_service.domain.valueobject.Email;
import com.bootcamp.customer_service.domain.valueobject.Phone;
import lombok.Getter;

import java.util.Objects;

@Getter
public class NaturalCustomer extends Customer {

    private final NaturalCustomerType type;

    public NaturalCustomer(CustomerId id,
                           Document document,
                           Email email,
                           Phone phone,
                           NaturalCustomerType type,
                           String password) {
        super(id, document, email, phone, password);
        this.type = Objects.requireNonNull(type);
    }
}
