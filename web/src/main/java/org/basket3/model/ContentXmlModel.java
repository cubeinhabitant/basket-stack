package org.basket3.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
		"key",
		"lastModified",
		"etag",
		"size"
	})
@XmlRootElement(name="bucket") 
public class ContentXmlModel {
	
	private String key;
	private String lastModified;
	private String etag;
	private Long size;
	private String storageClass;
	private OwnerXmlModel owner;
	
	public ContentXmlModel() {
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getModified() {
		return lastModified;
	}

	public void setModified(String modified) {
		this.lastModified = modified;
	}

	public String getEtag() {
		return etag;
	}

	public void setEtag(String etag) {
		this.etag = etag;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public OwnerXmlModel getOwner() {
		return owner;
	}

	public void setOwner(OwnerXmlModel owner) {
		this.owner = owner;
	}

	public String getStorageClass() {
		return storageClass;
	}

	public void setStorageClass(String storageClass) {
		this.storageClass = storageClass;
	}
}
