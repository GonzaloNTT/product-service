package com.bootcamp.product_service.application.service.tarjets;

import com.bootcamp.operation.GastoEvent;
import com.bootcamp.product_service.application.mapper.command.TarjetaCreditoCommand;
import com.bootcamp.product_service.application.port.out.TarjetaCreditoRepositoryPort;
import com.bootcamp.product_service.domain.aggregate.Tarjets.TarjetaCredito;
import com.bootcamp.product_service.domain.entity.ConsumoTarjetaCredito;
import com.bootcamp.product_service.domain.enums.TipoMoneda;
import com.bootcamp.product_service.domain.enums.TipoTarjeta;
import com.bootcamp.product_service.domain.service.tarjets.TarjetaCreditoService;
import com.bootcamp.product_service.domain.valueobject.DatosProducto;
import com.bootcamp.product_service.domain.valueobject.Dinero;
import com.bootcamp.product_service.infrastructure.cache.ProductReactiveCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@Slf4j
public class TarjetaCreditoServiceImpl implements TarjetaCreditoService {
    private final TarjetaCreditoRepositoryPort repository;
    private final ProductReactiveCache cache;

    public TarjetaCreditoServiceImpl(TarjetaCreditoRepositoryPort repository, ProductReactiveCache cache) {
        this.repository = repository;
        this.cache = cache;
    }

    @Override
    public Mono<TarjetaCredito> create(TarjetaCreditoCommand command) {
        log.info("Creando tarjeta crédito {}", command);
        // Validar que el usuario no tenga otra tarjeta de crédito
        return validarTarjetaCredito(command)
                .then(Mono.defer(() -> {
                    TarjetaCredito tarjetaCredito = buildTarjetaCredito(command);
                    return repository.save(tarjetaCredito);
                }));
    }

    private TarjetaCredito buildTarjetaCredito(TarjetaCreditoCommand command) {
        return new TarjetaCredito(
                UUID.randomUUID().toString(),
                command.clienteId(),
                new DatosProducto(),
                new Dinero(command.limiteCredito(), TipoMoneda.SOLES)
        );
    }

    private Mono<Void> validarTarjetaCredito(TarjetaCreditoCommand command) {
        return repository.findByClienteIdAndTipo(command.clienteId(), TipoTarjeta.CREDITO)
                .hasElements()
                .flatMap(exists -> {
                    if (exists) {
                        return Mono.error(new IllegalArgumentException(
                                "El cliente ya tiene una tarjeta de credito"));
                    }
                    return Mono.empty();
                });
    }

    @Override
    public Mono<TarjetaCredito> findById(String id) {
        return null;
    }

    @Override
    public Flux<TarjetaCredito> findAll() {
        return null;
    }

    @Override
    public Mono<Boolean> delete(String id) {
        return null;
    }

    @Override
    public Mono<Void> ejecutarGasto(GastoEvent message) {
        return repository.findById(message.getProductoDestino().getIdProducto())
                .switchIfEmpty(Mono.error(new IllegalArgumentException(
                        "No se encontró la tarjeta con id " + message.getProductoDestino().getIdProducto()
                )))
                .flatMap(tarjeta -> {
                    tarjeta.registrarConsumo(
                            new ConsumoTarjetaCredito(
                                    message.getReferenciaGasto(),
                                    new Dinero(
                                            new BigDecimal(message.getDinero().getMonto()),
                                            TipoMoneda.SOLES
                                    )
                            )
                    );
                    return repository.save(tarjeta);
                })
                .then();
    }
}
