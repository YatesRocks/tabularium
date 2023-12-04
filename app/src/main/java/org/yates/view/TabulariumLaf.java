package org.yates.view;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.formdev.flatlaf.FlatDarkLaf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class TabulariumLaf {
	private static Logger log = LoggerFactory.getLogger(TabulariumLaf.class);
	private static final FlatDarkLaf lookAndFeel = new FlatDarkLaf();

	public static void setLookAndFeel() {
		try {
			UIManager.setLookAndFeel(lookAndFeel);
			log.info("Set LaF to " + lookAndFeel.getClass());
		} catch (UnsupportedLookAndFeelException e) {
			log.error(e.getMessage());
		}
	}
}