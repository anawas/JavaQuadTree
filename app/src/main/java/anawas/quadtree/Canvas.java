package anawas.quadtree;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.Stroke;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Canvas extends JFrame {

  class Painter extends JComponent {
    QuadTree qtree;

    public Painter(QuadTree qtree) {
      this.qtree = qtree;
    }

    @Override
    public void paint(Graphics g) {
      super.paint(g);
      Graphics2D g2d = (Graphics2D)getGraphics();
      g2d.setPaint(Color.BLACK);
      qtree.draw(g);
    }
  }
  public Canvas() {
    super("Window");
  }

  public void drawQuadtree(QuadTree qtree) {
    this.getContentPane().add(new Painter(qtree));
  }
}
