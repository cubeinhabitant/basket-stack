package org.basket3.model;

import java.io.Serializable;

import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Bucket that is not meant for direct pesistence
 * Used for XML marshalling of the BucketModel.
 * 
 * @author Kerby Martino
 *
 */
@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
	    "id",
		"name",
	    "creationDate"
	})
@XmlRootElement(name="bucket") 
public class BucketXmlModel {
	@Id
	private Long id; // Id with respect to the user's BucketList
	
	@XmlElement(required = true)
	private String name;
	@XmlElement(required = true)
	private String creationDate;
	//private String modificationDate;
	
	public BucketXmlModel() {
		
	}
	
	public BucketXmlModel(Long id, String name, String creationDate) {
		this.id = id;
		this.setBucketName(name);
		this.creationDate = creationDate;
	}
	
	public String getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public String getBucketName() {
		return name;
	}

	public void setBucketName(String bucketName) {
		this.name = bucketName;
	}
}
