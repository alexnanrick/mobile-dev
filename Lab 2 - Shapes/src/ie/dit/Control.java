package ie.dit;

public class Control {
	public static void main(String[] args) 
	{
		double test;
		
		Square s1 = new Square(5, 5);
		
		test = s1.area(5, 5);
		
		System.out.println(test);
		
		Circle c1 = new Circle(6);
		
		test = c1.area(5);
		
		System.out.println(test);
	} // end main
}
