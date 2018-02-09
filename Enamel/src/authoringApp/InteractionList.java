package authoringApp;

import java.util.LinkedList;

public class InteractionList {
	
	private LinkedList<Interaction> interactionList;
	/*
	 * Default Constructor
	 */
	public InteractionList() {
		this.interactionList = new LinkedList<Interaction>();
	}
	
	
	/*
	 * Copy Constructor
	 */
	public InteractionList(InteractionList toCopy) {
		this.interactionList = new LinkedList<Interaction>();
		for (Interaction i : toCopy.interactionList) {
			this.interactionList.add(i);
		}
	}
	
	/*
	 * Shallow copy of list for use in the scenario parent
	 */
	public LinkedList<Interaction> getList() {
		return new LinkedList<Interaction>(this.interactionList);
	}
	
	/*
	 * Add new Interaction to the list
	 */
	public void add(Interaction i) {
		this.interactionList.add(i);
	}
	
	public String toString() {
		String res = "";
		for(Interaction i : this.interactionList) {
			System.out.println(i.getTitle());
		}
		return res;
	}
}
