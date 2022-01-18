package Primitives;

import java.util.Objects;

public class Vector {
    private Point3D _head;

    //Constructors
    public Vector() {
        this._head = new Point3D(1,1,1);
    }

    public Vector(double x, double y, double z) {
        Point3D temp = new Point3D(x, y, z);
        if (Point3D.ZERO.equals(temp)) {
            temp=Point3D.ZERO;
            throw new IllegalArgumentException("Zero vector is not allowed");
        }
        this._head = temp;
    }

    public Vector(Point3D p) {
        if (Point3D.ZERO.equals(p)) {
            p=Point3D.ZERO;
            throw new IllegalArgumentException("Zero vector is not allowed");
        }
        this._head = new Point3D(p);
    }

    public Vector(Vector other) {
        _head = new Point3D(other._head);
    }

    //Getter
    public Point3D getHead() {
        return this._head;
    }

    //Setters
    public void setHead(Point3D head){
        this._head = new Point3D(head);
    }
    public void setHead(double x, double y, double z){
        this._head = new Point3D(x,y,z);
    }

    //Other Methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector vector2 = (Vector) o;
        return this._head.equals(vector2._head);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_head);
    }

    @Override
    public String toString() {
        return "Ending Point: " + _head;
    }

    public double length(){
        return(new Point3D(0,0,0).distance(this._head));
    }

    public double lengthSquared() {
        return _head.getX()* _head.getX() + //
                _head.getY() * _head.getY() + //
                _head.getZ() * _head.getZ();
    }

    public Vector normalize(){
        Vector normalVector = new Vector();
        double length = this.length();
        normalVector.setHead(this.getHead().getX() / length,
                this.getHead().getY() / length,
                this.getHead().getZ() / length);
        return normalVector;
    }
    public Vector add(Vector other){
        return new Vector(this.getHead().add(other.getHead()));
    }
    public Vector subtract(Vector other){
        return this.getHead().subtract(other.getHead());
    }
    public Vector scale(double scalar){
        if(scalar == 0) return null;
        return (new Vector(
                this.getHead().getX() * scalar,
                this.getHead().getY() * scalar,
                this.getHead().getZ() * scalar));
    }
    public Vector crossProduct(Vector other){
        Point3D newHead = new Point3D();
        newHead.setX( this.getHead().getY() * other.getHead().getZ()
                - this.getHead().getZ() * other.getHead().getY());
        newHead.setY( this.getHead().getZ() * other.getHead().getX()
                - this.getHead().getX() * other.getHead().getZ());
        newHead.setZ( this.getHead().getX() * other.getHead().getY()
                - this.getHead().getY() * other.getHead().getX());
        return (new Vector(newHead));
    }
    public double dotProduct(Vector other) {
        return  ( this.getHead().getX() * other.getHead().getX()
                + this.getHead().getY() * other.getHead().getY()
                + this.getHead().getZ() * other.getHead().getZ());
    }

}
