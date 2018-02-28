package com.guigu.salmanager.utils;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class ImagePanel extends JPanel {

	Image image;

	public ImagePanel(Image image) {
		this.image = image;

		// ��ȡ��ǰ��Ļ�Ŀ�Ⱥ͸߶�
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int heigth = Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setSize(width, heigth);
	}

	public void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
	}

}
