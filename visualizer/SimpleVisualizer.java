package visualizer;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class SimpleVisualizer extends JFrame {

	private static final long serialVersionUID = 1L;
	private BufferedImage canvas = new BufferedImage(42, 23, BufferedImage.TYPE_INT_RGB);
	private JLabel jLabel = new JLabel();
	private ArrayList<GraphicComponent> gp = new ArrayList<GraphicComponent>();

	public void clearCanvas() {
		resizeCanvas();
		synchronized (gp) {
			gp.clear();
		}
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
		setTitle("Resize: " + getHeight() + "x" + getWidth());
		synchronized (gp) {
			synchronized (canvas) {
				for (GraphicComponent go : gp) {
					go.draw(canvas.getGraphics());
				}
				repaint();
			}
		}
	}

	public SimpleVisualizer(boolean exitOnClose) {
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

		mainPanel.add(jLabel);
		add(mainPanel);

		setVisible(true);
	}
}
