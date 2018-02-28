package com.guigu.salmanager.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import com.guigu.salmanager.dao.WarehouseDAO;
import com.guigu.salmanager.dao.impl.WarehouseDAOImpl;
import com.guigu.salmanager.model.Category;
import com.guigu.salmanager.model.Goods;
import com.guigu.salmanager.model.Warehouse;
import com.guigu.salmanager.service.CategoryService;
import com.guigu.salmanager.service.GoodsService;
import com.guigu.salmanager.service.impl.CategoryServiceImpl;
import com.guigu.salmanager.service.impl.GoodsServiceImpl;
import com.guigu.salmanager.utils.Item;
import com.guigu.salmanager.utils.MyFont;

public class ModifyGoodsJFrame extends JFrame implements MouseListener {
	// 定义全局组件
	JPanel backgroundPanel, labelPanel, contentPanel, buttonPanel;
	JLabel label_name, label_price, label_origin, label_stock, label_warehouse, label_category;
	JTextField name, price, origin, stock;
	JComboBox warehouse, category;
	JButton button_add;
	private CategoryService categoryService = new CategoryServiceImpl();
	private WarehouseDAO Warehousedao = new WarehouseDAOImpl();
	private GoodsService goodsService = new GoodsServiceImpl();
	int result;
	// 表格对象
	JTable table;
	int selectedRow;
	GoodsManagerJPanel parentPanel;

	public ModifyGoodsJFrame(GoodsManagerJPanel parentPanel, JTable table, int selectedRow) {

		this.parentPanel = parentPanel;
		this.selectedRow = selectedRow;
		this.table = table;

		initBackgroundPanel();

		this.add(backgroundPanel);
		this.setTitle("修改商品信息");
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
		JLabel title = new JLabel("商品信息");
		title.setFont(MyFont.Static);
		labelPanel.add(title);

	}

	// 初始化中间的组件
	private void initContentPanel() {
		contentPanel = new JPanel(new GridLayout(6, 2));

		label_name = new JLabel("商品名称", JLabel.CENTER);
		label_price = new JLabel("商品价格", JLabel.CENTER);
		label_origin = new JLabel("商品产地", JLabel.CENTER);
		label_stock = new JLabel("商品库存", JLabel.CENTER);
		label_warehouse = new JLabel("所属仓库", JLabel.CENTER);
		label_category = new JLabel("所属分类", JLabel.CENTER);

		double price_double = (Double) table.getValueAt(selectedRow, 2);
		String price_string = String.valueOf(price_double);

		double stock_double = (Double) table.getValueAt(selectedRow, 6);
		String stock_string = String.valueOf(stock_double);
		// 初始化文本框
		name = new JTextField((String) table.getValueAt(selectedRow, 1));
		price = new JTextField(price_string);
		origin = new JTextField((String) table.getValueAt(selectedRow, 3));
		stock = new JTextField(stock_string);

		// 商品种类的下来框 从数据库加载的数据
		category = new JComboBox();

		List<Category> list_category = null;

		try {
			list_category = categoryService.findAll();
			if (list_category != null) {
				int sign = 0;
				for (int i = 0; i < list_category.size(); i++) {
					String id = list_category.get(i).getCategory_Id();
					String name = list_category.get(i).getName();
					if (id.equals(table.getValueAt(selectedRow, 8))) {
						sign = i;
					}
					category.addItem(new Item(id, name));
				}
				category.setSelectedIndex(sign);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 仓库下拉列表
		warehouse = new JComboBox();

		List<Warehouse> list_warehouse = null;

		try {
			list_warehouse = Warehousedao.findAll();
			if (list_warehouse != null) {
				int sign = 0;
				for (int i = 0; i < list_warehouse.size(); i++) {
					String id = list_warehouse.get(i).getWarehouse_Id();
					String name = list_warehouse.get(i).getName();
					if (id.equals(table.getValueAt(selectedRow, 7))) {
						sign = i;
					}
					warehouse.addItem(new Item(id, name));
				}

				// 设置默认选中的情况
				warehouse.setSelectedIndex(sign);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 添加组件
		contentPanel.add(label_name);
		contentPanel.add(name);
		contentPanel.add(label_price);
		contentPanel.add(price);
		contentPanel.add(label_origin);
		contentPanel.add(origin);
		contentPanel.add(label_stock);
		contentPanel.add(stock);
		contentPanel.add(label_category);
		contentPanel.add(category);
		contentPanel.add(label_warehouse);
		contentPanel.add(warehouse);

	}

	// 初始化底部按钮
	private void initButtonPanel() {
		buttonPanel = new JPanel();
		button_add = new JButton("修改");
		button_add.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.lightBlue));
		button_add.setForeground(Color.white);
		button_add.setFont(MyFont.Static);
		button_add.addMouseListener(this);
		buttonPanel.add(button_add);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == button_add) {
			String name_string = name.getText().trim();
			String price_string = price.getText().trim();
			String origin_string = origin.getText().trim();
			String stock_string = stock.getText().trim();

			if (name_string.isEmpty()) {
				JOptionPane.showMessageDialog(null, "请输入商品名称");
			} else if (price_string.isEmpty()) {
				JOptionPane.showMessageDialog(null, "请输入商品价格");
			} else if (origin_string.isEmpty()) {
				JOptionPane.showMessageDialog(null, "请输入商品产地");
			} else if (stock_string.isEmpty()) {
				JOptionPane.showMessageDialog(null, "请输入商品库存");
			} else {

				double price_double = Double.valueOf(price_string);
				double stock_double = Double.valueOf(stock_string);
				// 得到选择的仓库id和分类id
				String warehouse_id = ((Item) warehouse.getSelectedItem()).getKey();
				String category_id = ((Item) category.getSelectedItem()).getKey();
				String goods_Id = (String) table.getValueAt(selectedRow, 0);

				Goods goods = new Goods(goods_Id, name_string, price_double, origin_string, stock_double, warehouse_id,
						category_id, 0);
				// System.out.println(goods.toString());
				try {
					result = goodsService.updateGoods(goods);
					// System.out.println(result);
				} catch (Exception e1) {
					e1.printStackTrace();
				}

				if (result > 0) {
					JOptionPane.showMessageDialog(null, "商品修改成功");
					this.setVisible(false);
					try {
						parentPanel.vector = goodsService.findAllGoods();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
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
