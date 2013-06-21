package org.knight.kenobi.myenglish.test;

import java.sql.SQLException;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.knight.kenobi.myenglish.model.Dialogue;
import org.knight.kenobi.myenglish.model.Person;
import org.knight.kenobi.myenglish.model.Replica;
import org.knight.kenobi.myenglish.model.Sentence;
import org.knight.kenobi.myenglish.model.Tapescript;
import org.knight.kenobi.myenglish.model.Unit;
import org.knight.kenobi.myenglish.util.Resources;

/**
 * 
 * @author Ilya
 * 
 */
@RunWith(Arquillian.class)
public class SentenceTest {
	@Deployment
	public static Archive<?> createTestArchive() {
		return ShrinkWrap
				.create(WebArchive.class, "test.war")
				.addClasses(Resources.class, Person.class, Unit.class,
						Tapescript.class, Dialogue.class, Replica.class,
						Sentence.class)
				.addAsResource("META-INF/test-persistence.xml",
						"META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
				.addAsWebInfResource("test-ds.xml");
	}

	// private static EntityManagerFactory entityManagerFactory;
	// @Inject
	// private static EntityManager entityManager;
	// private static EntityTransaction entityTransaction;
	private final String TEXT = "Hi.";
	private final String TRANSLATION = "Привет.";
	private final Replica REPLICA = new Replica();

	@BeforeClass
	public static void initEntityManager() throws Exception {
		// entityManagerFactory =
		// Persistence.createEntityManagerFactory("primary");
		// entityManager = entityManagerFactory.createEntityManager();
	}

	@AfterClass
	public static void closeEntityManager() throws SQLException {
		// entityManager.close();
		// entityManagerFactory.close();
	}

	@Before
	public void initTransaction() {
		// entityTransaction = entityManager.getTransaction();
	}

	@Test
	public void CreateWithConstructorTest() {
		Sentence sentence = new Sentence();
		Assert.assertNotNull(sentence);
	}

	@Test
	public void CreateWithFactory() {
		Sentence sentence = Sentence.Create(TEXT, TRANSLATION);
		Assert.assertNotNull(sentence);
	}

	@Test
	public void CreateWithConstructorAndPropertiesTest() {
		Sentence sentence = new Sentence();
		sentence.setText(TEXT);
		sentence.setTranslation(TRANSLATION);
		sentence.setReplica(REPLICA);
		Assert.assertEquals(sentence.getText(), TEXT);
		Assert.assertEquals(sentence.getTranslation(), TRANSLATION);
		Assert.assertEquals(sentence.getReplica(), REPLICA);
	}

	@Test
	public void CreateWithFactoryAndWithPropertiesTest() {
		Sentence sentence = Sentence.Create(TEXT, TRANSLATION);
		Assert.assertEquals(sentence.getText(), TEXT);
		Assert.assertEquals(sentence.getTranslation(), TRANSLATION);
	}

	@Test
	public void CreateWithFactoryAndWithAllPropertiesTest() {
		Sentence sentence = Sentence.Create(TEXT, TRANSLATION, REPLICA);
		Assert.assertEquals(sentence.getText(), TEXT);
		Assert.assertEquals(sentence.getTranslation(), TRANSLATION);
		Assert.assertEquals(sentence.getReplica(), REPLICA);
	}

	/*
	 * @Test public void PersistWithConstructorTest() { Sentence sentence = new
	 * Sentence(); sentence.setText(TEXT); sentence.setTranslation(TRANSLATION);
	 * entityTransaction.begin(); entityManager.persist(sentence);
	 * entityTransaction.commit(); sentence = entityManager.find(Sentence.class,
	 * sentence.getSentenceId()); Assert.assertEquals(sentence.getText(), TEXT);
	 * Assert.assertEquals(sentence.getTranslation(), TRANSLATION); }
	 * 
	 * @Test public void PersistWithFactoryTest() { Sentence sentence =
	 * Sentence.Create(TEXT, TRANSLATION); entityTransaction.begin();
	 * entityManager.persist(sentence); entityTransaction.commit(); sentence =
	 * entityManager.find(Sentence.class, sentence.getSentenceId());
	 * Assert.assertEquals(sentence.getText(), TEXT);
	 * Assert.assertEquals(sentence.getTranslation(), TRANSLATION); }
	 * 
	 * @Test public void PersistWithFactoryAndWithAllPropertiesTest() { Sentence
	 * sentence = Sentence.Create(TEXT, TRANSLATION, REPLICA);
	 * entityTransaction.begin(); entityManager.persist(sentence);
	 * entityTransaction.commit(); sentence = entityManager.find(Sentence.class,
	 * sentence.getSentenceId()); Assert.assertEquals(sentence.getText(), TEXT);
	 * Assert.assertEquals(sentence.getTranslation(), TRANSLATION);
	 * Assert.assertEquals(sentence.getReplica(), REPLICA); }
	 */

}
