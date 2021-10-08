JFR Event Streaming with RSocket
======================================

Subscribe JFR Event by RSocket.

* JEP 328: Flight Recorder https://openjdk.java.net/jeps/328
* JEP 349: JFR Event Streaming https://openjdk.java.net/jeps/349


# Anatomy of JFR Event

* Event ID
* Timestamp(CPU ticks)
* Duration(CPU ticks)
* Thread ID: most events have, not every event
* StackTrace ID
* Event Specific Payload

# Why JFR with RSocket?

* Both are streaming
* Both are message & event driven

# References

* Java Flight Recorder with IntelliJ IDEA: https://www.jetbrains.com/help/idea/java-flight-recorder.html
* Getting started with JFR: https://learning.oreilly.com/library/view/java-11-and/9781789133271/e94a5985-ab79-4fb8-a530-705ca6b5126e.xhtml
* Java Flight Recorder: https://docs.oracle.com/javacomponents/jmc-5-4/jfr-runtime-guide/about.htm
* Digging Into Sockets With Java Flight Recorder: https://dzone.com/articles/analyzing-tcp-socket-with-java-flight-recorder
* Monitoring REST APIs with Custom JDK Flight Recorder Events: https://www.morling.dev/blog/rest-api-monitoring-with-custom-jdk-flight-recorder-events/
* Continuous Monitoring with JDK Flight Recorder (JFR): https://www.infoq.com/presentations/monitoring-jdk-jfr/
* jdk.jfr: https://docs.oracle.com/en/java/javase/13/docs/api/jdk.jfr/jdk/jfr/package-summary.html
* Flight Recorder API Programmer’s Guide: https://docs.oracle.com/en/java/javase/14/jfapi/
* Flight Recorder demo: https://github.com/flight-recorder
* Continuous Monitoring with JDK Flight Recorder: https://www.youtube.com/watch?v=plYESjZ12hM
* VisualVM: https://visualvm.github.io/
* JDK Flight Recorder support for GraalVM Native Image: https://developers.redhat.com/articles/2021/07/23/jdk-flight-recorder-support-graalvm-native-image-journey-so-far#learn_more_about_jdk_flight_recorder
* Remote Recording stream: https://egahlin.github.io/2021/05/17/remote-recording-stream.html
* JfrUnit: extension for asserting JDK Flight Recorder events https://github.com/moditect/jfrunit

