package tm.server.webservice;

import org.jetbrains.annotations.NotNull;
import tm.server.api.webservice.ProjectWebService;
import tm.server.entity.Project;
import tm.server.entity.Task;

import javax.jws.WebService;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.ByteArrayOutputStream;

@WebService(endpointInterface = "tm.server.api.webservice.ProjectWebService")
public class ProjectWebServiceBean implements ProjectWebService {

    @NotNull private final Marshaller marshaller;
    @NotNull private final ByteArrayOutputStream bos = new ByteArrayOutputStream();

    public ProjectWebServiceBean() throws JAXBException {
        marshaller = JAXBContext.newInstance(Task.class).createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
    }

    @Override
    public String getNew() throws JAXBException {
        final Project project = new Project();
        project.setName("pr-01");
        project.setDescription("desc-01");
        project.setUserId("-1");

        bos.reset();
        marshaller.marshal(project, bos);
        return bos.toString();
    }
}
