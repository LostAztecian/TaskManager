
package tm.common.api.webservice;

import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getByIdResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getByIdResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="return" type="{http://webservice.api.common.tm/}user" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getByIdResponse", propOrder = {
    "_return"
})
@Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-06T09:58:33+03:00", comments = "JAXB RI v2.3.2")
public class GetByIdResponse {

    @XmlElement(name = "return")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-06T09:58:33+03:00", comments = "JAXB RI v2.3.2")
    protected User _return;

    /**
     * Gets the value of the return property.
     * 
     * @return
     *     possible object is
     *     {@link User }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-06T09:58:33+03:00", comments = "JAXB RI v2.3.2")
    public User getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     * 
     * @param value
     *     allowed object is
     *     {@link User }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-06T09:58:33+03:00", comments = "JAXB RI v2.3.2")
    public void setReturn(User value) {
        this._return = value;
    }

}
