package Geometries;
import Primitives.Ray;

import java.util.Objects;

public class Cylinder extends Geometry{
    private double _radius;
    private double _height;
    private Ray _axis;

    public Cylinder(){
        this._radius = 0;
        this._height = 0;
        this._axis = new Ray();
    }
    public Cylinder(double radius, double height,
                    double xPoint, double yPoint, double zPoint,
                    double xVector, double yVector, double zVector) {
        this._radius = radius;
        this._height = height;
        this._axis = new Ray(xPoint, yPoint, zPoint, xVector, yVector, zVector);
    }
    public Cylinder(Cylinder toCopy){
        this._radius = toCopy._radius;
        this._height = toCopy._height;
        this._axis = new Ray(toCopy._axis);
    }

    public double getRadius() { return _radius; }
    public double getHeight() { return _height; }
    public Ray getAxis() { return _axis; }

    public void setAxis(double xPoint, double yPoint, double zPoint,
                        double xVector, double yVector, double zVector) {
        this._axis = new Ray(xPoint, yPoint, zPoint, xVector, yVector, zVector);
    }
    public void setHeight(double _height) { this._height = _height; }
    public void setRadius(double _radius) { this._radius = _radius; }

    public boolean equals(Cylinder cylinder2){
        return this._axis.equals(cylinder2._axis) &&
                this._height == cylinder2._height &&
                this._radius == cylinder2._radius;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cylinder cylinder = (Cylinder) o;
        return Double.compare(cylinder._radius, _radius) == 0 && Double.compare(cylinder._height, _height) == 0 && _axis.equals(cylinder._axis);
    }


    @Override
    public int hashCode() {
        return Objects.hash(_radius, _height, _axis);
    }

    @Override
    public String toString() {
        return  "radius: " + _radius +
                ", height:" + _height +
                ", axis:" + _axis;
    }
}
