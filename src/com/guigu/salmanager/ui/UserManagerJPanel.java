package com.guigu.salmanager.ui;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import com.guigu.salmanager.model.Users;
import com.guigu.salmanager.utils.ImagePanel;
import com.guigu.salmanager.utils.Item;
import com.guigu.salmanager.utils.MyFont;

public class UserManagerJPanel extends MouseAdapter implements MouseListener {

	private Users user;
	String newname;
	String newpassword;
	JPanel backgroundPanel;
	IndexJFrame indexJFrame;

	JLabel label_username, label_password;

	JTextField username = new JTextField(20);
	JPasswordField password = new JPasswordField(20);
	JComboBox identity;
	JButton button_modify;
	String str2;

	public UserManagerJPanel(Users user, String name, IndexJFrame indexJFrame) {
		this.user = user;
		this.newname = name;
		this.newpassword = newpassword;
		this.indexJFrame = indexJFrame;
		Image backgroundImage = null;
		try {
			backgroundImage = ImageIO.read(new File("image/userbackground.jpg"));

		} catch (IOException e) {
			e.printStackTrace();
		}

		backgroundPanel = new ImagePanel(backgroundImage);
		backgroundPanel.setLayout(null);

		username.setBounds(478, 202, 173, 30);
		username.setFont(MyFont.Static);
		username.setText(user.getName());
		username.setEditable(false);

		password.setBounds(478, 240, 173, 30);
		password.setFont(MyFont.Static);
		password.setText(user.getPassword());
		password.setEchoChar('*');
		password.setEditable(false);

		identity = new JComboBox();
		String str1 = user.getName();
		// str2 = user.getIdentity();
		if (user.getIdentity().equals("1")) {
			str2 = "管理员";
		} else {
			str2 = "普通用户";
		}
		// System.out.println(str1+str2);

		identity.addItem(new Item(str1, str2));
		identity.setBounds(478, 278, 110, 30);
		identity.setEditable(false);

		button_modify = new JButton("修改");
		button_modify.setBounds(510, 320, 70, 27);
		button_modify.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.lightBlue));
		button_modify.setForeground(Color.WHITE);
		button_modify.setFont(MyFont.Static);
		button_modify.addMouseListener(this);

		label_username = new JLabel("用户名", JLabel.CENTER);
		label_password = new JLabel("密码", JLabel.CENTER);
		label_username.setBounds(358, 202, 173, 30);
		label_password.setBounds(358, 240, 173, 30);
		label_username.setFont(MyFont.Static);
		label_password.setFont(MyFont.Static);

		backgroundPanel.add(label_username);
		backgroundPanel.add(label_password);
		backgroundPanel.add(username);
		backgroundPanel.add(password);
		backgroundPanel.add(identity);
		backgroundPanel.add(button_modify);

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == button_modify) {
			new PasswordConfirm(user, newname, newpassword, null, indexJFrame);
		}
	}

}
