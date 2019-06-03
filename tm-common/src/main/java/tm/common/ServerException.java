package tm.common;

public class ServerException extends Throwable {

    public ServerException(Throwable cause) {
        super("", cause);
    }

}
