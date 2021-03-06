package com.guigu.salmanager.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import com.guigu.salmanager.model.Goods;
import com.guigu.salmanager.model.StockOrder;
import com.guigu.salmanager.service.CategoryService;
import com.guigu.salmanager.service.GoodsService;
import com.guigu.salmanager.service.StockOrderService;
import com.guigu.salmanager.service.SupplierService;
import com.guigu.salmanager.service.WarehouseService;
import com.guigu.salmanager.service.impl.CategoryServiceImpl;
import com.guigu.salmanager.service.impl.GoodsServiceImpl;
import com.guigu.salmanager.service.impl.StockOrderServiceImpl;
import com.guigu.salmanager.service.impl.SupplierServiceImpl;
import com.guigu.salmanager.service.impl.WarehouseServiceImpl;
import com.guigu.salmanager.utils.Item;
import com.guigu.salmanager.utils.MyFont;

public class AddOutStockJFrame extends JFrame implements MouseListener{
	JPanel backgroundPanel, labelPanel, contentPanel, buttonPanel;
	JLabel label_name,  label_amount,lable_supplier;
	JTextField  amount,supplier;
	JComboBox name;
	JButton button_add;
	private CategoryService categoryService = new CategoryServiceImpl();
	private WarehouseService warehouseService = new WarehouseServiceImpl();
	private GoodsService goodsService =new GoodsServiceImpl();
	private StockOrderService sOrderService=new StockOrderServiceImpl();
	private SupplierService supplierService=new SupplierServiceImpl();
	int result;
	// 得到屏幕的宽度和高度
	final static int width = Toolkit.getDefaultToolkit().getScreenSize().width;
	final static int height = Toolkit.getDefaultToolkit().getScreenSize().height;


	// 父面板
	OutStockJPanel parentPanel;

	public AddOutStockJFrame(OutStockJPanel parentPanel) {
		this.parentPanel = parentPanel;

		initBackgroundPanel();

		this.add(backgroundPanel);
		this.setTitle("添加出库信息");
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
		button_add = new JButton("保存");
		button_add.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.lightBlue));
		button_add.setForeground(Color.white);
		button_add.setFont(MyFont.Static);
		button_add.addMouseListener(this);
		buttonPanel.add(button_add);
		
	}


	private void initLablePanel() {
		labelPanel = new JPanel();
		JLabel title = new JLabel("入库单信息");
		title.setFont(MyFont.Static);
		labelPanel.add(title);
		
	}


	private void initContentPanel() {
		contentPanel = new JPanel(new GridLayout(6, 2));

		label_name = new JLabel("商品名称", JLabel.CENTER);
		
		label_amount= new JLabel("商品数量", JLabel.CENTER);
		
		// 初始化文本框
		name = new JComboBox();
		amount = new JTextField();

		List<Goods> list_name = null;
		try {
			list_name = goodsService.findAll();
			if (list_name != null) {

				for (int i = 0; i < list_name.size(); i++) {
					String sid = list_name.get(i).getGoods_Id();
					String sname = list_name.get(i).getName();
					name.addItem(new Item(sid, sname));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 添加组件
		contentPanel.add(label_name);
		contentPanel.add(name);
		contentPanel.add(label_amount);
		contentPanel.add(amount);

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == button_add) {
			String amount_string=amount.getText().trim();
			if(amount_string.isEmpty()) {
				JOptionPane.showMessageDialog(null, "请输入商品数量");
			}else {
				double amount_int=Double.parseDouble(amount_string);
				String id_string=((Item)name.getSelectedItem()).getKey();
				//得到选择的仓库id和分类id
				String id=UUID.randomUUID().toString().replace("-", "");
				Date date1 =new Date();
				SimpleDateFormat sFormat=new SimpleDateFormat("yyyyMMddhhmmss");
				String bill_no=sFormat.format(date1);
				String Category_Id=null;
				String Warehouse_Id=null;
				String supplier_id=null;
				try {
					Goods good=goodsService.findById(id_string);
					Category_Id=goodsService.find(id_string).getCategory_Id();
					Warehouse_Id=goodsService.find(id_string).getWarehouse_Id();
					StockOrder s=sOrderService.find(id_string);
					if(s!=null&&good!=null) {
						StockOrder sOrder=new StockOrder(id, bill_no,IndexJFrame.user.getUser_id(),supplier_id,
								Warehouse_Id,Category_Id, amount_int,id_string, 1, 0);
						result=sOrderService.saveOrder(sOrder);
						goodsService.updateGoods(goodsService.find(id_string), amount_int,0);
						if(result>0) {
							JOptionPane.showMessageDialog(null, "添加成功");
							this.setVisible(false);
						}
					}else {
						JOptionPane.showMessageDialog(null, "请先入库商品");
						this.setVisible(false);
					}
					parentPanel.vector=sOrderService.findAllOrder(1);
					parentPanel.refreshTablePanel();

				} catch (Exception e2) {
					e2.printStackTrace();			
				}
			}
		}
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
