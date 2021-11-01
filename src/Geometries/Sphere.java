package Geometries;
import Primitives.Point3D;

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

}
