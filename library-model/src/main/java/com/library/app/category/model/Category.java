package com.library.app.category.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "category")
public class Category implements Serializable {

	/**
	 * Serializable will be required when we are using JMS for sending entities through messages.
	 */
	private static final long serialVersionUID = 2657551019023598969L;

	/********************************* Fields are declared here ***************************************/

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; // this will be the id of the category

	@Column(unique = true)
	private String name; // name for the category

	/******************************** Constructor is here ***********************************************/

	public Category() {

	}

	public Category(final String name) {
		this.name = name;
	}

	/******************************** Generate Getters and Setters ***************************************/

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	/******************************** Generate HashCode and equals ****************************************/
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Category other = (Category) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	/******************************** Generate toString **********************************************/

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + "]";
	}

}
