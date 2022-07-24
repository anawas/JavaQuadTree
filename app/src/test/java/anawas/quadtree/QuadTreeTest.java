package anawas.quadtree;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class QuadTreeTest {
  QuadTree qtree;
  @BeforeEach
  void setUp() {
    Rectangle region = new Rectangle(0,0,200,200);
    qtree = new QuadTree(region, 4);
  }

  @Test
  void testCapacity() {
    assertEquals(4, qtree.getCapacity());
  }

  @Test
  void getPoints() {
    Point p = new Point(10,10);
    qtree.addPoint(new Point(10,10));
    List<Point> pts = qtree.getPoints();
    assertEquals(pts.get(0), p);
  }

  @Test
  void addPointAccepted() {
    // point inside region --> added to tree
    Point p = new Point(10,10);
    qtree.addPoint(p);
    List<Point> pts = qtree.getPoints();
    assertEquals(1, pts.size());
  }

  @Test
  void addPointRejected() {
    // point outside region --> must not be added
    Point p = new Point(210,210);
    qtree.addPoint(p);
    List<Point> pts = qtree.getPoints();
    assertEquals(0, pts.size());

    qtree.addPoint(new Point(210,10));
    pts = qtree.getPoints();
    assertEquals(0, pts.size());
  }

  @Test
  void subdivide() {
    Random random = new Random();
    for (int i = 0; i <= 5; i++) {
      qtree.addPoint(new Point(random.nextInt(200), random.nextInt(200)));
    }
    assertTrue(qtree.isSubdivided());
  }
}