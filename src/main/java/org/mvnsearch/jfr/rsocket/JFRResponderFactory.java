package org.mvnsearch.jfr.rsocket;

import io.rsocket.ConnectionSetupPayload;
import io.rsocket.RSocket;
import io.rsocket.SocketAcceptor;
import reactor.core.publisher.Mono;

/**
 * JFR responder factory
 *
 * @author linux_china
 */
public class JFRResponderFactory {
    public SocketAcceptor responder() {
        return (this::createResponder);
    }

    public Mono<RSocket> createResponder(ConnectionSetupPayload setupPayload, RSocket requester) {
        JFREventStreamingResponder handler = new JFREventStreamingResponder(setupPayload, requester);
        return Mono.just(handler);
    }

}
