package anawas.quadtree;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.Stroke;
import javax.swing.JFrame;

public class Canvas extends JFrame {

  public Canvas() {
    super("Window");
  }

  @Override
  public void paint(Graphics g) {
  }

  public void drawQuadtree(QuadTree qtree) {
    Graphics g = getGraphics();
    super.paint(g);
    Graphics2D g2d = (Graphics2D)getGraphics();
    g2d.setPaint(Color.BLACK);
    qtree.draw(g);
  }
}
