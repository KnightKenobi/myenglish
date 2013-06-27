package org.knight.kenobi.myenglish.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Урок
 * 
 * @author Ilya
 * 
 */
@Entity
@XmlRootElement
@Table(name = "Unit")
public class Unit {

	/**
	 * Номер урок
	 */
	@Column(name = "UnitNumber")
	@Id
	private short unitNumber;

	/**
	 * Название
	 */
	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.PERSIST})
	@JoinColumn(name = "Name", nullable = false)
	@NotNull
	@NotEmpty
	private Sentence name;
	
	/**
	 * Аудиозаписи
	 */
	@OneToMany(mappedBy = "unit", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	private List<Tapescript> tapescripts;
	
	/**
	 * Извлечь номер урока
	 * @return Номер урока
	 */
	public short getUnitNumber(){
		return unitNumber;
	}
	
	/**
	 * Установить номер урока
	 * @param value Номер урока
	 */
	public Unit setUnitNumber(short unitNumber){
		this.unitNumber = unitNumber;
		return this;
	}
	
	/**
	 * Извлчь название
	 * @return Название
	 */
	public Sentence getName(){
		return name;
	}
	
	/**
	 * Установить название
	 * @param value Название
	 */
	public Unit setName(Sentence name){
		this.name = name;
		return this;
	}
	
	/**
	 * Извлечь аудиозаписи
	 * @return Аудиозаписи
	 */
	public List<Tapescript> getTapescripts(){
		return tapescripts;
	}
	
	
	public Unit() {
		tapescripts = new ArrayList<Tapescript>();
	}
	
	/**
	 * 
	 * @param unitNumber
	 * @param name
	 * @param tapescripts
	 * @return
	 */
	public static Unit Create(short unitNumber, Sentence name, Tapescript...tapescripts){
		Unit unit = new Unit().setUnitNumber(unitNumber).setName(name);
		unit.tapescripts.addAll(Arrays.asList(tapescripts));
		return unit;
	}
}
