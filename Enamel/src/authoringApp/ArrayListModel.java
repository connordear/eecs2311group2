package authoringApp;

import java.util.ArrayList;

import javax.swing.AbstractListModel;

public class ArrayListModel<T> extends AbstractListModel {
	private ArrayList<T> data;
	
	public ArrayListModel() {
		data = new ArrayList<T>();
	}

	@Override
	public int getSize() {
		return data.size();
	}

	@Override
	public Object getElementAt(int index) {
		return data.get(index);
	}
	
	

}
