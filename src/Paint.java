
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

//Adapted from - http://forum.codecall.net/topic/58137-java-mini-paint-program/

@SuppressWarnings("serial")
class Paint extends JComponent {
	private int brushSize = 18;
	boolean state = true;
	BufferedImage image; // Image we draw on
	Graphics2D graphics2D; // allows drawing
	int curX, curY, prevX, prevY; // mouse coordinates

	public Paint() {
		setDoubleBuffered(false);

		// adds mouse listener that will set prevX and prevY to coordinates of click
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				prevX = e.getX();
				prevY = e.getY();
			}
		});

		// adds mouse motion listener to paint as mousebutton is held down
		// draws the line at the input coordinates
		addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				curX = e.getX();
				curY = e.getY();

				if (state == true) {
					if (graphics2D != null)
						graphics2D.drawLine(prevX, prevY, curX, curY);
					repaint();
					prevX = curX;
					prevY = curY;

				} else if (state == false) {
					graphics2D.fillOval((prevX - (brushSize / 2)), (prevY - (brushSize / 2)), brushSize, brushSize);
					repaint();

					prevX = curX;
					prevY = curY;
				}
			}
		});
	}

	// Sets graphic and image
	public void paintComponent(Graphics g) {
		if (image == null) {
			image = new BufferedImage(getSize().width, getSize().height, BufferedImage.TYPE_3BYTE_BGR);
			graphics2D = (Graphics2D) image.getGraphics();
			graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			clear();
		}
		g.drawImage(image, 0, 0, null);
	}

	// sets color to white and fills frame with said color
	// switches the color back to chosen paint color
	public void clear() {
		graphics2D.setPaint(Color.white);
		graphics2D.fillRect(0, 0, getSize().width, getSize().height);
		graphics2D.setPaint(Color.black);
		repaint();
	}

	// Available colors
	public void red() {
		graphics2D.setPaint(Color.red);
		repaint();
	}

	public void black() {
		graphics2D.setPaint(Color.black);
		repaint();
	}

	public void magenta() {
		graphics2D.setPaint(Color.magenta);
		repaint();
	}

	public void blue() {
		graphics2D.setPaint(Color.blue);
		repaint();
	}

	public void green() {
		graphics2D.setPaint(Color.green);
		repaint();
	}

	public void yellow() {
		graphics2D.setPaint(Color.yellow);
		repaint();
	}

	public void gray() {
		graphics2D.setPaint(Color.gray);
		repaint();
	}

	public void pink() {
		graphics2D.setPaint(Color.pink);
		repaint();
	}

	public void cyan() {
		graphics2D.setPaint(Color.cyan);
		repaint();
	}

	public void orange() {
		graphics2D.setPaint(Color.orange);
		repaint();
	}

	public void erase() {
		graphics2D.setPaint(Color.white);
		repaint();
	}

	public void setState(Boolean val) {
		this.state = val;
	}

	public void save(File path) throws IOException {
		ImageIO.write(image, "PNG", new File(path.getPath().toString() + ".png"));
	}

	public void load(File file) throws IOException {
		image = ImageIO.read(new File(file.getPath().toString()));
		repaint();
	}
}
