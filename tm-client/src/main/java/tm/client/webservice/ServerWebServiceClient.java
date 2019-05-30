package tm.client.webservice;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.common.api.webservice.ServerWebService;
import tm.common.comparator.ComparatorType;

import javax.jws.WebMethod;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

public class ServerWebServiceClient implements ServerWebService {

    private final ServerWebService serverService;

    public ServerWebServiceClient() {
        ServerWebService serverWebService = null;
        try {
            URL url = new URL("http://localhost:8080/serverService/?wsdl");
            QName qName = new QName("http://webservice.server.tm/", "ServerWebServiceBeanService");
            Service service = Service.create(url, qName);
            serverWebService = service.getPort(ServerWebService.class);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        this.serverService = serverWebService;
    }

    @Override
    @WebMethod
    @NotNull
    public String showAbout() {
        return serverService.showAbout();
    }

    @Override
    @WebMethod
    @NotNull
    public Boolean shutdown() {
        return serverService.shutdown();
    }

    @Override
    @WebMethod
    @NotNull
    public String showHelp() {
        return serverService.showHelp();
    }

    @Override
    @WebMethod
    @NotNull
    public Boolean setSortMethod(@Nullable ComparatorType comparatorType) {
        return serverService.setSortMethod(comparatorType);
    }

    @Override
    @NotNull
    public Boolean dataClearBinary() {
        return serverService.dataClearBinary();
    }

    @Override
    @NotNull
    public Boolean dataSaveBinary() {
        return serverService.dataSaveBinary();
    }

    @Override
    @NotNull
    public Boolean dataLoadBinary() {
        return serverService.dataLoadBinary();
    }

    @Override
    @NotNull
    public Boolean dataClearJaxbXml() {
        return serverService.dataClearJaxbXml();
    }

    @Override
    @NotNull
    public Boolean dataSaveJaxbXml() {
        return serverService.dataSaveJaxbXml();
    }

    @Override
    @NotNull
    public Boolean dataLoadJaxbXml() {
        return serverService.dataLoadJaxbXml();
    }

    @Override
    @NotNull
    public Boolean dataClearJaxbJson() {
        return serverService.dataClearJaxbJson();
    }

    @Override
    @NotNull
    public Boolean dataSaveJaxbJson() {
        return serverService.dataSaveJaxbJson();
    }

    @Override
    @NotNull
    public Boolean dataLoadJaxbJson() {
        return serverService.dataLoadJaxbJson();
    }

    @Override
    @NotNull
    public Boolean dataClearFasterXml() {
        return serverService.dataClearFasterXml();
    }

    @Override
    @NotNull
    public Boolean dataSaveFasterXml() {
        return serverService.dataSaveFasterXml();
    }

    @Override
    @NotNull
    public Boolean dataLoadFasterXml() {
        return serverService.dataLoadFasterXml();
    }

    @Override
    @NotNull
    public Boolean dataClearFasterJson() {
        return serverService.dataClearFasterJson();
    }

    @Override
    @NotNull
    public Boolean dataSaveFasterJson() {
        return serverService.dataSaveFasterJson();
    }

    @Override
    @NotNull
    public Boolean dataLoadFasterJson() {
        return serverService.dataLoadFasterJson();
    }

}
