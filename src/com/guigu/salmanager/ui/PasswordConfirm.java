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
	JButton jb = new JButton("�޸�");

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
		oldpassword = new JPasswordField(20);// ԭ����
		password1 = new JPasswordField(20);// ����������
		password2 = new JPasswordField(20);// ȷ������

		JPanel contentPanel = new JPanel(new GridLayout(3, 2));

		JLabel label_oldpassword = new JLabel("ԭ����", JLabel.CENTER);
		JLabel label_password1 = new JLabel("������", JLabel.CENTER);
		JLabel label_password12 = new JLabel("ȷ������", JLabel.CENTER);

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
		jf.setTitle("   �޸�����");
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
				JOptionPane.showMessageDialog(null, "������ԭ����");
			} else if (!(oldpassword_string.equals(user.getPassword()))) {
				JOptionPane.showMessageDialog(null, "ԭ�������");
			} else if (!(password1_string.equals(password2_string))) {
				JOptionPane.showMessageDialog(null, "�����벻һ��");
			} else if (password1_string.isEmpty() || password2_string.isEmpty()) {
				JOptionPane.showMessageDialog(null, "�����벻��Ϊ��");
			} else {
				try {
					int u = userservice.modify(user, newname, password1_string);
					JOptionPane.showMessageDialog(null, "�����޸ĳɹ�,�����µ�¼");
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
