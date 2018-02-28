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
	// 得到屏幕的宽度和高度
	final static int width = Toolkit.getDefaultToolkit().getScreenSize().width;
	final static int height = Toolkit.getDefaultToolkit().getScreenSize().height;

	// 父面板
	SalesmanManagerJPanel parentPanel;

	public AddSalesmanJFrame(SalesmanManagerJPanel salesmanManagerJPanel) {
		this.parentPanel = salesmanManagerJPanel;

		initBackgroundPanel();

		this.add(backgroundPanel);
		this.setTitle("添加销售员信息");
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
		button_add = new JButton("保存");
		button_add.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.lightBlue));
		button_add.setForeground(Color.white);
		button_add.setFont(MyFont.Static);
		button_add.addMouseListener(this);
		buttonPanel.add(button_add);
	}

	private void initLablePanel() {
		labelPanel = new JPanel();
		JLabel title = new JLabel("销售员信息");
		title.setFont(MyFont.Static);
		labelPanel.add(title);
	}

	private void initContentPanel() {
		contentPanel = new JPanel(new GridLayout(4, 2));

		label_salesmanname = new JLabel("销售员名字", JLabel.CENTER);
		label_mobiletelephone = new JLabel("销售员移动电话", JLabel.CENTER);
		label_contactaddress = new JLabel("销售员地址", JLabel.CENTER);
		label_email = new JLabel("销售员邮箱", JLabel.CENTER);

		// 初始化文本框
		salesmanname = new JTextField();
		mobiletelephone = new JTextField();
		contactaddress = new JTextField();
		email = new JTextField();

		// 添加组件
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
				JOptionPane.showMessageDialog(null, "请输入销售员名字");
			} else if (mobiletelephone_string.isEmpty()) {
				JOptionPane.showMessageDialog(null, "请输入销售员移动电话");
			} else if (contactaddress_string.isEmpty()) {
				JOptionPane.showMessageDialog(null, "请输入销售员地址");
			} else if (email_string.isEmpty()) {
				JOptionPane.showMessageDialog(null, "请输入销售员邮箱");
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
					JOptionPane.showMessageDialog(null, "销售员添加成功");
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
