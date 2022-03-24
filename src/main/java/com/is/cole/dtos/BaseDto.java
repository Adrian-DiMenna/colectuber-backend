package com.is.cole.dtos;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;

public class BaseDto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;

	@XmlElement(name = "id")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
}
