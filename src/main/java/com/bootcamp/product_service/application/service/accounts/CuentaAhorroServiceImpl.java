package com.bootcamp.product_service.application.service.accounts;

import com.bootcamp.product_service.application.mapper.command.CreditoCommand;
import com.bootcamp.product_service.application.mapper.command.CuentaAhorroCommand;
import com.bootcamp.product_service.application.port.out.CuentaAhorroRepositoryPort;
import com.bootcamp.product_service.domain.aggregate.accounts.CuentaAhorro;
import com.bootcamp.product_service.domain.aggregate.credit.Credito;
import com.bootcamp.product_service.domain.enums.TipoCredito;
import com.bootcamp.product_service.domain.enums.TipoCuentaAhorro;
import com.bootcamp.product_service.domain.enums.TipoMoneda;
import com.bootcamp.product_service.domain.service.accounts.CuentaAhorroService;
import com.bootcamp.product_service.domain.valueobject.DatosProducto;
import com.bootcamp.product_service.domain.valueobject.Dinero;
import com.bootcamp.product_service.domain.valueobject.PoliticaComision;
import com.bootcamp.product_service.domain.valueobject.PoliticaMovimientos;
import com.bootcamp.product_service.infrastructure.cache.ProductReactiveCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@Slf4j
public class CuentaAhorroServiceImpl implements CuentaAhorroService {
    private final CuentaAhorroRepositoryPort repository;

    public CuentaAhorroServiceImpl(CuentaAhorroRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public Mono<CuentaAhorro> create(CuentaAhorroCommand command) {
        log.info("Creando cuenta ahorros {}", command);
        return switch (command.tipoCuentaAhorro()) {
            case NORMAL -> validarCuentaAhorroPersonal(command)
                    .then(Mono.defer(() -> {
                        CuentaAhorro cuentaAhorro = buildCuentaAhorro(command);
                        return repository.save(cuentaAhorro);
                    }));

            case VIP -> {
                //cliente es VIP y rtiwene tarejta de credito
                CuentaAhorro cuentaAhorro = buildCuentaAhorro(command);
                yield repository.save(cuentaAhorro);
            }
            default -> Mono.error(new IllegalArgumentException("Tipo de crédito no soportado: " + command.tipoCuentaAhorro()));
        };
    }

    private Mono<Void> validarCuentaAhorroPersonal(CuentaAhorroCommand command) {
        return repository.findByClienteIdAndTipoCuentaAhorro(command.clienteId(), TipoCuentaAhorro.NORMAL)
                .hasElements()
                .flatMap(exists -> {
                    if (exists) {
                        return Mono.error(new IllegalArgumentException(
                                "El cliente ya tiene una cuenta de ahorros activa"));
                    }
                    return Mono.empty();
                });
    }

    private CuentaAhorro buildCuentaAhorro(CuentaAhorroCommand command) {
        return new CuentaAhorro(
                command.clienteId(),
                command.tipoCuentaAhorro()
        );
    }

    @Override
    public Mono<CuentaAhorro> findById(String id) {
        return null;
    }

    @Override
    public Flux<CuentaAhorro> findAll() {
        return null;
    }

    @Override
    public Mono<Boolean> delete(String id) {
        return null;
    }
}
