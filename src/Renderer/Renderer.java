package Renderer;

import Elements.Light;
import Geometries.GeoPoint;
import Geometries.Geometry;
import Primitives.Point3D;
import Primitives.Ray;
import Primitives.Vector;
import Scene.Scene;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Renderer {
    private Scene _scene;
    private ImageWriter _imageWriter;
    private static final double ESP = 0.1;

    //Constructors
    public Renderer(){
        this._scene = new Scene();
        this._imageWriter = new ImageWriter("imgWriterTest", 1600, 1000, 800, 500);
    }

    public Renderer(Scene scene, ImageWriter imageWriter){
        this._scene = scene;
        this._imageWriter = imageWriter;
    }

    //Getters
    public Scene get_scene() { return _scene; }
    public ImageWriter get_imageWriter() { return _imageWriter; }

   //Setters
    public void set_scene(Scene _scene) { this._scene = _scene;}
    public void set_imageWriter(ImageWriter _imageWriter) { this._imageWriter = _imageWriter; }

    //Other Methods
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
        Color ambientLight = _scene.getAmbientLight().getIntensity(geoPoint.getPoint());
        Color emissionLight = geoPoint.getGeometry().getEmission();
        Color diffuseLight = new Color(0,0,0);
        Color specularLight = new Color(0,0,0);
        for(Light light : _scene.getLights()){
            if(! shaded(light.getL(geoPoint.getPoint()), geoPoint.getPoint(), geoPoint.getGeometry().getNormal(geoPoint.getPoint()))) {
                diffuseLight = Light.colorPlusColor(
                        diffuseLight,
                        calcDiffuseComp(
                                geoPoint.getGeometry().getMaterial().getKd(),
                                geoPoint.getGeometry().getNormal(geoPoint.getPoint()),
                                light.getL(geoPoint.getPoint()),
                                light.getIntensity(geoPoint.getPoint())));
                specularLight = Light.colorPlusColor(
                        specularLight,
                        calcSpecularComp(
                                geoPoint.getGeometry().getMaterial().getKs(),
                                new Vector(_scene.getCamera().getP0().subtract(geoPoint.getPoint())),
                                geoPoint.getGeometry().getNormal(geoPoint.getPoint()),
                                light.getL(geoPoint.getPoint()),
                                geoPoint.getGeometry().getMaterial().getShininess(),
                                light.getIntensity(geoPoint.getPoint())
                        )
                );
            }

        }
        return Light.colorPlusColor(
                Light.colorPlusColor(ambientLight, emissionLight),
                Light.colorPlusColor(diffuseLight, specularLight));
    }

    private Color calcDiffuseComp(double kd, Vector normal, Vector l, Color intensity){
        double coefficient = kd * normal.normalize().dotProduct(l.normalize());
        return Light.colorMultiplyDouble(intensity, coefficient);
    }

    private Color calcSpecularComp(double ks, Vector v, Vector normal, Vector l, double shininess, Color intensity){
        Vector r = l.subtract(normal.scale( -2 * l.dotProduct(normal)));
        double coefficient = Math.pow( v.normalize().dotProduct(r.normalize()), shininess) *  ks;
        return Light.colorMultiplyDouble(intensity, coefficient);
    }

    private boolean shaded( Vector l, Point3D point, Vector n){
        Vector epsVector = n.scale(ESP);
        Point3D newPoint = point.add(epsVector);

        Ray shadowRay = new Ray(newPoint, l);
        List<GeoPoint> intersections = getSceneRayIntersections(shadowRay);
        return !intersections.isEmpty();
    }
}