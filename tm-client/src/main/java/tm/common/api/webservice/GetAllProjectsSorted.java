
package tm.common.api.webservice;

import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getAllProjectsSorted complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getAllProjectsSorted"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="arg0" type="{http://webservice.api.common.tm/}session" minOccurs="0"/&gt;
 *         &lt;element name="arg1" type="{http://webservice.api.common.tm/}comparatorType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getAllProjectsSorted", propOrder = {
    "arg0",
    "arg1"
})
@Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-03T03:21:21+03:00", comments = "JAXB RI v2.3.2")
public class GetAllProjectsSorted {

    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-03T03:21:21+03:00", comments = "JAXB RI v2.3.2")
    protected Session arg0;
    @XmlSchemaType(name = "string")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-03T03:21:21+03:00", comments = "JAXB RI v2.3.2")
    protected ComparatorType arg1;

    /**
     * Gets the value of the arg0 property.
     * 
     * @return
     *     possible object is
     *     {@link Session }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-03T03:21:21+03:00", comments = "JAXB RI v2.3.2")
    public Session getArg0() {
        return arg0;
    }

    /**
     * Sets the value of the arg0 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Session }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-03T03:21:21+03:00", comments = "JAXB RI v2.3.2")
    public void setArg0(Session value) {
        this.arg0 = value;
    }

    /**
     * Gets the value of the arg1 property.
     * 
     * @return
     *     possible object is
     *     {@link ComparatorType }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-03T03:21:21+03:00", comments = "JAXB RI v2.3.2")
    public ComparatorType getArg1() {
        return arg1;
    }

    /**
     * Sets the value of the arg1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link ComparatorType }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-03T03:21:21+03:00", comments = "JAXB RI v2.3.2")
    public void setArg1(ComparatorType value) {
        this.arg1 = value;
    }

}
