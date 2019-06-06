package tm.common.api.webservice;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.common.ServerException;
import tm.common.comparator.ComparatorType;
import tm.common.entity.Session;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(name = "serverService")
public interface ServerWebService {

    @NotNull @WebMethod
    String showAbout() throws ServerException;

    @NotNull @WebMethod
    Boolean shutdown(@Nullable Session session) throws ServerException;

    @NotNull @WebMethod
    String showHelp(@Nullable Session session) throws ServerException;

    @NotNull @WebMethod
    Boolean setSortMethod(@Nullable Session session, @Nullable ComparatorType comparatorType) throws ServerException;

    @NotNull @WebMethod
    Boolean dataClearBinary(@Nullable Session session) throws ServerException;

    @NotNull @WebMethod
    Boolean dataSaveBinary(@Nullable Session session) throws ServerException;

    @NotNull @WebMethod
    Boolean dataLoadBinary(@Nullable Session session) throws ServerException;

    @NotNull @WebMethod
    Boolean dataClearJaxbXml(@Nullable Session session) throws ServerException;

    @NotNull @WebMethod
    Boolean dataSaveJaxbXml(@Nullable Session session) throws ServerException;

    @NotNull @WebMethod
    Boolean dataLoadJaxbXml(@Nullable Session session) throws ServerException;

    @NotNull @WebMethod
    Boolean dataClearJaxbJson(@Nullable Session session) throws ServerException;

    @NotNull @WebMethod
    Boolean dataSaveJaxbJson(@Nullable Session session) throws ServerException;

    @NotNull @WebMethod
    Boolean dataLoadJaxbJson(@Nullable Session session) throws ServerException;

    @NotNull @WebMethod
    Boolean dataClearFasterXml(@Nullable Session session) throws ServerException;

    @NotNull @WebMethod
    Boolean dataSaveFasterXml(@Nullable Session session) throws ServerException;

    @NotNull @WebMethod
    Boolean dataLoadFasterXml(@Nullable Session session) throws ServerException;

    @NotNull @WebMethod
    Boolean dataClearFasterJson(@Nullable Session session) throws ServerException;

    @NotNull @WebMethod
    Boolean dataSaveFasterJson(@Nullable Session session) throws ServerException;

    @NotNull @WebMethod
    Boolean dataLoadFasterJson(@Nullable Session session) throws ServerException;

}
