package org.knight.kenobi.myenglish.test;

import static org.junit.Assert.assertNotNull;

import java.util.logging.Logger;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.knight.kenobi.myenglish.model.Dialogue;
import org.knight.kenobi.myenglish.model.Person;
import org.knight.kenobi.myenglish.model.Replica;
import org.knight.kenobi.myenglish.model.Sentence;
import org.knight.kenobi.myenglish.model.Tapescript;
import org.knight.kenobi.myenglish.model.Unit;
import org.knight.kenobi.myenglish.service.UnitRegistration;
import org.knight.kenobi.myenglish.util.Resources;

@RunWith(Arquillian.class)
public class UnitRegistrationTest {
	@Deployment
	public static Archive<?> createTestArchive() {
		return ShrinkWrap
				.create(WebArchive.class, "test.war")
				.addClasses(Resources.class, UnitRegistration.class,
						Unit.class, Tapescript.class, Dialogue.class,
						Replica.class, Sentence.class, Person.class)
				.addAsResource("META-INF/test-persistence.xml",
						"META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
				// Deploy our test datasource
				.addAsWebInfResource("test-ds.xml");
	}

	@Inject
	UnitRegistration unitRegistration;

	@Inject
	Logger logger;

	@Test
	public void testRegister() {
		Person personA = Person.Create("A");
		
		Unit unit = Unit.Create((short) 9, Sentence.Create("I love Chicago",
				"Я люблю Чикаго"), Tapescript.Create(9.1F, Sentence.Create(
				"Getting around", "На транспорте"), "23 9.1.mp3", Dialogue
				.Create(Sentence.Create("Buying a ticket", "Покупка билета"),
						new Person[] { personA, Person.Create("B") },
						Replica.Create(personA,
								Sentence.Create("Hi.", "Привет.")))));
		//Unit unit = new Unit();
		unitRegistration.register(unit);
		assertNotNull(unit.getUnitNumber());
	}
}
