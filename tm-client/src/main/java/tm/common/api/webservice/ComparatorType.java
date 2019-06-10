
package tm.common.api.webservice;

import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for comparatorType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="comparatorType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="BY_CREATION_DATE"/&gt;
 *     &lt;enumeration value="BY_START_DATE"/&gt;
 *     &lt;enumeration value="BY_END_DATE"/&gt;
 *     &lt;enumeration value="BY_STATUS"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "comparatorType")
@XmlEnum
@Generated(value = "com.sun.tools.xjc.Driver", date = "2019-06-10T10:59:06+03:00", comments = "JAXB RI v2.3.2")
public enum ComparatorType {

    BY_CREATION_DATE,
    BY_START_DATE,
    BY_END_DATE,
    BY_STATUS;

    public String value() {
        return name();
    }

    public static ComparatorType fromValue(String v) {
        return valueOf(v);
    }

}
