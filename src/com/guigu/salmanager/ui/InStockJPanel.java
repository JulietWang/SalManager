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
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableColumnModel;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.guigu.salmanager.dao.StockOrderDAO;
import com.guigu.salmanager.dao.impl.StockOrderDAOImp;
import com.guigu.salmanager.model.Category;
import com.guigu.salmanager.model.StockOrder;
import com.guigu.salmanager.model.Warehouse;
import com.guigu.salmanager.service.CategoryService;
import com.guigu.salmanager.service.GoodsService;
import com.guigu.salmanager.service.StockOrderService;
import com.guigu.salmanager.service.UsersService;
import com.guigu.salmanager.service.WarehouseService;
import com.guigu.salmanager.service.impl.CategoryServiceImpl;
import com.guigu.salmanager.service.impl.GoodsServiceImpl;
import com.guigu.salmanager.service.impl.StockOrderServiceImpl;
import com.guigu.salmanager.service.impl.UsersServiceImpl;
import com.guigu.salmanager.service.impl.WarehouseServiceImpl;
import com.guigu.salmanager.utils.BaseTableModule;
import com.guigu.salmanager.utils.Item;
import com.guigu.salmanager.utils.Tools;

public class InStockJPanel extends MouseAdapter {
	JPanel backgroundPanel, topPanel, toolPanel, tablePanel,choisePanel;
	JTable table;
	JScrollPane jScrollPane;
	JTextField name;
	JLabel lable_name,label_category, label_warehouse, tool_add, tool_delete,tool_import;
	JComboBox category,warehouse;
	BaseTableModule baseTableModule;
	private GoodsService goodsService =new GoodsServiceImpl();
	private StockOrderService stockOrderService=new StockOrderServiceImpl();
	private StockOrderDAO sOrder=new StockOrderDAOImp();
	private UsersService userService=new UsersServiceImpl();
	private CategoryService categoryService = new CategoryServiceImpl();
	private WarehouseService warehouseService = new WarehouseServiceImpl();
	 Vector<Vector> vector =new Vector<>();

