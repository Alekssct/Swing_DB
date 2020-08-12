package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JTable;

import gui.View;
import model.Device;

public abstract class CboxListener implements ActionListener{
	private View view;
	private static JComboBox cbox;
	private static int cboxCount;
	
	public static int getCboxCount() {
		return cboxCount;
	}

	public static void CboxListener(JComboBox cbox, JTable table, List<Device> devices) {
		String tableData = (String) cbox.getItemAt(cbox.getSelectedIndex());
		cboxCount = cbox.getSelectedIndex();
		List<Device> newDevice = devices;
		int count = 0;
		// clear table
		View.clearTable(devices);

		// filter table
		for (int i = 0; i < newDevice.size(); i++) {
			if ((newDevice.get(i).getName().equals(tableData)) & (!newDevice.get(i).equals(null))) {
				count = View.filterTable(newDevice, count, i);
				continue;
			}
			if ("Всё оборудование".equals(tableData)) {
				View.allFilterDevices(View.getTable(), newDevice, i);
			}
		}
	}
}
