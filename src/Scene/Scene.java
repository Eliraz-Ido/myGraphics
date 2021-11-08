package Scene;

import Elements.Camera;
import Geometries.Geometry;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Scene {
    String _name;
    List<Geometry> _geometries;
    Camera _camera;

    public Scene(){
        this._name = "";
        this._geometries = new ArrayList<Geometry>();
        this._camera = new Camera();
    }
    public Scene(String name){
        this._name = name;
        this._geometries = new ArrayList<Geometry>();
        this._camera = new Camera();
    }
    public Scene(String name, Geometry g){
        this._name = name;
        this._geometries = new ArrayList<Geometry>();
        this._geometries.add(g);
        this._camera = new Camera();
    }
    public Scene(String name, Geometry g, Camera camera){
        this._name = name;
        this._geometries = new ArrayList<Geometry>();
        this._geometries.add(g);
        this.setCamera(camera);
    }

    public String getName() { return this._name; }
    public List<Geometry> getGeometries() { return this._geometries; }
    public Camera getCamera() { return  this._camera; }
    public void setName(String name) {this._name = name; }
    public void setGeometries(ArrayList<Geometry> g){ this._geometries = g; }
    public void setCamera(Camera camera) { this._camera = camera; }

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
                return copy.size() == this.getGeometries().size();
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(_name, _geometries);
    }

    public void addGeometry(Geometry toAdd){
        for(Geometry g : this._geometries){
            if(toAdd.equals(g)){
                System.out.println("already exists");
            }
            else{
                _geometries.add(g);
            }
        }

    }

    @Override
    public String toString() {
        return "Scene: " +
                "name:" + _name + '\'' +
                "Geometries: " + _geometries;
    }
}
