package uk.co.roteala.handlers;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.ReferenceCountUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.SerializationUtils;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.CoreSubscriber;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.netty.ByteBufFlux;
import reactor.netty.Connection;
import reactor.netty.NettyInbound;
import reactor.netty.NettyOutbound;
import uk.co.roteala.common.*;
import uk.co.roteala.common.events.Message;
import uk.co.roteala.processor.MessageProcessor;
import uk.co.roteala.processor.Processor;
import uk.co.roteala.storage.StorageServices;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

/**
 * Handles the connection between the clients and server
 * */
@Slf4j
@Component
@RequiredArgsConstructor
public class TransmissionHandler implements BiFunction<NettyInbound, NettyOutbound, Publisher<Void>> {

    private final MessageProcessor messageProcessor;
    @Override
    public Mono<Void> apply(NettyInbound inbound, NettyOutbound outbound) {


        this.messageProcessor.forwardMessage(inbound, outbound);

        return outbound.neverComplete();
    }
}
