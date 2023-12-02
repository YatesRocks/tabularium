package org.yates.ui;

import javax.swing.JFrame;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.miginfocom.swing.MigLayout;

public class MainWindow extends JFrame {
	private static final int DEFAULT_WIDTH   = 525;
	private static final int DEFAULT_HEIGHT  = 600;
	private static final String WINDOW_TITLE = "Tabularium";

	private static final Logger logger = LoggerFactory.getLogger(MainWindow.class);

	public void start() {
		logger.info("Starting");
		TabulariumLaf.setLookAndFeel();
		setTitle(WINDOW_TITLE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		setLocationRelativeTo(null);

		WelcomeSplash splash = new WelcomeSplash();
		add(splash);

		setVisible(true);
	}
}