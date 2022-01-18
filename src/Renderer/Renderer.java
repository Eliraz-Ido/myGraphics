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

import static Helper.ColorFormulas.colorMultiplyDouble;
import static Helper.ColorFormulas.addingColors;

public class Renderer {
    private Scene _scene;
    private ImageWriter _imageWriter;
    private static final double ESP = 0.2;
    final int recursiveLevel = 3;

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
        for(int i = 0; i < this._imageWriter.getNy(); i++) {
            for (int j = 0; j < this._imageWriter.getNx(); j++) {
                Ray ray = this._scene.getCamera().constructRayThroughPixel(
                        _imageWriter.getNx(), _imageWriter.getNy(), j, i,
                        _scene.getScreenDistance(), _imageWriter.getWidth(),
                        _imageWriter.getHeight());
                List<GeoPoint> intersectionPoints =
                        getSceneRayIntersections(ray);
                if (intersectionPoints.isEmpty())
                    _imageWriter.writePixel(j, i, _scene.getBackground());
                else {
                    GeoPoint closestPoint = getClosestPoint(intersectionPoints, _scene.getCamera().getP0());
                    _imageWriter.writePixel(j, i, calcColor(closestPoint));
                }
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

    private GeoPoint getClosestPoint(List<GeoPoint> intersectionPoints, Point3D startPoint){
        double distance = Double.MAX_VALUE;
        Point3D p0 = startPoint;
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
        return calcColor(geoPoint, new Ray(_scene.getCamera().getP0(), geoPoint.getPoint().subtract(_scene.getCamera().getP0())), recursiveLevel);
    }
    private Color calcColor(GeoPoint geoPoint,Ray ray, int level ){
        if(level == 0 ) return new Color(0,0,0);

        Point3D point = geoPoint.getPoint();
        Geometry geometry = geoPoint.getGeometry();
        Color ambientLight = _scene.getAmbientLight().getIntensity(point);
        Color emissionLight = geometry.getEmission();
        Color diffuseLight = new Color(0,0,0);
        Color specularLight = new Color(0,0,0);
        Color reflectedLight =  new Color(0,0,0);
        Color refractedLight =  new Color(0,0,0);
        for(Light light : _scene.getLights()) {
            if(! shaded(light.getL(point), point, geometry.getNormal(point))) {
                diffuseLight = addingColors(
                        diffuseLight,
                        calcDiffuseComp(
                                geometry.getMaterial().getKd(),
                                geometry.getNormal(point),
                                light.getL(point),
                                light.getIntensity(point)));
                specularLight = addingColors(
                        specularLight,
                        calcSpecularComp(
                                geometry.getMaterial().getKs(),
                                new Vector(_scene.getCamera().getP0().subtract(point)),
                                ray.getDirection(),
//                                geometry.getNormal(point),
                                light.getL(point),
                                geometry.getMaterial().getShininess(),
                                light.getIntensity(point)
                        )
                );

                reflectedLight = colorMultiplyDouble(
                        calcReflect(ray, point, geometry.getNormal(point), level),
                        geometry.getMaterial().getKr());
                refractedLight = colorMultiplyDouble(
                        calcRefract(ray, point, geometry.getNormal(point), level),
                        geometry.getMaterial().getKt());
            }
        }

        return  addingColors(ambientLight, emissionLight, diffuseLight, specularLight, reflectedLight, refractedLight);
    }

    private Color calcDiffuseComp(double kd, Vector normal, Vector l, Color intensity){
        double coefficient = kd * normal.normalize().dotProduct(l.normalize());
        return colorMultiplyDouble(intensity, coefficient);
    }

    private Color calcSpecularComp(double ks, Vector v, Vector normal, Vector l, double shininess, Color intensity){
        Vector r = l.normalize().subtract(normal.normalize().scale( -2 * l.normalize().dotProduct(normal.normalize())));
        double coefficient = Math.pow( v.normalize().dotProduct(r.normalize()), shininess) *  ks;
        return colorMultiplyDouble(intensity, coefficient);
    }

    private boolean shaded( Vector l, Point3D point, Vector n){
        Vector epsVector = n.scale(ESP);
        Point3D newPoint = point.add(epsVector);

        Ray shadowRay = new Ray(newPoint, l.scale(1).normalize());
        List<GeoPoint> intersections = getSceneRayIntersections(shadowRay);
        return !intersections.isEmpty();
    }

    private Color calcReflect(Ray inRay, Point3D point, Vector n, int level) {
        Vector v = inRay.getDirection().normalize();
        Vector r = new Vector(v.normalize().subtract(n.normalize().scale(-2 * v.normalize().dotProduct(n.normalize()))));
        Ray reflectedRay = new Ray(point, r.normalize());

        List<GeoPoint> reflectedIntersectionPoints = getSceneRayIntersections(reflectedRay);

        if (!reflectedIntersectionPoints.isEmpty()) {
            GeoPoint closestPoint = getClosestPoint(reflectedIntersectionPoints, point);
            return calcColor(closestPoint, reflectedRay, level - 1);
        }
        return new Color(0, 0, 0);
    }

    private Color calcRefract(Ray inRay, Point3D point, Vector n, int level){
        Ray refractedRay = inRay;
        List<GeoPoint> refractedIntersectionPoints = getSceneRayIntersections(refractedRay);

        if(!refractedIntersectionPoints.isEmpty()){
            GeoPoint closestPoint = getClosestPoint(refractedIntersectionPoints, point);
            return calcColor(closestPoint,refractedRay, level - 1);
        }else{
            return new Color(0,0,0);
        }
    }

}