package models;

public class ResultBean {
	
	public String label;
	public String result;

	public ResultBean(String label,String result) {
		this.label = label;
		this.result = result;
	}

	public ResultBean() {
		this.label = "";
		this.result = "";
	}
}