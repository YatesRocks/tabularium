package org.yates.view;

import javax.swing.JList;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;

class DatabaseJList extends JList<String> {
	DatabaseJList(ListModel model) {
		super(model);
		setLayoutOrientation(JList.VERTICAL);
		setVisibleRowCount(5);
		setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
	}
}