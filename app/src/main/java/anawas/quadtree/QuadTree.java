package anawas.quadtree;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

/**
 * The QuadTree. It works for a point set in a 2D plane
 * @author Andreas Wassmer
 * @version 0.1
 */
public class QuadTree {
  // Number of points a quadrant can have until its subdivided
  private int capacity;
  // The region of the 2D plane this instance covers
  private Rectangle region;
  // keeps track of the points in this tree
  private final List<Point> points;

  private boolean hasSubdivions = false;

  // The other Quadtrees after subdivison
  // +----------+------------+
  // | northwest | northeast |
  // +----------+------------+
  // | southwest | southeast |
  // +----------+------------+
  private QuadTree northwest;
  private QuadTree northeast;
  private QuadTree southwest;
  private QuadTree southeast;

  /**
   * The constructor
   * @param region The region (width, height) of the plane which the Quadtree covers
   * @param capacity The number of points a quadrant can contain before it's subdivided
   */
  public QuadTree(Rectangle region, int capacity) {
    this.region = region;
    this.capacity = capacity;
    this.points = new ArrayList<>();
  }

  public List<Point> getPoints() {
    return points;
  }

  /**
   * Adds a point to this QuadTree. It performs the following steps:
   * - Checks if point is inside region. If not, the method does nothing and returns
   * - Checks if the capacity is reached. If so, the tree is subdivided
   * @param point The point to add to the tree
   */
  public boolean addPoint(Point point) {
    if (!region.contains(point)) {
      return false;
    }

    if (points.size() < capacity) {
      points.add(point);
    } else {
      // Capacity of this quadrant has been exceeded -> subdivide it
      subdivide();
      if (northwest.addPoint(point)) {
        return true;
      } else if (northeast.addPoint(point)) {
        return true;
      } else if (southwest.addPoint(point)) {
        return true;
      } else {
        return southeast.addPoint(point);
      }
    }
    return true;
  }

  /**
   * Divides a quadrant into 4 new smaller quadrants
   */
  private void subdivide() {
    // this quadrant already has subdivisions
    if (hasSubdivions) {
      return;
    }

    int xLeft = (int)region.getX();
    int yLeft = (int)region.getY();
    int w = (int)region.getWidth();
    int h = (int)region.getHeight();

    northwest = new QuadTree(new Rectangle(xLeft, yLeft, w/2, h/2), this.capacity);
    northeast = new QuadTree(new Rectangle(xLeft+w/2, yLeft, w/2, h/2), this.capacity);
    southwest = new QuadTree(new Rectangle(xLeft, yLeft+h/2, w/2, h/2), this.capacity);
    southeast = new QuadTree(new Rectangle(xLeft+w/2, yLeft+h/2, w/2, h/2), this.capacity);
    hasSubdivions = true;
  }

  public boolean isSubdivided() {
    return hasSubdivions;
  }

  public int getCapacity() {
    return this.capacity;
  }

  public Rectangle getRegion() {
    return this.region;
  }

  public void draw(Graphics g) {
    Rectangle reg = getRegion();
    points.forEach(p -> g.fillOval(p.x, p.y, 6,6));
    g.drawRect(reg.x, reg.y, reg.width, reg.height);
    if (hasSubdivions) {
      northwest.draw(g);
      northeast.draw(g);
      southwest.draw(g);
      southeast.draw(g);
    }
  }

  @Override
  public String toString() {
    String output =  "QuadTree{" +
        "capacity=" + capacity +
        ", region=" + region +
        ", # points=" + points.size() +
        ", hasSubdivions=" + hasSubdivions;
    if (hasSubdivions) {
      output += "\n  nw=" + northwest +
          "\n  ne=" + northeast +
          "\n  sw=" + southwest +
          "\n  se=" + southeast;
    }
    output += '}';

    return output;
  }
}
