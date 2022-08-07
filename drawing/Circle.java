package gui.drawing;

public class Circle extends Shape {

	private double r;
	
	public Circle(double r) {
		this.r=r;
	}
	
	@Override
	public int getX(double angle) {
		return (int)(r* Math.cos(angle));
	}

	@Override
	public int getY(double angle) {
		return (int)(r* Math.sin(angle));
	}

}
