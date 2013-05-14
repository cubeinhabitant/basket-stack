package org.basket3.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
/**
 * Model for the XML-marshable Bucket list
 * that is not meant for persistence.
 * 
 * @author Kerby
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "owner",
	"buckets"
})
@XmlRootElement(name="ListAllMyBucketsResult") 
public class ListAllMyBucketsResult {
	@XmlElement(required = true)
	private OwnerXmlModel owner;
	@XmlElement(name = "Bucket", required = true)
	private List<BucketXmlModel> buckets;
	 
	public List<BucketXmlModel> getBuckets() {
		if (buckets == null){
			buckets = new ArrayList<BucketXmlModel>();
		}
		return buckets;
	}
	public void setBuckets(List<BucketXmlModel> buckets) {
		this.buckets = buckets;
	}
	public OwnerXmlModel getOwner() {
		return owner;
	}
	public void setOwner(OwnerXmlModel owner) {
		this.owner = owner;
	}
	 
	 
}
