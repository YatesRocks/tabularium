package org.yates.view;

import org.yates.controller.FileActions;
import javax.swing.JFrame;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainWindow extends JFrame implements FileActions {
	private static final int DEFAULT_WIDTH   = 525;
	private static final int DEFAULT_HEIGHT  = 600;
	private static final String WINDOW_TITLE = "Tabularium";

	private static final Logger log = LoggerFactory.getLogger(MainWindow.class);

	public void start() {
		log.info("Starting");
		TabulariumLaf.setLookAndFeel();
		setTitle(WINDOW_TITLE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		setLocationRelativeTo(null);
		setJMenuBar(new DatabaseMenuBar(this));

		WelcomeSplash splash = new WelcomeSplash();
		add(splash);

		setVisible(true);
	}

	@Override
	public void openFile() {
		log.info("Opening file...");		
	}

	@Override
	public void newFile() {
		log.info("Creating new file...");
	}

	@Override
	public void saveFile() {
		log.info("Saving file...");
	}
}