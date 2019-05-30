package tm.common.api.webservice;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.common.comparator.ComparatorType;

import javax.jws.WebMethod;
import javax.jws.WebService;

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
    Boolean dataClearBinary();

    @NotNull @WebMethod
    Boolean dataSaveBinary();

    @NotNull @WebMethod
    Boolean dataLoadBinary();

    @NotNull @WebMethod
    Boolean dataClearJaxbXml();

    @NotNull @WebMethod
    Boolean dataSaveJaxbXml();

    @NotNull @WebMethod
    Boolean dataLoadJaxbXml();

    @NotNull @WebMethod
    Boolean dataClearJaxbJson();

    @NotNull @WebMethod
    Boolean dataSaveJaxbJson();

    @NotNull @WebMethod
    Boolean dataLoadJaxbJson();

    @NotNull @WebMethod
    Boolean dataClearFasterXml();

    @NotNull @WebMethod
    Boolean dataSaveFasterXml();

    @NotNull @WebMethod
    Boolean dataLoadFasterXml();

    @NotNull @WebMethod
    Boolean dataClearFasterJson();

    @NotNull @WebMethod
    Boolean dataSaveFasterJson();

    @NotNull @WebMethod
    Boolean dataLoadFasterJson();

}
