package tm.client.webservice;

import tm.common.api.webservice.UserWebService;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebServiceClient;
import java.net.URL;

@WebServiceClient(
        name = "UserWebServiceBeanService",
        targetNamespace = "http://webservice.api.common.tm/",
        wsdlLocation = "http://localhost:8080/userService?wsdl"
)
public class UserWebServiceBeanService extends Service {

    public UserWebServiceBeanService(URL wsdlDocumentLocation, QName serviceName) {
        super(wsdlDocumentLocation, serviceName);
    }

    public UserWebService getUserWebServiceImplPort() {
        return (UserWebService) super.getPort(
                new QName("http://webservice.api.common.tm/", "UserWebServiceBeanService"),
                UserWebService.class);
    }

}
