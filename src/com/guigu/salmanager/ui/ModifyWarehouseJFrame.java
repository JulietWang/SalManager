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
import javax.swing.JTable;
import javax.swing.JTextField;

import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import com.guigu.salmanager.model.Warehouse;
import com.guigu.salmanager.service.CategoryService;
import com.guigu.salmanager.service.WarehouseService;
import com.guigu.salmanager.service.impl.CategoryServiceImpl;
import com.guigu.salmanager.service.impl.WarehouseServiceImpl;
import com.guigu.salmanager.utils.MyFont;

public class ModifyWarehouseJFrame extends JFrame implements MouseListener {
	JButton button_add;
	JPanel backgroundPanel, labelPanel, contentPanel, buttonPanel;
	JLabel label_name, label_sort;
	JTextField name, sort;
	int result;
	private WarehouseService warehouseService = new WarehouseServiceImpl();

	JTable table;
	int selectedRow;
	WarehouseManagerJpanel parentPanel;

	public ModifyWarehouseJFrame(WarehouseManagerJpanel parentPanel, JTable table, int selectedRow) {
		this.parentPanel = parentPanel;
		this.selectedRow = selectedRow;
		this.table = table;

		initBackgroundPanel();

		this.add(backgroundPanel);
		this.setTitle("ÐÞ¸Ä²Ö¿âÐÅÏ¢");
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
		button_add = new JButton("ÐÞ¸Ä");
		button_add.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.lightBlue));
		button_add.setForeground(Color.white);
		button_add.setFont(MyFont.Static);
		button_add.addMouseListener(this);
		buttonPanel.add(button_add);

	}

	private void initLablePanel() {
		labelPanel = new JPanel();
		JLabel title = new JLabel("²Ö¿âÐÅÏ¢");
		title.setFont(MyFont.Static);
		labelPanel.add(title);
	}

	private void initContentPanel() {
		contentPanel = new JPanel(new GridLayout(6, 2));

		label_name = new JLabel("²Ö¿âÃû³Æ", JLabel.CENTER);
		label_sort = new JLabel("²Ö¿âÐòºÅ", JLabel.CENTER);

		int sort_int = (Integer) table.getValueAt(selectedRow, 3);
		String sorte_string = String.valueOf(sort_int);

		name = new JTextField((String) table.getValueAt(selectedRow, 1));
		sort = new JTextField(sorte_string);
		contentPanel.add(label_name);
		contentPanel.add(name);
		contentPanel.add(label_sort);
		contentPanel.add(sort);

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == button_add) {
			String name_string = name.getText().trim();
			String sort_string = sort.getText().trim();
			if (name_string.isEmpty()) {
				JOptionPane.showMessageDialog(null, "ÇëÊäÈë²Ö¿âÃû³Æ");
			} else if (sort_string.isEmpty()) {
				JOptionPane.showMessageDialog(null, "ÇëÊäÈë²Ö¿â¼Û¸ñ");
			} else {
				int sort_int = Integer.valueOf(sort_string);
				String id = (String) table.getValueAt(selectedRow, 0);

				Warehouse warehouse = new Warehouse(id, name_string, 0, sort_int);
				try {
					result = warehouseService.updateWarehouse(warehouse);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				if (result > 0) {
					JOptionPane.showMessageDialog(null, "²Ö¿âÐÞ¸Ä³É¹¦");
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
