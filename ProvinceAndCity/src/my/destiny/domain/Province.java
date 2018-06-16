package my.destiny.domain;

import java.util.List;

public class Province {

	private int pid;
	private String name;
	private List<City> cityList;// 一方关联多方

	public int getPid() {
		return pid;
	}

	@Override
	public String toString() {
		return "Province [pid=" + pid + ", name=" + name + ", cityList="
				+ cityList + "]";
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<City> getCityList() {
		return cityList;
	}

	public void setCityList(List<City> cityList) {
		this.cityList = cityList;
	}
}
