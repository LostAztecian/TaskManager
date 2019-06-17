package tm.common.exception;

public class ServerException extends Throwable {

    public ServerException(Throwable cause) {
        super("", cause);
    }

}
