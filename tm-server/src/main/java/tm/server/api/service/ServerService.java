package tm.server.api.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.common.comparator.ComparatorType;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public interface ServerService {

    @NotNull
    String showAbout();

    @NotNull
    Boolean shutdown();

    @NotNull
    String showHelp();

    @NotNull
    Boolean setSortMethod(@Nullable ComparatorType comparatorType);

    @NotNull
    Boolean dataClearBinary() throws IOException;

    @NotNull
    Boolean dataSaveBinary() throws IOException;

    @NotNull
    Boolean dataLoadBinary() throws IOException, ClassNotFoundException;

    @NotNull
    Boolean dataClearJaxbXml() throws IOException;

    @NotNull
    Boolean dataSaveJaxbXml() throws JAXBException, IOException;

    @NotNull
    Boolean dataLoadJaxbXml() throws JAXBException;

    @NotNull
    Boolean dataClearJaxbJson() throws IOException;

    @NotNull
    Boolean dataSaveJaxbJson() throws IOException, JAXBException;

    @NotNull
    Boolean dataLoadJaxbJson() throws JAXBException;

    @NotNull
    Boolean dataClearFasterXml() throws IOException;

    @NotNull
    Boolean dataSaveFasterXml() throws IOException;

    @NotNull
    Boolean dataLoadFasterXml() throws IOException;

    @NotNull
    Boolean dataClearFasterJson() throws IOException;

    @NotNull
    Boolean dataSaveFasterJson() throws IOException;

    @NotNull
    Boolean dataLoadFasterJson() throws IOException;

}
