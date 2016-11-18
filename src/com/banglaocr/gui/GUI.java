package com.banglaocr.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.banglaocr.dto.TranningBean;
import com.banglaocr.testing.Testing;
import com.banglaocr.traning.ImageAdder;

public class GUI extends JFrame {

	private JPanel contentPane;
	private JList list;
	private DefaultListModel model;
	private JTable table;
	public Testing testing;
	private static final String[] COL_NAMES = { "output", "input", "dist" };
	private DefaultTableModel tableModel;
	public ArrayList<Object[]> rowDataList;
	public TranningBean tBean = new TranningBean();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					ImageAdder.clearAllTempImages();

					Testing.clearAllTempImages();
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI() {
		super("Bangla OCR");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 629, 457);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		JMenuItem mntmAddTraningImage = new JMenuItem("Add Traning Image");
		mntmAddTraningImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String absolutepath = showSaveFileDialog("Choose");
					TranningBean dum = new TranningBean();
					dum = ImageAdder.addTrainingImage(absolutepath);
					tBean.getWithHole().addAll(dum.getWithHole());
					tBean.getWithOutHole().addAll(dum.getWithOutHole());
					model.addElement(absolutepath);
//					panel.updateUI();
				} catch (Exception e1) {
					// e1.printStackTrace();
				}
			}
		});
		mnFile.add(mntmAddTraningImage);

		JMenuItem mntmRecognize = new JMenuItem("Recognize");
		mntmRecognize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Testing testing = new Testing();
					testing.addTestingImage(showSaveFileDialog("Open"), tBean);
					rowDataList = testing.getRowData();
					for (Object[] rowData : rowDataList) {
						tableModel.addRow(rowData);
					}
					table.setRowHeight(((ImageIcon) tableModel.getValueAt(0, 1))
							.getIconHeight());

				} catch (Exception e1) {
					// e1.printStackTrace();
				}
				pack();
			}
		});
		mnFile.add(mntmRecognize);
		mnFile.add(mntmExit);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		model = new DefaultListModel();
		model.addElement("Training Images");
		tableModel = new DefaultTableModel(COL_NAMES, 0) {
			@Override
			public Class<?> getColumnClass(int column) {
				if (getRowCount() > 0) {
					return getValueAt(0, column).getClass();
				}

				return super.getColumnClass(column);
			}
		};
		list = new JList(model);
		contentPane.add(list, BorderLayout.NORTH);
		
				table = new JTable();
				table.setFont(new Font("Vrinda", Font.PLAIN, 30));
				table.setModel(tableModel);
				table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
				JScrollPane scrollPane_1 = new JScrollPane();
				contentPane.add(scrollPane_1, BorderLayout.CENTER);
				
						scrollPane_1.setViewportView(table);
		pack();
	}

	private String showSaveFileDialog(String msg) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Specify a file to chose");

		int userSelection = fileChooser.showDialog(this, msg);
		if (userSelection == JFileChooser.APPROVE_OPTION) {
			File fileToSave = fileChooser.getSelectedFile();
			return fileToSave.getAbsolutePath();
		}
		return null;
	}
}
