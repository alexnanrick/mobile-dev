package ie.dit;

public class Circle extends Shape{
	private static double pi = 3.14;
	private double radius;
	
	public Circle(double radius) {
		this.radius = radius;
	}
	
	@Override
	public String toString() {
		return "Circle [radius=" + radius + "]";
	}

	public double area(int radius) {
		area = pi * radius * radius;
		return area;
	}
	
	public static double getPi() {
		return pi;
	}

	public static void setPi(double pi) {
		Circle.pi = pi;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}
}
