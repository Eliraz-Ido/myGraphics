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

    public Point3D get_head() {
        return this._head;
    }
    public void set_head(Point3D head){
        this._head = new Point3D(head);
    }
    public void set_head(double x, double y, double z){
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
        normalVector.set_head(this.get_head().getX() / length,
                this.get_head().getY() / length,
                this.get_head().getZ() / length);
        return normalVector;
    }
    public Vector add(Vector other){
        return new Vector(this.get_head().add(other.get_head()));
    }
    public Vector subtract(Vector other){
        return this.get_head().subtract(other.get_head());
    }
    public Vector scale(double scalar){
        return (new Vector(
                this.get_head().getX() * scalar,
                this.get_head().getY() * scalar,
                this.get_head().getZ() * scalar));
    }
    public Vector crossProduct(Vector other){
        Point3D newHead = new Point3D();
        newHead.setX( this.get_head().getY() * other.get_head().getZ()
                - this.get_head().getZ() * other.get_head().getY());
        newHead.setY( this.get_head().getZ() * other.get_head().getX()
                - this.get_head().getX() * other.get_head().getZ());
        newHead.setZ( this.get_head().getX() * other.get_head().getY()
                - this.get_head().getY() * other.get_head().getX());
        return (new Vector(newHead));
    }
    public double dotProduct(Vector other){
        return  ( this.get_head().getX() * other.get_head().getX()
                + this.get_head().getY() * other.get_head().getY()
                + this.get_head().getZ() * other.get_head().getZ());
    }

}
