package Geometries;
import Primitives.Point3D;
import Primitives.Ray;
import Primitives.Vector;

import java.awt.*;
import java.util.List;
import java.util.Objects;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

public class Plane extends Geometry{
    private Point3D _pointInPlane;
    private Vector _verticalToPlane;

    //Constructors
    public Plane(){
        this._pointInPlane = new Point3D();
        this._verticalToPlane = new Vector().normalize();
    }

    public Plane(double xPoint, double yPoint, double zPoint,
                 double xVector, double yVector, double zVector){
        this._pointInPlane = new Point3D(xPoint, yPoint, zPoint);
        this._verticalToPlane = (new Vector(xVector, yVector, zVector)).normalize();
    }

    public Plane(Plane toCopy){
        this._pointInPlane = new Point3D(toCopy._pointInPlane);
        this._verticalToPlane = (new Vector(toCopy._verticalToPlane)).normalize();
    }

    public Plane(Point3D p1, Point3D p2, Point3D p3) {
        Vector v1 = p2.subtract(p1);
        Vector v2 = p3.subtract(p1);
        this._verticalToPlane = v1.crossProduct(v2).normalize();
        this._pointInPlane = new Point3D(p1);
    }

    public Plane(Point3D p1, Point3D p2, Point3D p3, Color color) {
        Vector v1 = p2.subtract(p1);
        Vector v2 = p3.subtract(p1);
        this._verticalToPlane = v1.crossProduct(v2).normalize();
        this._pointInPlane = new Point3D(p1);
        this._emission = color;
    }

    public Plane(Point3D p, Vector n) {
        this._verticalToPlane = n.normalize();
        this._pointInPlane = new Point3D(p);
    }

    public Plane(Point3D p, Vector n, Color color) {
        this._verticalToPlane = n.normalize();
        this._pointInPlane = new Point3D(p);
        this._emission = color;
    }

    //Getters
    public Point3D getPointInPlane() { return _pointInPlane; }
    public Vector getVerticalToPlane() { return _verticalToPlane; }

    //Setters
    public void setVerticalToPlane(double xVector, double yVector, double zVector) {
        this._verticalToPlane = new Vector(xVector, yVector, zVector).normalize();
    }

    public void setStartingPoint(double xPoint, double yPoint, double zPoint) {
        this._pointInPlane = new Point3D(xPoint, yPoint, zPoint);
    }

    //Other Methods
    @Override
    public boolean equals(Object o) {          // To compare 2 planes we need to 1. see if the 2 vertices to the planes are equals. Because we normalize every vector we can use equals method.
        if (this == o) return true;            //2. Determine whether a point is in the other plane.  if the vertices are equals but the point is not in the other plane => the planes are parallel.
        if (o == null || getClass() != o.getClass()) return false;
        Plane plane2 = (Plane) o;
        return  this.isPointInPlane(plane2._pointInPlane) &&
                Objects.equals(_verticalToPlane, plane2._verticalToPlane) &&
                this._material.equals(plane2._material);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_pointInPlane, _verticalToPlane);
    }

    @Override
    public String toString() {
        return  "Point In Plane: " + _pointInPlane +
                "Vertical To Plane: " + _verticalToPlane;
    }

    private boolean isPointInPlane(Point3D otherPoint){               // Calculating plane equation and determining whether a point is in that plane.
        Point3D normal = this.getVerticalToPlane().getHead();        // normal * (x,y,z) + d = 0.  where normal is the vector vertical to plane.
        double correctD = -(normal.getX() * this.getPointInPlane().getX() +
                            normal.getY() * this.getPointInPlane().getY() +     // Calculating the correct d for the plane.
                            normal.getZ() * this.getPointInPlane().getZ());
        double otherD = -(normal.getX() * otherPoint.getX() +
                          normal.getY() * otherPoint.getY() +   // Calculating the d for the plane with otherPoint .
                          normal.getZ() * otherPoint.getZ());

        return correctD - otherD < 0.005 && otherD - correctD < 0.005;
    }


    @Override
    public List<GeoPoint> findIntersections(Ray ray) {
//        List<GeoPoint> intersections = new ArrayList<>();
//        if (!this._pointInPlane.equals(ray.getStartingPoint())) {
//            double numerator = this._verticalToPlane.dotProduct(this._pointInPlane.subtract(ray.getStartingPoint()));
//            double denominator = this._verticalToPlane.dotProduct(ray.getDirection());
//            if (denominator == 0 ) {       // The Ray is contained or parallel to the plane.
//                return null;
//            } else if ( 0 < numerator / denominator) {
//                Point3D p = ray.getStartingPoint().add(ray.getDirection().scale(numerator / denominator));
//                intersections.add(new GeoPoint(this,p));
//                return intersections;
//            }
//        }
//        return null;

        Vector p0Q;
           try {
               p0Q = _pointInPlane.subtract(ray.getStartingPoint());
           } catch (IllegalArgumentException e) {
               return null; // ray starts from point Q - no intersections
           }

           double nv = _verticalToPlane.dotProduct(ray.getDirection());
           if (isZero(nv)) // ray is parallel to the plane - no intersections
               return null;

           double t = alignZero(_verticalToPlane.dotProduct(p0Q) / nv);
           return t <= 0  ? null : primitives.Util.listOf(new GeoPoint(this, new Point3D(ray.getStartingPoint().add(ray.getDirection().scale(t)))));
    }

    @Override
    public Vector getNormal(Point3D point) {
        return this._verticalToPlane.normalize();
    }
}
