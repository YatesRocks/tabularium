package org.yates.view;

import org.yates.controller.FileActions;

import javax.swing.JMenuBar; 
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

class DatabaseMenuBar extends JMenuBar {
	DatabaseMenuBar(FileActions fileActions) {
		add(new FileMenu(fileActions));
	}
}