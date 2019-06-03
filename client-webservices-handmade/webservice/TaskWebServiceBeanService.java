package tm.client.webservice;

import tm.common.api.webservice.TaskWebService;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebServiceClient;
import java.net.URL;

@WebServiceClient(
        name = "TaskWebServiceBeanService",
        targetNamespace = "http://webservice.api.common.tm/",
        wsdlLocation = "http://localhost:8080/projectService?wsdl"
)
public class TaskWebServiceBeanService extends Service {

    public TaskWebServiceBeanService(URL wsdlDocumentLocation, QName serviceName) {
        super(wsdlDocumentLocation, serviceName);
    }

    public TaskWebService getUserWebServiceImplPort() {
        return (TaskWebService) super.getPort(
                new QName("http://webservice.api.common.tm/", "TaskWebServiceBeanService"),
                TaskWebService.class);
    }
    
}
