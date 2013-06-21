package org.knight.kenobi.myenglish.model;

import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Реплика учатсника диалога
 * @author Ilya
 *
 */
@Entity
@Table(name = "Replica")
public class Replica {
	
	/**
	 * ИД диалога
	 */
	@Id
	@GeneratedValue
	@Column( name = "ReplicaId")
	private int replicaId;
	
	/**
	 * Диалог
	 */
	@ManyToOne
	@JoinColumn(name = "DialogueId")
	@NotNull
	@NotEmpty
    private Dialogue dialogue;
	
	/**
	 * Лицо произнёсшее реплику
	 */
	@OneToOne
	@JoinColumn(name = "PersonName", nullable = false)
	@NotNull
	@NotEmpty
	private Person person;
	
	/**
	 * Предложения
	 */
	@OneToMany(mappedBy = "replica")
	private List<Sentence> sentences;

	/**
	 * @return the replicaId
	 */
	public int getReplicaId() {
		return replicaId;
	}

	/**
	 * @return the dialogue
	 */
	public Dialogue getDialogue() {
		return dialogue;
	}

	/**
	 * @param dialogue the dialogue to set
	 */
	public Replica setDialogue(Dialogue dialogue) {
		this.dialogue = dialogue;
		return this;
	}

	/**
	 * @return the person
	 */
	public Person getPerson() {
		return person;
	}
	
	/**
	 * @param person the person to set
	 */
	public Replica setPerson(Person person) {
		this.person = person;
		return this;
	}
	
	/**
	 * @return the sentences
	 */
	public List<Sentence> getSentences() {
		return sentences;
	}

	/**
	 * 
	 * @param person
	 * @param sentences
	 * @return
	 */
	public static Replica Create(Person person, Sentence...sentences){
		Replica replica = new Replica().setPerson(person);
		replica.getSentences().addAll(Arrays.asList(sentences));
		return replica;
	}
	
	/**
	 * 
	 * @param dialogue
	 * @param person
	 * @param sentences
	 * @return
	 */
	public static Replica Create(Dialogue dialogue, Person person, Sentence...sentences){
		return Replica.Create(person, sentences).setDialogue(dialogue);		
	}
}
