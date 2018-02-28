package com.guigu.salmanager.ui;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import com.guigu.salmanager.model.Users;
import com.guigu.salmanager.service.UsersService;
import com.guigu.salmanager.service.impl.UsersServiceImpl;
import com.guigu.salmanager.utils.ImagePanel;
import com.guigu.salmanager.utils.MyFont;

public class LoginJFrame extends JFrame implements MouseListener, FocusListener {

	// 定义全局组件
	JTextField username = new JTextField(20);
	JPasswordField password = new JPasswordField(20);

	// 定义 背景图片
	ImagePanel backgroundPanle = null;
	// 定义按钮
	JButton button_login, button_reset;
	
	private UsersService userservice =new UsersServiceImpl();
	
	public LoginJFrame() {
		Image backgroundImage = null;
		try {
			backgroundImage = ImageIO.read(new File("image/loginbackground.png"));
			Image img = ImageIO.read(new File("image/logo.png"));
			this.setIconImage(img);
		} catch (IOException e) {
			e.printStackTrace();
		}

		backgroundPanle = new ImagePanel(backgroundImage);
		backgroundPanle.setLayout(null);

		username.setBounds(378, 202, 173, 30);
		username.setFont(MyFont.Static);
		username.addFocusListener(this);
		username.setText("用户名/账户");

		password.setBounds(378, 240, 173, 30);
		password.setFont(MyFont.Static);
		password.addFocusListener(this);
		password.setText("密码");
		password.setEchoChar('\0');

		// 添加两个按钮
		button_login = new JButton("登录");
		button_login.setBounds(380, 280, 70, 27);
		button_login.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.lightBlue));
		button_login.setForeground(Color.WHITE);
		button_login.setFont(MyFont.Static);
		button_login.addMouseListener(this);

		button_reset = new JButton("重置");
		button_reset.setBounds(480, 280, 70, 27);
		button_reset.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.red));
		button_reset.setForeground(Color.WHITE);
		button_reset.setFont(MyFont.Static);
		button_reset.addMouseListener(this);

		backgroundPanle.add(button_login);
		backgroundPanle.add(button_reset);
		backgroundPanle.add(username);
		backgroundPanle.add(password);

		this.add(backgroundPanle);
		this.setTitle("   销售管理系统");
		this.setSize(830, 530);
		this.setVisible(true);
		this.requestFocus();
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource()==button_login) {
			if("用户名/账户".equals(username.getText())) {
				JOptionPane.showMessageDialog(null, "用户名不能为空");
			}else if("密码".equals(password.getText())) {
				JOptionPane.showMessageDialog(null, "用户密码不能为空");
			}else {
			
				try {
					//用户输入的用户名和密码
					Users loginUser=new Users();
					loginUser.setName(username.getText());
					loginUser.setPassword(password.getText());
					
					//查询用户名和密码是否存在
					Users findUser =userservice.login(loginUser);
					if(findUser==null) {
						JOptionPane.showMessageDialog(null, "用户名或者密码不正确");
					}else {
//						System.out.println("用户名密码正确...登录到主页面面");
						//.......主页面
						this.setVisible(false);
						new IndexJFrame(findUser);
					}
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}else  if(e.getSource()==button_reset){
			username.setText("用户名/账户");
			password.setText("密码");
			password.setEchoChar('\0');
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	// 获得焦点
	@Override
	public void focusGained(FocusEvent e) {
		if (e.getSource() == username) {
			if (username.getText().equals("用户名/账户")) {
				username.setText("");
			}
		} else if (e.getSource() == password) {
			if (password.getText().equals("密码")) {
				password.setText("");
				password.setEchoChar('*');
			}

		}

	}

	@Override
	public void focusLost(FocusEvent e) {
		if (e.getSource() == username) {
			if (username.getText().equals("")) {
				username.setText("用户名/账户");
			}
		} else if (e.getSource() == password) {
			if (password.getText().equals("")) {
				password.setText("密码");
				password.setEchoChar('\0');
			}

		}

	}

}
