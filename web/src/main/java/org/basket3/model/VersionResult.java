package org.basket3.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ListVersionsResult", propOrder = {
    "name",
	"prefix",
	"market",
	"maxkeys"
})
public class VersionResult {
	private String name;
	private String prefix;
	private String marker;
	private Long maxKeys;
	private boolean isTruncated;
	@XmlElement(name = "Contents", required = true)
	private List<ContentXmlModel> contents;
	
	public VersionResult() {
		contents = new ArrayList<ContentXmlModel>();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public String getMarker() {
		return marker;
	}
	public void setMarker(String marker) {
		this.marker = marker;
	}
	public Long getMaxKeys() {
		return maxKeys;
	}
	public void setMaxKeys(Long maxKeys) {
		this.maxKeys = maxKeys;
	}
	public boolean isTruncated() {
		return isTruncated;
	}
	public void setTruncated(boolean isTruncated) {
		this.isTruncated = isTruncated;
	}
	public List<ContentXmlModel> getContents() {
		return contents;
	}
	public void setContents(List<ContentXmlModel> contents) {
		this.contents = contents;
	}
}
