package main;

import java.util.List;

import persistence.PersistenceUtil;
import entity.EventCause;

public class EventCauseConfig {

	public static void main(String[] args){
		EventCauseConfig config = new EventCauseConfig();
	}
	
	public EventCauseConfig(){
//		createEventCause(eventCauseID, eventID, causeCode, description);
	}
	
	public void viewEventCause(){
		List<EventCause> eventCases = PersistenceUtil.findAllEventCauses();
		for(EventCause currentEventCase : eventCases){
			System.out.println("");
		}
	}
	
	public void createEventCause(String eventID, String causeCode, String description){
		viewEventCause();
		EventCause eventCause = new EventCause(eventID, causeCode, description);
		PersistenceUtil.persist(eventCause);
		System.out.println("EventCause record created");
		viewEventCause();
	}
	
}
