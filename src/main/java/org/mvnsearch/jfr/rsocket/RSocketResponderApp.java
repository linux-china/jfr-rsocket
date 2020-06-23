package org.mvnsearch.jfr.rsocket;

import io.rsocket.core.RSocketServer;
import io.rsocket.transport.netty.server.CloseableChannel;
import io.rsocket.transport.netty.server.TcpServerTransport;

/**
 * RSocket Responder App
 *
 * @author linux_china
 */
public class RSocketResponderApp {
    public static void main(String[] args) throws Exception {
        JFRResponderFactory responderFactory = new JFRResponderFactory();
        CloseableChannel closeableChannel = RSocketServer.create()
                .acceptor(responderFactory.responder())
                .bind(TcpServerTransport.create("0.0.0.0", 42252))
                .block();
        System.out.println("RSocket JFR is listening on 42252!");
        closeableChannel.onClose().block();
    }
}
