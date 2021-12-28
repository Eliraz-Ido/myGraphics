package Scene;

import Elements.AmbientLight;
import Elements.Camera;
import Elements.Light;
import Geometries.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Scene {
    private String _name;
    private List<Geometry> _geometries;
    private Camera _camera;
    private double _screenDistance;
    private Color _background;
    private AmbientLight _ambientLight;
    private List<Light> _lights;

    //Constructors
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
        this._lights = new ArrayList<Light>();
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

    //Getters
    public String getName() { return this._name; }
    public List<Geometry> getGeometries() { return this._geometries; }
    public Camera getCamera() { return  this._camera; }
    public Color getBackground() { return this._background; }
    public double getScreenDistance() { return this._screenDistance; }
    public AmbientLight getAmbientLight() { return _ambientLight; }
    public List<Light> getLights() { return _lights; }

    //Setters
    public void setName(String name) {this._name = name; }
    public void setGeometries(ArrayList<Geometry> g){ this._geometries = g; }
    public void setCamera(Camera camera) { this._camera = camera; }
    public void setBackground(Color c) { this._background = c; }
    public void setScreenDistance(double distance) { this._screenDistance = distance; }
    public void setAmbientLight(AmbientLight _ambientLight) { this._ambientLight = _ambientLight; }
    public void setLights(List<Light> _lights) { this._lights = _lights; }

    //Other Methods
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
    public void addLight(Light light) { this._lights.add(light); }
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
