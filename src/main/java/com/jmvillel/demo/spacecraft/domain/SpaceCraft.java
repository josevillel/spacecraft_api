package com.jmvillel.demo.spacecraft.domain;


import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity(name="spacecraft")
public class SpaceCraft {
	

	public SpaceCraft() {}

	public SpaceCraft(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	/**
	 * Name of the SpaceCraft
	 */
	@Column(length = 100)
	private String name;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object o) {

	    if (this == o)
	      return true;
	    
	    if (!(o instanceof SpaceCraft))
	      return false;
	    SpaceCraft sc = (SpaceCraft) o;
	    return Objects.equals(this.id, sc.id) && Objects.equals(this.name, sc.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.id, this.name);
	}

	@Override
	public String toString() {
		return "{" + "id:" + this.id + ", name='" + this.name +  "'}";
	}
	

}
