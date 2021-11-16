package Primitives;

import java.util.Objects;

public class Ray {
    private Point3D _startingPoint;
    private Vector _direction;

    public Ray(){
        this._direction = new Vector(1,1,1).normalize();
        this._startingPoint = new Point3D();
    }
    public Ray(double xPoint, double yPoint, double zPoint,
               double xVector, double yVector, double zVector){
        this._startingPoint = new Point3D(xPoint, yPoint, zPoint);
        this._direction = new Vector(xVector, yVector, zVector).normalize();
    }
    public Ray(Ray toCopy){
        this._startingPoint = new Point3D(toCopy.getStartingPoint());
        this._direction = new Vector(toCopy.getDirection()).normalize();
    }

    public Ray(Point3D point, Vector vector){
        this._startingPoint = new Point3D(point);
        this._direction = new Vector(vector).normalize();
    }

    public Point3D getStartingPoint() { return this._startingPoint; }
    public Vector getDirection() { return this._direction; }

    public void setStartingPoint(double xPoint, double yPoint, double zPoint){
        this._startingPoint = new Point3D(xPoint, yPoint, zPoint);
    }
    public void setStartingPoint(Point3D point){
        this._startingPoint = new Point3D(point);
    }

    public void setDirection(double xVector, double yVector, double zVector){
        this._direction = new Vector(xVector, yVector, zVector).normalize();
    }
    public void setDirection(Vector vector){
        this._direction = new Vector(vector);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ray ray2 = (Ray) o;
        return _startingPoint.equals(ray2._startingPoint) && _direction.equals(ray2._direction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_startingPoint, _direction);
    }

    @Override
    public String toString() {
        return "Starting Point: "+ _startingPoint +
                "Direction: "+ _direction.normalize();
    }

}