package com.guigu.salmanager.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

import com.guigu.salmanager.model.Users;
import com.guigu.salmanager.utils.ImagePanel;
import com.guigu.salmanager.utils.MyFont;

public class IndexJFrame extends JFrame implements MouseListener, ActionListener {

	static Users user;

	// 获得屏幕大小
	final static int width = Toolkit.getDefaultToolkit().getScreenSize().width;
	final static int heigth = Toolkit.getDefaultToolkit().getScreenSize().height;

	// 定义全局组件
	JPanel backgroundPanel, topPanel, topMenu, topPrompt, centerPanel;

	JTabbedPane jTabbedPanel;

	JLabel home, baseData, purchase_sale_stock, userManager;

	public IndexJFrame(Users user) {
		this.user = user;

		// 设置tab面板的缩进
		 UIManager.put("TabbedPane.tabAreaInsets", new javax.swing.plaf.InsetsUIResource(0, 0, 0, 0));
		try {
			Image image = ImageIO.read(new File("image/logo.png"));
			this.setIconImage(image);
		} catch (IOException e) {
			e.printStackTrace();
		}

		initBackgroundPanel();

		this.setTitle("销售管理系统");
		this.setSize((int) (width * 0.8), (int) (heigth * 0.8));
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	// 初始化背景
	private void initBackgroundPanel() {
		backgroundPanel = new JPanel(new BorderLayout());

		// 初始化中间的部分
		initCenterPanel();
		// 初始化顶部的部分
		initTop();

		backgroundPanel.add(centerPanel, "Center");
		backgroundPanel.add(topPanel, "North");

		this.add(backgroundPanel);
	}

	private void initCenterPanel() {
		centerPanel = new JPanel(new BorderLayout());
		// 这个方法是用来创建背景图
		createHome();
		// 设置控件是否是透明的
		centerPanel.setOpaque(false);

	}

	// 创建首页面板
	private void createHome() {
		// 移除所有的控件
		centerPanel.removeAll();
		try {
			Image bgImage = ImageIO.read(new File("image/indexbackground.png"));
			ImagePanel centerBackGround = new ImagePanel(bgImage);
			centerPanel.add(centerBackGround, "Center");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// 初始化顶部面板
	private void initTop() {

		initTopMenu();
		initTopPrompt();

		topPanel = new JPanel(new BorderLayout());
		topPanel.setPreferredSize(new Dimension(width, 40));

		topPanel.add(topMenu, "West");
		topPanel.add(topPrompt, "East");

	}

	// 初始化顶部菜单
	private void initTopMenu() {
		topMenu = new JPanel();
		topMenu.setPreferredSize(new Dimension(500, 40));
		topMenu.setOpaque(false);

		String[] namesStrings = { "首页", "基础数据", "进销存管理", "用户管理" };
		home = createMenuLabel(home, namesStrings[0], "home", topMenu);
		home.setName("home");

		baseData = createMenuLabel(baseData, namesStrings[1], "baseData", topMenu);
		baseData.setName("baseData");

		purchase_sale_stock = createMenuLabel(purchase_sale_stock, namesStrings[2], "purchase_sale_stock", topMenu);
		purchase_sale_stock.setName("purchase_sale_stock");

		userManager = createMenuLabel(userManager, namesStrings[3], "userManager", topMenu);
		userManager.setName("userManager");

	}

	// 创建顶部菜单Lable
	public JLabel createMenuLabel(JLabel jlb, String text, String name, JPanel who) {
		JLabel line = new JLabel(" |  ");
		Icon icon = new ImageIcon("image/" + name + ".png");
		jlb = new JLabel(icon);
		jlb.setText(text);
		jlb.addMouseListener(this);
		jlb.setFont(MyFont.Static);
		who.add(jlb);
		if (!"userManager".equals(name)) {
			who.add(line);
		}
		return jlb;
	}

	// 初始化顶部欢迎面板
	private void initTopPrompt() {
		Icon icon = new ImageIcon("image/male.png");
		JLabel lable = new JLabel(icon);
		if (user != null) {
			lable.setText("欢迎您:" + user.getName());
		} else {
			lable.setText("欢迎您:");
		}
		lable.setFont(MyFont.Static);

		topPrompt = new JPanel();
		topPrompt.setPreferredSize(new Dimension(180, 40));
		topPrompt.setOpaque(false);
		topPrompt.add(lable);
	}

	//创建基础数据面板
	private void createBaseTab() {
		centerPanel.removeAll();
		//设置tab标题的位置
		jTabbedPanel =new JTabbedPane(JTabbedPane.TOP);
		//设置tab布局
		jTabbedPanel.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
		jTabbedPanel.setFont(MyFont.Static);
		
		jTabbedPanel.addTab("商品管理", new GoodsManagerJPanel().backgroundPanel);
		jTabbedPanel.addTab("客户管理", new CustomerManagerJPanel().backgroundPanel);
		jTabbedPanel.addTab("供应商管理", new SupplierManagerJPanel().backgroundPanel);
		jTabbedPanel.addTab("销售员管理", new SalesmanManagerJPanel().backgroundPanel);

		centerPanel.add(jTabbedPanel,"Center");
	}

	//创建进销存面板
	
			private void createSOMTab(){
				centerPanel.removeAll();
				//设置tab标题的位置
				jTabbedPanel =new JTabbedPane(JTabbedPane.TOP);
				//设置tab布局
				//jTabbedPanel.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
				jTabbedPanel.setFont(MyFont.Static);
				jTabbedPanel.addTab("销售单", new SaleOrderJPanel().backgroundPanel);
				jTabbedPanel.addTab("入库单", new InStockJPanel().backgroundPanel);
				jTabbedPanel.addTab("出库单", new OutStockJPanel().backgroundPanel);
				
				jTabbedPanel.addTab("仓库管理",new WarehouseManagerJpanel().backgroundPanel);
					
				centerPanel.add(jTabbedPanel,"Center");
				
			}

			//用户管理
			private void createUserManager(IndexJFrame indexJFrame) {
				centerPanel.removeAll();
				//设置tab标题的位置
				jTabbedPanel =new JTabbedPane(JTabbedPane.TOP);
				//设置tab布局
				jTabbedPanel.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
				jTabbedPanel.setFont(MyFont.Static);
			
				jTabbedPanel.addTab("用户管理",new UserManagerJPanel(user,user.getName(), indexJFrame).backgroundPanel);
				centerPanel.add(jTabbedPanel,"Center");
			}
			
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
		if (e.getSource() == home) {
			createHome();
			home.setText("首页");
			baseData.setText("<html>" + "基础数据" + "</html>");
			purchase_sale_stock.setText("<html>" + "进销存管理" + "</html>");
			userManager.setText("<html>" + "用户管理" + "</html>");
		} else if (e.getSource() == baseData) {
			createBaseTab();
			baseData.setText("基础数据");
			home.setText("<html>" + "首页" + "</html>");
			purchase_sale_stock.setText("<html>" + "进销存管理" + "</html>");
			userManager.setText("<html>" + "用户管理" + "</html>");
		}else if(e.getSource()==purchase_sale_stock) {
			createSOMTab();
			purchase_sale_stock.setText( "进销存管理" );
			baseData.setText("<html>" + "基础数据" + "</html>");
			home.setText("<html>" + "首页" + "</html>");
			userManager.setText("<html>" + "用户管理" + "</html>");
		}else if(e.getSource()==userManager) {
			createUserManager(this);
			userManager.setText("用户管理");
			home.setText("<html>" + "首页" + "</html>");
			baseData.setText("<html>" + "基础数据" + "</html>");
			purchase_sale_stock.setText("<html>" + "进销存管理" + "</html>");
		}else {
			System.out.println("hello guigu");
		}
		
		

	}

	@Override
	public void actionPerformed(ActionEvent e) {

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
