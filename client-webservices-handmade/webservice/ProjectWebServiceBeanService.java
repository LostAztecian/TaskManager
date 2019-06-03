package tm.client.webservice;

import tm.common.api.webservice.ProjectWebService;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebServiceClient;
import java.net.URL;

@WebServiceClient(
        name = "ProjectWebServiceBeanService",
        targetNamespace = "http://webservice.api.common.tm/",
        wsdlLocation = "http://localhost:8080/projectService?wsdl"
)
public class ProjectWebServiceBeanService extends Service {

    public ProjectWebServiceBeanService(URL wsdlDocumentLocation, QName serviceName) {
        super(wsdlDocumentLocation, serviceName);
    }

    public ProjectWebService getUserWebServiceImplPort() {
        return (ProjectWebService) super.getPort(
                new QName("http://webservice.api.common.tm/", "ProjectWebServiceBeanService"),
                ProjectWebService.class);
    }

}
