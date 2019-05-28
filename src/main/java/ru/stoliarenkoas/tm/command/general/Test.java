package ru.stoliarenkoas.tm.command.general;

import org.eclipse.persistence.jaxb.JAXBContextProperties;
import org.eclipse.persistence.jaxb.MarshallerProperties;
import org.eclipse.persistence.jaxb.UnmarshallerProperties;
import org.jetbrains.annotations.NotNull;
import ru.stoliarenkoas.tm.command.AbstractCommand;
import ru.stoliarenkoas.tm.entity.Project;

import javax.xml.bind.*;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Test extends AbstractCommand {

    @NotNull public static final String NAME = "test";
    @NotNull private static final String DESCRIPTION = "test";

    @Override @NotNull
    public String getName() { return NAME; }

    @Override @NotNull
    public String getDescription() { return DESCRIPTION; }

    @Override
    public boolean isPrivate() {
        return false;
    }

    @Override
    protected void run() throws Exception {
        Project project = new Project();
        project.setName("nnm");
        project.setDescription("dsc");
        project.setUserId("pid111");
        project.setStartDate(new Date());
        project.setEndDate(new Date());
        try {
            System.out.println(project);
            Map<String, Object> properties = new HashMap<>();
            properties.put(JAXBContextProperties.MEDIA_TYPE, "application/json");
            properties.put(JAXBContextProperties.JSON_INCLUDE_ROOT, false);
            JAXBContext context = org.eclipse.persistence.jaxb.JAXBContextFactory.createContext(new Class[]{Project.class}, properties);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(MarshallerProperties.MEDIA_TYPE, "application/json");
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            ByteArrayOutputStream container = new ByteArrayOutputStream();
            marshaller.marshal(project, container);
            ByteArrayInputStream input = new ByteArrayInputStream(container.toByteArray());
            Unmarshaller unmarshaller = context.createUnmarshaller();
            unmarshaller.setProperty(UnmarshallerProperties.MEDIA_TYPE, "application/json");
            StreamSource json = new StreamSource(input);
            Project unmarshalled = unmarshaller.unmarshal(json, Project.class).getValue();
            System.out.println(unmarshalled);

        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }

}
