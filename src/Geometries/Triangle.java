package Geometries;
import Primitives.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Triangle extends Geometry{
    private Point3D _p1;
    private Point3D _p2;
    private Point3D _p3;

    //Constructors
    public Triangle(){
        this._p1 = new Point3D();
        this._p2 = new Point3D();
        this._p3 = new Point3D();
    }

    public Triangle(double x1, double y1, double z1,
                    double x2, double y2, double z2,
                    double x3, double y3, double z3){
        this._p1 = new Point3D(x1,y1,z1);
        this._p2 = new Point3D(x2,y2,z2);
        this._p3 = new Point3D(x3,y3,z3);
    }

    public Triangle(Triangle toCopy){
        this._material = new Material(toCopy._material);
        this._emission = toCopy._emission;
        this._p1 = new Point3D(toCopy._p1);
        this._p2 = new Point3D(toCopy._p2);
        this._p3 = new Point3D(toCopy._p3);
    }

    public Triangle(Point3D p1,Point3D p2, Point3D p3){
        this._p1 = new Point3D(p1);
        this._p2 = new Point3D(p2);
        this._p3 = new Point3D(p3);
    }

    public Triangle(Point3D p1, Point3D p2, Point3D p3, Color color) {
        this._p1 = p1;
        this._p2 = p2;
        this._p3 = p3;
        this._emission = color;
    }

    //Getters
    public Point3D getP1() { return _p1; }
    public Point3D getP2() { return _p2; }
    public Point3D getP3() { return _p3; }

    //Setters
    public void setP1(double x1, double y1, double z1) {
        this._p1 = new Point3D(x1,y1,z1);
    }
    public void setP2(double x2, double y2, double z2) { this._p2 = new Point3D(x2,y2,z2); }
    public void setP3(double x3, double y3, double z3) {
        this._p3 = new Point3D(x3,y3,z3);
    }

    //Other Methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Triangle triangle2 = (Triangle) o;
        return  (this._p1.equals(triangle2._p1) && this._p2.equals(triangle2._p2) && this._p3.equals(triangle2._p3) ||
                 this._p1.equals(triangle2._p1) && this._p2.equals(triangle2._p3) && this._p3.equals(triangle2._p2) ||
                 this._p1.equals(triangle2._p2) && this._p2.equals(triangle2._p1) && this._p3.equals(triangle2._p3) ||
                 this._p1.equals(triangle2._p2) && this._p2.equals(triangle2._p3) && this._p3.equals(triangle2._p1) ||
                 this._p1.equals(triangle2._p3) && this._p2.equals(triangle2._p1) && this._p3.equals(triangle2._p2) ||
                 this._p1.equals(triangle2._p3) && this._p2.equals(triangle2._p2) && this._p3.equals(triangle2._p1))
                && this._material.equals(triangle2._material);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_p1, _p2, _p3);
    }

    @Override
    public String toString() {
        return  "P1: " + _p1 +
                "P2: " + _p2 +
                "P3: " + _p3 +
                "Material: " + _material;
    }



    @Override
    public List<GeoPoint> findIntersections(Ray ray) {
        List<GeoPoint> intersections = new ArrayList<>();
        Plane p = new Plane(this._p1,this._p2,this._p3);
        if(p.findIntersections(ray) != null) {
            Vector v1 = _p1.subtract(ray.getStartingPoint());
            Vector v2 = _p2.subtract(ray.getStartingPoint());
            Vector v3 = _p3.subtract(ray.getStartingPoint());

            Vector n1 = v1.crossProduct(v2).normalize();
            Vector n2 = v2.crossProduct(v3).normalize();
            Vector n3 = v3.crossProduct(v1).normalize();

            double sign1 = Math.signum(n1.dotProduct(ray.getDirection()));
            double sigh2 = Math.signum(n2.dotProduct(ray.getDirection()));
            double sigh3 = Math.signum(n3.dotProduct(ray.getDirection()));

            if (sign1 == 0.0 || sigh2 == 0.0 || sigh3 == 0.0) {
                return null;
            }
            if (sign1 == sigh2 && sign1 == sigh3) {
                if (!p.getPointInPlane().equals(ray.getStartingPoint())) {
                    double numerator = p.getVerticalToPlane().dotProduct(p.getPointInPlane().subtract(ray.getStartingPoint()));
                    double denominator = p.getVerticalToPlane().dotProduct(ray.getDirection());
                    if (denominator == 0) {       // The Ray is contained or parallel to the plane.
                        return null;
                    } else if (0 < numerator / denominator) {
                        Point3D point = ray.getStartingPoint().add(ray.getDirection().scale(numerator / denominator));
                        intersections.add(new GeoPoint(this, point));
                        return intersections;
                    }
                }
            }
        }
        return null;
    }

    @Override
    public Vector getNormal(Point3D point) {
        Plane p = new Plane(this._p1,this._p2,this._p3);
        return p.getNormal(point);
    }
}
