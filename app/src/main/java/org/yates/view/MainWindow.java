package org.yates.view;

import org.yates.controller.CardChangeListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.CardLayout;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainWindow extends JFrame implements CardChangeListener {
	private static final int DEFAULT_WIDTH   = 525;
	private static final int DEFAULT_HEIGHT  = 600;
	private static final String WINDOW_TITLE = "Tabularium";

	private static final Logger log = LoggerFactory.getLogger(MainWindow.class);

	private CardLayout cardLayout;
	private JPanel cardPanel;

	public void start() {
		log.info("Starting application");
		TabulariumLaf.setLookAndFeel();
		setTitle(WINDOW_TITLE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		setLocationRelativeTo(null);

		cardLayout = new CardLayout();
		cardPanel = new JPanel(cardLayout);

		WelcomeSplash splash = new WelcomeSplash();
		cardPanel.add(splash, "WelcomeSplash");
		splash.setCardChangeListener(this);

		DatabasePanel dbPanel = new DatabasePanel();
		cardPanel.add(dbPanel, "DatabasePanel");

		setJMenuBar(new DatabaseMenuBar(dbPanel));

		add(cardPanel);

		cardLayout.show(cardPanel, "WelcomeSplash");

		setVisible(true);
	}

	@Override
	public void onCardChange(String cardName) {
		cardLayout.show(cardPanel, cardName);
	}
}