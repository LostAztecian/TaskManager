
package tm.common.api.webservice;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getAllTasksSortedResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getAllTasksSortedResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="return" type="{http://webservice.api.common.tm/}task" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getAllTasksSortedResponse", propOrder = {
    "_return"
})
@Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-03T06:45:25+03:00", comments = "JAXB RI v2.3.2")
public class GetAllTasksSortedResponse {

    @XmlElement(name = "return")
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-03T06:45:25+03:00", comments = "JAXB RI v2.3.2")
    protected List<Task> _return;

    /**
     * Gets the value of the return property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the return property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReturn().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Task }
     * 
     * 
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-03T06:45:25+03:00", comments = "JAXB RI v2.3.2")
    public List<Task> getReturn() {
        if (_return == null) {
            _return = new ArrayList<Task>();
        }
        return this._return;
    }

}
