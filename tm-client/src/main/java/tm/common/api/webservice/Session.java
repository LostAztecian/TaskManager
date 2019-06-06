
package tm.common.api.webservice;

import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for session complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="session"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="creationDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="hash" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="sortMethod" type="{http://webservice.api.common.tm/}comparatorType" minOccurs="0"/&gt;
 *         &lt;element name="userId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="userLogin" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "session", propOrder = {
    "creationDate",
    "hash",
    "id",
    "sortMethod",
    "userId",
    "userLogin"
})
@Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-06T09:58:33+03:00", comments = "JAXB RI v2.3.2")
public class Session {

    @XmlSchemaType(name = "dateTime")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-06T09:58:33+03:00", comments = "JAXB RI v2.3.2")
    protected XMLGregorianCalendar creationDate;
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-06T09:58:33+03:00", comments = "JAXB RI v2.3.2")
    protected String hash;
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-06T09:58:33+03:00", comments = "JAXB RI v2.3.2")
    protected String id;
    @XmlSchemaType(name = "string")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-06T09:58:33+03:00", comments = "JAXB RI v2.3.2")
    protected ComparatorType sortMethod;
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-06T09:58:33+03:00", comments = "JAXB RI v2.3.2")
    protected String userId;
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-06T09:58:33+03:00", comments = "JAXB RI v2.3.2")
    protected String userLogin;

    /**
     * Gets the value of the creationDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-06T09:58:33+03:00", comments = "JAXB RI v2.3.2")
    public XMLGregorianCalendar getCreationDate() {
        return creationDate;
    }

    /**
     * Sets the value of the creationDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-06T09:58:33+03:00", comments = "JAXB RI v2.3.2")
    public void setCreationDate(XMLGregorianCalendar value) {
        this.creationDate = value;
    }

    /**
     * Gets the value of the hash property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-06T09:58:33+03:00", comments = "JAXB RI v2.3.2")
    public String getHash() {
        return hash;
    }

    /**
     * Sets the value of the hash property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-06T09:58:33+03:00", comments = "JAXB RI v2.3.2")
    public void setHash(String value) {
        this.hash = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-06T09:58:33+03:00", comments = "JAXB RI v2.3.2")
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-06T09:58:33+03:00", comments = "JAXB RI v2.3.2")
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the sortMethod property.
     * 
     * @return
     *     possible object is
     *     {@link ComparatorType }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-06T09:58:33+03:00", comments = "JAXB RI v2.3.2")
    public ComparatorType getSortMethod() {
        return sortMethod;
    }

    /**
     * Sets the value of the sortMethod property.
     * 
     * @param value
     *     allowed object is
     *     {@link ComparatorType }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-06T09:58:33+03:00", comments = "JAXB RI v2.3.2")
    public void setSortMethod(ComparatorType value) {
        this.sortMethod = value;
    }

    /**
     * Gets the value of the userId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-06T09:58:33+03:00", comments = "JAXB RI v2.3.2")
    public String getUserId() {
        return userId;
    }

    /**
     * Sets the value of the userId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-06T09:58:33+03:00", comments = "JAXB RI v2.3.2")
    public void setUserId(String value) {
        this.userId = value;
    }

    /**
     * Gets the value of the userLogin property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-06T09:58:33+03:00", comments = "JAXB RI v2.3.2")
    public String getUserLogin() {
        return userLogin;
    }

    /**
     * Sets the value of the userLogin property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-06T09:58:33+03:00", comments = "JAXB RI v2.3.2")
    public void setUserLogin(String value) {
        this.userLogin = value;
    }

}
