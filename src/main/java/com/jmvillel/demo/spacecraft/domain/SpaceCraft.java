package com.jmvillel.demo.spacecraft.domain;


import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SpaceCraft {
	

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	/**
	 * Name of the SpaceCraft
	 */
	@Column(length = 500)
	private String name;
	
	
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
