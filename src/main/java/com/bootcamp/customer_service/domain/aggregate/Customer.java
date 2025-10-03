package com.bootcamp.customer_service.domain.aggregate;

import com.bootcamp.customer_service.domain.enums.CustomerType;
import com.bootcamp.customer_service.domain.valueobject.CustomerId;
import com.bootcamp.customer_service.domain.valueobject.Document;
import com.bootcamp.customer_service.domain.valueobject.Email;
import com.bootcamp.customer_service.domain.valueobject.Phone;
import lombok.Getter;
import java.util.*;

/**
 * 🔹 Root Aggregate: Customer
 */
@Getter
public abstract class Customer {
    protected final CustomerId id;
    protected final Document document;
    protected final Email email;
    protected final Phone phone;
    protected final String password;

    protected Customer(CustomerId id,
                       Document document,
                       Email email,
                       Phone phone,
                       String password) {
        this.id = Objects.requireNonNull(id);
        this.document = Objects.requireNonNull(document);
        this.email = Objects.requireNonNull(email);
        this.phone = Objects.requireNonNull(phone);
        this.password = Objects.requireNonNull(password);
    }

}
