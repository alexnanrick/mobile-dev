package ie.dit;

public class Square extends Shape{
	int width;
	int heigth;
	
	public Square(int width, int heigth) {
		this.width = width;
		this.heigth = heigth;
	}
	
	public double area(int width, int heigth) {
		area = width * heigth;
		
		return area;
	}

	@Override
	public String toString() {
		return "Square [width=" + width + ", heigth=" + heigth + "]";
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeigth() {
		return heigth;
	}

	public void setHeigth(int heigth) {
		this.heigth = heigth;
	}
}
