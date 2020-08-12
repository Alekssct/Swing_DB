package main;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import gui.View;
import model.Device;

public class Main {

	public static void main(String[] args) {
		PropertyConfigurator.configure("logger\\log4j.properties");
		List<Device> devices = createDevices();
		new View(devices);
		
	}

	private static List<Device> createDevices() {
		Device device1 = new Device();
		device1.setName("Процессор");
		device1.setFirm("Intel");
		device1.setModel("i5");
		device1.setCount(3);
		device1.setPrice(120);

		Device device2 = new Device();
		device2.setName("Видеокарта");
		device2.setFirm("Radeon");
		device2.setModel("R290");
		device2.setCount(1);
		device2.setPrice(90);

		Device device3 = new Device();
		device3.setName("Оперативная память");
		device3.setFirm("Corsar");
		device3.setModel("19600");
		device3.setCount(2);
		device3.setPrice(40);
		
		Device device4 = new Device();
		device4.setName("Ноутбук");
		device4.setFirm("Dell");
		device4.setModel("inspiron 15");
		device4.setCount(2);
		device4.setPrice(420);

		//create array devices
		List<Device> devices = new ArrayList<Device>();
		devices.add(device1);
		devices.add(device2);
		devices.add(device3);
		devices.add(device4);
		return devices;
	}
}
