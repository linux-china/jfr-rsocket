package org.mvnsearch.jfr.rsocket;

import io.rsocket.ConnectionSetupPayload;
import io.rsocket.Payload;
import io.rsocket.RSocket;
import io.rsocket.util.DefaultPayload;
import jdk.jfr.consumer.RecordingStream;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

/**
 * JFR Event Streaming Responder
 *
 * @author linux_china
 */
public class JFREventStreamingResponder implements RSocket {
    private String id;
    private RSocket requester;
    private Mono<Void> comboOnClose;
    private Sinks.One<Void> onClose = Sinks.one();

    public JFREventStreamingResponder(ConnectionSetupPayload setupPayload, RSocket requester) {
        this.id = UUID.randomUUID().toString();
        this.requester = requester;
        this.comboOnClose = Mono.first(onClose.asMono(), requester.onClose());
    }

    @Override
    public Mono<Void> onClose() {
        return this.comboOnClose;
    }

    public void dispose() {
        this.onClose.tryEmitEmpty();
        this.requester.dispose();
    }

    public String getId() {
        return id;
    }

    public RSocket getRequester() {
        return requester;
    }

    @Override
    public Flux<Payload> requestStream(Payload payload) {
        return Flux.create(sink -> {
            String jfrSetting = payload.getDataUtf8();
            var rs = new RecordingStream();
            rs.setEndTime(Instant.now().plus(15, ChronoUnit.SECONDS));
            rs.onClose(sink::complete);
            rs.onError(sink::error);
            rs.enable(jfrSetting).withPeriod(Duration.ofSeconds(1));
            rs.onEvent(event -> {
                sink.next(DefaultPayload.create(event.toString()));
            });
            rs.startAsync();
        });
    }

}
