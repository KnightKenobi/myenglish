package org.knight.kenobi.myenglish.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Предложение из реплики
 * 
 */
@Entity
@Table(name = "Sentence")
public class Sentence {

	/**
	 * ИД предложения
	 */
	@Id
	@GeneratedValue
	@Column(name = "SentenceId")
	private long sentenceId;

	/*
	 * Текст предложения
	 */
	@Column(name = "Text", unique = true, nullable = true, length = 4000)
	@NotNull
	@NotEmpty
	private String text;

	/*
	 * Перевод предложения
	 */
	@Column(name = "Translation", unique = true, nullable = true, length = 4000)
	@NotNull
	@NotEmpty
	private String translation;

	/**
	 * Реплика
	 */
	@ManyToOne
	@JoinColumn(name = "replicaId")
	//@NotNull
	//@NotEmpty
	private Replica replica;

	/**
	 * 
	 * @return
	 */
	public long getSentenceId() {
		return sentenceId;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text
	 *            the text to set
	 */
	public Sentence setText(String text) {
		this.text = text;
		return this;
	}

	/**
	 * @return the translation
	 */
	public String getTranslation() {
		return translation;
	}

	/**
	 * @param translation
	 *            the translation to set
	 */
	public Sentence setTranslation(String translation) {
		this.translation = translation;
		return this;
	}

	/**
	 * @return the replica
	 */
	public Replica getReplica() {
		return replica;
	}

	/**
	 * @param replica
	 *            the replica to set
	 */
	public Sentence setReplica(Replica replica) {
		this.replica = replica;
		return this;
	}

	/**
	 * 
	 * @param text
	 * @param translation
	 */
	public static Sentence Create(String text, String translation) {
		return new Sentence().setText(text).setTranslation(translation);		
	}

	/**
	 * 
	 * @param text
	 * @param translation
	 * @param replica
	 */
	public static Sentence Create(String text, String translation,
			Replica replica) {
		return Create(text, translation).setReplica(replica);		
	}
}
