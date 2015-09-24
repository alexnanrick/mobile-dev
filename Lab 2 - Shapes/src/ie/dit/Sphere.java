package ie.dit;

public class Sphere implements ThreeDShape {
	private double volume;
	private double SA;
	private double pi = 3.14;
	public int radius;
	
	public Sphere(int radius) {
		this.radius = radius;
	}
	
	@Override
	public double Volume(int radius) {
		volume = (4/3) * pi * radius * radius * radius;
		return volume;
	}

	@Override
	public double surfaceArea(int radius) {
		SA = 4 * pi * radius * radius;
		return SA;
	}
	
	public double getVolume() {
		return volume;
	}

	public void setVolume(double volume) {
		this.volume = volume;
	}

	public double getSA() {
		return SA;
	}

	public void setSA(double sA) {
		SA = sA;
	}

	public double getPi() {
		return pi;
	}

	public void setPi(double pi) {
		this.pi = pi;
	}

	@Override
	public String toString() {
		return "Sphere [volume=" + volume + ", SA=" + SA + ", pi=" + pi + "]";
	}
	
}
