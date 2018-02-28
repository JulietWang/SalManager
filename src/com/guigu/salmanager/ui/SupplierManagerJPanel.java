package com.guigu.salmanager.ui;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Vector;

import javax.swing.Icon;
import javax.swing.ImageIcon;
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

import com.guigu.salmanager.dao.SupplierDAO;
import com.guigu.salmanager.dao.impl.SupplierDAOImpl;
import com.guigu.salmanager.model.Supplier;
import com.guigu.salmanager.service.SupplierService;
import com.guigu.salmanager.service.impl.SupplierServiceImpl;
import com.guigu.salmanager.utils.BaseTableModule;
import com.guigu.salmanager.utils.Tools;

public class SupplierManagerJPanel extends JPanel implements MouseListener {

	JPanel backgroundPanel, topPanel, toolPanel, tablePanel;
	JTable table;
	JScrollPane jScrollPane;
	JLabel tool_add, tool_modify, tool_delete, tool_import;
	BaseTableModule baseTableModule;
	private SupplierService supplierService = new SupplierServiceImpl();
	private SupplierDAO supplierDAO = new SupplierDAOImpl();

	public SupplierManagerJPanel() {
		backgroundPanel = new JPanel(new BorderLayout());
		initTopPanel();
		initTablePanel();
	}

	private void initTopPanel() {
		topPanel = new JPanel(new BorderLayout());
		initToolPanel();
		backgroundPanel.add(topPanel, "North");
	}

	private void initToolPanel() {
		toolPanel = new JPanel();
		// 菜单图标
		Icon icon_add = new ImageIcon("image/add.png");
		tool_add = new JLabel(icon_add);
		tool_add.setToolTipText("新建供应商");
		tool_add.addMouseListener(this);

		Icon icon_modify = new ImageIcon("image/modify.png");
		tool_modify = new JLabel(icon_modify);
		tool_modify.setToolTipText("修改供应商");
		tool_modify.addMouseListener(this);

		Icon icon_delete = new ImageIcon("image/delete.png");
		tool_delete = new JLabel(icon_delete);
		tool_delete.setToolTipText("删除供应商");
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

	private void initTablePanel() {

		String[] params = { "供应商编号", "供应商简称", "供应商全称", "负责人", "移动电话", "公司地址", "工厂地址", "最后进货日期" };

		Vector<Vector> vector = new Vector<>();
		try {
			// 把所有的供应商信息 封装到Vector中
			vector = supplierService.findAllSupplier();
		} catch (Exception e) {
			e.printStackTrace();
		}

		baseTableModule = new BaseTableModule(params, vector);
		table = new JTable(baseTableModule);

		// 设置自定义的表格样式
		Tools.setTableStyle(table);
		// 通过列的模型设置某些列不可见
		DefaultTableColumnModel dcm = (DefaultTableColumnModel) table.getColumnModel();
		// 将第一列 将宽度和高度设置为0
		dcm.getColumn(0).setMinWidth(0);
		dcm.getColumn(0).setMaxWidth(0);

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
		initTablePanel();
		// backgroundPanel.validate();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == tool_add) {
			// System.out.println("add页面执行...");
			new AddSupplierJFrame(this);
		} else if (e.getSource() == tool_modify) {
			// System.out.println("修改页面执行...");
			int row = table.getSelectedRow();
			if (row < 0) {
				JOptionPane.showMessageDialog(null, "请选择供应商");
			} else {
				new ModifySupplierJFrame(this, table, row);
			}

		} else if (e.getSource() == tool_delete) {
			// 得到用户选中了哪一行数据
			int row = table.getSelectedRow();
			if (row < 0) {
				JOptionPane.showMessageDialog(null, "请选择供应商");
			} else {
				// 得到用户要删除的数据
				String id = (String) table.getValueAt(row, 0);
				int result = JOptionPane.showConfirmDialog(null, "是否确认删除?", "用户提示", JOptionPane.YES_NO_OPTION);
				if (result == 0) {
					try {
						int tempresult = supplierService.deleteSupplier(id);
						if (tempresult > 0) {
							JOptionPane.showMessageDialog(null, "供应商删除成功...");
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
			cell.setCellValue("supplierId");
			cell = row.createCell((short) 1);
			cell.setCellValue("supplierSimpleName");
			cell = row.createCell((short) 2);
			cell.setCellValue("supplierName");
			cell = row.createCell((short) 3);
			cell.setCellValue("owner");
			cell = row.createCell((short) 4);
			cell.setCellValue("mobilephone ");
			cell = row.createCell((short) 5);
			cell.setCellValue("companyAddress");
			cell = row.createCell((short) 6);
			cell.setCellValue("factoryAddress");
			cell = row.createCell((short) 7);
			cell.setCellValue("lastPurchaseDate");

			List<Supplier> supplierList = supplierDAO.findAllSupplier();
			for (int i = 1; i < supplierList.size(); i++) {
				Supplier supplier = supplierList.get(i);

				row = sheet.createRow(i);

				cell = row.createCell(0);
				cell.setCellValue(supplier.getSupplier_Id());
				cell = row.createCell(1);
				cell.setCellValue(supplier.getSupplierSimpleName());
				cell = row.createCell(2);
				cell.setCellValue(supplier.getSupplierName());
				cell = row.createCell(3);
				cell.setCellValue(supplier.getOwner());
				cell = row.createCell(4);
				cell.setCellValue(supplier.getMobiletelephone());
				cell = row.createCell(5);
				cell.setCellValue(supplier.getCompanyAddress());
				cell = row.createCell(6);
				cell.setCellValue(supplier.getFactoryAddress());
				cell = row.createCell(7);
				cell.setCellValue(supplier.getLastPurchaseDate());
			}

			FileOutputStream FOut = new FileOutputStream("D:/supplierdata.xls");
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
