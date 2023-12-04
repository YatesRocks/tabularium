package org.yates.view;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.io.File;
import java.awt.event.ActionListener;
import java.awt.Font;

import net.miginfocom.swing.MigLayout;

import org.yates.model.DatabaseListModel;
import org.yates.controller.FileActions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class DatabasePanel extends JPanel implements FileActions {
	private DatabaseListModel model;
	private DatabaseJList jList;
	private Logger log = LoggerFactory.getLogger(DatabasePanel.class);
	private FileNameExtensionFilter filter = new FileNameExtensionFilter("Serialized List File", "ser");

	private File fileOpened = null;

	DatabasePanel() {
		MigLayout layout = new MigLayout("debug, wrap");
		setLayout(layout);

		createNewDatabase();

		add(listEditorPanel(), "push, align center, center");
	}

	private void createNewDatabase() {
		model = new DatabaseListModel();
		jList = new DatabaseJList(model);
	}

	private JPanel listEditorPanel() {
		JScrollPane listScrollPane = new JScrollPane(jList);
		JPanel panel = new JPanel(new MigLayout("wrap"));
		JLabel title = new JLabel("This is your Tabularium");
		Font titleFont = new Font("Serif", Font.PLAIN, 18);
		title.setFont(titleFont);
		panel.add(title, "align center, center");
		panel.add(listScrollPane, "grow, align center, center");
		panel.add(listButtonPanel(), "align center, center");
		return panel;
	}

	private JPanel listButtonPanel() {
		JPanel panel = new JPanel();
		panel.add(createButton("Add", e -> add()));
		panel.add(createButton("Edit", e -> edit()));
		panel.add(createButton("Delete", e -> delete()));
		panel.add(createButton("Clear", e -> clear()));
		return panel;
	}

	private JButton createButton(String label, ActionListener listener) {
		JButton button = new JButton(label);
		button.addActionListener(listener);
		return button;
	}

	private String inputDialog(String prompt, String buttonLabel) {
		String res = JOptionPane.showInputDialog(this, prompt, buttonLabel, JOptionPane.PLAIN_MESSAGE);
		return res;
	}

	private void infoDialog(String prompt) {
		JOptionPane.showMessageDialog(this, prompt, "Info", JOptionPane.INFORMATION_MESSAGE);
	}

	private boolean checkInput(String input) {
		return input != null && !input.trim().isEmpty();
	}

	private void add() {
		String newItem = inputDialog("Enter a new item:", "Add item");
		if (!checkInput(newItem))
			return;
		if (jList.getSelectedIndex() == -1)
			model.addItem(newItem);
		else
			model.insertItem(jList.getSelectedIndex() + 1, newItem);
	}

	private void edit() {
		int selection = jList.getSelectedIndex();
		if (selection != -1) {
			String newValue = inputDialog("Enter your new value:", "Edit item");
			if (checkInput(newValue))
				model.setItem(selection, newValue);
		} else {
			infoDialog("Please select an item to edit");
		}
	}

	private void delete() {
		int selection = jList.getSelectedIndex();
		if (selection != -1) {
			model.deleteItem(selection);
		} else {
			infoDialog("Please select an item to delete");
		}
	}

	private void clear() {
		model.clearList();
	}

	private boolean savePrompt() {
		int result = JOptionPane.showConfirmDialog(
			this,
			"Do you want to save?",
			"Are you sure?",
			JOptionPane.YES_NO_OPTION
		);

		return result == JOptionPane.YES_OPTION;
	}

	@Override
	public void saveFile() {
		if (fileOpened != null) {
			model.saveToFile(fileOpened);
			return;
		}
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fileChooser.setFileFilter(filter);
		int result = fileChooser.showSaveDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			log.info("Saving file to: " + selectedFile.toString());
			model.saveToFile(selectedFile);
		} else if (result == JFileChooser.CANCEL_OPTION) {
			log.debug("canceled");
		}
	}

	@Override
	public void newFile() {
		if (!model.isEmpty() && savePrompt())
			saveFile();
		model.clearList();	
		fileOpened = null;
	}

	@Override
	public void openFile() {
		if (!model.isEmpty() && savePrompt())
			saveFile();
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fileChooser.setFileFilter(filter);
		int result = fileChooser.showOpenDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			log.info("Opening file: " + selectedFile.toString());
			model.loadFromFile(selectedFile);
			fileOpened = selectedFile;
		} else if (result == JFileChooser.CANCEL_OPTION) {
			log.debug("canceled");
		}
	}
}