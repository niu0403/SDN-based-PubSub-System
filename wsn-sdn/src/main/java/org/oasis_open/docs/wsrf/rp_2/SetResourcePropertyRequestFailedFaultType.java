
package org.oasis_open.docs.wsrf.rp_2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.oasis_open.docs.wsrf.bf_2.BaseFaultType;


/**
 * <p>Java class for SetResourcePropertyRequestFailedFaultType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SetResourcePropertyRequestFailedFaultType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://docs.oasis-open.org/wsrf/bf-2}BaseFaultType">
 *       &lt;sequence>
 *         &lt;element name="ResourcePropertyChangeFailure" type="{http://docs.oasis-open.org/wsrf/rp-2}ResourcePropertyChangeFailureType"/>
 *       &lt;/sequence>
 *       &lt;anyAttribute processContents='lax' namespace='##other'/>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SetResourcePropertyRequestFailedFaultType", propOrder = {
    "resourcePropertyChangeFailure"
})
public class SetResourcePropertyRequestFailedFaultType
    extends BaseFaultType
{

    @XmlElement(name = "ResourcePropertyChangeFailure", required = true)
    protected ResourcePropertyChangeFailureType resourcePropertyChangeFailure;

    /**
     * Gets the value of the resourcePropertyChangeFailure property.
     * 
     * @return
     *     possible object is
     *     {@link ResourcePropertyChangeFailureType }
     *     
     */
    public ResourcePropertyChangeFailureType getResourcePropertyChangeFailure() {
        return resourcePropertyChangeFailure;
    }

    /**
     * Sets the value of the resourcePropertyChangeFailure property.
     * 
     * @param value
     *     allowed object is
     *     {@link ResourcePropertyChangeFailureType }
     *     
     */
    public void setResourcePropertyChangeFailure(ResourcePropertyChangeFailureType value) {
        this.resourcePropertyChangeFailure = value;
    }

}
