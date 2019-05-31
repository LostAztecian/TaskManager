package tm.server.api.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.common.comparator.ComparatorType;
import tm.common.entity.Session;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public interface ServerService {

    @NotNull
    String showAbout();

    @NotNull
    Boolean shutdown(@Nullable Session session);

    @NotNull
    String showHelp(@Nullable Session session);

    @NotNull
    Boolean setSortMethod(@Nullable Session session, @Nullable ComparatorType comparatorType);

    @NotNull
    Boolean dataClearBinary(@Nullable Session session) throws IOException;

    @NotNull
    Boolean dataSaveBinary(@Nullable Session session) throws IOException;

    @NotNull
    Boolean dataLoadBinary(@Nullable Session session) throws IOException, ClassNotFoundException;

    @NotNull
    Boolean dataClearJaxbXml(@Nullable Session session) throws IOException;

    @NotNull
    Boolean dataSaveJaxbXml(@Nullable Session session) throws JAXBException, IOException;

    @NotNull
    Boolean dataLoadJaxbXml(@Nullable Session session) throws JAXBException;

    @NotNull
    Boolean dataClearJaxbJson(@Nullable Session session) throws IOException;

    @NotNull
    Boolean dataSaveJaxbJson(@Nullable Session session) throws IOException, JAXBException;

    @NotNull
    Boolean dataLoadJaxbJson(@Nullable Session session) throws JAXBException;

    @NotNull
    Boolean dataClearFasterXml(@Nullable Session session) throws IOException;

    @NotNull
    Boolean dataSaveFasterXml(@Nullable Session session) throws IOException;

    @NotNull
    Boolean dataLoadFasterXml(@Nullable Session session) throws IOException;

    @NotNull
    Boolean dataClearFasterJson(@Nullable Session session) throws IOException;

    @NotNull
    Boolean dataSaveFasterJson(@Nullable Session session) throws IOException;

    @NotNull
    Boolean dataLoadFasterJson(@Nullable Session session) throws IOException;

}
