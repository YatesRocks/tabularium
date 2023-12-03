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
	private static MigLayout layout;

	private JLabel makeTitle() {
		final String titleContent = "Welcome to the Tabularium";
		JLabel title = new JLabel(titleContent);
		Font titleFont = new Font("Monospaced", Font.BOLD, 30);
		title.setFont(titleFont);
		return title;
	}

	private JTextArea makeDescription() {
		final String descriptionText = "Welcome to your Tabularium, an intuitive and powerful graphical user interface (GUI) designed to enhance your experience with JList. Tabularium simplifies the management and navigation of data by providing a user-friendly environment for interacting with your lists. Seamlessly integrated with Java Swing, this application offers a robust set of features, allowing you to effortlessly organize, view, and manipulate tabular data. Whether you're a seasoned developer or just getting started, Tabularium is your go-to tool for a streamlined and efficient JList experience. Explore the possibilities and unleash the full potential of your data with Tabularium";
		JTextArea description = new JTextArea(descriptionText);	
		Font descriptionFont = new Font("Monospaced", Font.PLAIN, 12);
		description.setEditable(false);
		description.setLineWrap(true);
		description.setWrapStyleWord(true);
		description.setFont(descriptionFont);
		return description;
	}

	private JPanel titleDescriptionGroup() {
		JPanel group = new JPanel();
		group.setLayout(new MigLayout("wrap"));
		group.add(makeTitle(), "align center, center");
		group.add(makeDescription(), "grow, align center, center");
		return group;
	}

	WelcomeSplash() {
		layout = new MigLayout("debug", "", "");
		setLayout(layout);

		add(titleDescriptionGroup(), "push, align center, center, wrap");

		log.info("Loaded welcome splash");
	}
}