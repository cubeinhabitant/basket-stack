package org.basket3.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * XML-Marshable model of a bucket that is 
 * stored in the Datastore.
 * 
 * @author Kerby
 *
 */
@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="Object") 
@XmlType(name = "", propOrder = {
	    "id",
	    "creationDate"
	})
public class BucketObjectXmlModel {
	private String id; // User generated ID/name
	private String creationDate;
	
	public BucketObjectXmlModel() {
		
	}
	
	public BucketObjectXmlModel(String id){
		this.setId(id);
	}
	
	public BucketObjectXmlModel(String id, String creationDate){
		this.setId(id);
		this.setCreationDate(creationDate);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}
}
