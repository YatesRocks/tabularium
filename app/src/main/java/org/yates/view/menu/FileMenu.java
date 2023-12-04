package org.yates.view;

import org.yates.controller.FileActions;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class FileMenu extends JMenu {
	private static final Logger log = LoggerFactory.getLogger(FileMenu.class);

	private static FileActions fileActions;

	FileMenu(FileActions fileActions) {
		super("File");

		this.fileActions = Objects.requireNonNull(fileActions, "FileActions must not be null");

		add(createMenuItem("Open", e -> openFile()));
		add(createMenuItem("New", e -> newFile()));
		add(createMenuItem("Save", e -> saveFile()));
		add(createMenuItem("Save & Exit", e -> exitApplication()));		
	}

	private JMenuItem createMenuItem(String label, ActionListener listener) {
		JMenuItem menuItem = new JMenuItem(label);
		menuItem.addActionListener(listener);
		return menuItem;
	}

	private void openFile() {
		fileActions.openFile();
    }

	private void newFile() {
		fileActions.newFile();
	}

	private void saveFile() {
		fileActions.saveFile();
	}

	private void exitApplication() {
		saveFile();
		log.info("Exiting application");
		System.exit(0);
	}
}