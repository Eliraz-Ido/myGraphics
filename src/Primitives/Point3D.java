package Primitives;
import Geometries.GeoPoint;

import java.lang.Math;
import java.util.Objects;

public class Point3D{
    private Coordinate _x;
    private Coordinate _y;
    private Coordinate _z;
    public static final Point3D ZERO = new Point3D(0,0,0);

    public Point3D(){
        this.setX(0);
        this.setY(0);
        this.setZ(0);
    }
    public Point3D(double x, double y, double z){
        this.setX(x);
        this.setY(y);
        this.setZ(z);
     }
    public Point3D(Point3D toCopy){
        this._x = new Coordinate(toCopy.getX());
        this._y = new Coordinate(toCopy.getY());
        this._z = new Coordinate(toCopy.getZ());
    }


    public double getX() { return _x.getCoordinate(); }
    public double getY() { return _y.getCoordinate(); }
    public double getZ() { return _z.getCoordinate(); }

    public void setX(double x) { this._x = new Coordinate(x); }
    public void setY(double y) { this._y = new Coordinate(y); }
    public void setZ(double z) { this._z = new Coordinate(z); }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point3D point2 = (Point3D) o;
        return _x.equals(point2._x) && _y.equals(point2._y) && _z.equals(point2._z);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_x, _y, _z);
    }

    @Override
    public String toString() {
        return "(" + this._x + "," + this._y + "," + this._z + ")";
    }

    public Point3D add(Point3D other){
        return new Point3D(
                this.getX() + other.getX(),
                this.getY() + other.getY(),
                this.getZ() + other.getZ());
    }
    public Point3D add(Vector v){
        return this.add(v.getHead());
    }

    public Vector subtract(Point3D other){
        return new Vector(
                this.getX() - other.getX(),
                this.getY() - other.getY(),
                this.getZ() - other.getZ());
    }

    public double distance(Point3D other){
        return Math.sqrt
                ( Math.pow(this.getX() + other.getX(), 2)
                        + Math.pow(this.getY() + other.getY(), 2)
                        + Math.pow(this.getZ() + other.getZ(), 2));
    }

    public double distance(GeoPoint point) {
        return this.distance(point.getPoint());
    }
}
