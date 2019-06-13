
package tm.common.api.webservice;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for deleteUsersByIds complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="deleteUsersByIds"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="arg0" type="{http://webservice.api.common.tm/}sessionDTO" minOccurs="0"/&gt;
 *         &lt;element name="arg1" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "deleteUsersByIds", propOrder = {
    "arg0",
    "arg1"
})
@Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-13T11:00:29+03:00", comments = "JAXB RI v2.3.2")
public class DeleteUsersByIds {

    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-13T11:00:29+03:00", comments = "JAXB RI v2.3.2")
    protected SessionDTO arg0;
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-13T11:00:29+03:00", comments = "JAXB RI v2.3.2")
    protected List<String> arg1;

    /**
     * Gets the value of the arg0 property.
     * 
     * @return
     *     possible object is
     *     {@link SessionDTO }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-13T11:00:29+03:00", comments = "JAXB RI v2.3.2")
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
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-13T11:00:29+03:00", comments = "JAXB RI v2.3.2")
    public void setArg0(SessionDTO value) {
        this.arg0 = value;
    }

    /**
     * Gets the value of the arg1 property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the arg1 property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getArg1().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-13T11:00:29+03:00", comments = "JAXB RI v2.3.2")
    public List<String> getArg1() {
        if (arg1 == null) {
            arg1 = new ArrayList<String>();
        }
        return this.arg1;
    }

}
