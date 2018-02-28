package com.guigu.salmanager.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.UUID;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import com.guigu.salmanager.model.Supplier;
import com.guigu.salmanager.service.SupplierService;
import com.guigu.salmanager.service.impl.SupplierServiceImpl;
import com.guigu.salmanager.utils.DateChooser;
import com.guigu.salmanager.utils.MyFont;
import com.guigu.salmanager.utils.DateChooser;

public class ModifySupplierJFrame extends JFrame implements MouseListener {

	JPanel backgroundPanel, labelPanel, contentPanel, buttonPanel;
	JLabel label_simplename, label_name, label_owner, label_mobilephone, label_companyAddress, label_factoryAddress,
			label_lastPurchaseDate;
	JTextField simplename, name, owner, mobilephone, companyAddress, factoryAddress, lastPurchaseDate;
	JButton button_add;
	int result;
	DateChooser dateChooser;  
	private SupplierService supplierService = new SupplierServiceImpl();
	// ������
	JTable table;
	int selectedRow;
	// �����
	SupplierManagerJPanel parentPanel;

	public ModifySupplierJFrame(SupplierManagerJPanel supplierManagerJPanel, JTable table, int selectedRow) {
		this.parentPanel = supplierManagerJPanel;
		this.selectedRow = selectedRow;
		this.table = table;

		initBackgroundPanel();

		this.add(backgroundPanel);
		this.setTitle("�޸Ĺ�Ӧ����Ϣ");
		this.setSize(640, 360);
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
		button_add = new JButton("�޸�");
		button_add.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.lightBlue));
		button_add.setForeground(Color.white);
		button_add.setFont(MyFont.Static);
		button_add.addMouseListener(this);
		buttonPanel.add(button_add);
	}

	private void initLablePanel() {
		labelPanel = new JPanel();
		JLabel title = new JLabel("��Ӧ����Ϣ");
		title.setFont(MyFont.Static);
		labelPanel.add(title);
	}

	private void initContentPanel() {
		contentPanel = new JPanel(new GridLayout(7, 2));
		label_simplename = new JLabel("��Ӧ�̹�˾���", JLabel.CENTER);
		label_name = new JLabel("��Ӧ�̹�˾ȫ��", JLabel.CENTER);
		label_owner = new JLabel("��Ӧ�̸�����", JLabel.CENTER);
		label_mobilephone = new JLabel("��Ӧ���ƶ��绰", JLabel.CENTER);
		label_companyAddress = new JLabel("��˾��ַ", JLabel.CENTER);
		label_factoryAddress = new JLabel("������ַ", JLabel.CENTER);
		label_lastPurchaseDate = new JLabel("����������", JLabel.CENTER);

		// ��ʼ���ı���
		simplename = new JTextField((String) table.getValueAt(selectedRow, 1));
		name = new JTextField((String) table.getValueAt(selectedRow, 2));
		owner = new JTextField((String) table.getValueAt(selectedRow, 3));
		mobilephone = new JTextField((String) table.getValueAt(selectedRow, 4));
		companyAddress = new JTextField((String) table.getValueAt(selectedRow, 5));
		factoryAddress = new JTextField((String) table.getValueAt(selectedRow, 6));
		lastPurchaseDate = new JTextField((String) table.getValueAt(selectedRow, 7));
		dateChooser= DateChooser.getInstance("yyyy-MM-dd");
		dateChooser.register(lastPurchaseDate);  
		// ������
		contentPanel.add(label_simplename);
		contentPanel.add(simplename);
		contentPanel.add(label_name);
		contentPanel.add(name);
		contentPanel.add(label_owner);
		contentPanel.add(owner);
		contentPanel.add(label_mobilephone);
		contentPanel.add(mobilephone);
		contentPanel.add(label_companyAddress);
		contentPanel.add(companyAddress);
		contentPanel.add(label_factoryAddress);
		contentPanel.add(factoryAddress);
		contentPanel.add(label_lastPurchaseDate);
		contentPanel.add(lastPurchaseDate);
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		if (e.getSource() == button_add) {

			String simplename_string = simplename.getText().trim();
			String name_string = name.getText().trim();
			String owner_string = owner.getText().trim();
			String mobilephone_string = mobilephone.getText().trim();
			String companyAddress_string = companyAddress.getText().trim();
			String factoryAddress_string = factoryAddress.getText().trim();
			String lastPurchaseDate_string = lastPurchaseDate.getText().trim();
			// "��˾���", "��˾ȫ��", "������", "�ƶ��绰", "����Ա���", "�ͻ���ַ", "����ͻ�����"
			if (simplename_string.isEmpty()) {
				JOptionPane.showMessageDialog(null, "�����빩Ӧ�̹�˾���");
			} else if (name_string.isEmpty()) {
				JOptionPane.showMessageDialog(null, "�����빩Ӧ������");
			} else if (owner_string.isEmpty()) {
				JOptionPane.showMessageDialog(null, "�����빩Ӧ�̸�����");
			} else if (mobilephone_string.isEmpty()) {
				JOptionPane.showMessageDialog(null, "�����빩Ӧ���ƶ��绰");
			} else if (companyAddress_string.isEmpty()) {
				JOptionPane.showMessageDialog(null, "�����빩Ӧ�̹�˾��ַ");
			} else if (factoryAddress_string.isEmpty()) {
				JOptionPane.showMessageDialog(null, "�����빩Ӧ�̹�����ַ");
			} else if (lastPurchaseDate_string.isEmpty()) {
				JOptionPane.showMessageDialog(null, "�����빩Ӧ������������");
			} else {

				String id =  (String) table.getValueAt(selectedRow, 0);
				Supplier supplier = new Supplier(id, simplename_string, name_string, owner_string, mobilephone_string,
						companyAddress_string, factoryAddress_string, lastPurchaseDate_string,0);
				try {
					result = supplierService.updateSupplier(supplier);
				} catch (Exception e1) {
					e1.printStackTrace();
				}

				if (result > 0) {
					JOptionPane.showMessageDialog(null, "��Ӧ���޸ĳɹ�");
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
