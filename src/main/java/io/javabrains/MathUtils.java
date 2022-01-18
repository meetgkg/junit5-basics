package io.javabrains;

public class MathUtils {
	
	public int add(int a, int b) {
		return a + b;
	}
	
	public int substract(int a, int b) {
		return a - b;
	}
	
	public int multiply(int a, int b) {
		return a * b;
	}
	
	public int divide(int a, int b) {
		return a / b;
	}
	
	public double calcCircleArea(double radius) {
		return Math.PI * radius * radius;
	}
	
	public double calcCircleDiameter(double radius) {
		return 2 * Math.PI * radius ;
	}

}
