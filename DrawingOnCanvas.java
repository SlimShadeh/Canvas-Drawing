package gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Label;
import java.awt.List;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.Panel;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import gui.drawing.Circle;
import gui.drawing.MyCanvas;

public class DrawingOnCanvas extends Frame {

	private Button drawButton=new Button("Draw!");
	private MyCanvas scene=new MyCanvas(); 
	
	private void populateWindow() {
		
		Menu bgColorMenu=new Menu("Bg color");
		MenuItem bgWhite=new MenuItem("white");
		MenuItem bgGrey=new MenuItem("grey");
		
		bgColorMenu.add(bgGrey);
		bgColorMenu.add(bgWhite);
		
		bgWhite.addActionListener((ae)->{
			scene.setBgColor(Color.WHITE);
		});
		
		bgGrey.addActionListener((ae)->{
			scene.setBgColor(Color.GRAY);
		});
		
		Menu factorMenu=new Menu("Size");
		
		MenuItem small = new MenuItem("3");
		MenuItem big = new MenuItem("5");
		
		factorMenu.add(small);
		factorMenu.add(big);
		
		small.addActionListener((ae)->{
			scene.setFactor(Integer.parseInt(((MenuItem)ae.getSource()).getLabel()));
		});
		
		big.addActionListener((ae)->{
			scene.setFactor(Integer.parseInt(((MenuItem)ae.getSource()).getLabel()));
		});
		
		MenuItem quitMenu=new MenuItem("Quit", new MenuShortcut(KeyEvent.VK_Q));
		
		quitMenu.addActionListener((ae)->{
			dispose();
		});
		
		Menu file=new Menu("File");
		file.add(bgColorMenu);
		file.add(factorMenu);
		file.addSeparator();
		file.add(quitMenu);
		
		MenuBar menuBar=new MenuBar();
		menuBar.add(file);
		setMenuBar(menuBar);
		
		add(scene,BorderLayout.CENTER);
		
		drawButton.addActionListener((ae) ->{
			scene.repaint();
		});
		
		Panel buttonPanel=new Panel();
		
		Choice chooseShape=new Choice();
		chooseShape.add("Heart");
		chooseShape.add("Circle");
		
		chooseShape.addItemListener((ie)->{
			String name=chooseShape.getSelectedItem();
			if(name.equals("Heart")) {
				scene.setShape(new Heart());
			}
			else if(name.equals("Circle")) {
				scene.setShape(new Circle(30));
			}
		});
		
		List chooseColor=new List(2);
		chooseColor.add("Black");
		chooseColor.add("Red");
		chooseColor.add("Green");
		
		chooseColor.select(0);
		
		chooseColor.addItemListener((ie)->{
			String item=chooseColor.getSelectedItem();
			if(item.equals("Black")) {
				scene.setColor(Color.BLACK);
			}
			else if(item.equals("Red")) {
				scene.setColor(Color.RED);
			}
			else if(item.equals("Green")) {
				scene.setColor(Color.GREEN);
			}
		});
		
		buttonPanel.add(new Label("Shape: "));
		buttonPanel.add(chooseShape);
		buttonPanel.add(new Label("Color: "));
		buttonPanel.add(chooseColor);
		buttonPanel.add(drawButton);
		
		add(buttonPanel, BorderLayout.SOUTH);
		
		
	}
	
	public DrawingOnCanvas() {
		setBounds(700,200,400,300);
		setResizable(false);
		setTitle("Drawing");
		
		populateWindow();
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new DrawingOnCanvas();
	}
}
