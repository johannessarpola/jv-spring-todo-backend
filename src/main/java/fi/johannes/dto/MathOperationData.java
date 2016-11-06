package fi.johannes.dto;


public class MathOperationData {

	String[] numbers;
	

	public MathOperationData(String[] numbers) {
		super();
		this.numbers = numbers;
	}
	public MathOperationData() {
	}
	public String[] getNumbers() {
		return numbers;
	}
	public void setNumbers(String[] numbers) {
		this.numbers = numbers;
	}
}
