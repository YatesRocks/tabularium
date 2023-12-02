package org.yates.ui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import java.awt.Font;

import net.miginfocom.swing.MigLayout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class WelcomeSplash extends JPanel {

	private static final Logger log = LoggerFactory.getLogger(WelcomeSplash.class);
	private static final String welcomeMessage = "Welcome to the Tabularium";
	private static JLabel titleLabel;
	private static Font titleFont;
	private static MigLayout layout;
	private static JTextArea description;

	WelcomeSplash() {
		layout = new MigLayout("debug", "", "");
		setLayout(layout);
		titleLabel = new JLabel(welcomeMessage);
		titleFont = new Font("Serif", Font.BOLD, 30); 
		titleLabel.setFont(titleFont); 
		description = new JTextArea("");

		add(titleLabel, "push, align center, center");

		log.info("Loaded welcome splash");
	}
}