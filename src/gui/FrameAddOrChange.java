package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.log4j.Logger;

import model.Device;

public class FrameAddOrChange extends JFrame {
	private View view;
	private Device devices;
	private final static Logger LOGGER = Logger.getLogger(FrameAddOrChange.class);
	
	public FrameAddOrChange(List<Device> devices) {

		JFrame addFrame = new JFrame("Добавление товара");
		addFrame.setLocationRelativeTo(null);
		addFrame.setLocation(300, 300);
		addFrame.setResizable(false);

		JPanel addPanel = new JPanel();
		JLabel textName = new JLabel("Наименование");
		JTextField textNameField = new JTextField(20);
		textNameField.setText("");
		JLabel textFirm = new JLabel("Фирма");
		JTextField textFirmeField = new JTextField(20);
		textFirmeField.setText("");
		JLabel textModel = new JLabel("Модель");
		JTextField textModelField = new JTextField(20);
		textModelField.setText("");
		JLabel textCount = new JLabel("Количество");
		JTextField textCountField = new JTextField(20);
		textCountField.setText("");
		JLabel textPrice = new JLabel("Цена, $");
		JTextField textPriceField = new JTextField(20);
		textCountField.setText("");
		JButton add = new JButton("Добавить");
		add.setBounds(0, 200, 50, 25);
		JButton cancel = new JButton("Отменить");
		cancel.setBounds(0, 200, 50, 25);

		addPanel(addFrame, addPanel, textName, textNameField, textFirm, textFirmeField, textModel, textModelField,
				textCount, textCountField, textPrice, textPriceField, add, cancel);

		add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (textNameField.getText().isEmpty() || textFirmeField.getText().isEmpty()
						|| textModelField.getText().isEmpty() || textCountField.getText().isEmpty()
						|| textPriceField.getText().isEmpty()) {
					JOptionPane.showMessageDialog(add, "Не заполнено одно из полей", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else {
					addNewDeviceInDevicesList(devices, textNameField, textFirmeField, textModelField, textCountField,
							textPriceField);
					addFrame.setVisible(false);
				}
			}
		});

		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addFrame.setVisible(false);
				view = new View(devices);
			}
		});
	}

	public FrameAddOrChange(List<Device> devices, int row) {

		JFrame changeFrame = new JFrame("Изменение товара");
		changeFrame.setLocationRelativeTo(null);
		changeFrame.setResizable(false);

		JPanel changePanel = new JPanel();
		JLabel textName = new JLabel("Наименование");
		JTextField textNameField = new JTextField(20);
		textNameField.setText("");
		JLabel textFirm = new JLabel("Фирма");
		JTextField textFirmeField = new JTextField(20);
		textFirmeField.setText("");
		JLabel textModel = new JLabel("Модель");
		JTextField textModelField = new JTextField(20);
		textModelField.setText("");
		JLabel textCount = new JLabel("Количество");
		JTextField textCountField = new JTextField(20);
		textCountField.setText("");
		JLabel textPrice = new JLabel("Цена, $");
		JTextField textPriceField = new JTextField(20);
		textCountField.setText("");
		JButton change = new JButton("Изменить");
		change.setBounds(0, 200, 50, 25);
		JButton cancel = new JButton("Отменить");
		cancel.setBounds(0, 200, 50, 25);

		try {
			textNameField.setText(devices.get(row).getName());
			textFirmeField.setText(devices.get(row).getFirm());
			textModelField.setText(devices.get(row).getModel());
			textCountField.setText(devices.get(row).getCount().toString());
			textPriceField.setText(devices.get(row).getPrice().toString());
			addPanel(changeFrame, changePanel, textName, textNameField, textFirm, textFirmeField, textModel, textModelField,
					textCount, textCountField, textPrice, textPriceField, change, cancel);
		} catch (ArrayIndexOutOfBoundsException e) {
			LOGGER.error("We've got ArrayIndexOutOfBoundsException!", e);
			JOptionPane.showMessageDialog(change, "Не выбрана строка устройств.", "Error",
					JOptionPane.ERROR_MESSAGE);
			changeFrame.setVisible(false);
			view = new View(devices);
		}

		change.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (textNameField.getText().isEmpty() || textFirmeField.getText().isEmpty()
						|| textModelField.getText().isEmpty() || textCountField.getText().isEmpty()
						|| textPriceField.getText().isEmpty()) {
					JOptionPane.showMessageDialog(change, "Не заполнено одно из полей", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else {
					addNewDeviceInDevicesList(devices, textNameField, textFirmeField, textModelField, textCountField,
							textPriceField, row);
					changeFrame.setVisible(false);
					view = new View(devices);
				}
			}
		});
		
		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				changeFrame.setVisible(false);
				view = new View(devices);
			}
		});
	}
	
	private void addNewDeviceInDevicesList(List<Device> devices, JTextField textNameField, JTextField textFirmeField,
			JTextField textModelField, JTextField textCountField, JTextField textPriceField) {
		int addingDevice = 0;
		Device device = new Device();
		device.setName(textNameField.getText());
		device.setFirm(textFirmeField.getText());
		device.setModel(textModelField.getText());
		try {
			device.setCount(Integer.parseInt(textCountField.getText()));
			addingDevice++;
		} catch (NumberFormatException e) {
			LOGGER.error("We've got NumberFormatException!", e);
			JOptionPane.showMessageDialog(view, "Укажите количество числом!", "Error", JOptionPane.ERROR_MESSAGE);
		}
		try {
			device.setPrice(Integer.parseInt(textPriceField.getText()));
			addingDevice++;
		} catch (NumberFormatException e) {
			LOGGER.error("We've got NumberFormatException!", e);
			JOptionPane.showMessageDialog(view, "Укажите сумму числом!", "Error", JOptionPane.ERROR_MESSAGE);
		}
		if (addingDevice == 2) {
			devices.add(device);
			JOptionPane.showMessageDialog(view, "Товар добавлен.", "Внимание", JOptionPane.INFORMATION_MESSAGE);
		}
		view = new View(devices);
	}

	private void addNewDeviceInDevicesList(List<Device> devices, JTextField textNameField, JTextField textFirmeField,
			JTextField textModelField, JTextField textCountField, JTextField textPriceField, int row) {
		int addingDevice = 0;
		Device device = new Device();
		device.setName(textNameField.getText());
		device.setFirm(textFirmeField.getText());
		device.setModel(textModelField.getText());
		try {
			device.setCount(Integer.parseInt(textCountField.getText()));
			addingDevice++;
		} catch (NumberFormatException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(view, "Укажите количество числом!", "Error", JOptionPane.ERROR_MESSAGE);
		}
		try {
			device.setPrice(Integer.parseInt(textPriceField.getText()));
			addingDevice++;
		} catch (NumberFormatException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(view, "Укажите сумму числом!", "Error", JOptionPane.ERROR_MESSAGE);
		}
		if (addingDevice == 2) {
			devices.remove(row);
			devices.add(device);
			JOptionPane.showMessageDialog(view, "Товар изменен.", "Внимание", JOptionPane.INFORMATION_MESSAGE);
		}
		view = new View(devices);
	}

	private void addPanel(JFrame changeFrame, JPanel changePanel, JLabel textName, JTextField textNameField,
			JLabel textFirm, JTextField textFirmeField, JLabel textModel, JTextField textModelField, JLabel textCount,
			JTextField textCountField, JLabel textPrice, JTextField textPriceField, JButton change, JButton cancel) {
		changePanel.setLayout(new GridLayout(6, 2));
		changePanel.add(textName);
		changePanel.add(textNameField);
		changePanel.add(textFirm);
		changePanel.add(textFirmeField);
		changePanel.add(textModel);
		changePanel.add(textModelField);
		changePanel.add(textCount);
		changePanel.add(textCountField);
		changePanel.add(textPrice);
		changePanel.add(textPriceField);
		changePanel.add(change);
		changePanel.add(cancel);
		changeFrame.add(changePanel);
		changeFrame.setSize(500, 220);
		changeFrame.setVisible(true);
	}
}
