package org.knight.kenobi.myenglish.service;

import java.util.logging.Logger;

import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.knight.kenobi.myenglish.model.Unit;

@Stateful
public class UnitRegistration {
	
	@Inject
	private Logger logger;

	@Inject
	private EntityManager entityManager;

	@Inject
	private Event<Unit> unitEvent;
	
	public void register(Unit unit){
		entityManager.persist(unit);		
		unitEvent.fire(unit);
	}
	
	public Unit load(short unitNumber){
		return entityManager.find(Unit.class, unitNumber);
	}
	
}
