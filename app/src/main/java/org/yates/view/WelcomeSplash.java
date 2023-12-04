package org.yates.view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import javax.swing.text.StyledDocument;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.BadLocationException;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import net.miginfocom.swing.MigLayout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.yates.controller.CardChangeListener;

class WelcomeSplash extends JPanel {

	private static final Logger log = LoggerFactory.getLogger(WelcomeSplash.class);
	private CardChangeListener cardChangeListener;

	private JLabel makeTitle() {
		final String titleContent = "Welcome to the Tabularium";
		JLabel title = new JLabel(titleContent);
		Font titleFont = new Font("Serif", Font.BOLD, 30);
		title.setFont(titleFont);
		return title;
	}

    private JTextPane makeDescription() {
        final String descriptionText = "Welcome to your Tabularium, an intuitive and powerful graphical user interface (GUI) designed to enhance your experience with JList. Tabularium simplifies the management and navigation of data by providing a user-friendly environment for interacting with your lists. Seamlessly integrated with Java Swing, this application offers a robust set of features, allowing you to effortlessly organize, view, and manipulate tabular data. Whether you're a seasoned developer or just getting started, Tabularium is your go-to tool for a streamlined and efficient JList experience. Explore the possibilities and unleash the full potential of your data with Tabularium.";

        JTextPane description = new JTextPane();
        StyledDocument doc = description.getStyledDocument();

        // Set default font style
        Style defaultStyle = StyleContext.getDefaultStyleContext().getStyle(StyleContext.DEFAULT_STYLE);
        StyleConstants.setFontFamily(defaultStyle, "Monospaced");
        StyleConstants.setFontSize(defaultStyle, 12);

        // Set text
        try {
            doc.insertString(0, descriptionText, defaultStyle);
            StyleConstants.setAlignment(defaultStyle, StyleConstants.ALIGN_JUSTIFIED);
			doc.setParagraphAttributes(0, doc.getLength(), defaultStyle, false);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }

        // Set additional attributes
        description.setEditable(false);
        description.setOpaque(true);  // Make it non-opaque to allow background customization
        description.setAlignmentX(JTextPane.CENTER_ALIGNMENT);

        return description;
    }

    private JButton createBeginButton() {
		JButton begin = new JButton("Click here to begin");
		begin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (cardChangeListener != null)
					cardChangeListener.onCardChange("DatabasePanel");
			}
		});
		return begin;
    }

	private JPanel welcomeBox() {
		JPanel group = new JPanel();
		group.setLayout(new MigLayout("wrap"));
		group.add(makeTitle(), "align center, center");
		group.add(makeDescription(), "grow, align center, center, width ::100%");
		group.add(createBeginButton(), "align center, center");
		return group;
	}

	public void setCardChangeListener(CardChangeListener listener) {
		this.cardChangeListener = listener;
		log.info("Set card change listener to " + listener.getClass());
	}

	WelcomeSplash() {
		MigLayout layout = new MigLayout("debug, wrap", "", "");
		setLayout(layout);

		add(welcomeBox(), "push, align center, center");
	}
}