package tm.server.webservice;

import org.jetbrains.annotations.NotNull;
import tm.server.api.webservice.TaskWebService;
import tm.server.entity.Task;

import javax.jws.WebService;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.ByteArrayOutputStream;

@WebService(endpointInterface = "tm.server.api.webservice.TaskWebService")
public class TaskWebServiceBean implements TaskWebService {

    @NotNull private final Marshaller marshaller;
    @NotNull private final ByteArrayOutputStream bos = new ByteArrayOutputStream();

    public TaskWebServiceBean() throws JAXBException {
        marshaller = JAXBContext.newInstance(Task.class).createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
    }

    @Override
    public String getNew() throws JAXBException {
        final Task task = new Task();
        task.setName("ts001");
        task.setDescription("taskdesc");
        task.setProjectId("prID");
        task.setUserId("uID");

        bos.reset();
        marshaller.marshal(task, bos);

        return bos.toString();
    }

}
