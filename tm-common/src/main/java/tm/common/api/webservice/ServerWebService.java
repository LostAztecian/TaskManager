package tm.common.api.webservice;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.common.exception.ServerException;
import tm.common.comparator.ComparatorType;
import tm.common.entity.SessionDTO;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(name = "serverService")
public interface ServerWebService {

    @NotNull @WebMethod
    String showAbout() throws ServerException;

    @NotNull @WebMethod
    Boolean shutdown(@Nullable SessionDTO session) throws ServerException;

    @NotNull @WebMethod
    String showHelp(@Nullable SessionDTO session) throws ServerException;

    @NotNull @WebMethod
    Boolean setSortMethod(@Nullable SessionDTO session, @Nullable ComparatorType comparatorType) throws ServerException;

    @NotNull @WebMethod
    Boolean dataClearBinary(@Nullable SessionDTO session) throws ServerException;

    @NotNull @WebMethod
    Boolean dataSaveBinary(@Nullable SessionDTO session) throws ServerException;

    @NotNull @WebMethod
    Boolean dataLoadBinary(@Nullable SessionDTO session) throws ServerException;

    @NotNull @WebMethod
    Boolean dataClearJaxbXml(@Nullable SessionDTO session) throws ServerException;

    @NotNull @WebMethod
    Boolean dataSaveJaxbXml(@Nullable SessionDTO session) throws ServerException;

    @NotNull @WebMethod
    Boolean dataLoadJaxbXml(@Nullable SessionDTO session) throws ServerException;

    @NotNull @WebMethod
    Boolean dataClearJaxbJson(@Nullable SessionDTO session) throws ServerException;

    @NotNull @WebMethod
    Boolean dataSaveJaxbJson(@Nullable SessionDTO session) throws ServerException;

    @NotNull @WebMethod
    Boolean dataLoadJaxbJson(@Nullable SessionDTO session) throws ServerException;

    @NotNull @WebMethod
    Boolean dataClearFasterXml(@Nullable SessionDTO session) throws ServerException;

    @NotNull @WebMethod
    Boolean dataSaveFasterXml(@Nullable SessionDTO session) throws ServerException;

    @NotNull @WebMethod
    Boolean dataLoadFasterXml(@Nullable SessionDTO session) throws ServerException;

    @NotNull @WebMethod
    Boolean dataClearFasterJson(@Nullable SessionDTO session) throws ServerException;

    @NotNull @WebMethod
    Boolean dataSaveFasterJson(@Nullable SessionDTO session) throws ServerException;

    @NotNull @WebMethod
    Boolean dataLoadFasterJson(@Nullable SessionDTO session) throws ServerException;

}
