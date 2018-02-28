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

import com.guigu.salmanager.dao.SalesmanDAO;
import com.guigu.salmanager.dao.impl.SalesmanDAOImpl;
import com.guigu.salmanager.model.Salesman;
import com.guigu.salmanager.service.SalesmanService;
import com.guigu.salmanager.service.impl.SalesmanServiceImpl;
import com.guigu.salmanager.utils.BaseTableModule;
import com.guigu.salmanager.utils.Tools;

public class SalesmanManagerJPanel extends JPanel implements MouseListener {
	JPanel backgroundPanel, topPanel, toolPanel, tablePanel;
	JTable table;
	JScrollPane jScrollPane;
	JLabel tool_add, tool_modify, tool_delete, tool_import;
	BaseTableModule baseTableModule;
	private SalesmanService salesmanService = new SalesmanServiceImpl();
	private SalesmanDAO salesmanDAO = new SalesmanDAOImpl();

	public SalesmanManagerJPanel() {
		backgroundPanel = new JPanel(new BorderLayout());
		initTopPanel();
		initTablePanel();
		
	}

	private void initTablePanel() {
		String[] params = { "����Աid", "����Ա����", "�ƶ��绰","�����ַ", "�����ʼ�" };
		Vector<Vector> vector = new Vector<>();
		try {
			// �����е�����Ա��Ϣ ��װ��Vector��
			vector = salesmanService.findAllSalesman();
		} catch (Exception e) {
			e.printStackTrace();
		}

		baseTableModule = new BaseTableModule(params, vector);
		table = new JTable(baseTableModule);

		// �����Զ���ı����ʽ
		Tools.setTableStyle(table);
		// ͨ���е�ģ������ĳЩ�в��ɼ�
		DefaultTableColumnModel dcm = (DefaultTableColumnModel) table.getColumnModel();
		// ����һ�� ����Ⱥ͸߶�����Ϊ0
		dcm.getColumn(0).setMinWidth(0);
		dcm.getColumn(0).setMaxWidth(0);

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
		initTablePanel();
		// backgroundPanel.validate();
	}

	private void initTopPanel() {
		topPanel = new JPanel(new BorderLayout());
		initToolPanel();
		backgroundPanel.add(topPanel, "North");
	}

	private void initToolPanel() {
		toolPanel = new JPanel();
		// �˵�ͼ��
		Icon icon_add = new ImageIcon("image/add.png");
		tool_add = new JLabel(icon_add);
		tool_add.setToolTipText("�½�����Ա");
		tool_add.addMouseListener(this);

		Icon icon_modify = new ImageIcon("image/modify.png");
		tool_modify = new JLabel(icon_modify);
		tool_modify.setToolTipText("�޸�����Ա");
		tool_modify.addMouseListener(this);

		Icon icon_delete = new ImageIcon("image/delete.png");
		tool_delete = new JLabel(icon_delete);
		tool_delete.setToolTipText("ɾ������Ա");
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

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == tool_add) {
			// System.out.println("addҳ��ִ��...");
			new AddSalesmanJFrame(this);
		} else if (e.getSource() == tool_modify) {
			// System.out.println("�޸�ҳ��ִ��...");
			int row = table.getSelectedRow();
			if (row < 0) {
				JOptionPane.showMessageDialog(null, "��ѡ������Ա");
			} else {
				new ModifySalesmanJFrame(this, table, row);
			}

		} else if (e.getSource() == tool_delete) {
			// �õ��û�ѡ������һ������
			int row = table.getSelectedRow();
			if (row < 0) {
				JOptionPane.showMessageDialog(null, "��ѡ������Ա");
			} else {
				// �õ��û�Ҫɾ��������
				String id = (String) table.getValueAt(row, 0);
				int result = JOptionPane.showConfirmDialog(null, "�Ƿ�ȷ��ɾ��?", "�û���ʾ", JOptionPane.YES_NO_OPTION);
				if (result == 0) {
					try {
						int tempresult = salesmanService.deleteSalesman(id);
						if (tempresult > 0) {
							JOptionPane.showMessageDialog(null, "����Աɾ���ɹ�...");
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
			// ��������Ŀ¼
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet("data");
			// ����������
			XSSFRow row = sheet.createRow((short) 0);
			XSSFCell cell = null;

			// ������һ�б�ͷ����
			cell = row.createCell((short) 0);
			cell.setCellValue("salesmanId");
			cell = row.createCell((short) 1);
			cell.setCellValue("salesmanname");
			cell = row.createCell((short) 2);
			cell.setCellValue("mobiletelephone ");
			cell = row.createCell((short) 3);
			cell.setCellValue("contactaddress ");
			cell = row.createCell((short) 4);
			cell.setCellValue("email  ");

			List<Salesman> salesmanList = salesmanDAO.findAllSalesman();
			for (int i = 1; i < salesmanList.size(); i++) {
				Salesman salesman = salesmanList.get(i);

				row = sheet.createRow(i);

				cell = row.createCell(0);
				cell.setCellValue(salesman.getSalesman_Id());
				cell = row.createCell(1);
				cell.setCellValue(salesman.getSalesmanName());
				cell = row.createCell(2);
				cell.setCellValue(salesman.getMobiletelephone());
				cell = row.createCell(3);
				cell.setCellValue(salesman.getContactAddress());
				cell = row.createCell(4);
				cell.setCellValue(salesman.getEmail());

			}

			FileOutputStream FOut = new FileOutputStream("D:/salesmandata.xls");
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