	public InStockJPanel() {
		backgroundPanel = new JPanel(new BorderLayout());
		initTopPanel();
		try {
			vector=stockOrderService.findAllOrder(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		initTablePanel(vector);

	}
	//初始化顶部面板
	private void initTopPanel() {
		topPanel =new JPanel(new BorderLayout());
		initToolPanel();
		initChoisePanel();
		backgroundPanel.add(topPanel,"North");
	}
	private void initChoisePanel() {
		choisePanel=new JPanel();
		lable_name=new JLabel("商品名称");
		name=new JTextField(10);
		name.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				String nameJText=name.getText().trim();
				try {
					//把所有的商品信息 封装到Vector中
					vector=stockOrderService.findName(nameJText,0);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				refreshTablePanel();
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {	
				
				String nameJText=name.getText().trim();
				try {
					//把所有的商品信息 封装到Vector中
					vector=stockOrderService.findName(nameJText,0);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				refreshTablePanel();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				System.out.println("hello3");
				String nameJText=name.getText().trim();
				try {
					//把所有的商品信息 封装到Vector中
					vector=stockOrderService.findName(nameJText,0);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				refreshTablePanel();
				
			}
		});
		
		label_category=new JLabel("商品分类");
		category=new JComboBox();
		label_warehouse=new JLabel("所属仓库");
		warehouse = new JComboBox();
		
		List<Category> list_category = null;
		category.addItem(new Item("0","全部"));
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
		
		List<Warehouse> list_warehouse = null;
		warehouse.addItem(new Item("0","全部"));
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
		
		
		category.setSelectedIndex(0);
		warehouse.setSelectedIndex(0);
		choisePanel.add(lable_name);
		choisePanel.add(name);
		choisePanel.add(label_category);
		choisePanel.add(category);
		choisePanel.add(label_warehouse);
		choisePanel.add(warehouse);
		
		category.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				name.setText("");
				String warehouse_value=((Item)warehouse.getSelectedItem()).getValue();
				String category_value=((Item)category.getSelectedItem()).getValue();
				try {
					vector=stockOrderService.find(warehouse_value,category_value,0);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				refreshTablePanel();
			}
		});
		warehouse.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				name.setText("");
				String category_value=((Item)category.getSelectedItem()).getValue();
				String warehouse_value=((Item)warehouse.getSelectedItem()).getValue();
				try {
					vector=stockOrderService.find(warehouse_value,category_value,0);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				refreshTablePanel();
			}
		});
		name.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				category.setSelectedIndex(0);
				warehouse.setSelectedIndex(0);
				refreshTablePanel();
			}
			
		});
		
		
		topPanel.add(choisePanel,"East");
	}

	//初始化工具面板
	private void initToolPanel() {
		toolPanel =new JPanel();
		//菜单图标
		Icon icon_add =new ImageIcon("image/add.png");
		tool_add=new JLabel(icon_add);
		tool_add.setToolTipText("新建入库单");
		tool_add.addMouseListener(this);
		
		Icon icon_delete =new ImageIcon("image/delete.png");
		tool_delete=new JLabel(icon_delete);
		tool_delete.setToolTipText("删除入库单");
		tool_delete.addMouseListener(this);
		
		Icon icon_import =new ImageIcon("image/import.png");
		tool_import=new JLabel(icon_import);
		tool_import.setToolTipText("数据导出");
		tool_import.addMouseListener(this);
		
		toolPanel.add(tool_add);
		toolPanel.add(tool_delete);
		toolPanel.add(tool_import);
		
		
		
		topPanel.add(toolPanel,"West");
	}

	private void initTablePanel(Vector vector) {
		String [] params = {"id","订单号","经手人","供应商","所属仓库","所属分类","数量","商品名称"};
		
		baseTableModule =new BaseTableModule(params, vector);
		table =new JTable(baseTableModule);
		
		//设置自定义的表格样式
		Tools.setTableStyle(table);
		//通过列的模型设置某些列不可见
		DefaultTableColumnModel dcm =(DefaultTableColumnModel) table.getColumnModel();
		dcm.getColumn(0).setMinWidth(0);
		dcm.getColumn(0).setMaxWidth(0);
		
		jScrollPane =new JScrollPane(table);
		//设置自动有的jScrollPane的样式
		Tools.setJspStyle(jScrollPane);
		
		tablePanel =new JPanel(new BorderLayout());
		tablePanel.setOpaque(false);
		tablePanel.add(jScrollPane);
		
		backgroundPanel.add(tablePanel,"Center");
	}
	//重新刷新数据即可
	public void refreshTablePanel() {
		backgroundPanel.remove(tablePanel);
		initTablePanel(vector);
	}
	
	//鼠标点击事件
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource()==tool_add) {
			new AddInStockJFrame(this);
			refreshTablePanel();
		}else if(e.getSource()==tool_delete) {
			//得到用户选中了哪一行数据
			int row =table.getSelectedRow();
			if(row<0) {
				JOptionPane.showMessageDialog(null, "请选择订单");
			}else {
				//得到用户要删除的数据
				String id =(String) table.getValueAt(row, 0);
				int result =JOptionPane.showConfirmDialog(null, "是否确认删除?","用户提示",JOptionPane.YES_NO_OPTION);
				if(result==0) {
					try {
						int tempresult=stockOrderService.deleteOrder(id);
						
						if(tempresult>0) {
							JOptionPane.showMessageDialog(null, "订单删除成功...");
							vector=stockOrderService.findAllOrder(0);
							refreshTablePanel();
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				
			}
			
		}else if (e.getSource()==tool_import) {
			int result=ImportData();
			if (result > 0) {
				JOptionPane.showMessageDialog(null, "数据已经导出到D盘下");
			}
		}else if (e.getSource()==name) {
			category.setSelectedIndex(0);
			warehouse.setSelectedIndex(0);
		}
		
	}
	private int ImportData() {
		
		try {
			//创建工作目录
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet("data");
			//创建数据行
			XSSFRow row = sheet.createRow((short) 0);
			XSSFCell cell = null;
			
			//创建第一行表头数据
			cell = row.createCell((short) 0);
			cell.setCellValue("订单号");
			cell = row.createCell((short) 1);
			cell.setCellValue("经手人");
			cell = row.createCell((short) 2);
			cell.setCellValue("供应商");
			cell = row.createCell((short) 3);
			cell.setCellValue("所属仓库");
			cell = row.createCell((short) 4);
			cell.setCellValue("所属分类");
			cell = row.createCell((short) 5);
			cell.setCellValue("数量");
			cell = row.createCell((short) 6);
			cell.setCellValue("商品名称");
			
			for(int i = 0; i <baseTableModule.getRowCount(); i++) {
				row = sheet.createRow(i+1);
				cell=row.createCell(0);
				cell.setCellValue((String)baseTableModule.getValueAt(i, 1));
				cell=row.createCell(1);
				cell.setCellValue((String)baseTableModule.getValueAt(i, 2));
				cell=row.createCell(2);
				cell.setCellValue((String)baseTableModule.getValueAt(i, 3));
				cell=row.createCell(3);
				cell.setCellValue((String)baseTableModule.getValueAt(i, 4));
				cell=row.createCell(4);
				cell.setCellValue((String)baseTableModule.getValueAt(i, 5));
				cell=row.createCell(5);
				cell.setCellValue((double)baseTableModule.getValueAt(i, 6));
				cell=row.createCell(6);
				cell.setCellValue((String)baseTableModule.getValueAt(i, 7));
			}
			FileOutputStream FOut = new FileOutputStream("D:/instockdata.xlsx");
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
