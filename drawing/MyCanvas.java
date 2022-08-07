package gui.drawing;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

public class MyCanvas extends Canvas implements Runnable {

	private Shape shape;
	private Color lineColor=Color.BLACK;
	private int steps=100;
	private long sleepTime=10;
	private Thread thread;
	
	public MyCanvas() {
		shape=new Circle(30);
	}
	@Override
	public void paint(Graphics g) {
		finish();
		
		thread=new Thread(this);
		thread.start();	
	}
	
	public void setShape(Shape shape) {
		this.shape=shape;
	}
	
	public void setColor(Color color) {
		this.lineColor=color;
	}
	
	private synchronized void finish() {
		if(thread!=null) {
			thread.interrupt();
		}
		while(thread!=null) {
			try {
				wait();
			} catch (InterruptedException e) {}
		}
		
	}
	@Override
	public void run() {

		Graphics g=this.getGraphics();
		g.setColor(lineColor);
		g.translate(this.getWidth()/2, this.getHeight()/2);
		
		double inc=Math.PI/steps;
		int x=shape.getX(0);
		int y=shape.getY(0);
		int endX,endY;
		
		try {
		for(double angle=0;angle<2* Math.PI;angle+=inc) {
			
			Thread.sleep(sleepTime);
			if(Thread.interrupted()) {
				break;
			}
			endX=shape.getX(angle);
			endY=shape.getY(angle);
			g.drawLine(x, y, endX, endY);
			x=endX;
			y=endY;
		}
		} catch (InterruptedException e) {}
		
		synchronized(this) {
			thread=null;
			notify();
		}
	}
	public void setBgColor(Color color) {
		this.setBackground(color);
	}
	public void setFactor(int factor) {
		if(shape!=null) {
			shape.factor=factor;		
		}
	}
	
}
