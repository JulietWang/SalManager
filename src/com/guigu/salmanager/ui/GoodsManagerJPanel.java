package com.guigu.salmanager.ui;

import java.awt.BorderLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Vector;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableColumnModel;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.guigu.salmanager.dao.GoodsDAO;
import com.guigu.salmanager.dao.impl.GoodsDAOImpl;
import com.guigu.salmanager.model.Category;
import com.guigu.salmanager.model.Goods;
import com.guigu.salmanager.model.Warehouse;
import com.guigu.salmanager.service.CategoryService;
import com.guigu.salmanager.service.GoodsService;
import com.guigu.salmanager.service.WarehouseService;
import com.guigu.salmanager.service.impl.CategoryServiceImpl;
import com.guigu.salmanager.service.impl.GoodsServiceImpl;
import com.guigu.salmanager.service.impl.WarehouseServiceImpl;
import com.guigu.salmanager.utils.BaseTableModule;
import com.guigu.salmanager.utils.Item;
import com.guigu.salmanager.utils.Tools;

public class GoodsManagerJPanel extends MouseAdapter {

	JPanel backgroundPanel, topPanel, toolPanel, selectPanel, tablePanel;
	JTable table;
	JScrollPane jScrollPane;
	JLabel label_category, label_warehouse, tool_add, tool_modify, tool_delete, tool_import;
	JComboBox warehouse, category;
	BaseTableModule baseTableModule;
	static Vector<Vector> vector = new Vector<>();
	private GoodsService goodsService = new GoodsServiceImpl();
	private GoodsDAO goodsDAO = new GoodsDAOImpl();
	private CategoryService categoryService = new CategoryServiceImpl();
	private WarehouseService warehouseService = new WarehouseServiceImpl();

	public GoodsManagerJPanel() {
		backgroundPanel = new JPanel(new BorderLayout());
		try {
			// 把所有的商品信息 封装到Vector中
			vector = goodsService.findAllGoods();
		} catch (Exception e) {
			e.printStackTrace();
		}
		initTopPanel();
		initTablePanel(vector);

	}

	// 初始化顶部面板
	private void initTopPanel() {
		topPanel = new JPanel(new BorderLayout());
		initToolPanel();
		initSelectPanel();
		backgroundPanel.add(topPanel, "North");
	}

