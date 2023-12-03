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
	private static Font textFont;
	private static MigLayout layout;
	private static JTextArea description;
	private static final String descriptionText = "Welcome to your Tabularium, an intuitive and powerful graphical user interface (GUI) designed to enhance your experience with JList. Tabularium simplifies the management and navigation of data by providing a user-friendly environment for interacting with your lists. Seamlessly integrated with Java Swing, this application offers a robust set of features, allowing you to effortlessly organize, view, and manipulate tabular data. Whether you're a seasoned developer or just getting started, Tabularium is your go-to tool for a streamlined and efficient JList experience. Explore the possibilities and unleash the full potential of your data with Tabularium";

	WelcomeSplash() {
		layout = new MigLayout("debug", "", "");
		setLayout(layout);
		titleLabel = new JLabel(welcomeMessage);
		titleFont = new Font("Serif", Font.BOLD, 30); 
		textFont = new Font("Monospaced", Font.PLAIN, 12);
		titleLabel.setFont(titleFont); 
		description = new JTextArea(descriptionText);
		description.setEditable(false);
		description.setLineWrap(true);
		description.setWrapStyleWord(true);
		description.setFont(textFont);

		add(titleLabel, "push, align center, center, wrap");
		add(description, "grow, align center, center");

		log.info("Loaded welcome splash");
	}
}