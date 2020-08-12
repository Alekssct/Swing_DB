package model;

public class Device {

	private String name;
	private String firm;
	private String model;
	private Integer count;
	private Integer price;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirm() {
		return firm;
	}

	public void setFirm(String firm) {
		this.firm = firm;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firm == null) ? 0 : firm.hashCode());
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Device other = (Device) obj;
		if (firm == null) {
			if (other.firm != null)
				return false;
		} else if (!firm.equals(other.firm))
			return false;
		if (model == null) {
			if (other.model != null)
				return false;
		} else if (!model.equals(other.model))
			return false;
		return true;
	}
}
