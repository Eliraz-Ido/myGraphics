package Geometries;
import Primitives.Point3D;
import Primitives.Vector;

import java.util.Objects;

public class Plane extends Geometry{
    private Point3D _pointInPlane;
    private Vector _verticalToPlane;

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
        Vector v1 = p1.subtract(p2);
        Vector v2 = p1.subtract(p3);
        this._verticalToPlane = v1.crossProduct(v2).normalize();
        this._pointInPlane = new Point3D(p1);
    }

    public Plane(Point3D p, Vector n) {
        this._verticalToPlane = n.normalize();
        this._pointInPlane = new Point3D(p);
    }

    public Point3D getPointInPlane() { return _pointInPlane; }
    public Vector getVerticalToPlane() { return _verticalToPlane; }

    public void setVerticalToPlane(double xVector, double yVector, double zVector) {
        this._verticalToPlane = new Vector(xVector, yVector, zVector);
    }

    public void setStartingPoint(double xPoint, double yPoint, double zPoint) {
        this._pointInPlane = new Point3D(xPoint, yPoint, zPoint);
    }

    @Override
    public boolean equals(Object o) {          // To compare 2 planes we need to 1. see if the 2 vertices to the planes are equals. Because we normalize every vector we can use equals method.
        if (this == o) return true;            //2. Determine whether a point is in the other plane.  if the vertices are equals but the point is not in the other plane => the planes are parallel.
        if (o == null || getClass() != o.getClass()) return false;
        Plane plane2 = (Plane) o;
        return this.isPointInPlane(plane2._pointInPlane) && Objects.equals(_verticalToPlane, plane2._verticalToPlane);
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
        Point3D normal = this.getVerticalToPlane().get_head();        // normal * (x,y,z) + d = 0.  where normal is the vector vertical to plane.
        double correctD = -(normal.getX() * this.getPointInPlane().getX() +
                            normal.getY() * this.getPointInPlane().getY() +     // Calculating the correct d for the plane.
                            normal.getZ() * this.getPointInPlane().getZ());
        double otherD = -(normal.getX() * otherPoint.getX() +
                          normal.getY() * otherPoint.getY() +     // Calculating the d for the plane with otherPoint .
                          normal.getZ() * otherPoint.getZ());

        return correctD == otherD;
    }

}
