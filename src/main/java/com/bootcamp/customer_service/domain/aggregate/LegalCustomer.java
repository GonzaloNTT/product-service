package com.bootcamp.customer_service.domain.aggregate;

import com.bootcamp.customer_service.domain.enums.LegalCustomerType;
import com.bootcamp.customer_service.domain.valueobject.CustomerId;
import com.bootcamp.customer_service.domain.valueobject.Document;
import com.bootcamp.customer_service.domain.valueobject.Email;
import com.bootcamp.customer_service.domain.valueobject.Phone;
import lombok.Getter;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
public class LegalCustomer extends Customer {

    private final LegalCustomerType type;
    private final Set<CustomerId> representatives;

    public LegalCustomer(CustomerId id,
                         Document document,
                         Email email,
                         Phone phone,
                         String password,
                         LegalCustomerType type,
                         Set<CustomerId> representatives) {
        super(id, document, email, phone, password);
        if (representatives.isEmpty()) throw new IllegalArgumentException("Debe tener al menos un representante");
        this.type = Objects.requireNonNull(type);
        this.representatives = Objects.requireNonNull(representatives);
    }

    public void addRepresentative(CustomerId newRep) {
        representatives.add(newRep);
    }

}
