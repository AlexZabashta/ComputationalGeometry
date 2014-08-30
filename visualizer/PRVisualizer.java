package visualizer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.SynchronousQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import lib.two_dimensional.primitives.IntPoint;

public class PRVisualizer extends JFrame {

	private static final long serialVersionUID = 1L;
	private BufferedImage canvas = new BufferedImage(42, 23, BufferedImage.TYPE_INT_RGB);
	private JLabel jLabel = new JLabel();
	private ArrayList<GraphicComponent> gp = new ArrayList<GraphicComponent>();

	int orderSize = -1;
	List<IntPoint> points = new ArrayList<>();
	SynchronousQueue<List<IntPoint>> pipe = new SynchronousQueue<>();

	private void addPoint(int x, int y, boolean b) {
		switch (orderSize) {
		case -1:
			return;
		case 0:
			if (b) {
				points.add(new IntPoint(x, y));
			} else {
				try {
					pipe.put(points);
					points = new ArrayList<IntPoint>();
				} catch (InterruptedException e) {
				}
			}
			return;
		default:
			if (b) {
				points.add(new IntPoint(x, y));
			} else {
				List<IntPoint> cur = points;
				points = new ArrayList<IntPoint>();

				if (cur.size() < orderSize) {
					cur = new ArrayList<IntPoint>();
				}

				try {
					pipe.put(cur);
				} catch (InterruptedException e) {
				}

			}
			return;
		}
	}

	public List<IntPoint> getPoints(int os) throws InterruptedException {
		if (os < 0) {
			throw new IllegalArgumentException();
		}
		orderSize = os;
		List<IntPoint> q = pipe.take();
		os = -1;
		return q;
	}

	public void clearCanvas() {
		synchronized (gp) {
			gp.clear();
		}
		resizeCanvas();
		repaint();
	}

	private void resizeCanvas() {
		canvas = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
		jLabel.setIcon(new ImageIcon(canvas));
	}

	public void draw(GraphicComponent go) {
		synchronized (gp) {
			gp.add(go);
		}
		synchronized (canvas) {
			go.draw(canvas.getGraphics());
			repaint();
		}
	}

	public void onResize() {
		resizeCanvas();
		synchronized (gp) {
			synchronized (canvas) {
				for (GraphicComponent go : gp) {
					go.draw(canvas.getGraphics());
				}
				repaint();
			}
		}
	}

	public PRVisualizer(boolean exitOnClose) {
		if (exitOnClose) {
			setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		}
		setBounds(100, 0, 640, 480);
		JPanel mainPanel = new JPanel();

		resizeCanvas();
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				onResize();
			}
		});

		mainPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				canvas.setRGB(e.getX(), e.getY(), 0xFFFFFF);
				repaint();
				addPoint(e.getX(), e.getY(), e.getButton() == 1);
			}
		});

		mainPanel.add(jLabel);
		add(mainPanel);

		setVisible(true);
	}
}
