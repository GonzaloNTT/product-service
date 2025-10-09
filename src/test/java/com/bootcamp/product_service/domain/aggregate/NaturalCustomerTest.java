package com.bootcamp.product_service.domain.aggregate;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NaturalCustomerTest {
    @Test
    @Disabled
    void shouldCreateNaturalCustomerSuccessfully() {
        /*DocumentoIdentificacion document = new DocumentoIdentificacion(TipoDocumento.valueOf("DNI"), "12345678");
        Correo email = new Correo("user@example.com");
        Telefono phone = new Telefono("987654321", "356938035643809");
        DatosUsuario datos = new DatosUsuario(document, phone, email);

        ClienteNatural customer = new ClienteNatural(
                datos,
                TipoClienteNatural.NORMAL
        );

        assertNotNull(customer);
        assertEquals("user@example.com", customer.getUsuario().correo().value());
        assertEquals(TipoClienteNatural.NORMAL, customer.getTipo());*/
    }

    @Test
    @Disabled
    void shouldThrowExceptionWhenTypeIsNull() {
        /*DocumentoIdentificacion document = new DocumentoIdentificacion(TipoDocumento.valueOf("DNI"), "12345678");
        Correo email = new Correo("user@example.com");
        Telefono phone = new Telefono("987654321", "356938035643809");
        DatosUsuario datos = new DatosUsuario(document, phone, email);

        assertThrows(NullPointerException.class, () ->
                new ClienteNatural(
                        datos,
                        TipoClienteNatural.NORMAL
                )
        );*/
    }
}