
package tm.common.api.webservice;

import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getNewTaskResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getNewTaskResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="return" type="{http://webservice.api.common.tm/}task" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getNewTaskResponse", propOrder = {
    "_return"
})
@Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-03T03:21:20+03:00", comments = "JAXB RI v2.3.2")
public class GetNewTaskResponse {

    @XmlElement(name = "return")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-03T03:21:20+03:00", comments = "JAXB RI v2.3.2")
    protected Task _return;

    /**
     * Gets the value of the return property.
     * 
     * @return
     *     possible object is
     *     {@link Task }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-03T03:21:20+03:00", comments = "JAXB RI v2.3.2")
    public Task getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     * 
     * @param value
     *     allowed object is
     *     {@link Task }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-03T03:21:20+03:00", comments = "JAXB RI v2.3.2")
    public void setReturn(Task value) {
        this._return = value;
    }

}