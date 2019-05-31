package tm.server.webservice;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.common.api.webservice.ServerWebService;
import tm.common.comparator.ComparatorType;
import tm.common.entity.Session;
import tm.server.api.service.ServerService;

import javax.jws.WebService;
import javax.xml.bind.JAXBException;
import java.io.IOException;

@WebService(endpointInterface = "tm.common.api.webservice.ServerWebService")
public class ServerWebServiceBean implements ServerWebService {

    private ServerService serverService;

    public ServerWebServiceBean(@NotNull final ServerService serverService) {
        this.serverService = serverService;
    }

    @Override @NotNull
    public String showAbout() {
        return serverService.showAbout();
    }

    @Override @NotNull
    public Boolean shutdown(@Nullable final Session session) {
        return serverService.shutdown(session);
    }

    @Override @NotNull
    public String showHelp(@Nullable final Session session) {
        return serverService.showHelp(session);
    }

    @Override @NotNull
    public Boolean setSortMethod(@Nullable final Session session, @Nullable final ComparatorType comparatorType) {
        return serverService.setSortMethod(session, comparatorType);
    }

    @Override @NotNull
    public Boolean dataClearBinary(@Nullable final Session session) throws IOException {
        return serverService.dataClearBinary(session);
    }

    @Override @NotNull
    public Boolean dataSaveBinary(@Nullable final Session session) throws IOException {
        return serverService.dataSaveBinary(session);
    }

    @Override @NotNull
    public Boolean dataLoadBinary(@Nullable final Session session) throws IOException, ClassNotFoundException {
        return serverService.dataLoadBinary(session);
    }

    @Override @NotNull
    public Boolean dataClearJaxbXml(@Nullable final Session session) throws IOException {
        return serverService.dataClearJaxbXml(session);
    }

    @Override @NotNull
    public Boolean dataSaveJaxbXml(@Nullable final Session session) throws IOException, JAXBException {
        return serverService.dataSaveJaxbXml(session);
    }

    @Override @NotNull
    public Boolean dataLoadJaxbXml(@Nullable final Session session) throws JAXBException {
        return serverService.dataLoadJaxbXml(session);
    }

    @Override @NotNull
    public Boolean dataClearJaxbJson(@Nullable final Session session) throws IOException {
        return serverService.dataClearJaxbJson(session);
    }

    @Override @NotNull
    public Boolean dataSaveJaxbJson(@Nullable final Session session) throws IOException, JAXBException {
        return serverService.dataSaveJaxbJson(session);
    }

    @Override @NotNull
    public Boolean dataLoadJaxbJson(@Nullable final Session session) throws JAXBException {
        return serverService.dataLoadJaxbJson(session);
    }

    @Override @NotNull
    public Boolean dataClearFasterXml(@Nullable final Session session) throws IOException {
        return serverService.dataClearFasterXml(session);
    }

    @Override @NotNull
    public Boolean dataSaveFasterXml(@Nullable final Session session) throws IOException {
        return serverService.dataSaveFasterXml(session);
    }

    @Override @NotNull
    public Boolean dataLoadFasterXml(@Nullable final Session session) throws IOException {
        return serverService.dataLoadFasterXml(session);
    }

    @Override @NotNull
    public Boolean dataClearFasterJson(@Nullable final Session session) throws IOException {
        return serverService.dataClearFasterJson(session);
    }

    @Override @NotNull
    public Boolean dataSaveFasterJson(@Nullable final Session session) throws IOException {
        return serverService.dataSaveFasterJson(session);
    }

    @Override @NotNull
    public Boolean dataLoadFasterJson(@Nullable final Session session) throws IOException {
        return serverService.dataLoadFasterJson(session);
    }
}
