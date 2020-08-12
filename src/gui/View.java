package gui;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.Device;
import javax.swing.*;

import org.apache.log4j.Logger;

import listener.CboxListener;
import java.awt.event.*;

public class View extends JFrame {

	private final static Logger LOGGER = Logger.getLogger(View.class);

	private View view;
	private static JTable table;
	private JFrame frame;

	static int countSizeListDevices;
	private static String[][] data = new String[1][5];
	private String[] column = { "Наименование", "Фирма", "Модель", "Количество", "Цена, $" };

	public View(List<Device> devices) {

		int dataColumn = devices.size();
		data = new String[dataColumn][5];

		JFrame frame = new JFrame("База товаров");
		frame.setLocationRelativeTo(null);
		frame.setLocation(300, 300);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		String[] cboxList = { "Всё оборудование", "Процессор", "Видеокарта", "Оперативная память", "Ноутбук" };

		JComboBox cbox = new JComboBox(cboxList);
		panel.setLayout(new BorderLayout());
		panel.add(cbox, BorderLayout.NORTH);

		JButton addButton = new JButton("Добавить");
		addButton.setBounds(0, 280, 200, 25);
		JButton deleteButton = new JButton("Удалить");
		deleteButton.setBounds(200, 280, 200, 25);
		JButton changeButton = new JButton("Изменить");
		changeButton.setBounds(400, 280, 200, 25);
		panel.add(addButton);
		panel.add(deleteButton);
		panel.add(changeButton);

		table = new JTable(data, column);
		table.setBounds(0, 0, 600, 200);
		panel.add(table, BorderLayout.SOUTH);
		JScrollPane scroll = new JScrollPane(table);
		panel.add(scroll, BorderLayout.CENTER);
		frame.add(panel, BorderLayout.NORTH);

		frame.setSize(615, 350);
		frame.setVisible(true);
		for (int i = 0; i < devices.size(); i++) {
			allFilterDevices(table, devices, i);
		}

		// cbox listener
		cbox.addActionListener(new CboxListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CboxListener(cbox, table, devices);
				table.repaint();
			}
		});

		// add button listener
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				FrameAddOrChange FrameAddOrChange = new FrameAddOrChange(devices);
				table.repaint();
			}
		});

		// delete button listener
		deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int sort = table.getSelectedRow();
				try {
					if (sort < 0) { devices.remove(sort); }
				} catch (ArrayIndexOutOfBoundsException e1) {
					LOGGER.error("We've got ArrayIndexOutOfBoundsException!", e1);
					JOptionPane.showMessageDialog(view, "Не выбрана строка устройств.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
				int sortCount = 0;
				String tempFilter = cboxList[CboxListener.getCboxCount()];
				if (CboxListener.getCboxCount() == 0) {
					if (sort >= 0) { devices.remove(sort); }
				} else {
					for (int i = 0; i < devices.size(); i++) {
						if (tempFilter.equals(devices.get(i).getName()) & (sort == sortCount)) {
							devices.remove(i);
							sortCount++;
							JOptionPane.showMessageDialog(deleteButton, "Товар удален", "Внимание",
									JOptionPane.INFORMATION_MESSAGE);
						}
					}
				}
				frame.setVisible(false);
				table.repaint();
				new View(devices);
			}
		});

		// change button listener
		changeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				FrameAddOrChange FrameAddOrChange = new FrameAddOrChange(devices, row);
				frame.setVisible(false);
				table.repaint();
			}
		});
	}

	public static JTable getTable() {
		return table;
	}

	public static int filterTable(List<Device> newDevice, int count, int i) {
		data[count][0] = newDevice.get(i).getName();
		data[count][1] = newDevice.get(i).getFirm();
		data[count][2] = newDevice.get(i).getModel();
		data[count][3] = newDevice.get(i).getCount().toString();
		data[count][4] = newDevice.get(i).getPrice().toString();
		count++;
		return count;
	}

	public static void allFilterDevices(JTable table, List<Device> newDevice, int i) {
		data[i][0] = newDevice.get(i).getName();
		data[i][1] = newDevice.get(i).getFirm();
		data[i][2] = newDevice.get(i).getModel();
		data[i][3] = newDevice.get(i).getCount().toString();
		data[i][4] = newDevice.get(i).getPrice().toString();
		table.repaint();
	}

	public static void clearTable(List<Device> devices) {
		List<Device> newDevice = devices;
		for (int i = 0; i < newDevice.size(); i++) {
			data[i][0] = "";
			data[i][1] = "";
			data[i][2] = "";
			data[i][3] = "";
			data[i][4] = "";
		}
	}

	public static String[][] addTable(List<Device> devices, String[][] data) {
		String[][] newData = new String[devices.size()][5];
		System.arraycopy(newData, 0, data, 0, data.length);
		int count = 0;
		for (int i = 0; i < devices.size(); i++) {
			newData[count][0] = devices.get(i).getName();
			newData[count][1] = devices.get(i).getFirm();
			newData[count][2] = devices.get(i).getModel();
			newData[count][3] = devices.get(i).getCount().toString();
			newData[count][4] = devices.get(i).getPrice().toString();
			count++;
		}
		data = newData;
		return data;
	}

}