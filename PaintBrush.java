// Mustafa Tufan 221401029 BİL 211 Mid-term

// If you here to read the code, enjoy trying to understand my English on comments :)

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PaintBrush {
	public static void main(String[] args) {
		new Paint();
	}
}

@SuppressWarnings("serial")
class Paint extends JFrame {

	// Setting colors to use for color panels and drawing

	Color blueColor = new Color(68, 114, 196);
	Color redColor = new Color(255, 0, 0);
	Color greenColor = new Color(0, 176, 80);
	Color yellowColor = new Color(255, 255, 0);
	Color orangeColor = new Color(237, 125, 49);
	Color purpleColor = new Color(112, 48, 160);
	Color blackColor = new Color(0, 0, 0);

	// No default mode, black as default color

	int mode = 0;
	Color color = Color.BLACK;

	// Constructor creates 2 panels, one for settings, one for drawing

	public Paint() {
		setSize(1280, 720);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout(2, 1));
		JPanel sa = new SettingArea();
		JPanel pa = new PaintArea();
		add(sa);
		add(pa);
		setVisible(true);
	}

	public class SettingArea extends JPanel implements MouseListener, ActionListener {

		JPanel blue = new JPanel();
		JPanel red = new JPanel();
		JPanel green = new JPanel();
		JPanel yellow = new JPanel();
		JPanel orange = new JPanel();
		JPanel purple = new JPanel();
		JPanel black = new JPanel();

		JButton square = new JButton("Dikdörtgen Çiz");
		JButton oval = new JButton("Oval Çiz");
		JButton draw = new JButton("Kalemle Çiz");
		JButton move = new JButton("Taşı");

		JPanel colors = new JPanel();
		JPanel functions = new JPanel();

		// Creates panels and buttons, then adds them to setting panel

		public SettingArea() {

			setLayout(new GridLayout(2, 1));

			colors.setLayout(new FlowLayout());
			colors.setPreferredSize(getMaximumSize());

			blue.setPreferredSize(new Dimension(100, 40));
			blue.setBackground(blueColor);
			red.setPreferredSize(new Dimension(100, 40));
			red.setBackground(redColor);
			green.setPreferredSize(new Dimension(100, 40));
			green.setBackground(greenColor);
			yellow.setPreferredSize(new Dimension(100, 40));
			yellow.setBackground(yellowColor);
			orange.setPreferredSize(new Dimension(100, 40));
			orange.setBackground(orangeColor);
			purple.setPreferredSize(new Dimension(100, 40));
			purple.setBackground(purpleColor);
			black.setPreferredSize(new Dimension(100, 40));
			black.setBackground(blackColor);

			colors.add(blue);
			colors.add(red);
			colors.add(green);
			colors.add(yellow);
			colors.add(orange);
			colors.add(purple);
			colors.add(black);
			colors.addMouseListener(this);

			functions.setLayout(new FlowLayout());
			functions.setPreferredSize(getMaximumSize());

			square.setPreferredSize(new Dimension(200, 40));
			square.addActionListener(this);
			oval.setPreferredSize(new Dimension(200, 40));
			oval.addActionListener(this);
			draw.setPreferredSize(new Dimension(200, 40));
			draw.addActionListener(this);
			move.setPreferredSize(new Dimension(200, 40));
			move.addActionListener(this);

			functions.add(square);
			functions.add(oval);
			functions.add(draw);
			functions.add(move);

			add(colors);
			add(functions);
		}

		// Method to find if mouse is inside a panel

		public void inColorArea(int x, int y) {
			if (x >= blue.getLocation().x && x <= blue.getLocation().x + blue.getWidth()
					&& y >= blue.getLocation().y && y <= blue.getLocation().y + blue.getHeight())
				color = blueColor;
			if (x >= red.getLocation().x && x <= red.getLocation().x + red.getWidth()
					&& y >= red.getLocation().y && y <= red.getLocation().y + red.getHeight())
				color = redColor;
			if (x >= green.getLocation().x && x <= green.getLocation().x + green.getWidth()
					&& y >= green.getLocation().y && y <= green.getLocation().y + green.getHeight())
				color = greenColor;
			if (x >= yellow.getLocation().x && x <= yellow.getLocation().x + yellow.getWidth()
					&& y >= yellow.getLocation().y
					&& y <= yellow.getLocation().y + yellow.getHeight())
				color = yellowColor;
			if (x >= orange.getLocation().x && x <= orange.getLocation().x + orange.getWidth()
					&& y >= orange.getLocation().y
					&& y <= orange.getLocation().y + orange.getHeight())
				color = orangeColor;
			if (x >= purple.getLocation().x && x <= purple.getLocation().x + purple.getWidth()
					&& y >= purple.getLocation().y
					&& y <= purple.getLocation().y + purple.getHeight())
				color = purpleColor;
			if (x >= black.getLocation().x && x <= black.getLocation().x + black.getWidth()
					&& y >= black.getLocation().y && y <= black.getLocation().y + black.getHeight())
				color = blackColor;
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			inColorArea(e.getX(), e.getY());
		}

		@Override
		public void mousePressed(MouseEvent e) {
			inColorArea(e.getX(), e.getY());
		}

		public void mouseReleased(MouseEvent e) {
		}

		public void mouseEntered(MouseEvent e) {
		}

		public void mouseExited(MouseEvent e) {
		}

		// Changes mode depending on clicked button

		@Override
		public void actionPerformed(ActionEvent e) {
			switch (e.getActionCommand()) {
			case ("Dikdörtgen Çiz"):
				mode = 1;
				break;
			case ("Oval Çiz"):
				mode = 2;
				break;
			case ("Kalemle Çiz"):
				mode = 3;
				break;
			case ("Taşı"):
				mode = 4;
				break;
			}
		}
	}

	public class PaintArea extends JPanel implements MouseMotionListener, MouseListener {

		ArrayList<Object> all = new ArrayList<>(); // Contains all things we draw
		boolean moveAction = false; // Activates if user moving something
		int old_mouse_x;
		int old_mouse_y; // Helps to move shapes from selected location

		public PaintArea() {
			addMouseMotionListener(this);
			addMouseListener(this);
		}

		// Draws a line at first. Then paints the panel from arraylist depending on
		// their class type

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(Color.blue);
			g.drawLine(0, 0, getWidth(), 0);
			for (int i = 0; i < all.size(); i++) {
				Object object = all.get(i);

				// If its a dot, it concats two dot or it just paints a dot. Ik the conditions
				// are unreadable but im too lazy to edit, sorry ;-;

				if (object instanceof Dot) {
					if (i > 0 && all.get(i - 1) instanceof Dot && ((Dot) all.get(i)).drag == true
							&& ((Dot) all.get(i - 1)).drag == true) {
						Dot dot = (Dot) all.get(i - 1);
						Dot dot2 = (Dot) object;
						int x = dot.x;
						int y = dot.y;
						int x2 = dot2.x;
						int y2 = dot2.y;
						g.setColor(dot.color);
						g.drawLine(x, y, x2, y2);
					} else {
						Dot dot = (Dot) object;
						int x = dot.x;
						int y = dot.y;
						g.setColor(dot.color);
						g.fillOval(x, y, 1, 1);
					}
				}

				// Draws a shape depending on their type

				if (object instanceof Shape) {
					Shape shape = (Shape) object;
					int min_x = shape.min_x;
					int min_y = shape.min_y;
					int max_x = shape.max_x;
					int max_y = shape.max_y;
					g.setColor(shape.color);
					if (((Shape) object).type.equals("Rect"))
						g.fillRect(min_x, min_y, max_x - min_x, max_y - min_y);
					else
						g.fillOval(min_x, min_y, max_x - min_x, max_y - min_y);
				}
			}
		}

		// Pushes choosen to last place of arraylist

		private boolean pushChoosenToLast(int x, int y) {
			for (int i = all.size() - 1; i >= 0; i--) {
				if (inLocation(x, y, all.get(i))) {
					all.add(all.get(i));
					all.remove(i);
					return true;
				}
			}
			return false;
		}

		// Finds the shape from mouse location

		private boolean inLocation(int x, int y, Object object) {
			if (object instanceof Dot)
				return false;
			Shape shape = (Shape) object;
			if (shape.type.equals("Rect")) {
				if (x >= shape.min_x && x <= shape.max_x && y >= shape.min_y && y <= shape.max_y)
					return true;
				return false;
			} else {
				int a = (shape.max_x - shape.min_x) / 2;
				int b = (shape.max_y - shape.min_y) / 2;
				int rx = Math.abs(x - ((shape.max_x + shape.min_x) / 2));
				int ry = Math.abs(y - ((shape.max_y + shape.min_y) / 2));
				if ((Math.pow(rx, 2) / Math.pow(a, 2)) + (Math.pow(ry, 2) / Math.pow(b, 2))  <= 1)
					return true;
				return false;
			}
		}

		// Just paints a dot

		@Override
		public void mouseClicked(MouseEvent e) {
			if (mode == 3) {
				Dot dot = new Dot(e.getX(), e.getY(), color, false);
				all.add(dot);
			}
			repaint();
		}

		// Paints a dot, or gets prepared for moving thing
		// I forgot why I need to move, the shape that I want to move, to the last place
		// of arraylist but im too scared to change it

		@Override
		public void mousePressed(MouseEvent e) {
			if (mode == 3) {
				Dot dot = new Dot(e.getX(), e.getY(), color, false);
				all.add(dot);
			}

			if (mode == 4) {
				moveAction = pushChoosenToLast(e.getX(), e.getY());
				if (moveAction) {
					old_mouse_x = e.getX();
					old_mouse_y = e.getY();
				}
			}
			repaint();
		}

		// It stops the drawing or moving process

		@Override
		public void mouseReleased(MouseEvent e) {
			if (mode == 1 || mode == 2) {
				if (all.size() > 0 && all.get(all.size() - 1) instanceof Shape
						&& ((Shape) all.get(all.size() - 1)).edit == true) {
					((Shape) all.get(all.size() - 1)).edit = false;
				}
			}

			if (mode == 4) {
				moveAction = false;
			}

			repaint();
		}

		// If user trying to draw a shape, if it just started doing it, it creates a
		// shape, if user was already start it, it expands it (its actually not expands
		// it, just deletes last shape from arraylist and adds new one but bigger)

		// Draw mode is just adds another dot

		// Move mode finds where user choosen the shape to move from (ENGLISH OMG) and
		// moves depending on that location

		@Override
		public void mouseDragged(MouseEvent e) {
			if (mode == 1 || mode == 2) {
				String type;
				if (mode == 1)
					type = "Rect";
				else
					type = "Oval";
				if (all.size() > 0 && all.get(all.size() - 1) instanceof Shape
						&& ((Shape) all.get(all.size() - 1)).edit == true) {

					Shape old_shape = ((Shape) all.get(all.size() - 1));
					Shape shape = new Shape(old_shape.old_x, old_shape.old_y, e.getX(), e.getY(),
							type, color, true);
					all.remove(all.size() - 1);
					all.add(shape);
				} else {
					Shape shape = new Shape(e.getX(), e.getY(), e.getX(), e.getY(), type, color,
							true);
					all.add(shape);
				}
			}

			if (mode == 3) {
				Dot dot = new Dot(e.getX(), e.getY(), color, true);
				all.add(dot);
			}

			if (mode == 4) {
				if (!moveAction)
					return;
				int x_diff = e.getX() - old_mouse_x;
				int y_diff = e.getY() - old_mouse_y;
				Shape old_shape = ((Shape) all.get(all.size() - 1));
				Shape shape = new Shape(old_shape.old_x + x_diff, old_shape.old_y + y_diff,
						old_shape.x + x_diff, old_shape.y + y_diff, old_shape.type,
						old_shape.color, false);
				all.remove(all.size() - 1);
				all.add(shape);
				old_mouse_x = e.getX();
				old_mouse_y = e.getY();
			}
			repaint();
		}

		// Basic needs for a dot object, only interesting one is drag variable and its
		// because paintComponent needs to know if it should concat two dot

		public class Dot {
			int x;
			int y;
			Color color;
			boolean drag;

			public Dot(int x, int y, Color color, boolean drag) {
				this.x = x;
				this.y = y;
				this.color = color;
				this.drag = drag;
			}
		}

		// I legit have no idea why it needs so much variables but i think i created
		// them to make code more readable but has no advantage whatsoever, edit
		// variable may seem interesting and its because mouseDragged needs to know if
		// its just created or is it trying to expand

		public class Shape {
			int old_x;
			int old_y;
			int x;
			int y;
			int min_x;
			int min_y;
			int max_x;
			int max_y;
			String type;
			Color color;
			boolean edit;

			public Shape(int old_x, int old_y, int x, int y, String type, Color color, Boolean edit) {
				this.old_x = old_x;
				this.old_y = old_y;
				this.x = x;
				this.y = y;
				this.type = type;
				this.color = color;
				this.edit = edit;
				min_x = Math.min(old_x, x);
				min_y = Math.min(old_y, y);
				max_x = Math.max(old_x, x);
				max_y = Math.max(old_y, y);
			}
		}

		public void mouseMoved(MouseEvent e) {
		}

		public void mouseEntered(MouseEvent e) {
		}

		public void mouseExited(MouseEvent e) {
		}
	}
}
