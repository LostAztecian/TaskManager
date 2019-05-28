package tm.server.command.general;

import org.jetbrains.annotations.NotNull;
import tm.server.command.AbstractCommand;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AboutCommand extends AbstractCommand {

    @NotNull public static final String NAME = "about";
    @NotNull private static final String DESCRIPTION = "show build information";

    @Override @NotNull
    public String getName() {
        return NAME;
    }

    @Override @NotNull
    public String getDescription() {
        return DESCRIPTION;
    }

    @Override
    public boolean isPrivate() {
        return false;
    }

    @Override
    protected void run() throws IOException {
        try (InputStream input = AboutCommand.class.getClassLoader().getResourceAsStream("application.properties")) {
            Properties prop = new Properties();
            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
                return;
            }
            //load a properties file from class path, inside static method
            prop.load(input);
            //get the property value and print it out
            System.out.printf("AppName: %s, Version: %s, Build#: %s %n%n",
                    prop.getProperty("application.name"),
                    prop.getProperty("application.version"),
                    prop.getProperty("application.build") );
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
