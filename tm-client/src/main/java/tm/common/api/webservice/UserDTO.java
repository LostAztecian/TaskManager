
package tm.common.api.webservice;

import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for userDTO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="userDTO"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="login" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="passwordHash" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="role" type="{http://webservice.api.common.tm/}role" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "userDTO", propOrder = {
    "id",
    "login",
    "passwordHash",
    "role"
})
@Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-24T11:01:27+03:00", comments = "JAXB RI v2.3.2")
public class UserDTO {

    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-24T11:01:27+03:00", comments = "JAXB RI v2.3.2")
    protected String id;
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-24T11:01:27+03:00", comments = "JAXB RI v2.3.2")
    protected String login;
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-24T11:01:27+03:00", comments = "JAXB RI v2.3.2")
    protected String passwordHash;
    @XmlSchemaType(name = "string")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-24T11:01:27+03:00", comments = "JAXB RI v2.3.2")
    protected Role role;

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-24T11:01:27+03:00", comments = "JAXB RI v2.3.2")
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
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-24T11:01:27+03:00", comments = "JAXB RI v2.3.2")
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the login property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-24T11:01:27+03:00", comments = "JAXB RI v2.3.2")
    public String getLogin() {
        return login;
    }

    /**
     * Sets the value of the login property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-24T11:01:27+03:00", comments = "JAXB RI v2.3.2")
    public void setLogin(String value) {
        this.login = value;
    }

    /**
     * Gets the value of the passwordHash property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-24T11:01:27+03:00", comments = "JAXB RI v2.3.2")
    public String getPasswordHash() {
        return passwordHash;
    }

    /**
     * Sets the value of the passwordHash property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-24T11:01:27+03:00", comments = "JAXB RI v2.3.2")
    public void setPasswordHash(String value) {
        this.passwordHash = value;
    }

    /**
     * Gets the value of the role property.
     * 
     * @return
     *     possible object is
     *     {@link Role }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-24T11:01:27+03:00", comments = "JAXB RI v2.3.2")
    public Role getRole() {
        return role;
    }

    /**
     * Sets the value of the role property.
     * 
     * @param value
     *     allowed object is
     *     {@link Role }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-24T11:01:27+03:00", comments = "JAXB RI v2.3.2")
    public void setRole(Role value) {
        this.role = value;
    }

}
