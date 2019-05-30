package tm.server.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tm.common.comparator.ComparatorType;
import tm.server.api.ServiceLocator;
import tm.server.api.service.ServerService;
import tm.server.command.general.AboutCommand;

import javax.xml.ws.Endpoint;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ServerServiceImpl implements ServerService {

    private final ServiceLocator serviceLocator;

    public ServerServiceImpl(ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    @Override @NotNull
    public String showAbout() {
        String out = "Sorry, unable to find config.properties";
        try (InputStream input = AboutCommand.class.getClassLoader().getResourceAsStream("application.properties")) {
            Properties prop = new Properties();
            if (input == null) {
                return out;
            }
            //load a properties file from class path, inside static method
            prop.load(input);
            //get the property value and print it out
            out = String.format("AppName: %s, Version: %s, Build#: %s %n%n",
                    prop.getProperty("application.name"),
                    prop.getProperty("application.version"),
                    prop.getProperty("application.build") );
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return out;
    }

    @Override @NotNull
    public Boolean shutdown() {
        serviceLocator.getEndpoints().forEach(Endpoint::stop);
        System.out.println("[ENDPOINTS STOPPED]");
        serviceLocator.terminate();
        System.out.println("[SERVER TERMINATED]");
        return true; //TODO check if its working after stopping endpoints
    }

    @Override @NotNull
    public String showHelp() {
        final boolean loggedIn = serviceLocator.getCurrentUser() != null;
        final StringBuilder sb = new StringBuilder();
        serviceLocator.getCommands().values()
                .forEach(c -> {if (!c.isPrivate() || loggedIn) sb.append(c).append("\n");});
        return sb.toString();
    }

    @Override @NotNull
    public Boolean setSortMethod(@Nullable final ComparatorType comparatorType) {
        if (comparatorType == null) return false;
        serviceLocator.setCurrentSortMethod(comparatorType.comparator);
        return true;
    }

    @Override @NotNull
    public Boolean dataClearBinary() {
        return null;
    }

    @Override @NotNull
    public Boolean dataSaveBinary() {
        return null;
    }

    @Override @NotNull
    public Boolean dataLoadBinary() {
        return null;
    }

    @Override @NotNull
    public Boolean dataClearJaxbXml() {
        return null;
    }

    @Override @NotNull
    public Boolean dataSaveJaxbXml() {
        return null;
    }

    @Override @NotNull
    public Boolean dataLoadJaxbXml() {
        return null;
    }

    @Override @NotNull
    public Boolean dataClearJaxbJson() {
        return null;
    }

    @Override @NotNull
    public Boolean dataSaveJaxbJson() {
        return null;
    }

    @Override @NotNull
    public Boolean dataLoadJaxbJson() {
        return null;
    }

    @Override @NotNull
    public Boolean dataClearFasterXml() {
        return null;
    }

    @Override @NotNull
    public Boolean dataSaveFasterXml() {
        return null;
    }

    @Override @NotNull
    public Boolean dataLoadFasterXml() {
        return null;
    }

    @Override @NotNull
    public Boolean dataClearFasterJson() {
        return null;
    }

    @Override @NotNull
    public Boolean dataSaveFasterJson() {
        return null;
    }

    @Override @NotNull
    public Boolean dataLoadFasterJson() {
        return null;
    }

}
