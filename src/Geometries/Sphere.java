package Geometries;
import Primitives.Point3D;
import Primitives.Ray;
import Primitives.Vector;

import java.awt.*;
import java.util.List;
import java.util.Objects;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

public class Sphere extends Geometry{
    private Point3D _center;
    private double _radius;

    //Constructors
    public Sphere(){
        this._center = new Point3D();
        this._radius = 2;
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

    public Sphere(double radius, Point3D center, Color color) {
        this._radius = radius;
        this._center = new Point3D(center);
        this._emission = color;
    }

    //Getters
    public double getRadius() { return _radius; }
    public Point3D getCenter() { return _center; }

    //Setters
    public void setCenter(double xPoint, double yPoint, double zPoint) {
        this._center = new Point3D(xPoint, yPoint, zPoint);
    }
    public void setRadius(double _radius) { this._radius = _radius; }

    //Other Methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sphere sphere2 = (Sphere) o;
        return  Double.compare(sphere2._radius, _radius) == 0 &&
                Objects.equals(_center, sphere2._center) &&
                this._material.equals(sphere2._material);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_center, _radius);
    }

    @Override
    public String toString() {
        return "Center: "+ _center +
                "Radius: " + _radius +
                "Material: " + _material;
    }


    @Override
    public List<GeoPoint> findIntersections(Ray ray) {
        Point3D p0 = ray.getStartingPoint();
        Vector v = ray.getDirection();
        Vector u;
        u = _center.subtract(p0);

        double tm = v.dotProduct(u);
        double dSquared = isZero(tm) ? u.lengthSquared() : u.lengthSquared() - tm * tm;

        double thSquared = alignZero(_radius * _radius - dSquared);
        if (thSquared <= 0) return null;
        double th = Math.sqrt(thSquared);
        if (isZero(th)) return null;

        double t1 = alignZero(tm + th);
        if (t1 <= 0) return null; // t1 is behind the head since th must be positive (sqrt), t2 must be non-positive as t1

        double t2 = alignZero(tm - th);

		// if both t1 and t2 are positive
		if (t2 > 0)
				return primitives.Util.listOf(new GeoPoint(this,new Point3D(p0).add(v.scale(t1))),
                        new GeoPoint(this, new Point3D(p0).add(v.scale(t2))));

		else // t2 is behind the head
			return primitives.Util.listOf(new GeoPoint(this,new Point3D(p0).add(v.scale(t1))));
    }
        @Override
    public Vector getNormal(Point3D point) {
        return point.subtract(this._center);
    }
}
