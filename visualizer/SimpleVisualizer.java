package visualizer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class SimpleVisualizer extends JFrame {

	private static final long serialVersionUID = 1L;
	BufferedImage canvas;
	JLabel jLabel = new JLabel();
	int x = 0, y = 0;
	int height, width;

	int[] black;

	private void reDraw() {
		height = getHeight();
		width = getWidth();
		setTitle(height + " " + width);
		canvas = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		jLabel.setIcon(new ImageIcon(canvas));
		black = new int[width * height];
	}

	public void clearCanvas() {
		Arrays.fill(black, 0xFF000000);
		canvas.setRGB(0, 0, width, height, black, 0, width);
	}

	public SimpleVisualizer(boolean exitOnClose) {
		if (exitOnClose) {
			setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		}
		setBounds(100, 0, 640, 480);
		JPanel mainPanel = new JPanel();

		mainPanel.add(jLabel);
		add(mainPanel);

		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				reDraw();
			}
		});

		mainPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				setTitle(e.getButton() + " " + e.getX() + " " + e.getY());
				Graphics g = canvas.getGraphics();
				g.setColor(Color.MAGENTA);
				g.drawLine(x, y, e.getX(), e.getY());
				x = e.getX();
				y = e.getY();
				repaint();
			}
		});

		setVisible(true);
	}
}
