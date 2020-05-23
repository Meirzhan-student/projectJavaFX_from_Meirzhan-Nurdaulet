package sample;

import java.io.*;
import java.net.Socket;
import java.util.function.Consumer;

public class Client extends Service {
    private String ip;
    private int port;

    public Client( String ip, int port, Consumer<Serializable> onReceiveCallback) {
        super(onReceiveCallback);
        this.ip = ip;
        this.port = port;
    }

    @Override
    protected boolean isServer() {
        return false;
    }

    @Override
    protected String getIP() {
        return ip;
    }

    @Override
    protected int getPort() {
        return port;
    }
}
