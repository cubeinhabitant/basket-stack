package org.basket3.model;

import javax.xml.bind.annotation.XmlRootElement;
/**
 * XML-marshable model for a user. 
 * That is not meant for persistence.
 * 
 * @author Kerby
 *
 */
@XmlRootElement(name="Owner")
public class OwnerXmlModel {
	private String id;
	private String displayName;
	
	public OwnerXmlModel() {}
	
	public OwnerXmlModel(String id){
		this.id = id;
	}
	
	public OwnerXmlModel(String id, String displayName){
		this.id = id;
		this.displayName = displayName;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
}
