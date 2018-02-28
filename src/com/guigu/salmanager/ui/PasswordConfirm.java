package com.guigu.salmanager.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import com.guigu.salmanager.model.Users;
import com.guigu.salmanager.service.UsersService;
import com.guigu.salmanager.service.impl.UsersServiceImpl;
import com.guigu.salmanager.utils.MyFont;

public class PasswordConfirm extends JFrame implements MouseListener {

	private UsersService userservice = new UsersServiceImpl();
	JButton jb = new JButton("修改");

	JPasswordField oldpassword, password1, password2;
	private Users user;
	String newname;
	String newpassword;
	UserManagerJPanel userManagerJPanel;
	IndexJFrame indexJFrame;
	JFrame jf = new JFrame();

	public PasswordConfirm(Users user, String newname, String newpassword, UserManagerJPanel userManagerJPanel,
			IndexJFrame indexJFrame) {
		this.user = user;
		this.newname = newname;
		this.newpassword = newpassword;
		this.userManagerJPanel = userManagerJPanel;
		this.indexJFrame = indexJFrame;
		JPanel jp = new JPanel();
		jp.setLayout(null);
		oldpassword = new JPasswordField(20);// 原密码
		password1 = new JPasswordField(20);// 输入新密码
		password2 = new JPasswordField(20);// 确认密码

		JPanel contentPanel = new JPanel(new GridLayout(3, 2));

		JLabel label_oldpassword = new JLabel("原密码", JLabel.CENTER);
		JLabel label_password1 = new JLabel("新密码", JLabel.CENTER);
		JLabel label_password12 = new JLabel("确认密码", JLabel.CENTER);

		jb.setBounds(100, 100, 70, 27);
		jb.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.lightBlue));
		jb.setForeground(Color.WHITE);
		jb.setFont(MyFont.Static);
		jb.addMouseListener(this);

		contentPanel.setSize(230, 90);
		contentPanel.add(label_oldpassword);
		contentPanel.add(oldpassword);
		contentPanel.add(label_password1);
		contentPanel.add(password1);
		contentPanel.add(label_password12);
		contentPanel.add(password2);
		jp.add(contentPanel);
		jp.add(jb);

		jf.add(jp);
		jf.setTitle("   修改密码");
		jf.setSize(300, 220);
		jf.setVisible(true);
		jf.setLocationRelativeTo(null);
		jf.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == jb) {
			String oldpassword_string = oldpassword.getText().trim();
			String password1_string = password1.getText().trim();
			String password2_string = password2.getText().trim();
			// System.out.println(oldpassword.getText());
			// System.out.println(password1.getText());
			// System.out.println(password2.getText());
			if (oldpassword_string.isEmpty()) {
				JOptionPane.showMessageDialog(null, "请输入原密码");
			} else if (!(oldpassword_string.equals(user.getPassword()))) {
				JOptionPane.showMessageDialog(null, "原密码错误");
			} else if (!(password1_string.equals(password2_string))) {
				JOptionPane.showMessageDialog(null, "新密码不一致");
			} else if (password1_string.isEmpty() || password2_string.isEmpty()) {
				JOptionPane.showMessageDialog(null, "新密码不能为空");
			} else {
				try {
					int u = userservice.modify(user, newname, password1_string);
					JOptionPane.showMessageDialog(null, "密码修改成功,请重新登录");
					jf.setVisible(false);
					indexJFrame.setVisible(false);
					new LoginJFrame();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
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

}
