package org.mvnsearch.jfr;

import jdk.jfr.consumer.RecordingStream;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

/**
 * Reactor with JFR Event
 *
 * @author linux_china
 */
public class ReactorJFRTest {

    @Test
    public void testMonoJFR() throws Exception {
        //create JFR Recording
        var rs = new RecordingStream();
        rs.setEndTime(Instant.now().plus(5, ChronoUnit.SECONDS));
        rs.onClose(() -> System.out.println("recording closed!!!!"));
        //config event
        String jfrEventName = PlaceOrderEvent.class.getCanonicalName();
        rs.enable(jfrEventName).withoutStackTrace();
        rs.onEvent(jfrEventName, event -> System.out.println(event.toString()));
        //start recording
        rs.startAsync();
        //fire JFR event
        PlaceOrderEvent jfrEvent = new PlaceOrderEvent(1L, 2L, 1500L);
        Mono.just("order-111")
                .doOnSubscribe(subscription -> jfrEvent.begin())
                .doOnSuccess(jfrEvent::setOrderNo)
                .doFinally(signalType -> jfrEvent.commit())
                .subscribe();
        Thread.sleep(6000);
        System.out.println("jfr closed");
        Thread.sleep(10000);
    }
}
