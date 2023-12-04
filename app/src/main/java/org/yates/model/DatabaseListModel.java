package org.yates.model;

import java.io.*;
import java.util.*;

import javax.swing.AbstractListModel;

public class DatabaseListModel extends AbstractListModel<String> implements Serializable {
	private List<String> itemList;

	public DatabaseListModel() {
		itemList = new ArrayList<String>();
	}

	private boolean inBounds(int index) {
		return index >= 0 && index < itemList.size();
	}

	public void addItem(String item) {
		itemList.add(item);
		fireIntervalAdded(this, getSize() - 1, getSize() - 1);
	}

	public void insertItem(int index, String item) {
		if (inBounds(index))
			itemList.add(index, item);
		else if (index == getSize())
			itemList.add(item);
		else return;
		fireIntervalAdded(this, index, index);
	}

	public void deleteItem(int index) {
		if (inBounds(index))
			itemList.remove(index);
		fireIntervalRemoved(this, index, index);
	}

	public void setItem(int index, String item) {
		if (inBounds(index))
			itemList.set(index, item);
		fireContentsChanged(this, index, index);
	}

	public void moveItem(int fromIndex, int toIndex) {
		if (inBounds(fromIndex) && inBounds(toIndex)) {
            String item = itemList.remove(fromIndex);
            itemList.add(toIndex, item);
        }
        fireContentsChanged(this, fromIndex, toIndex);
	}

	public void saveToFile(File fileName) {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
			oos.writeObject(itemList);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void loadFromFile(File fileName) {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
			clearList();
			itemList = (List<String>) ois.readObject();
			fireIntervalAdded(this, 0, getSize() - 1);
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void clearList() {
		if (!isEmpty()) {
			int size = getSize();
			itemList.clear();
			fireIntervalRemoved(this, 0, size - 1);
		}
	}

	public boolean isEmpty() {
		return getSize() == 0;
	}

	public List<String> getItemList() {
		return itemList;
	}

	@Override
	public int getSize() {
		return itemList.size();
	}

	@Override
	public String getElementAt(int index) {
		return itemList.get(index);
	}
}
