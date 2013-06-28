package org.knight.kenobi.myenglish.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Диалог
 * @author Ilya
 *
 */
@Entity
@Table(name = "Dialogue")
public class Dialogue {
	
	/**
	 * ИД диалога
	 */
	@Id
	@GeneratedValue
	@Column(name = "DialogueId")
	private short dialogueId;
	
	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.PERSIST})
	@JoinColumn(name = "Name")
	//@NotNull
	//@NotEmpty
	private Sentence name;
	
	/**
	 * Аудиозапись
	 */
	@ManyToOne
	@JoinColumn(name = "TapescriptNumber")
	//@NotNull
	private Tapescript tapescript;
	
	/**
	 * Реплики учатников диалога
	 */
	@OneToMany(mappedBy = "dialogue", cascade = {CascadeType.PERSIST, CascadeType.PERSIST})
	private List<Replica> replics;
	
	/**
	 * Лица участвующие в диалоге
	 */
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.PERSIST})
	@JoinTable(name = "DialoguePersons",
			   joinColumns = @JoinColumn(name = "DialogueId"),
			   inverseJoinColumns = @JoinColumn(name = "PersonName"))
	private List<Person> persons;

	/**
	 * @return the dialogueId
	 */
	public short getDialogueId() {
		return dialogueId;
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
	public Dialogue setName(Sentence name) {
		this.name = name;
		return this;
	}

	/**
	 * @return the tapescript
	 */
	public Tapescript getTapescript() {
		return tapescript;
	}

	/**
	 * @param tapescript the tapescript to set
	 */
	public Dialogue setTapescript(Tapescript tapescript) {
		this.tapescript = tapescript;
		return this;
	}

	/**
	 * @return the replics
	 */
	public List<Replica> getReplics() {
		return replics;
	}

	/**
	 * @return the persons
	 */
	public List<Person> getPersons() {
		return persons;
	}
	

	public Dialogue() {
		replics = new ArrayList<Replica>();
		persons = new ArrayList<Person>();
	}

	/**
	 * 
	 * @param name
	 * @param persons
	 * @param replics
	 * @return
	 */
	public static Dialogue Create(Sentence name, Person[] persons, Replica...replics){
		Dialogue dialogue = new Dialogue().setName(name);
		dialogue.getPersons().addAll(Arrays.asList(persons));
		dialogue.getReplics().addAll(Arrays.asList(replics));
		return dialogue;
				
	}
	
	/**
	 * 
	 * @param name
	 * @param tapescript
	 * @param persons
	 * @param replics
	 * @return
	 */
	public static Dialogue Create(Sentence name, Tapescript tapescript,  Person[] persons, Replica...replics){
		return Create(name, persons, replics).setTapescript(tapescript);
	}
}
