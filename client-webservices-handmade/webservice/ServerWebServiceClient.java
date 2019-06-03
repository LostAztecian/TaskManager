package tm.client.webservice;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.common.api.webservice.ServerWebService;
import tm.common.comparator.ComparatorType;
import tm.common.entity.Session;

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
    public Boolean shutdown(@Nullable final Session session) {
        return serverService.shutdown(session);
    }

    @Override
    @WebMethod
    @NotNull
    public String showHelp(@Nullable final Session session) {
        return serverService.showHelp(session);
    }

    @Override
    @WebMethod
    @NotNull
    public Boolean setSortMethod(@Nullable final Session session, @Nullable final ComparatorType comparatorType) {
        return serverService.setSortMethod(session, comparatorType);
    }

    @Override
    @NotNull
    public Boolean dataClearBinary(@Nullable final Session session) throws IOException {
        return serverService.dataClearBinary(session);
    }

    @Override
    @NotNull
    public Boolean dataSaveBinary(@Nullable final Session session) throws IOException {
        return serverService.dataSaveBinary(session);
    }

    @Override
    @NotNull
    public Boolean dataLoadBinary(@Nullable final Session session) throws IOException, ClassNotFoundException {
        return serverService.dataLoadBinary(session);
    }

    @Override
    @NotNull
    public Boolean dataClearJaxbXml(@Nullable final Session session) throws IOException {
        return serverService.dataClearJaxbXml(session);
    }

    @Override
    @NotNull
    public Boolean dataSaveJaxbXml(@Nullable final Session session) throws IOException, JAXBException {
        return serverService.dataSaveJaxbXml(session);
    }

    @Override
    @NotNull
    public Boolean dataLoadJaxbXml(@Nullable final Session session) throws JAXBException {
        return serverService.dataLoadJaxbXml(session);
    }

    @Override
    @NotNull
    public Boolean dataClearJaxbJson(@Nullable final Session session) throws IOException {
        return serverService.dataClearJaxbJson(session);
    }

    @Override
    @NotNull
    public Boolean dataSaveJaxbJson(@Nullable final Session session) throws IOException, JAXBException {
        return serverService.dataSaveJaxbJson(session);
    }

    @Override
    @NotNull
    public Boolean dataLoadJaxbJson(@Nullable final Session session) throws JAXBException {
        return serverService.dataLoadJaxbJson(session);
    }

    @Override
    @NotNull
    public Boolean dataClearFasterXml(@Nullable final Session session) throws IOException {
        return serverService.dataClearFasterXml(session);
    }

    @Override
    @NotNull
    public Boolean dataSaveFasterXml(@Nullable final Session session) throws IOException {
        return serverService.dataSaveFasterXml(session);
    }

    @Override
    @NotNull
    public Boolean dataLoadFasterXml(@Nullable final Session session) throws IOException {
        return serverService.dataLoadFasterXml(session);
    }

    @Override
    @NotNull
    public Boolean dataClearFasterJson(@Nullable final Session session) throws IOException {
        return serverService.dataClearFasterJson(session);
    }

    @Override
    @NotNull
    public Boolean dataSaveFasterJson(@Nullable final Session session) throws IOException {
        return serverService.dataSaveFasterJson(session);
    }

    @Override
    @NotNull
    public Boolean dataLoadFasterJson(@Nullable final Session session) throws IOException {
        return serverService.dataLoadFasterJson(session);
    }

}
