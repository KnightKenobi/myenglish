package org.knight.kenobi.myenglish.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Лицо участвующее в диалоге
 * @author Ilya
 *
 */
@Entity
@Table(name = "Person")
public class Person {
	
	/**
	 * Имя лица
	 */
	@Id
	@Column(name = "PersonName")
	private String name;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public Person setName(String name) {
		this.name = name;
		return this;
	}
	
	/**
	 * 
	 * @param name
	 * @return
	 */
	public static Person Create(String name) {
		return new Person().setName(name);		
	}
}
