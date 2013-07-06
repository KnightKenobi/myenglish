package org.knight.kenobi.myenglish.test;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.NamingException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.knight.kenobi.myenglish.model.Dialogue;
import org.knight.kenobi.myenglish.model.Person;
import org.knight.kenobi.myenglish.model.Replica;
import org.knight.kenobi.myenglish.model.Sentence;
import org.knight.kenobi.myenglish.model.Tapescript;
import org.knight.kenobi.myenglish.model.Unit;
import org.knight.kenobi.myenglish.service.UnitRegistration;


public class UnitRegistrationTestEJBContainer {
	
	EJBContainer ejbContainer;
	Context context;
	
	@Before
	public void setUp() {
		ejbContainer = EJBContainer.createEJBContainer();
		context = ejbContainer.getContext();
	}
	
	@Test
	public void testUnitRegistration() throws NamingException {
		Person personA = Person.Create("A");		
		Unit unit = Unit.Create((short) 9, Sentence.Create("I love Chicago",
				"Я люблю Чикаго"), Tapescript.Create(9.1F, Sentence.Create(
				"Getting around", "На транспорте"), "23 9.1.mp3", Dialogue
				.Create(Sentence.Create("Buying a ticket", "Покупка билета"),
						new Person[] { personA, Person.Create("B") },
						Replica.Create(personA,
								Sentence.Create("Hi.", "Привет.")))));
		UnitRegistration unitRegistration = (UnitRegistration)context.lookup("java:global/classes/UnitRegistration");
		unitRegistration.register(unit);
		
	}
	
	@After
	public void tearDown() {
		if (ejbContainer != null) {
			ejbContainer.close();
		}
	}

}
