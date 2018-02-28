package com.guigu.salmanager.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.UUID;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import com.guigu.salmanager.model.Salesman;
import com.guigu.salmanager.service.SalesmanService;
import com.guigu.salmanager.service.impl.SalesmanServiceImpl;
import com.guigu.salmanager.utils.MyFont;

public class AddSalesmanJFrame extends JFrame implements MouseListener {

	JPanel backgroundPanel, labelPanel, contentPanel, buttonPanel;
	JLabel label_salesmanname, label_mobiletelephone, label_contactaddress, label_email;
	JTextField salesmanname, mobiletelephone, contactaddress, email;
	JButton button_add;
	private SalesmanService salesmanService = new SalesmanServiceImpl();

	int result;
	// �õ���Ļ�Ŀ�Ⱥ͸߶�
	final static int width = Toolkit.getDefaultToolkit().getScreenSize().width;
	final static int height = Toolkit.getDefaultToolkit().getScreenSize().height;

	// �����
	SalesmanManagerJPanel parentPanel;

	public AddSalesmanJFrame(SalesmanManagerJPanel salesmanManagerJPanel) {
		this.parentPanel = salesmanManagerJPanel;

		initBackgroundPanel();

		this.add(backgroundPanel);
		this.setTitle("�������Ա��Ϣ");
		this.setSize(440, 260);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}

	private void initBackgroundPanel() {
		backgroundPanel = new JPanel(new BorderLayout());

		initContentPanel();
		initLablePanel();
		initButtonPanel();

		backgroundPanel.add(labelPanel, "North");
		backgroundPanel.add(contentPanel, "Center");
		backgroundPanel.add(buttonPanel, "South");
	}

	private void initButtonPanel() {
		buttonPanel = new JPanel();
		button_add = new JButton("����");
		button_add.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.lightBlue));
		button_add.setForeground(Color.white);
		button_add.setFont(MyFont.Static);
		button_add.addMouseListener(this);
		buttonPanel.add(button_add);
	}

	private void initLablePanel() {
		labelPanel = new JPanel();
		JLabel title = new JLabel("����Ա��Ϣ");
		title.setFont(MyFont.Static);
		labelPanel.add(title);
	}

	private void initContentPanel() {
		contentPanel = new JPanel(new GridLayout(4, 2));

		label_salesmanname = new JLabel("����Ա����", JLabel.CENTER);
		label_mobiletelephone = new JLabel("����Ա�ƶ��绰", JLabel.CENTER);
		label_contactaddress = new JLabel("����Ա��ַ", JLabel.CENTER);
		label_email = new JLabel("����Ա����", JLabel.CENTER);

		// ��ʼ���ı���
		salesmanname = new JTextField();
		mobiletelephone = new JTextField();
		contactaddress = new JTextField();
		email = new JTextField();

		// ������
		contentPanel.add(label_salesmanname);
		contentPanel.add(salesmanname);
		contentPanel.add(label_mobiletelephone);
		contentPanel.add(mobiletelephone);
		contentPanel.add(label_contactaddress);
		contentPanel.add(contactaddress);
		contentPanel.add(label_email);
		contentPanel.add(email);

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == button_add) {
			
			String salesmanname_string = salesmanname.getText().trim();
			String mobiletelephone_string = mobiletelephone.getText().trim();
			String contactaddress_string = contactaddress.getText().trim();
			String email_string = email.getText().trim();

			
			if (salesmanname_string.isEmpty()) {
				JOptionPane.showMessageDialog(null, "����������Ա����");
			} else if (mobiletelephone_string.isEmpty()) {
				JOptionPane.showMessageDialog(null, "����������Ա�ƶ��绰");
			} else if (contactaddress_string.isEmpty()) {
				JOptionPane.showMessageDialog(null, "����������Ա��ַ");
			} else if (email_string.isEmpty()) {
				JOptionPane.showMessageDialog(null, "����������Ա����");
			} else {

				String id = UUID.randomUUID().toString().replace("-", "");
				Salesman salesman = new Salesman(id, salesmanname_string, mobiletelephone_string, contactaddress_string,
						email_string,0);
				try {
					result = salesmanService.saveSalesman(salesman);
				} catch (Exception e1) {
					e1.printStackTrace();
				}

				if (result > 0) {
					JOptionPane.showMessageDialog(null, "����Ա��ӳɹ�");
					this.setVisible(false);
					parentPanel.refreshTablePanel();
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
