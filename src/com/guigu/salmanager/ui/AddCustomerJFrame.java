package com.guigu.salmanager.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.UUID;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import com.guigu.salmanager.model.Customer;
import com.guigu.salmanager.model.Salesman;
import com.guigu.salmanager.service.CustomerService;
import com.guigu.salmanager.service.SalesmanService;
import com.guigu.salmanager.service.impl.CustomerServiceImpl;
import com.guigu.salmanager.service.impl.SalesmanServiceImpl;
import com.guigu.salmanager.utils.DateChooser;
import com.guigu.salmanager.utils.Item;
import com.guigu.salmanager.utils.JdbcUtils;
import com.guigu.salmanager.utils.MyFont;

public class AddCustomerJFrame extends JFrame implements MouseListener {
	// 定义全局组件
	JPanel backgroundPanel, labelPanel, contentPanel, buttonPanel;
	JLabel label_simplename, label_name, label_owner, label_mobilephone, label_salesmanid, label_cutomeraddress,
			label_lastdeliverdate;
	JTextField simplename, name, owner, mobilephone, cutomeraddress, lastdeliverdate;
	JComboBox salesman;
	JButton button_add;
	DateChooser dateChooser;  
	private CustomerService customerService = new CustomerServiceImpl();
	private SalesmanService salesmanService = new SalesmanServiceImpl();
	int result;
	// 得到屏幕的宽度和高度
	final static int width = Toolkit.getDefaultToolkit().getScreenSize().width;
	final static int height = Toolkit.getDefaultToolkit().getScreenSize().height;

	// 父面板
	CustomerManagerJPanel parentPanel;

	public AddCustomerJFrame(CustomerManagerJPanel customerManagerJPanel) {
		this.parentPanel = customerManagerJPanel;

		initBackgroundPanel();

		this.add(backgroundPanel);
		this.setTitle("添加客户信息");
		this.setSize(640, 360);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}

	// 初始化背景
	private void initBackgroundPanel() {
		backgroundPanel = new JPanel(new BorderLayout());

		initContentPanel();
		initLablePanel();
		initButtonPanel();

		backgroundPanel.add(labelPanel, "North");
		backgroundPanel.add(contentPanel, "Center");
		backgroundPanel.add(buttonPanel, "South");
	}

	// 初始化顶部的标签部分
	private void initLablePanel() {
		labelPanel = new JPanel();
		JLabel title = new JLabel("客户信息");
		title.setFont(MyFont.Static);
		labelPanel.add(title);

	}

	// 初始化中间的组件
	private void initContentPanel() {
		contentPanel = new JPanel(new GridLayout(7, 2));
		label_simplename = new JLabel("客户公司简称", JLabel.CENTER);
		label_name = new JLabel("客户公司全称", JLabel.CENTER);
		label_owner = new JLabel("客户负责人", JLabel.CENTER);
		label_mobilephone = new JLabel("客户移动电话", JLabel.CENTER);
		label_salesmanid = new JLabel("销售员名字", JLabel.CENTER);
		label_cutomeraddress = new JLabel("送货地址", JLabel.CENTER);
		label_lastdeliverdate = new JLabel("最后送货日期", JLabel.CENTER);

		// 初始化文本框
		simplename = new JTextField();
		name = new JTextField();
		owner = new JTextField();
		mobilephone = new JTextField();
		cutomeraddress = new JTextField();
		lastdeliverdate = new JTextField();
		dateChooser= DateChooser.getInstance("yyyy-MM-dd");
		dateChooser.register(lastdeliverdate);  
		// 客户种类的下来框 从数据库加载的数据
		salesman = new JComboBox();

		List<Salesman> list_salesman = null;

		try {
			list_salesman = salesmanService.findAll();
			if (list_salesman != null) {

				for (int i = 0; i < list_salesman.size(); i++) {
					String id = list_salesman.get(i).getSalesman_Id();
					String name = list_salesman.get(i).getSalesmanName();
					String address = list_salesman.get(i).getContactAddress();
					salesman.addItem(new Item(id, name));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 添加组件
		contentPanel.add(label_simplename);
		contentPanel.add(simplename);
		contentPanel.add(label_name);
		contentPanel.add(name);
		contentPanel.add(label_owner);
		contentPanel.add(owner);
		contentPanel.add(label_mobilephone);
		contentPanel.add(mobilephone);
		contentPanel.add(label_salesmanid);
		contentPanel.add(salesman);
		contentPanel.add(label_cutomeraddress);
		contentPanel.add(cutomeraddress);
		contentPanel.add(label_lastdeliverdate);
		contentPanel.add(lastdeliverdate);

	}

	// 初始化底部按钮
	private void initButtonPanel() {
		buttonPanel = new JPanel();
		button_add = new JButton("保存");
		button_add.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.lightBlue));
		button_add.setForeground(Color.white);
		button_add.setFont(MyFont.Static);
		button_add.addMouseListener(this);
		buttonPanel.add(button_add);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == button_add) {

			String simplename_string = simplename.getText().trim();
			String name_string = name.getText().trim();
			String owner_string = owner.getText().trim();
			String mobilephone_string = mobilephone.getText().trim();
			String cutomeraddress_string = cutomeraddress.getText().trim();
			String lastdeliverdate_string = lastdeliverdate.getText().trim();
			if (simplename_string.isEmpty()) {
				JOptionPane.showMessageDialog(null, "请输入客户公司简称");
			} else if (name_string.isEmpty()) {
				JOptionPane.showMessageDialog(null, "请输入客户名称");
			} else if (owner_string.isEmpty()) {
				JOptionPane.showMessageDialog(null, "请输入客户负责人");
			} else if (mobilephone_string.isEmpty()) {
				JOptionPane.showMessageDialog(null, "请输入客户移动电话");
			} else if (cutomeraddress_string.isEmpty()) {
				JOptionPane.showMessageDialog(null, "请输入客户送货地址");
			} else if (lastdeliverdate_string.isEmpty()) {
				JOptionPane.showMessageDialog(null, "请输入客户最后送货日期");
			} else {

				// 得到选择的销售员id和名字
				String salesman_id = ((Item) salesman.getSelectedItem()).getKey();
				String id = UUID.randomUUID().toString().replace("-", "");
				Customer customer = new Customer(id, simplename_string, name_string, owner_string, mobilephone_string,
						salesman_id, cutomeraddress_string, lastdeliverdate_string,0);
				try {
					result = customerService.saveCustomer(customer);
				} catch (Exception e1) {
					e1.printStackTrace();
				}

				if (result > 0) {
					JOptionPane.showMessageDialog(null, "客户添加成功");
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
