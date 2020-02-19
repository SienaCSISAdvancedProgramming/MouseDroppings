import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
   Mouse Droppings.  Disgusting.  Our mouse, as it moves, will leave some
   little red circles on the screen.  When we leave the window, it clears.

   Demonstration of mouse event handlers and a paintComponent method
   that uses a list of objects that get redrawn as needed.

   @author Jim Teresco
   @version Spring 2020
*/

public class MouseDroppings extends MouseAdapter implements Runnable {

    public static final int SIZE = 10;
    public static final Color droppingColor = Color.RED;
    private ArrayList<Point> droppings = new ArrayList<>();
    private JPanel panel;
    
    /**
       The run method to set up the graphical user interface
    */
    @Override
    public void run() {
	
	// set up the GUI "look and feel" which should match
	// the OS on which we are running
	JFrame.setDefaultLookAndFeelDecorated(true);
	
	// create a JFrame in which we will build our very
	// tiny GUI, and give the window a name
	JFrame frame = new JFrame("MouseDroppings");
	frame.setPreferredSize(new Dimension(500,500));
	
	// tell the JFrame that when someone closes the
	// window, the application should terminate
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	// JPanel with a paintComponent method
	panel = new JPanel() {
		@Override
		public void paintComponent(Graphics g) {
		    
		    // first, we should call the paintComponent method we are
		    // overriding in JPanel
		    super.paintComponent(g);

		    // draw all of the ovals in the list
		    g.setColor(droppingColor);
		    for (Point p : droppings) {
			g.fillOval(p.x-SIZE/2, p.y-SIZE/2, SIZE, SIZE);
		    }
		    
		}
	    };
	frame.add(panel);
	panel.addMouseListener(this);
	panel.addMouseMotionListener(this);
	
	// display the window we've created
	frame.pack();
	frame.setVisible(true);
    }

    @Override
    public void mouseMoved(MouseEvent e) {

	droppings.add(e.getPoint());
	panel.repaint();
    }
    
    @Override
    public void mouseExited(MouseEvent e) {

	droppings.clear();
	panel.repaint();
    }
    
    public static void main(String args[]) {

	// The main method is responsible for creating a thread (more
	// about those later) that will construct and show the graphical
	// user interface.
	javax.swing.SwingUtilities.invokeLater(new MouseDroppings());
    }
}
   
