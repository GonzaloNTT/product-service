package com.bootcamp.product_service.application.service.credit;

import com.bootcamp.product_service.application.mapper.command.CreditoCommand;
import com.bootcamp.product_service.application.port.out.CreditoRepositoryPort;
import com.bootcamp.product_service.domain.aggregate.credit.Credito;
import com.bootcamp.product_service.domain.enums.TipoCredito;
import com.bootcamp.product_service.domain.enums.TipoMoneda;
import com.bootcamp.product_service.domain.service.credit.CreditoService;
import com.bootcamp.product_service.domain.valueobject.DatosProducto;
import com.bootcamp.product_service.domain.valueobject.Dinero;
import com.bootcamp.product_service.infrastructure.cache.ProductReactiveCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@Slf4j
public class CreditoServiceImpl implements CreditoService {
    private final CreditoRepositoryPort repository;
    private final ProductReactiveCache cache;


    public CreditoServiceImpl(CreditoRepositoryPort repository, ProductReactiveCache cache) {
        this.repository = repository;
        this.cache = cache;
    }

    @Override
    public Mono<Credito> create(CreditoCommand command) {
        log.info("Creando crédito {}", command);
        return switch (command.tipoCredito()) {
            case PERSONAL -> validarCreditoPersonal(command)
                    .then(Mono.defer(() -> {
                        Credito credito = buildCredito(command);
                        return repository.save(credito);
                    }));

            case EMPRESARIAL -> {
                Credito credito = buildCredito(command);
                yield repository.save(credito);
            }
            default -> Mono.error(new IllegalArgumentException("Tipo de crédito no soportado: " + command.tipoCredito()));
        };
    }

    private Mono<Void> validarCreditoPersonal(CreditoCommand command) {
        return repository.findByClienteIdAndTipoCredito(command.clienteId(), TipoCredito.PERSONAL)
                .hasElements()
                .flatMap(exists -> {
                    if (exists) {
                        return Mono.error(new IllegalArgumentException(
                                "El cliente ya tiene un crédito personal activo"));
                    }
                    return Mono.empty();
                });
    }

    private Credito buildCredito(CreditoCommand command) {
        return new Credito(
                UUID.randomUUID().toString(),
                command.clienteId(),
                new DatosProducto(), // 👈 aquí podrías mapear info real del producto
                command.tipoCredito(),
                new Dinero(command.monto(), TipoMoneda.valueOf(command.moneda())),
                command.plazo()
        );
    }

    @Override
    public Mono<Credito> findById(String id) {
        return null;
    }

    @Override
    public Flux<Credito> findAll() {
        return null;
    }

    @Override
    public Mono<Boolean> delete(String id) {
        return null;
    }
}