	private void initSelectPanel() {
		selectPanel = new JPanel();
		label_category = new JLabel("商品种类");
		label_warehouse = new JLabel("所属仓库");
		// 商品种类的下来框 从数据库加载的数据
		category = new JComboBox();
		category.addItem(new Item("all", "全部"));
		List<Category> list_category = null;

		try {
			list_category = categoryService.findAll();
			if (list_category != null) {

				for (int i = 0; i < list_category.size(); i++) {
					String id = list_category.get(i).getCategory_Id();
					String name = list_category.get(i).getName();
					category.addItem(new Item(id, name));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 仓库下拉列表
		warehouse = new JComboBox();
		warehouse.addItem(new Item("all", "全部"));
		List<Warehouse> list_warehouse = null;

		try {
			list_warehouse = warehouseService.findAllWarehouse();
			if (list_warehouse != null) {
				for (int i = 0; i < list_warehouse.size(); i++) {
					String id = list_warehouse.get(i).getWarehouse_Id();
					String name = list_warehouse.get(i).getName();
					warehouse.addItem(new Item(id, name));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		category.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				String warehouse_value = ((Item) warehouse.getSelectedItem()).getValue();
				String category_value = ((Item) category.getSelectedItem()).getValue();
				try {
					vector = goodsService.findGoods(category_value, warehouse_value);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				refreshTablePanel();
			}
		});

		warehouse.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				String warehouse_value = ((Item) warehouse.getSelectedItem()).getValue();
				String category_value = ((Item) category.getSelectedItem()).getValue();
				try {
					vector = goodsService.findGoods(category_value, warehouse_value);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				refreshTablePanel();
			}
		});
		warehouse.setSelectedIndex(0);
		category.setSelectedIndex(0);
		selectPanel.add(label_category);
		selectPanel.add(category);
		selectPanel.add(label_warehouse);
		selectPanel.add(warehouse);
		selectPanel.addMouseListener(this);
		topPanel.add(selectPanel, "East");

	}

	// 初始化工具面板
	private void initToolPanel() {
		toolPanel = new JPanel();
		// 菜单图标
		Icon icon_add = new ImageIcon("image/add.png");
		tool_add = new JLabel(icon_add);
		tool_add.setToolTipText("新建商品");
		tool_add.addMouseListener(this);

		Icon icon_modify = new ImageIcon("image/modify.png");
		tool_modify = new JLabel(icon_modify);
		tool_modify.setToolTipText("修改商品");
		tool_modify.addMouseListener(this);

		Icon icon_delete = new ImageIcon("image/delete.png");
		tool_delete = new JLabel(icon_delete);
		tool_delete.setToolTipText("删除商品");
		tool_delete.addMouseListener(this);

		Icon icon_import = new ImageIcon("image/import.png");
		tool_import = new JLabel(icon_import);
		tool_import.setToolTipText("数据导出");
		tool_import.addMouseListener(this);

		toolPanel.add(tool_add);
		toolPanel.add(tool_modify);
		toolPanel.add(tool_delete);
		toolPanel.add(tool_import);

		topPanel.add(toolPanel, "West");
	}

	private void initTablePanel(Vector vector) {
		String[] params = { "商品id", "名称", "价格", "产地", "所属分类", "所属仓库", "库存", "仓库id", "分类id" };

		baseTableModule = new BaseTableModule(params, vector);
		table = new JTable(baseTableModule);

		// 设置自定义的表格样式
		Tools.setTableStyle(table);
		// 通过列的模型设置某些列不可见
		DefaultTableColumnModel dcm = (DefaultTableColumnModel) table.getColumnModel();
		// 将第一列 第八列和第九列 将宽度和高度设置为0
		dcm.getColumn(0).setMinWidth(0);
		dcm.getColumn(0).setMaxWidth(0);

		dcm.getColumn(7).setMinWidth(0);
		dcm.getColumn(7).setMaxWidth(0);

		dcm.getColumn(8).setMinWidth(0);
		dcm.getColumn(8).setMaxWidth(0);

		jScrollPane = new JScrollPane(table);
		// 设置自动有的jScrollPane的样式
		Tools.setJspStyle(jScrollPane);

		tablePanel = new JPanel(new BorderLayout());
		tablePanel.setOpaque(false);
		tablePanel.add(jScrollPane);

		backgroundPanel.add(tablePanel, "Center");
	}

	// 重新刷新数据即可
	public void refreshTablePanel() {
		backgroundPanel.remove(tablePanel);
		initTablePanel(vector);
		// backgroundPanel.validate();
	}

	// 鼠标点击事件
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == tool_add) {
			// System.out.println("add页面执行...");
			new AddGoodsJFrame(this);
		} else if (e.getSource() == tool_modify) {
			// System.out.println("修改页面执行...");
			int row = table.getSelectedRow();
			if (row < 0) {
				JOptionPane.showMessageDialog(null, "请选择商品");
			} else {
				new ModifyGoodsJFrame(this, table, row);
			}

		} else if (e.getSource() == tool_delete) {
			// 得到用户选中了哪一行数据
			int row = table.getSelectedRow();
			if (row < 0) {
				JOptionPane.showMessageDialog(null, "请选择商品");
			} else {
				// 得到用户要删除的数据
				String id = (String) table.getValueAt(row, 0);
				int result = JOptionPane.showConfirmDialog(null, "是否确认删除?", "用户提示", JOptionPane.YES_NO_OPTION);
				if (result == 0) {
					try {
						int tempresult = goodsService.deleteGoods(id);
						if (tempresult > 0) {
							JOptionPane.showMessageDialog(null, "商品删除成功...");
							vector = goodsService.findAllGoods();
							refreshTablePanel();
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}

			}

		} else if (e.getSource() == tool_import) {
			int result = importData();
			if (result > 0) {
				JOptionPane.showMessageDialog(null, "数据已经导出到D盘下");
			}
		}
	}

	private int importData() {
		try {
			// 创建工作目录
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet("data");
			// 创建数据行
			XSSFRow row = sheet.createRow((short) 0);
			XSSFCell cell = null;

			// 创建第一行表头数据
			cell = row.createCell((short) 0);
			cell.setCellValue("ID");
			cell = row.createCell((short) 1);
			cell.setCellValue("NAME");
			cell = row.createCell((short) 2);
			cell.setCellValue("PRICE");
			cell = row.createCell((short) 3);
			cell.setCellValue("ORIGIN");
			cell = row.createCell((short) 4);
			cell.setCellValue("STOCK");

			List<Goods> goodsList = goodsDAO.findAllGoods();
			for (int i = 1; i < goodsList.size(); i++) {
				Goods goods = goodsList.get(i);

				row = sheet.createRow(i);

				cell = row.createCell(0);
				cell.setCellValue(goods.getGoods_Id());
				cell = row.createCell(1);
				cell.setCellValue(goods.getName());
				cell = row.createCell(2);
				cell.setCellValue(goods.getPrice());
				cell = row.createCell(3);
				cell.setCellValue(goods.getOrigin());
				cell = row.createCell(4);
				cell.setCellValue(goods.getStock());
			}

			FileOutputStream FOut = new FileOutputStream("D:/goodsdata.xls");
			workbook.write(FOut);
			FOut.flush();
			FOut.close();
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return 1;

	}

}
