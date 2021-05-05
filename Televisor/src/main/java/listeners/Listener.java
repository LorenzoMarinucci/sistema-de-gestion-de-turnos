package listeners;

public abstract class Listener {

    protected String host;
    protected Integer port;

    public Listener(String host, Integer port) {
        this.host = host;
        this.port = port;
    }

}
