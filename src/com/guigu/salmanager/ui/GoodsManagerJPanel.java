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
			// �����е���Ʒ��Ϣ ��װ��Vector��
			vector = goodsService.findAllGoods();
		} catch (Exception e) {
			e.printStackTrace();
		}
		initTopPanel();
		initTablePanel(vector);

	}

	// ��ʼ���������
	private void initTopPanel() {
		topPanel = new JPanel(new BorderLayout());
		initToolPanel();
		initSelectPanel();
		backgroundPanel.add(topPanel, "North");
	}

	private void initSelectPanel() {
		selectPanel = new JPanel();
		label_category = new JLabel("��Ʒ����");
		label_warehouse = new JLabel("�����ֿ�");
		// ��Ʒ����������� �����ݿ���ص�����
		category = new JComboBox();
		category.addItem(new Item("all", "ȫ��"));
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

		// �ֿ������б�
		warehouse = new JComboBox();
		warehouse.addItem(new Item("all", "ȫ��"));
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

	// ��ʼ���������
	private void initToolPanel() {
		toolPanel = new JPanel();
		// �˵�ͼ��
		Icon icon_add = new ImageIcon("image/add.png");
		tool_add = new JLabel(icon_add);
		tool_add.setToolTipText("�½���Ʒ");
		tool_add.addMouseListener(this);

		Icon icon_modify = new ImageIcon("image/modify.png");
		tool_modify = new JLabel(icon_modify);
		tool_modify.setToolTipText("�޸���Ʒ");
		tool_modify.addMouseListener(this);

		Icon icon_delete = new ImageIcon("image/delete.png");
		tool_delete = new JLabel(icon_delete);
		tool_delete.setToolTipText("ɾ����Ʒ");
		tool_delete.addMouseListener(this);

		Icon icon_import = new ImageIcon("image/import.png");
		tool_import = new JLabel(icon_import);
		tool_import.setToolTipText("���ݵ���");
		tool_import.addMouseListener(this);

		toolPanel.add(tool_add);
		toolPanel.add(tool_modify);
		toolPanel.add(tool_delete);
		toolPanel.add(tool_import);

		topPanel.add(toolPanel, "West");
	}

	private void initTablePanel(Vector vector) {
		String[] params = { "��Ʒid", "����", "�۸�", "����", "��������", "�����ֿ�", "���", "�ֿ�id", "����id" };

		baseTableModule = new BaseTableModule(params, vector);
		table = new JTable(baseTableModule);

		// �����Զ���ı����ʽ
		Tools.setTableStyle(table);
		// ͨ���е�ģ������ĳЩ�в��ɼ�
		DefaultTableColumnModel dcm = (DefaultTableColumnModel) table.getColumnModel();
		// ����һ�� �ڰ��к͵ھ��� ����Ⱥ͸߶�����Ϊ0
		dcm.getColumn(0).setMinWidth(0);
		dcm.getColumn(0).setMaxWidth(0);

		dcm.getColumn(7).setMinWidth(0);
		dcm.getColumn(7).setMaxWidth(0);

		dcm.getColumn(8).setMinWidth(0);
		dcm.getColumn(8).setMaxWidth(0);

		jScrollPane = new JScrollPane(table);
		// �����Զ��е�jScrollPane����ʽ
		Tools.setJspStyle(jScrollPane);

		tablePanel = new JPanel(new BorderLayout());
		tablePanel.setOpaque(false);
		tablePanel.add(jScrollPane);

		backgroundPanel.add(tablePanel, "Center");
	}

	// ����ˢ�����ݼ���
	public void refreshTablePanel() {
		backgroundPanel.remove(tablePanel);
		initTablePanel(vector);
		// backgroundPanel.validate();
	}

	// ������¼�
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == tool_add) {
			// System.out.println("addҳ��ִ��...");
			new AddGoodsJFrame(this);
		} else if (e.getSource() == tool_modify) {
			// System.out.println("�޸�ҳ��ִ��...");
			int row = table.getSelectedRow();
			if (row < 0) {
				JOptionPane.showMessageDialog(null, "��ѡ����Ʒ");
			} else {
				new ModifyGoodsJFrame(this, table, row);
			}

		} else if (e.getSource() == tool_delete) {
			// �õ��û�ѡ������һ������
			int row = table.getSelectedRow();
			if (row < 0) {
				JOptionPane.showMessageDialog(null, "��ѡ����Ʒ");
			} else {
				// �õ��û�Ҫɾ��������
				String id = (String) table.getValueAt(row, 0);
				int result = JOptionPane.showConfirmDialog(null, "�Ƿ�ȷ��ɾ��?", "�û���ʾ", JOptionPane.YES_NO_OPTION);
				if (result == 0) {
					try {
						int tempresult = goodsService.deleteGoods(id);
						if (tempresult > 0) {
							JOptionPane.showMessageDialog(null, "��Ʒɾ���ɹ�...");
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
				JOptionPane.showMessageDialog(null, "�����Ѿ�������D����");
			}
		}
	}

	private int importData() {
		try {
			// ��������Ŀ¼
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet("data");
			// ����������
			XSSFRow row = sheet.createRow((short) 0);
			XSSFCell cell = null;

			// ������һ�б�ͷ����
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
