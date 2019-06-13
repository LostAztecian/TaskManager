package tm.server.api.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.common.comparator.ComparatorType;
import tm.common.entity.SessionDTO;

public interface ServerService {

    @NotNull
    String showAbout() throws Exception;

    @NotNull
    Boolean shutdown(@Nullable SessionDTO session) throws Exception;

    @NotNull
    String showHelp(@Nullable SessionDTO session) throws Exception;

    @NotNull
    Boolean setSortMethod(@Nullable SessionDTO session, @Nullable ComparatorType comparatorType) throws Exception;

    @NotNull
    Boolean dataClearBinary(@Nullable SessionDTO session) throws Exception;

    @NotNull
    Boolean dataSaveBinary(@Nullable SessionDTO session) throws Exception;

    @NotNull
    Boolean dataLoadBinary(@Nullable SessionDTO session) throws Exception;

    @NotNull
    Boolean dataClearJaxbXml(@Nullable SessionDTO session) throws Exception;

    @NotNull
    Boolean dataSaveJaxbXml(@Nullable SessionDTO session) throws Exception;

    @NotNull
    Boolean dataLoadJaxbXml(@Nullable SessionDTO session) throws Exception;

    @NotNull
    Boolean dataClearJaxbJson(@Nullable SessionDTO session) throws Exception;

    @NotNull
    Boolean dataSaveJaxbJson(@Nullable SessionDTO session) throws Exception;

    @NotNull
    Boolean dataLoadJaxbJson(@Nullable SessionDTO session) throws Exception;

    @NotNull
    Boolean dataClearFasterXml(@Nullable SessionDTO session) throws Exception;

    @NotNull
    Boolean dataSaveFasterXml(@Nullable SessionDTO session) throws Exception;

    @NotNull
    Boolean dataLoadFasterXml(@Nullable SessionDTO session) throws Exception;

    @NotNull
    Boolean dataClearFasterJson(@Nullable SessionDTO session) throws Exception;

    @NotNull
    Boolean dataSaveFasterJson(@Nullable SessionDTO session) throws Exception;

    @NotNull
    Boolean dataLoadFasterJson(@Nullable SessionDTO session) throws Exception;

}
