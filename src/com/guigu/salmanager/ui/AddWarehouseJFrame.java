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

import com.guigu.salmanager.model.Warehouse;
import com.guigu.salmanager.service.CategoryService;
import com.guigu.salmanager.service.WarehouseService;
import com.guigu.salmanager.service.impl.CategoryServiceImpl;
import com.guigu.salmanager.service.impl.WarehouseServiceImpl;
import com.guigu.salmanager.utils.MyFont;

public class AddWarehouseJFrame extends JFrame implements MouseListener {
	JButton button_add;
	JPanel backgroundPanel, labelPanel, contentPanel, buttonPanel;
	JLabel label_name,label_sort;
	JTextField name,sort;
	int result;
	private WarehouseService warehouseService=new WarehouseServiceImpl();
	private CategoryService categoryService = new CategoryServiceImpl();
	
	final static int width = Toolkit.getDefaultToolkit().getScreenSize().width;
	final static int height = Toolkit.getDefaultToolkit().getScreenSize().height;
	
	WarehouseManagerJpanel parentPanel;
	public AddWarehouseJFrame(WarehouseManagerJpanel parentPanel) {
		this.parentPanel = parentPanel;

		initBackgroundPanel();

		this.add(backgroundPanel);
		this.setTitle("ÃÌº”≤÷ø‚–≈œ¢");
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
	private void initLablePanel() {
		labelPanel = new JPanel();
		JLabel title = new JLabel("≤÷ø‚–≈œ¢");
		title.setFont(MyFont.Static);
		labelPanel.add(title);

	}
	private void initContentPanel() {
		contentPanel = new JPanel(new GridLayout(6, 2));
		label_name = new JLabel("≤÷ø‚√˚≥∆", JLabel.CENTER);
		label_sort=new JLabel("≤÷ø‚–Ú∫≈",JLabel.CENTER);
		name = new JTextField();
		sort= new JTextField();
//		name.setBounds(255, 255, 5, 15);
		contentPanel.add(label_name);
		contentPanel.add(name);
		contentPanel.add(label_sort);
		contentPanel.add(sort);
	}
	
	private void initButtonPanel() {
		buttonPanel = new JPanel();
		button_add = new JButton("±£¥Ê");
		button_add.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.lightBlue));
		button_add.setForeground(Color.white);
		button_add.setFont(MyFont.Static);
		button_add.addMouseListener(this);
		buttonPanel.add(button_add);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == button_add) {
			String name_string=name.getText().trim();
			String sort_string=sort.getText().trim();
			if(name_string.isEmpty()) {
				JOptionPane.showMessageDialog(null, "«Î ‰»Î≤÷ø‚√˚≥∆");
			}else if(sort_string.isEmpty()) {
				JOptionPane.showMessageDialog(null, "«Î ‰»Î≤÷ø‚–Ú∫≈");
			}else {
				String id=UUID.randomUUID().toString().replace("-", "");
				int sort_int=Integer.valueOf(sort_string);
				Warehouse warehouse=new Warehouse(id,name_string,0,sort_int);
				try {
					 result=warehouseService.saveWarehouse(warehouse);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
				if(result>0) {
					JOptionPane.showMessageDialog(null, "≤÷ø‚ÃÌº”≥…π¶");
					this.setVisible(false);
					parentPanel.refreshTablePanel();
				}
			}
			
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		
	}

}
