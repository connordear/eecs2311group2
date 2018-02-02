package authoringApp;

import java.util.ArrayList;

public class InteractionList {
	
	private ArrayList<Interaction> interactionList;
	
	/*
	 * Default Constructor
	 */
	public InteractionList() {
		this.interactionList = new ArrayList<Interaction>();
	}
	
	
	/*
	 * Copy Constructor
	 */
	public InteractionList(InteractionList toCopy) {
		this.interactionList = new ArrayList<Interaction>();
		for (Interaction i : toCopy.interactionList) {
			this.interactionList.add(i);
		}
	}
	
	/*
	 * Shallow copy of list for use in the scenario parent
	 */
	public ArrayList<Interaction> getList() {
		return new ArrayList<Interaction>(this.interactionList);
	}
	
	/*
	 * Add new Interaction to the list
	 */
	public void add(Interaction i) {
		this.interactionList.add(i);
	}
}
