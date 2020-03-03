package org.mvnsearch.jfr.rsocket;

import jdk.jfr.consumer.RecordedEvent;

import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * JFR stream event consumer
 *
 * @author linux_china
 */
public class JfrStreamEventConsumer implements Consumer<RecordedEvent> {
    @Override
    public void accept(RecordedEvent recordedEvent) {
    }

    private static Predicate<RecordedEvent> testMaker(String s) {
        return e -> e.getEventType().getName().startsWith(s);
    }

    private static final Map<Predicate<RecordedEvent>,
            Function<RecordedEvent, Map<String, String>>> mappers =
            Map.of(testMaker("jdk.ObjectAllocationInNewTLAB"),
                    ev -> Map.of("start", "" + ev.getStartTime(), "end",
                            "" + ev.getEndTime(),
                            "thread",
                            ev.getThread("eventThread").getJavaName(),
                            "class", ev.getClass("objectClass").getName(),
                            "size", "" + ev.getLong("allocationSize"),
                            "tlab", "" + ev.getLong("tlabSize")
                    ));
}
