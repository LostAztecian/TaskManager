package tm.server.webservice;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.common.api.webservice.ServerWebService;
import tm.common.comparator.ComparatorType;
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
    public Boolean shutdown() {
        return serverService.shutdown();
    }

    @Override @NotNull
    public String showHelp() {
        return serverService.showHelp();
    }

    @Override @NotNull
    public Boolean setSortMethod(@Nullable final ComparatorType comparatorType) {
        return serverService.setSortMethod(comparatorType);
    }

    @Override @NotNull
    public Boolean dataClearBinary() throws IOException {
        return serverService.dataClearBinary();
    }

    @Override @NotNull
    public Boolean dataSaveBinary() throws IOException {
        return serverService.dataSaveBinary();
    }

    @Override @NotNull
    public Boolean dataLoadBinary() throws IOException, ClassNotFoundException {
        return serverService.dataLoadBinary();
    }

    @Override @NotNull
    public Boolean dataClearJaxbXml() throws IOException {
        return serverService.dataClearJaxbXml();
    }

    @Override @NotNull
    public Boolean dataSaveJaxbXml() throws IOException, JAXBException {
        return serverService.dataSaveJaxbXml();
    }

    @Override @NotNull
    public Boolean dataLoadJaxbXml() throws JAXBException {
        return serverService.dataLoadJaxbXml();
    }

    @Override @NotNull
    public Boolean dataClearJaxbJson() throws IOException {
        return serverService.dataClearJaxbJson();
    }

    @Override @NotNull
    public Boolean dataSaveJaxbJson() throws IOException, JAXBException {
        return serverService.dataSaveJaxbJson();
    }

    @Override @NotNull
    public Boolean dataLoadJaxbJson() throws JAXBException {
        return serverService.dataLoadJaxbJson();
    }

    @Override @NotNull
    public Boolean dataClearFasterXml() throws IOException {
        return serverService.dataClearFasterXml();
    }

    @Override @NotNull
    public Boolean dataSaveFasterXml() throws IOException {
        return serverService.dataSaveFasterXml();
    }

    @Override @NotNull
    public Boolean dataLoadFasterXml() throws IOException {
        return serverService.dataLoadFasterXml();
    }

    @Override @NotNull
    public Boolean dataClearFasterJson() throws IOException {
        return serverService.dataClearFasterJson();
    }

    @Override @NotNull
    public Boolean dataSaveFasterJson() throws IOException {
        return serverService.dataSaveFasterJson();
    }

    @Override @NotNull
    public Boolean dataLoadFasterJson() throws IOException {
        return serverService.dataLoadFasterJson();
    }
}
