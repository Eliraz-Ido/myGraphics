package Geometries;
import Primitives.Point3D;
import Primitives.Ray;
import Primitives.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Sphere extends Geometry{
    private Point3D _center;
    private double _radius;

    public Sphere(){
        this._center = new Point3D();
        this._radius = 0;
    }
    public Sphere(double xPoint, double yPoint, double zPoint, double radius){
        this._center = new Point3D(xPoint, yPoint, zPoint);
        this._radius = radius;
    }
    public Sphere(Sphere toCopy){
        this._center = new Point3D(toCopy._center);
        this._radius = toCopy._radius;
    }

    public Sphere(double radius, Point3D center) {
        this._center = center;
        this._radius = radius;
    }

    public double getRadius() { return _radius; }
    public Point3D getCenter() { return _center; }

    public void setCenter(double xPoint, double yPoint, double zPoint) {
        this._center = new Point3D(xPoint, yPoint, zPoint);
    }
    public void setRadius(double _radius) { this._radius = _radius; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sphere sphere2 = (Sphere) o;
        return Double.compare(sphere2._radius, _radius) == 0 && Objects.equals(_center, sphere2._center);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_center, _radius);
    }

    @Override
    public String toString() {
        return "Center: "+ _center +
                "Radius: " + _radius;
    }


    @Override
    public List<Point3D> findIntersections(Ray ray) {
        List<Point3D> list = new ArrayList<>();
        Vector u = this._center.subtract(ray.getStartingPoint());
        double tm = ray.getDirection().dotProduct(u);
        double d = Math.sqrt( Math.pow(u.length(), 2) - Math.pow(tm, 2) );
        if (d <= this._radius) {
            double th = Math.sqrt( Math.pow(this._radius, 2) - Math.pow(d, 2) );
            double t1 = tm - th;
            double t2 = tm + th;
            if (0 < t1 || 0 < t2) {
                if (0 < t1)
                    list.add(new Point3D( ray.getStartingPoint().add( ray.getDirection().scale(t1) )) );
                if (0 < t2)
                    list.add(new Point3D( ray.getStartingPoint().add( ray.getDirection().scale(t2) )) );
                return list;
            }
        }
        return null;
    }
        @Override
    public Vector getNormal(Point3D point) {
        return point.subtract(this._center);
    }
}
