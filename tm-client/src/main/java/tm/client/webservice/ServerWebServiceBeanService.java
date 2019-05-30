package tm.client.webservice;

import tm.common.api.webservice.ServerWebService;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebServiceClient;
import java.net.URL;

@WebServiceClient (
        name = "ServerWebServiceBeanService",
        targetNamespace = "http://webservice.api.common.tm/",
        wsdlLocation = "http://localhost:8080/serverService?wsdl"
)
public class ServerWebServiceBeanService extends Service {

    public ServerWebServiceBeanService(URL wsdlDocumentLocation, QName serviceName) {
        super(wsdlDocumentLocation, serviceName);
    }

    public ServerWebService getServerWebServiceImplPort() {
        return (ServerWebService) super.getPort(
                new QName("http://webservice.api.common.tm/", "ServerWebServiceBeanService"),
                ServerWebService.class);
    }

}
