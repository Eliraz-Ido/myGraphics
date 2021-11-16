package Primitives;

import java.util.Objects;

public class Vector {
    private Point3D _head;

    public Vector() {
        this._head = new Point3D(1,1,1);
    }
    public Vector(double x, double y, double z) {
        this._head = new Point3D(x, y, z);
        if (Point3D.ZERO.equals(_head))
            throw new IllegalArgumentException("Zero vector is not allowed");
    }
    public Vector(Point3D p) {
        if (Point3D.ZERO.equals(p))
            throw new IllegalArgumentException("Zero vector is not allowed");
        this._head = new Point3D(p);
    }

    public Vector(Vector other) {
        _head = new Point3D(other._head);
    }

    public Point3D getHead() {
        return this._head;
    }
    public void setHead(Point3D head){
        this._head = new Point3D(head);
    }
    public void setHead(double x, double y, double z){
        this._head = new Point3D(x,y,z);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector vector2 = (Vector) o;
        return this.normalize()._head.equals(vector2.normalize()._head);
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
    public double dotProduct(Vector other){
        return  ( this.getHead().getX() * other.getHead().getX()
                + this.getHead().getY() * other.getHead().getY()
                + this.getHead().getZ() * other.getHead().getZ());
    }

}
