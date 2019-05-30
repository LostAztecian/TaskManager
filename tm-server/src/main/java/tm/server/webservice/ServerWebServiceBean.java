package tm.server.webservice;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.common.api.webservice.ServerWebService;
import tm.common.comparator.ComparatorType;
import tm.server.api.service.ServerService;

import javax.jws.WebService;

@WebService(endpointInterface = "tm.common.api.webservice.ServerWebService")
public class ServerWebServiceBean implements ServerWebService {

    private ServerService serverService;

    public ServerWebServiceBean(ServerService serverService) {
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
    public Boolean setSortMethod(@Nullable ComparatorType comparatorType) {
        return serverService.setSortMethod(comparatorType);
    }

    @Override @NotNull
    public Boolean dataClearBinary() {
        return serverService.dataClearBinary();
    }

    @Override @NotNull
    public Boolean dataSaveBinary() {
        return serverService.dataSaveBinary();
    }

    @Override @NotNull
    public Boolean dataLoadBinary() {
        return serverService.dataLoadBinary();
    }

    @Override @NotNull
    public Boolean dataClearJaxbXml() {
        return serverService.dataClearJaxbXml();
    }

    @Override @NotNull
    public Boolean dataSaveJaxbXml() {
        return serverService.dataSaveJaxbXml();
    }

    @Override @NotNull
    public Boolean dataLoadJaxbXml() {
        return serverService.dataLoadJaxbXml();
    }

    @Override @NotNull
    public Boolean dataClearJaxbJson() {
        return serverService.dataClearJaxbJson();
    }

    @Override @NotNull
    public Boolean dataSaveJaxbJson() {
        return serverService.dataSaveJaxbJson();
    }

    @Override @NotNull
    public Boolean dataLoadJaxbJson() {
        return serverService.dataLoadJaxbJson();
    }

    @Override @NotNull
    public Boolean dataClearFasterXml() {
        return serverService.dataClearFasterXml();
    }

    @Override @NotNull
    public Boolean dataSaveFasterXml() {
        return serverService.dataSaveFasterXml();
    }

    @Override @NotNull
    public Boolean dataLoadFasterXml() {
        return serverService.dataLoadFasterXml();
    }

    @Override @NotNull
    public Boolean dataClearFasterJson() {
        return serverService.dataClearFasterJson();
    }

    @Override @NotNull
    public Boolean dataSaveFasterJson() {
        return serverService.dataSaveFasterJson();
    }

    @Override @NotNull
    public Boolean dataLoadFasterJson() {
        return serverService.dataLoadFasterJson();
    }
}
