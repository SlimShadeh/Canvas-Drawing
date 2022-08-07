package gui;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class DrawingLines extends Frame{

	//private int x,y;
	
	class Line{
		int begX,begY,endX,endY;
	}
	
	private ArrayList<Line> lines=new ArrayList<>();
	private Line line;
	
	public void paint(Graphics g) {
		for(Line line: lines) {
			g.drawLine(line.begX, line.begY, line.endX, line.endY);
		}
		super.paint(g);
	}
	
	private class MouseEventHandler extends MouseAdapter{
		
		public void mouseDragged(MouseEvent e) {
			line.endX=e.getX();
			line.endY=e.getY();
			repaint();
		}
		public void mousePressed(MouseEvent e) {
				line=new Line();
				lines.add(line);
				line.begX=e.getX();
				line.begY=e.getY();
		}
		public void mouseReleased(MouseEvent e) {
		
			repaint();
			
		}
	}
	
	public DrawingLines() {
		setBounds(700,200,400,300);
		setResizable(false);
		setTitle("Drawing Lines");
		
		addMouseListener(new MouseEventHandler());
		addMouseMotionListener(new MouseEventHandler());
		
		addWindowListener(new WindowAdapter() {
		public void windowClosing(WindowEvent e) {
			dispose();
		}
		});
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new DrawingLines();
	}
}
