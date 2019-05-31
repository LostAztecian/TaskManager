package tm.common.api.webservice;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.common.comparator.ComparatorType;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.bind.JAXBException;
import java.io.IOException;

@WebService(name = "serverService")
public interface ServerWebService {

    @NotNull @WebMethod
    String showAbout();

    @NotNull @WebMethod
    Boolean shutdown();

    @NotNull @WebMethod
    String showHelp();

    @NotNull @WebMethod
    Boolean setSortMethod(@Nullable ComparatorType comparatorType);

    @NotNull @WebMethod
    Boolean dataClearBinary() throws IOException;

    @NotNull @WebMethod
    Boolean dataSaveBinary() throws IOException;

    @NotNull @WebMethod
    Boolean dataLoadBinary() throws IOException, ClassNotFoundException;

    @NotNull @WebMethod
    Boolean dataClearJaxbXml() throws IOException;

    @NotNull @WebMethod
    Boolean dataSaveJaxbXml() throws IOException, JAXBException;

    @NotNull @WebMethod
    Boolean dataLoadJaxbXml() throws JAXBException;

    @NotNull @WebMethod
    Boolean dataClearJaxbJson() throws IOException;

    @NotNull @WebMethod
    Boolean dataSaveJaxbJson() throws IOException, JAXBException;

    @NotNull @WebMethod
    Boolean dataLoadJaxbJson() throws JAXBException;

    @NotNull @WebMethod
    Boolean dataClearFasterXml() throws IOException;

    @NotNull @WebMethod
    Boolean dataSaveFasterXml() throws IOException;

    @NotNull @WebMethod
    Boolean dataLoadFasterXml() throws IOException;

    @NotNull @WebMethod
    Boolean dataClearFasterJson() throws IOException;

    @NotNull @WebMethod
    Boolean dataSaveFasterJson() throws IOException;

    @NotNull @WebMethod
    Boolean dataLoadFasterJson() throws IOException;

}
