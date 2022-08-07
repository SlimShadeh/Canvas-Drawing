package gui;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MouseWindow extends Frame{

	private int x,y;
	private String text;
	
	@Override
	public void paint(Graphics g) {
		g.drawString(text, x, y);
		super.paint(g);
	}
	
	private void adjust(String text,MouseEvent e) {
		x=e.getX();
		y=e.getY();
		this.text=text+ " (" + x + ", " + y + ")";
		repaint();
	}
	
	public MouseWindow() {
		setBounds(700,200,400,300);
		setResizable(false);
		setTitle("Mouse window");
		
		addMouseListener(new MouseAdapter() {
		 

			@Override
			public void mouseClicked(MouseEvent e) {
				adjust("mouse clicked", e);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				adjust("mouse pressed",e);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				adjust("mouse released",e);
			}
		});
		
		addMouseMotionListener(new MouseMotionAdapter() {

			@Override
			public void mouseDragged(MouseEvent e) {
				adjust("mouse dragged",e);
				
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				adjust("mouse moved",e);
			}
		
		});
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new MouseWindow();
		
	}
}
