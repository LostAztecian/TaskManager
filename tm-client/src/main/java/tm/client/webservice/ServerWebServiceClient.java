package tm.client.webservice;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.common.api.webservice.ServerWebService;
import tm.common.comparator.ComparatorType;

import javax.jws.WebMethod;
import javax.xml.bind.JAXBException;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.io.IOException;
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
    public Boolean setSortMethod(@Nullable final ComparatorType comparatorType) {
        return serverService.setSortMethod(comparatorType);
    }

    @Override
    @NotNull
    public Boolean dataClearBinary() throws IOException {
        return serverService.dataClearBinary();
    }

    @Override
    @NotNull
    public Boolean dataSaveBinary() throws IOException {
        return serverService.dataSaveBinary();
    }

    @Override
    @NotNull
    public Boolean dataLoadBinary() throws IOException, ClassNotFoundException {
        return serverService.dataLoadBinary();
    }

    @Override
    @NotNull
    public Boolean dataClearJaxbXml() throws IOException {
        return serverService.dataClearJaxbXml();
    }

    @Override
    @NotNull
    public Boolean dataSaveJaxbXml() throws IOException, JAXBException {
        return serverService.dataSaveJaxbXml();
    }

    @Override
    @NotNull
    public Boolean dataLoadJaxbXml() throws JAXBException {
        return serverService.dataLoadJaxbXml();
    }

    @Override
    @NotNull
    public Boolean dataClearJaxbJson() throws IOException {
        return serverService.dataClearJaxbJson();
    }

    @Override
    @NotNull
    public Boolean dataSaveJaxbJson() throws IOException, JAXBException {
        return serverService.dataSaveJaxbJson();
    }

    @Override
    @NotNull
    public Boolean dataLoadJaxbJson() throws JAXBException {
        return serverService.dataLoadJaxbJson();
    }

    @Override
    @NotNull
    public Boolean dataClearFasterXml() throws IOException {
        return serverService.dataClearFasterXml();
    }

    @Override
    @NotNull
    public Boolean dataSaveFasterXml() throws IOException {
        return serverService.dataSaveFasterXml();
    }

    @Override
    @NotNull
    public Boolean dataLoadFasterXml() throws IOException {
        return serverService.dataLoadFasterXml();
    }

    @Override
    @NotNull
    public Boolean dataClearFasterJson() throws IOException {
        return serverService.dataClearFasterJson();
    }

    @Override
    @NotNull
    public Boolean dataSaveFasterJson() throws IOException {
        return serverService.dataSaveFasterJson();
    }

    @Override
    @NotNull
    public Boolean dataLoadFasterJson() throws IOException {
        return serverService.dataLoadFasterJson();
    }

}
