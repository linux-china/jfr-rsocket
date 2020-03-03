package org.mvnsearch.jfr.rsocket;

import io.rsocket.RSocketFactory;
import io.rsocket.transport.netty.server.CloseableChannel;
import io.rsocket.uri.UriTransportRegistry;

/**
 * RSocket Responder App
 *
 * @author linux_china
 */
public class RSocketResponderApp {
    public static void main(String[] args) throws Exception {
        JFRResponderFactory responderFactory = new JFRResponderFactory();
        CloseableChannel closeableChannel = (CloseableChannel) RSocketFactory.receive()
                .acceptor(responderFactory.responder())
                .transport(UriTransportRegistry.serverForUri("tcp://0.0.0.0:42252"))
                .start()
                .block();
        System.out.println("RSocket JFR is listening on 42252!");
        closeableChannel.onClose().block();
    }
}
