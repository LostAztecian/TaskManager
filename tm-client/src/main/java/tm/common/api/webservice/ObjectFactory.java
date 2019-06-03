
package tm.common.api.webservice;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the tm.common.api.webservice package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _DataClearBinary_QNAME = new QName("http://webservice.api.common.tm/", "dataClearBinary");
    private final static QName _DataClearBinaryResponse_QNAME = new QName("http://webservice.api.common.tm/", "dataClearBinaryResponse");
    private final static QName _DataClearFasterJson_QNAME = new QName("http://webservice.api.common.tm/", "dataClearFasterJson");
    private final static QName _DataClearFasterJsonResponse_QNAME = new QName("http://webservice.api.common.tm/", "dataClearFasterJsonResponse");
    private final static QName _DataClearFasterXml_QNAME = new QName("http://webservice.api.common.tm/", "dataClearFasterXml");
    private final static QName _DataClearFasterXmlResponse_QNAME = new QName("http://webservice.api.common.tm/", "dataClearFasterXmlResponse");
    private final static QName _DataClearJaxbJson_QNAME = new QName("http://webservice.api.common.tm/", "dataClearJaxbJson");
    private final static QName _DataClearJaxbJsonResponse_QNAME = new QName("http://webservice.api.common.tm/", "dataClearJaxbJsonResponse");
    private final static QName _DataClearJaxbXml_QNAME = new QName("http://webservice.api.common.tm/", "dataClearJaxbXml");
    private final static QName _DataClearJaxbXmlResponse_QNAME = new QName("http://webservice.api.common.tm/", "dataClearJaxbXmlResponse");
    private final static QName _DataLoadBinary_QNAME = new QName("http://webservice.api.common.tm/", "dataLoadBinary");
    private final static QName _DataLoadBinaryResponse_QNAME = new QName("http://webservice.api.common.tm/", "dataLoadBinaryResponse");
    private final static QName _DataLoadFasterJson_QNAME = new QName("http://webservice.api.common.tm/", "dataLoadFasterJson");
    private final static QName _DataLoadFasterJsonResponse_QNAME = new QName("http://webservice.api.common.tm/", "dataLoadFasterJsonResponse");
    private final static QName _DataLoadFasterXml_QNAME = new QName("http://webservice.api.common.tm/", "dataLoadFasterXml");
    private final static QName _DataLoadFasterXmlResponse_QNAME = new QName("http://webservice.api.common.tm/", "dataLoadFasterXmlResponse");
    private final static QName _DataLoadJaxbJson_QNAME = new QName("http://webservice.api.common.tm/", "dataLoadJaxbJson");
    private final static QName _DataLoadJaxbJsonResponse_QNAME = new QName("http://webservice.api.common.tm/", "dataLoadJaxbJsonResponse");
    private final static QName _DataLoadJaxbXml_QNAME = new QName("http://webservice.api.common.tm/", "dataLoadJaxbXml");
    private final static QName _DataLoadJaxbXmlResponse_QNAME = new QName("http://webservice.api.common.tm/", "dataLoadJaxbXmlResponse");
    private final static QName _DataSaveBinary_QNAME = new QName("http://webservice.api.common.tm/", "dataSaveBinary");
    private final static QName _DataSaveBinaryResponse_QNAME = new QName("http://webservice.api.common.tm/", "dataSaveBinaryResponse");
    private final static QName _DataSaveFasterJson_QNAME = new QName("http://webservice.api.common.tm/", "dataSaveFasterJson");
    private final static QName _DataSaveFasterJsonResponse_QNAME = new QName("http://webservice.api.common.tm/", "dataSaveFasterJsonResponse");
    private final static QName _DataSaveFasterXml_QNAME = new QName("http://webservice.api.common.tm/", "dataSaveFasterXml");
    private final static QName _DataSaveFasterXmlResponse_QNAME = new QName("http://webservice.api.common.tm/", "dataSaveFasterXmlResponse");
    private final static QName _DataSaveJaxbJson_QNAME = new QName("http://webservice.api.common.tm/", "dataSaveJaxbJson");
    private final static QName _DataSaveJaxbJsonResponse_QNAME = new QName("http://webservice.api.common.tm/", "dataSaveJaxbJsonResponse");
    private final static QName _DataSaveJaxbXml_QNAME = new QName("http://webservice.api.common.tm/", "dataSaveJaxbXml");
    private final static QName _DataSaveJaxbXmlResponse_QNAME = new QName("http://webservice.api.common.tm/", "dataSaveJaxbXmlResponse");
    private final static QName _SetSortMethod_QNAME = new QName("http://webservice.api.common.tm/", "setSortMethod");
    private final static QName _SetSortMethodResponse_QNAME = new QName("http://webservice.api.common.tm/", "setSortMethodResponse");
    private final static QName _ShowAbout_QNAME = new QName("http://webservice.api.common.tm/", "showAbout");
    private final static QName _ShowAboutResponse_QNAME = new QName("http://webservice.api.common.tm/", "showAboutResponse");
    private final static QName _ShowHelp_QNAME = new QName("http://webservice.api.common.tm/", "showHelp");
    private final static QName _ShowHelpResponse_QNAME = new QName("http://webservice.api.common.tm/", "showHelpResponse");
    private final static QName _Shutdown_QNAME = new QName("http://webservice.api.common.tm/", "shutdown");
    private final static QName _ShutdownResponse_QNAME = new QName("http://webservice.api.common.tm/", "shutdownResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: tm.common.api.webservice
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DataClearBinary }
     * 
     */
    public DataClearBinary createDataClearBinary() {
        return new DataClearBinary();
    }

    /**
     * Create an instance of {@link DataClearBinaryResponse }
     * 
     */
    public DataClearBinaryResponse createDataClearBinaryResponse() {
        return new DataClearBinaryResponse();
    }

    /**
     * Create an instance of {@link DataClearFasterJson }
     * 
     */
    public DataClearFasterJson createDataClearFasterJson() {
        return new DataClearFasterJson();
    }

    /**
     * Create an instance of {@link DataClearFasterJsonResponse }
     * 
     */
    public DataClearFasterJsonResponse createDataClearFasterJsonResponse() {
        return new DataClearFasterJsonResponse();
    }

    /**
     * Create an instance of {@link DataClearFasterXml }
     * 
     */
    public DataClearFasterXml createDataClearFasterXml() {
        return new DataClearFasterXml();
    }

    /**
     * Create an instance of {@link DataClearFasterXmlResponse }
     * 
     */
    public DataClearFasterXmlResponse createDataClearFasterXmlResponse() {
        return new DataClearFasterXmlResponse();
    }

    /**
     * Create an instance of {@link DataClearJaxbJson }
     * 
     */
    public DataClearJaxbJson createDataClearJaxbJson() {
        return new DataClearJaxbJson();
    }

    /**
     * Create an instance of {@link DataClearJaxbJsonResponse }
     * 
     */
    public DataClearJaxbJsonResponse createDataClearJaxbJsonResponse() {
        return new DataClearJaxbJsonResponse();
    }

    /**
     * Create an instance of {@link DataClearJaxbXml }
     * 
     */
    public DataClearJaxbXml createDataClearJaxbXml() {
        return new DataClearJaxbXml();
    }

    /**
     * Create an instance of {@link DataClearJaxbXmlResponse }
     * 
     */
    public DataClearJaxbXmlResponse createDataClearJaxbXmlResponse() {
        return new DataClearJaxbXmlResponse();
    }

    /**
     * Create an instance of {@link DataLoadBinary }
     * 
     */
    public DataLoadBinary createDataLoadBinary() {
        return new DataLoadBinary();
    }

    /**
     * Create an instance of {@link DataLoadBinaryResponse }
     * 
     */
    public DataLoadBinaryResponse createDataLoadBinaryResponse() {
        return new DataLoadBinaryResponse();
    }

    /**
     * Create an instance of {@link DataLoadFasterJson }
     * 
     */
    public DataLoadFasterJson createDataLoadFasterJson() {
        return new DataLoadFasterJson();
    }

    /**
     * Create an instance of {@link DataLoadFasterJsonResponse }
     * 
     */
    public DataLoadFasterJsonResponse createDataLoadFasterJsonResponse() {
        return new DataLoadFasterJsonResponse();
    }

    /**
     * Create an instance of {@link DataLoadFasterXml }
     * 
     */
    public DataLoadFasterXml createDataLoadFasterXml() {
        return new DataLoadFasterXml();
    }

    /**
     * Create an instance of {@link DataLoadFasterXmlResponse }
     * 
     */
    public DataLoadFasterXmlResponse createDataLoadFasterXmlResponse() {
        return new DataLoadFasterXmlResponse();
    }

    /**
     * Create an instance of {@link DataLoadJaxbJson }
     * 
     */
    public DataLoadJaxbJson createDataLoadJaxbJson() {
        return new DataLoadJaxbJson();
    }

    /**
     * Create an instance of {@link DataLoadJaxbJsonResponse }
     * 
     */
    public DataLoadJaxbJsonResponse createDataLoadJaxbJsonResponse() {
        return new DataLoadJaxbJsonResponse();
    }

    /**
     * Create an instance of {@link DataLoadJaxbXml }
     * 
     */
    public DataLoadJaxbXml createDataLoadJaxbXml() {
        return new DataLoadJaxbXml();
    }

    /**
     * Create an instance of {@link DataLoadJaxbXmlResponse }
     * 
     */
    public DataLoadJaxbXmlResponse createDataLoadJaxbXmlResponse() {
        return new DataLoadJaxbXmlResponse();
    }

    /**
     * Create an instance of {@link DataSaveBinary }
     * 
     */
    public DataSaveBinary createDataSaveBinary() {
        return new DataSaveBinary();
    }

    /**
     * Create an instance of {@link DataSaveBinaryResponse }
     * 
     */
    public DataSaveBinaryResponse createDataSaveBinaryResponse() {
        return new DataSaveBinaryResponse();
    }

    /**
     * Create an instance of {@link DataSaveFasterJson }
     * 
     */
    public DataSaveFasterJson createDataSaveFasterJson() {
        return new DataSaveFasterJson();
    }

    /**
     * Create an instance of {@link DataSaveFasterJsonResponse }
     * 
     */
    public DataSaveFasterJsonResponse createDataSaveFasterJsonResponse() {
        return new DataSaveFasterJsonResponse();
    }

    /**
     * Create an instance of {@link DataSaveFasterXml }
     * 
     */
    public DataSaveFasterXml createDataSaveFasterXml() {
        return new DataSaveFasterXml();
    }

    /**
     * Create an instance of {@link DataSaveFasterXmlResponse }
     * 
     */
    public DataSaveFasterXmlResponse createDataSaveFasterXmlResponse() {
        return new DataSaveFasterXmlResponse();
    }

    /**
     * Create an instance of {@link DataSaveJaxbJson }
     * 
     */
    public DataSaveJaxbJson createDataSaveJaxbJson() {
        return new DataSaveJaxbJson();
    }

    /**
     * Create an instance of {@link DataSaveJaxbJsonResponse }
     * 
     */
    public DataSaveJaxbJsonResponse createDataSaveJaxbJsonResponse() {
        return new DataSaveJaxbJsonResponse();
    }

    /**
     * Create an instance of {@link DataSaveJaxbXml }
     * 
     */
    public DataSaveJaxbXml createDataSaveJaxbXml() {
        return new DataSaveJaxbXml();
    }

    /**
     * Create an instance of {@link DataSaveJaxbXmlResponse }
     * 
     */
    public DataSaveJaxbXmlResponse createDataSaveJaxbXmlResponse() {
        return new DataSaveJaxbXmlResponse();
    }

    /**
     * Create an instance of {@link SetSortMethod }
     * 
     */
    public SetSortMethod createSetSortMethod() {
        return new SetSortMethod();
    }

    /**
     * Create an instance of {@link SetSortMethodResponse }
     * 
     */
    public SetSortMethodResponse createSetSortMethodResponse() {
        return new SetSortMethodResponse();
    }

    /**
     * Create an instance of {@link ShowAbout }
     * 
     */
    public ShowAbout createShowAbout() {
        return new ShowAbout();
    }

    /**
     * Create an instance of {@link ShowAboutResponse }
     * 
     */
    public ShowAboutResponse createShowAboutResponse() {
        return new ShowAboutResponse();
    }

    /**
     * Create an instance of {@link ShowHelp }
     * 
     */
    public ShowHelp createShowHelp() {
        return new ShowHelp();
    }

    /**
     * Create an instance of {@link ShowHelpResponse }
     * 
     */
    public ShowHelpResponse createShowHelpResponse() {
        return new ShowHelpResponse();
    }

    /**
     * Create an instance of {@link Shutdown }
     * 
     */
    public Shutdown createShutdown() {
        return new Shutdown();
    }

    /**
     * Create an instance of {@link ShutdownResponse }
     * 
     */
    public ShutdownResponse createShutdownResponse() {
        return new ShutdownResponse();
    }

    /**
     * Create an instance of {@link Session }
     * 
     */
    public Session createSession() {
        return new Session();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DataClearBinary }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DataClearBinary }{@code >}
     */
    @XmlElementDecl(namespace = "http://webservice.api.common.tm/", name = "dataClearBinary")
    public JAXBElement<DataClearBinary> createDataClearBinary(DataClearBinary value) {
        return new JAXBElement<DataClearBinary>(_DataClearBinary_QNAME, DataClearBinary.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DataClearBinaryResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DataClearBinaryResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://webservice.api.common.tm/", name = "dataClearBinaryResponse")
    public JAXBElement<DataClearBinaryResponse> createDataClearBinaryResponse(DataClearBinaryResponse value) {
        return new JAXBElement<DataClearBinaryResponse>(_DataClearBinaryResponse_QNAME, DataClearBinaryResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DataClearFasterJson }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DataClearFasterJson }{@code >}
     */
    @XmlElementDecl(namespace = "http://webservice.api.common.tm/", name = "dataClearFasterJson")
    public JAXBElement<DataClearFasterJson> createDataClearFasterJson(DataClearFasterJson value) {
        return new JAXBElement<DataClearFasterJson>(_DataClearFasterJson_QNAME, DataClearFasterJson.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DataClearFasterJsonResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DataClearFasterJsonResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://webservice.api.common.tm/", name = "dataClearFasterJsonResponse")
    public JAXBElement<DataClearFasterJsonResponse> createDataClearFasterJsonResponse(DataClearFasterJsonResponse value) {
        return new JAXBElement<DataClearFasterJsonResponse>(_DataClearFasterJsonResponse_QNAME, DataClearFasterJsonResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DataClearFasterXml }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DataClearFasterXml }{@code >}
     */
    @XmlElementDecl(namespace = "http://webservice.api.common.tm/", name = "dataClearFasterXml")
    public JAXBElement<DataClearFasterXml> createDataClearFasterXml(DataClearFasterXml value) {
        return new JAXBElement<DataClearFasterXml>(_DataClearFasterXml_QNAME, DataClearFasterXml.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DataClearFasterXmlResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DataClearFasterXmlResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://webservice.api.common.tm/", name = "dataClearFasterXmlResponse")
    public JAXBElement<DataClearFasterXmlResponse> createDataClearFasterXmlResponse(DataClearFasterXmlResponse value) {
        return new JAXBElement<DataClearFasterXmlResponse>(_DataClearFasterXmlResponse_QNAME, DataClearFasterXmlResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DataClearJaxbJson }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DataClearJaxbJson }{@code >}
     */
    @XmlElementDecl(namespace = "http://webservice.api.common.tm/", name = "dataClearJaxbJson")
    public JAXBElement<DataClearJaxbJson> createDataClearJaxbJson(DataClearJaxbJson value) {
        return new JAXBElement<DataClearJaxbJson>(_DataClearJaxbJson_QNAME, DataClearJaxbJson.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DataClearJaxbJsonResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DataClearJaxbJsonResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://webservice.api.common.tm/", name = "dataClearJaxbJsonResponse")
    public JAXBElement<DataClearJaxbJsonResponse> createDataClearJaxbJsonResponse(DataClearJaxbJsonResponse value) {
        return new JAXBElement<DataClearJaxbJsonResponse>(_DataClearJaxbJsonResponse_QNAME, DataClearJaxbJsonResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DataClearJaxbXml }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DataClearJaxbXml }{@code >}
     */
    @XmlElementDecl(namespace = "http://webservice.api.common.tm/", name = "dataClearJaxbXml")
    public JAXBElement<DataClearJaxbXml> createDataClearJaxbXml(DataClearJaxbXml value) {
        return new JAXBElement<DataClearJaxbXml>(_DataClearJaxbXml_QNAME, DataClearJaxbXml.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DataClearJaxbXmlResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DataClearJaxbXmlResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://webservice.api.common.tm/", name = "dataClearJaxbXmlResponse")
    public JAXBElement<DataClearJaxbXmlResponse> createDataClearJaxbXmlResponse(DataClearJaxbXmlResponse value) {
        return new JAXBElement<DataClearJaxbXmlResponse>(_DataClearJaxbXmlResponse_QNAME, DataClearJaxbXmlResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DataLoadBinary }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DataLoadBinary }{@code >}
     */
    @XmlElementDecl(namespace = "http://webservice.api.common.tm/", name = "dataLoadBinary")
    public JAXBElement<DataLoadBinary> createDataLoadBinary(DataLoadBinary value) {
        return new JAXBElement<DataLoadBinary>(_DataLoadBinary_QNAME, DataLoadBinary.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DataLoadBinaryResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DataLoadBinaryResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://webservice.api.common.tm/", name = "dataLoadBinaryResponse")
    public JAXBElement<DataLoadBinaryResponse> createDataLoadBinaryResponse(DataLoadBinaryResponse value) {
        return new JAXBElement<DataLoadBinaryResponse>(_DataLoadBinaryResponse_QNAME, DataLoadBinaryResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DataLoadFasterJson }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DataLoadFasterJson }{@code >}
     */
    @XmlElementDecl(namespace = "http://webservice.api.common.tm/", name = "dataLoadFasterJson")
    public JAXBElement<DataLoadFasterJson> createDataLoadFasterJson(DataLoadFasterJson value) {
        return new JAXBElement<DataLoadFasterJson>(_DataLoadFasterJson_QNAME, DataLoadFasterJson.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DataLoadFasterJsonResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DataLoadFasterJsonResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://webservice.api.common.tm/", name = "dataLoadFasterJsonResponse")
    public JAXBElement<DataLoadFasterJsonResponse> createDataLoadFasterJsonResponse(DataLoadFasterJsonResponse value) {
        return new JAXBElement<DataLoadFasterJsonResponse>(_DataLoadFasterJsonResponse_QNAME, DataLoadFasterJsonResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DataLoadFasterXml }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DataLoadFasterXml }{@code >}
     */
    @XmlElementDecl(namespace = "http://webservice.api.common.tm/", name = "dataLoadFasterXml")
    public JAXBElement<DataLoadFasterXml> createDataLoadFasterXml(DataLoadFasterXml value) {
        return new JAXBElement<DataLoadFasterXml>(_DataLoadFasterXml_QNAME, DataLoadFasterXml.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DataLoadFasterXmlResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DataLoadFasterXmlResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://webservice.api.common.tm/", name = "dataLoadFasterXmlResponse")
    public JAXBElement<DataLoadFasterXmlResponse> createDataLoadFasterXmlResponse(DataLoadFasterXmlResponse value) {
        return new JAXBElement<DataLoadFasterXmlResponse>(_DataLoadFasterXmlResponse_QNAME, DataLoadFasterXmlResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DataLoadJaxbJson }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DataLoadJaxbJson }{@code >}
     */
    @XmlElementDecl(namespace = "http://webservice.api.common.tm/", name = "dataLoadJaxbJson")
    public JAXBElement<DataLoadJaxbJson> createDataLoadJaxbJson(DataLoadJaxbJson value) {
        return new JAXBElement<DataLoadJaxbJson>(_DataLoadJaxbJson_QNAME, DataLoadJaxbJson.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DataLoadJaxbJsonResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DataLoadJaxbJsonResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://webservice.api.common.tm/", name = "dataLoadJaxbJsonResponse")
    public JAXBElement<DataLoadJaxbJsonResponse> createDataLoadJaxbJsonResponse(DataLoadJaxbJsonResponse value) {
        return new JAXBElement<DataLoadJaxbJsonResponse>(_DataLoadJaxbJsonResponse_QNAME, DataLoadJaxbJsonResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DataLoadJaxbXml }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DataLoadJaxbXml }{@code >}
     */
    @XmlElementDecl(namespace = "http://webservice.api.common.tm/", name = "dataLoadJaxbXml")
    public JAXBElement<DataLoadJaxbXml> createDataLoadJaxbXml(DataLoadJaxbXml value) {
        return new JAXBElement<DataLoadJaxbXml>(_DataLoadJaxbXml_QNAME, DataLoadJaxbXml.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DataLoadJaxbXmlResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DataLoadJaxbXmlResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://webservice.api.common.tm/", name = "dataLoadJaxbXmlResponse")
    public JAXBElement<DataLoadJaxbXmlResponse> createDataLoadJaxbXmlResponse(DataLoadJaxbXmlResponse value) {
        return new JAXBElement<DataLoadJaxbXmlResponse>(_DataLoadJaxbXmlResponse_QNAME, DataLoadJaxbXmlResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DataSaveBinary }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DataSaveBinary }{@code >}
     */
    @XmlElementDecl(namespace = "http://webservice.api.common.tm/", name = "dataSaveBinary")
    public JAXBElement<DataSaveBinary> createDataSaveBinary(DataSaveBinary value) {
        return new JAXBElement<DataSaveBinary>(_DataSaveBinary_QNAME, DataSaveBinary.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DataSaveBinaryResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DataSaveBinaryResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://webservice.api.common.tm/", name = "dataSaveBinaryResponse")
    public JAXBElement<DataSaveBinaryResponse> createDataSaveBinaryResponse(DataSaveBinaryResponse value) {
        return new JAXBElement<DataSaveBinaryResponse>(_DataSaveBinaryResponse_QNAME, DataSaveBinaryResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DataSaveFasterJson }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DataSaveFasterJson }{@code >}
     */
    @XmlElementDecl(namespace = "http://webservice.api.common.tm/", name = "dataSaveFasterJson")
    public JAXBElement<DataSaveFasterJson> createDataSaveFasterJson(DataSaveFasterJson value) {
        return new JAXBElement<DataSaveFasterJson>(_DataSaveFasterJson_QNAME, DataSaveFasterJson.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DataSaveFasterJsonResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DataSaveFasterJsonResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://webservice.api.common.tm/", name = "dataSaveFasterJsonResponse")
    public JAXBElement<DataSaveFasterJsonResponse> createDataSaveFasterJsonResponse(DataSaveFasterJsonResponse value) {
        return new JAXBElement<DataSaveFasterJsonResponse>(_DataSaveFasterJsonResponse_QNAME, DataSaveFasterJsonResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DataSaveFasterXml }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DataSaveFasterXml }{@code >}
     */
    @XmlElementDecl(namespace = "http://webservice.api.common.tm/", name = "dataSaveFasterXml")
    public JAXBElement<DataSaveFasterXml> createDataSaveFasterXml(DataSaveFasterXml value) {
        return new JAXBElement<DataSaveFasterXml>(_DataSaveFasterXml_QNAME, DataSaveFasterXml.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DataSaveFasterXmlResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DataSaveFasterXmlResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://webservice.api.common.tm/", name = "dataSaveFasterXmlResponse")
    public JAXBElement<DataSaveFasterXmlResponse> createDataSaveFasterXmlResponse(DataSaveFasterXmlResponse value) {
        return new JAXBElement<DataSaveFasterXmlResponse>(_DataSaveFasterXmlResponse_QNAME, DataSaveFasterXmlResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DataSaveJaxbJson }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DataSaveJaxbJson }{@code >}
     */
    @XmlElementDecl(namespace = "http://webservice.api.common.tm/", name = "dataSaveJaxbJson")
    public JAXBElement<DataSaveJaxbJson> createDataSaveJaxbJson(DataSaveJaxbJson value) {
        return new JAXBElement<DataSaveJaxbJson>(_DataSaveJaxbJson_QNAME, DataSaveJaxbJson.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DataSaveJaxbJsonResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DataSaveJaxbJsonResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://webservice.api.common.tm/", name = "dataSaveJaxbJsonResponse")
    public JAXBElement<DataSaveJaxbJsonResponse> createDataSaveJaxbJsonResponse(DataSaveJaxbJsonResponse value) {
        return new JAXBElement<DataSaveJaxbJsonResponse>(_DataSaveJaxbJsonResponse_QNAME, DataSaveJaxbJsonResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DataSaveJaxbXml }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DataSaveJaxbXml }{@code >}
     */
    @XmlElementDecl(namespace = "http://webservice.api.common.tm/", name = "dataSaveJaxbXml")
    public JAXBElement<DataSaveJaxbXml> createDataSaveJaxbXml(DataSaveJaxbXml value) {
        return new JAXBElement<DataSaveJaxbXml>(_DataSaveJaxbXml_QNAME, DataSaveJaxbXml.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DataSaveJaxbXmlResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DataSaveJaxbXmlResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://webservice.api.common.tm/", name = "dataSaveJaxbXmlResponse")
    public JAXBElement<DataSaveJaxbXmlResponse> createDataSaveJaxbXmlResponse(DataSaveJaxbXmlResponse value) {
        return new JAXBElement<DataSaveJaxbXmlResponse>(_DataSaveJaxbXmlResponse_QNAME, DataSaveJaxbXmlResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SetSortMethod }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link SetSortMethod }{@code >}
     */
    @XmlElementDecl(namespace = "http://webservice.api.common.tm/", name = "setSortMethod")
    public JAXBElement<SetSortMethod> createSetSortMethod(SetSortMethod value) {
        return new JAXBElement<SetSortMethod>(_SetSortMethod_QNAME, SetSortMethod.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SetSortMethodResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link SetSortMethodResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://webservice.api.common.tm/", name = "setSortMethodResponse")
    public JAXBElement<SetSortMethodResponse> createSetSortMethodResponse(SetSortMethodResponse value) {
        return new JAXBElement<SetSortMethodResponse>(_SetSortMethodResponse_QNAME, SetSortMethodResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ShowAbout }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ShowAbout }{@code >}
     */
    @XmlElementDecl(namespace = "http://webservice.api.common.tm/", name = "showAbout")
    public JAXBElement<ShowAbout> createShowAbout(ShowAbout value) {
        return new JAXBElement<ShowAbout>(_ShowAbout_QNAME, ShowAbout.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ShowAboutResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ShowAboutResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://webservice.api.common.tm/", name = "showAboutResponse")
    public JAXBElement<ShowAboutResponse> createShowAboutResponse(ShowAboutResponse value) {
        return new JAXBElement<ShowAboutResponse>(_ShowAboutResponse_QNAME, ShowAboutResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ShowHelp }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ShowHelp }{@code >}
     */
    @XmlElementDecl(namespace = "http://webservice.api.common.tm/", name = "showHelp")
    public JAXBElement<ShowHelp> createShowHelp(ShowHelp value) {
        return new JAXBElement<ShowHelp>(_ShowHelp_QNAME, ShowHelp.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ShowHelpResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ShowHelpResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://webservice.api.common.tm/", name = "showHelpResponse")
    public JAXBElement<ShowHelpResponse> createShowHelpResponse(ShowHelpResponse value) {
        return new JAXBElement<ShowHelpResponse>(_ShowHelpResponse_QNAME, ShowHelpResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Shutdown }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Shutdown }{@code >}
     */
    @XmlElementDecl(namespace = "http://webservice.api.common.tm/", name = "shutdown")
    public JAXBElement<Shutdown> createShutdown(Shutdown value) {
        return new JAXBElement<Shutdown>(_Shutdown_QNAME, Shutdown.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ShutdownResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ShutdownResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://webservice.api.common.tm/", name = "shutdownResponse")
    public JAXBElement<ShutdownResponse> createShutdownResponse(ShutdownResponse value) {
        return new JAXBElement<ShutdownResponse>(_ShutdownResponse_QNAME, ShutdownResponse.class, null, value);
    }

}
