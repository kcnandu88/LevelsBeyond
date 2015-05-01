package org.levelsbeyond.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name = "Notes")
@XmlRootElement 
public class Notes {

	@Id
    @Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	protected int id;
    
    @Column(name="notes")
    protected String body;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
    
    
    
}
