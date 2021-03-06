package org.mvnsearch.jfr;

import jdk.jfr.FlightRecorder;
import jdk.jfr.consumer.RecordingStream;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * JFR test
 *
 * @author linux_china
 */
public class JFRTest {

    @Test
    public void testPrintNames() throws Exception {
        AtomicInteger counter = new AtomicInteger();
        FlightRecorder.getFlightRecorder().getEventTypes().forEach(eventType -> {
            counter.incrementAndGet();
        });
        System.out.println(counter.intValue());
    }

    @Test
    public void testRecording() throws Exception {
        var rs = new RecordingStream();
        rs.setEndTime(Instant.now().plus(10, ChronoUnit.SECONDS));
        rs.onClose(() -> {
            System.out.println("recording closed!!!!");
        });
        rs.enable("jdk.CPULoad").withPeriod(Duration.ofSeconds(1));
        rs.enable("jdk.JavaThreadStatistics");
        rs.onEvent("jdk.CPULoad", event -> {
            System.out.println(event.toString());
        });
        rs.onEvent("jdk.JavaThreadStatistics", event -> {
            System.out.println(event.toString());
        });
        rs.startAsync();
        Thread.sleep(15000);
        System.out.println("jfr closed");
        Thread.sleep(15000);
    }
}
