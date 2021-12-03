package Renderer;

import Geometries.GeoPoint;
import Geometries.Geometry;
import Primitives.Point3D;
import Primitives.Ray;
import Scene.Scene;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Renderer {
    private Scene _scene;
    private ImageWriter _imageWriter;

    public Renderer(){
        this._scene = new Scene();
        this._imageWriter = new ImageWriter("imgWriterTest", 1600, 1000, 800, 500);
    }
    public Renderer(Scene scene, ImageWriter imageWriter){
        this._scene = scene;
        this._imageWriter = imageWriter;
    }

    public Scene get_scene() { return _scene; }
    public void set_scene(Scene _scene) { this._scene = _scene;}
    public ImageWriter get_imageWriter() { return _imageWriter; }
    public void set_imageWriter(ImageWriter _imageWriter) { this._imageWriter = _imageWriter; }

    public void renderImage() {
        for(int i = 0; i < this._imageWriter.getNy(); i++)
            for (int j = 0; j < this._imageWriter.getNx(); j++){
                Ray ray = this._scene.getCamera().constructRayThroughPixel(
                        _imageWriter.getNx(), _imageWriter.getNy(), j , i,
                        _scene.getScreenDistance(), _imageWriter.getWidth(),
                        _imageWriter.getHeight());
                List<GeoPoint> intersectionPoints =
                        getSceneRayIntersections(ray);
                if (intersectionPoints.isEmpty())
                    _imageWriter.writePixel(j,i, _scene.getBackground());
                else{
                    GeoPoint closestPoint = getClosestPoint(intersectionPoints);
                    _imageWriter.writePixel(j,i, calcColor(closestPoint));
                }
            }
        _imageWriter.writeToImage();
    }

    private List<GeoPoint> getSceneRayIntersections(Ray ray){
        List<GeoPoint> intersectionPoints = new ArrayList<>();
        for(Geometry g : _scene.getGeometries()){
            List<GeoPoint> geometryIntersectionPoints = g.findIntersections(ray);
            if(geometryIntersectionPoints != null)
                intersectionPoints.addAll(geometryIntersectionPoints);
        }
        return intersectionPoints;
    }

    private GeoPoint getClosestPoint(List<GeoPoint> intersectionPoints){
        double distance = Double.MAX_VALUE;
        Point3D p0 = _scene.getCamera().getP0();
        GeoPoint minDistancePoint = null;
        for(GeoPoint point : intersectionPoints){
            if(p0.distance(point) < distance){
                minDistancePoint = new GeoPoint(point);
                distance = p0.distance(point);
            }
        }
        return minDistancePoint;
    }

    private Color calcColor(GeoPoint geoPoint){
        return geoPoint.getGeometry().getEmission();
    }
}
