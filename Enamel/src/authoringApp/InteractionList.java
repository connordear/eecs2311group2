package authoringApp;

import java.util.ArrayList;
import javax.swing.DefaultListModel;

public class InteractionList extends DefaultListModel<Interaction>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
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
	@Override
	public void add(int i, Interaction e) {
		this.interactionList.add(i, e);
	}
	
	@Override
	public void addElement(Interaction e) {
		this.interactionList.add(e);
	}
	
	@Override
	public String toString() {
		String res = "";
		for(Interaction i : this.interactionList) {
			System.out.println(i.getTitle());
		}
		return res;
	}


	@Override
	public int getSize() {
		return this.interactionList.size();
	}


	@Override
	public Interaction getElementAt(int index) {
		return this.interactionList.get(index);
	}
	


}
