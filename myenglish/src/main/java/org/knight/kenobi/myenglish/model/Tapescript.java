/**
 * 
 */
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Аудиозапись
 * @author Ilya
 *
 */
@Entity
@Table(name = "Tapescript")
public class Tapescript {

	/**
	 * Номер аудиозаписи
	 */
	@Column(name = "TapescriptNumber")
	@Id
	private float tapescriptNumber;
	
	/**
	 * Название 
	 */
	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.PERSIST})
	@JoinColumn(name = "Name")
	//@NotNull
	//@NotEmpty
	private Sentence name;
	
	/**
	 * Файл аудиозаписи
	 */
	@Column(name="FileName",
			unique = true,
			nullable = false)
	@NotNull
	@NotEmpty
	private String fileName;
	
	/**
	 * Урок
	 */
	@ManyToOne
	@JoinColumn(name = "UnitNumber")
	private Unit unit;
	
	/**
	 * Диалоги
	 */
	@OneToMany(mappedBy = "tapescript", cascade = {CascadeType.PERSIST, CascadeType.PERSIST})
	private List<Dialogue> dialogues;

	/**
	 * @return the tapescriptNumber
	 */
	public float getTapescriptNumber() {
		return tapescriptNumber;
	}

	/**
	 * @param tapescriptNumber the tapescriptNumber to set
	 */
	public Tapescript setTapescriptNumber(float tapescriptNumber) {
		this.tapescriptNumber = tapescriptNumber;
		return this;
	}

	/**
	 * @return the name
	 */
	public Sentence getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public Tapescript setName(Sentence name) {
		this.name = name;
		return this;
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName the fileName to set
	 */
	public Tapescript setFileName(String fileName) {
		this.fileName = fileName;
		return this;
	}

	/**
	 * @return the unit
	 */
	public Unit getUnit() {
		return unit;
	}

	/**
	 * @param unit the unit to set
	 */
	public Tapescript setUnit(Unit unit) {
		this.unit = unit;
		return this;
	}

	/**
	 * @return the dialogues
	 */
	public List<Dialogue> getDialogues() {
		return dialogues;
	}
	
	
	public Tapescript() {
		dialogues = new ArrayList<Dialogue>();
	}
	
	/**
	 * 
	 * @param tapescriptNumber
	 * @param name
	 * @param fileName
	 * @param dialogues
	 * @return
	 */
	public static Tapescript Create(float tapescriptNumber,  Sentence name, String fileName, Dialogue...dialogues){
		Tapescript tapescript = new Tapescript().setTapescriptNumber(tapescriptNumber).setName(name).setFileName(fileName);
		tapescript.getDialogues().addAll(Arrays.asList(dialogues));
		return tapescript;
	}
	
	/**
	 * 
	 * @param tapescriptNumber
	 * @param name
	 * @param fileName
	 * @param unit
	 * @param dialogues
	 * @return
	 */
	public static Tapescript Create(float tapescriptNumber,  Sentence name, String fileName, Unit unit, Dialogue...dialogues) {
		return Tapescript.Create(tapescriptNumber, name, fileName, dialogues).setUnit(unit);
	}
	
}
