
package tm.common.api.webservice;

import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dataSaveJaxbJson complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dataSaveJaxbJson"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="arg0" type="{http://webservice.api.common.tm/}sessionDTO" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dataSaveJaxbJson", propOrder = {
    "arg0"
})
@Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-24T11:01:27+03:00", comments = "JAXB RI v2.3.2")
public class DataSaveJaxbJson {

    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-24T11:01:27+03:00", comments = "JAXB RI v2.3.2")
    protected SessionDTO arg0;

    /**
     * Gets the value of the arg0 property.
     * 
     * @return
     *     possible object is
     *     {@link SessionDTO }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-24T11:01:27+03:00", comments = "JAXB RI v2.3.2")
    public SessionDTO getArg0() {
        return arg0;
    }

    /**
     * Sets the value of the arg0 property.
     * 
     * @param value
     *     allowed object is
     *     {@link SessionDTO }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-24T11:01:27+03:00", comments = "JAXB RI v2.3.2")
    public void setArg0(SessionDTO value) {
        this.arg0 = value;
    }

}
