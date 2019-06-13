package tm.server.webservice;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.common.ServerException;
import tm.common.api.webservice.ServerWebService;
import tm.common.comparator.ComparatorType;
import tm.common.entity.SessionDTO;
import tm.server.api.service.ServerService;

import javax.jws.WebService;

@WebService(endpointInterface = "tm.common.api.webservice.ServerWebService")
public class ServerWebServiceBean implements ServerWebService {

    private ServerService serverService;

    public ServerWebServiceBean(@NotNull final ServerService serverService) throws ServerException {
        try {
            this.serverService = serverService;
        } catch (Exception e) {
            throw new ServerException(e);
        }
    }

    @Override @NotNull
    public String showAbout() throws ServerException {
        try {
            return serverService.showAbout();
        } catch (Exception e) {
            throw new ServerException(e);
        }
    }

    @Override @NotNull
    public Boolean shutdown(@Nullable final SessionDTO session) throws ServerException {
        try {
            return serverService.shutdown(session);
        } catch (Exception e) {
            throw new ServerException(e);
        }
    }

    @Override @NotNull
    public String showHelp(@Nullable final SessionDTO session) throws ServerException {
        try {
            return serverService.showHelp(session);
        } catch (Exception e) {
            throw new ServerException(e);
        }
    }

    @Override @NotNull
    public Boolean setSortMethod(@Nullable final SessionDTO session, @Nullable final ComparatorType comparatorType) throws ServerException {
        try {
            return serverService.setSortMethod(session, comparatorType);
        } catch (Exception e) {
            throw new ServerException(e);
        }
    }

    @Override @NotNull
    public Boolean dataClearBinary(@Nullable final SessionDTO session) throws ServerException {
        Boolean result = false;
        try {
            result = serverService.dataClearBinary(session);
        } catch (Exception e) {
            throw new ServerException(e);
        }
        return result;
    }

    @Override @NotNull
    public Boolean dataSaveBinary(@Nullable final SessionDTO session) throws ServerException {
        Boolean result = false;
        try {
            result = serverService.dataSaveBinary(session);
        } catch (Exception e) {
            throw new ServerException(e);
        }
        return result;
    }

    @Override @NotNull
    public Boolean dataLoadBinary(@Nullable final SessionDTO session) throws ServerException {
        Boolean result = false;
        try {
            result = serverService.dataLoadBinary(session);
        } catch (Exception e) {
            throw new ServerException(e);
        }
        return result;

    }

    @Override @NotNull
    public Boolean dataClearJaxbXml(@Nullable final SessionDTO session) throws ServerException {
        Boolean result = false;
        try {
            result = serverService.dataClearJaxbXml(session);
        } catch (Exception e) {
            throw new ServerException(e);
        }
        return result;

    }

    @Override @NotNull
    public Boolean dataSaveJaxbXml(@Nullable final SessionDTO session) throws ServerException {
        Boolean result = false;
        try {
            result = serverService.dataSaveJaxbXml(session);
        } catch (Exception e) {
            throw new ServerException(e);
        }
        return result;

    }

    @Override @NotNull
    public Boolean dataLoadJaxbXml(@Nullable final SessionDTO session) throws ServerException {
        Boolean result = false;
        try {
            result = serverService.dataLoadJaxbXml(session);
        } catch (Exception e) {
            throw new ServerException(e);
        }
        return result;

    }

    @Override @NotNull
    public Boolean dataClearJaxbJson(@Nullable final SessionDTO session) throws ServerException {
        Boolean result = false;
        try {
            result = serverService.dataClearJaxbJson(session);
        } catch (Exception e) {
            throw new ServerException(e);
        }
        return result;

    }

    @Override @NotNull
    public Boolean dataSaveJaxbJson(@Nullable final SessionDTO session) throws ServerException {
        Boolean result = false;
        try {
            result = serverService.dataSaveJaxbJson(session);
        } catch (Exception e) {
            throw new ServerException(e);
        }
        return result;

    }

    @Override @NotNull
    public Boolean dataLoadJaxbJson(@Nullable final SessionDTO session) throws ServerException {
        Boolean result = false;
        try {
            result = serverService.dataLoadJaxbJson(session);
        } catch (Exception e) {
            throw new ServerException(e);
        }
        return result;

    }

    @Override @NotNull
    public Boolean dataClearFasterXml(@Nullable final SessionDTO session) throws ServerException {
        Boolean result = false;
        try {
            result = serverService.dataClearFasterXml(session);
        } catch (Exception e) {
            throw new ServerException(e);
        }
        return result;

    }

    @Override @NotNull
    public Boolean dataSaveFasterXml(@Nullable final SessionDTO session) throws ServerException {
        Boolean result = false;
        try {
            result = serverService.dataSaveFasterXml(session);
        } catch (Exception e) {
            throw new ServerException(e);
        }
        return result;

    }

    @Override @NotNull
    public Boolean dataLoadFasterXml(@Nullable final SessionDTO session) throws ServerException {
        Boolean result = false;
        try {
            result = serverService.dataLoadFasterXml(session);
        } catch (Exception e) {
            throw new ServerException(e);
        }
        return result;

    }

    @Override @NotNull
    public Boolean dataClearFasterJson(@Nullable final SessionDTO session) throws ServerException {
        Boolean result = false;
        try {
            result = serverService.dataClearFasterJson(session);
        } catch (Exception e) {
            throw new ServerException(e);
        }
        return result;

    }

    @Override @NotNull
    public Boolean dataSaveFasterJson(@Nullable final SessionDTO session) throws ServerException {
        Boolean result = false;
        try {
            result = serverService.dataSaveFasterJson(session);
        } catch (Exception e) {
            throw new ServerException(e);
        }
        return result;

    }

    @Override @NotNull
    public Boolean dataLoadFasterJson(@Nullable final SessionDTO session) throws ServerException {
        Boolean result = false;
        try {
            result = serverService.dataLoadFasterJson(session);
        } catch (Exception e) {
            throw new ServerException(e);
        }
        return result;

    }
}
