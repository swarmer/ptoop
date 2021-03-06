package lab2.shapes.polygons;


import lab2.drawers.Drawer;
import lab2.drawers.EquilateralPolygonDrawer;
import lab2.shapes.Segment;
import lab2.shapes.Shape;
import lab2.shapes.exceptions.InvalidArgumentException;
import lab2.shapes.util.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class EquilateralPolygon implements Shape {
    private Point center;
    private int edgeCount;
    private double radius;

    public EquilateralPolygon(Point center, int edgeCount, double radius) throws InvalidArgumentException {
        this.center = center;
        this.edgeCount = edgeCount;
        this.radius = radius;

        if (edgeCount < 3)
            throw new InvalidArgumentException("edgeCount must be 3 or more");

        if (radius <= 0)
            throw new InvalidArgumentException("radius must be positive");
    }

    public Point getCenter() {
        return center;
    }

    public int getEdgeCount() {
        return edgeCount;
    }

    public double getRadius() {
        return radius;
    }

    public List<Segment> getSegments() {
        List<Point> points = new ArrayList<>();

        final double angleDelta = 2 * Math.PI / edgeCount;
        double angle = 0.0;

        for (int i = 0; i < edgeCount; ++i, angle += angleDelta) {
            double x = center.getX() + Math.cos(angle) * radius;
            double y = center.getY() - Math.sin(angle) * radius;
            points.add(new Point(x, y));
        }

        List<Segment> segments = new ArrayList<>();
        for (int i = 0; i < points.size(); ++i) {
            Point currentPoint = points.get(i);
            Point prevPoint = i == 0 ? points.get(points.size() - 1) : points.get(i - 1);
            segments.add(new Segment(prevPoint, currentPoint));
        }

        return segments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EquilateralPolygon that = (EquilateralPolygon) o;
        return edgeCount == that.edgeCount &&
                Double.compare(that.radius, radius) == 0 &&
                Objects.equals(center, that.center);
    }

    @Override
    public int hashCode() {
        return Objects.hash(center, edgeCount, radius);
    }

    @Override
    public String toString() {
        return "EquilateralPolygonDrawer{" +
                "center=" + center +
                ", edgeCount=" + edgeCount +
                ", radius=" + radius +
                '}';
    }

    @Override
    public Drawer getDrawer() {
        return new EquilateralPolygonDrawer(this);
    }
}
