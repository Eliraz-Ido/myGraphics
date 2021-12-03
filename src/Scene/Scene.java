package Scene;

import Elements.Camera;
import Geometries.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Scene {
    String _name;
    List<Geometry> _geometries;
    Camera _camera;
    private double _screenDistance;
    private Color _background;

    public Scene(){
        this._name = "";
        this._geometries = new ArrayList<Geometry>();
        this._camera = new Camera();
        this._screenDistance = 100;
        this._background = new Color(0,211,255);
    }
    public Scene(String name){
        this._name = name;
        this._geometries = new ArrayList<Geometry>();
        this._camera = new Camera();
        this._screenDistance = 100;
        this._background = new Color(0,211,255);
    }
    public Scene(String name, Geometry g){
        this._name = name;
        this._geometries = new ArrayList<Geometry>();
        this._geometries.add(g);
        this._camera = new Camera();
        this._screenDistance = 100;
        this._background = new Color(0,211,255);
    }
    public Scene(String name, Geometry g, Camera camera){
        this._name = name;
        this._geometries = new ArrayList<Geometry>();
        this._geometries.add(g);
        this.setCamera(camera);
        this._screenDistance = 100;
        this._background = new Color(0,211,255);
    }

    public String getName() { return this._name; }
    public void setName(String name) {this._name = name; }
    public List<Geometry> getGeometries() { return this._geometries; }
    public void setGeometries(ArrayList<Geometry> g){ this._geometries = g; }
    public Camera getCamera() { return  this._camera; }
    public void setCamera(Camera camera) { this._camera = camera; }
    public Color getBackground() { return this._background; }
    public void setBackground(Color c) { this._background = c; }
    public double getScreenDistance() { return this._screenDistance; }
    public void setScreenDistance(double distance) { this._screenDistance = distance; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Scene scene2 = (Scene) o;
        if (this.getGeometries().size() != scene2.getGeometries().size())
            return false;
        else {
            List<Geometry> copy = new ArrayList<>(this.getGeometries());
            if (copy.retainAll(scene2.getGeometries())) {
                return copy.size() == this.getGeometries().size()
                        && this._background == scene2.getBackground() &&
                        this._screenDistance == scene2.getScreenDistance();
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(_name, _geometries);
    }

    public void addGeometry(Geometry object){
         this._geometries.add(object);
    }



    @Override
    public String toString() {
        return "Scene: " +
                "name:" + _name + '\'' +
                "Geometries: " + _geometries + '\'' +
                "Screen Distance: " + _screenDistance + '\'' +
                "Background Color: " + _background
        ;
    }
}
