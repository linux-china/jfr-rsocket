package org.mvnsearch.jfr.rsocket;

import jdk.jfr.consumer.RecordingStream;

import java.lang.instrument.Instrumentation;
import java.time.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * JFR Agent main
 *
 * @author linux_china
 */
public class JFRAgentMain implements Runnable {
    public static void premain(String agentArgs, Instrumentation inst) {
        try {
            Logger.getLogger("AgentMain").log(Level.INFO, "Attaching JFR Monitor");
            new Thread(new JFRAgentMain()).start();
        } catch (Throwable t) {
            Logger.getLogger("AgentMain").log(Level.SEVERE, "Unable to attach JFR Monitor", t);
        }
    }

    public void run() {
        var consumer = new JfrStreamEventConsumer();
        try (var rs = new RecordingStream()) {
            rs.enable("jdk.CPULoad")
                    .withPeriod(Duration.ofSeconds(1));
            rs.enable("jdk.JavaMonitorEnter")
                    .withThreshold(Duration.ofMillis(10));
            rs.onEvent("jdk.CPULoad", consumer);
            rs.onEvent("jdk.JavaMonitorEnter", consumer);
            rs.start();
        }
    }
}
