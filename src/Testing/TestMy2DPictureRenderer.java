package Testing;
import java.awt.Color;

import org.junit.jupiter.api.Test;

import Elements.Camera;
import Geometries.Sphere;
import Geometries.Triangle;
import Primitives.Point3D;
import Primitives.Vector;
import Renderer.ImageWriter;
import Renderer.Renderer;
import Scene.Scene;

public class TestMy2DPictureRenderer {
//    @Test
//    public void dogFootprint(){
//        Scene scene = new Scene("Test scene");
//        scene.setCamera(new Camera(Point3D.ZERO, new Vector(0, 0, 1), new Vector(0, -1, 0)));
//        scene.setScreenDistance(100) ;
//        scene.setBackground(new Color(5, 30, 56, 255));
//
//        scene.addGeometry(new Sphere(100, new Point3D(-400,-200,400), new Color(87, 87, 87, 255)));
//        scene.addGeometry(new Sphere(100, new Point3D(400,-200,400), new Color(87, 87, 87, 255)));
//        scene.addGeometry(new Sphere(120, new Point3D(-170,-400,400), new Color(87, 87, 87, 255)));
//        scene.addGeometry(new Sphere(120, new Point3D(170,-400,400), new Color(87, 87, 87, 255)));
//
//        scene.addGeometry(new Sphere(60, new Point3D(-30,50, 150), new Color(87, 87, 87, 255)));
//        scene.addGeometry(new Sphere(60, new Point3D(30,50,150), new Color(87, 87, 87, 255)));
//        scene.addGeometry(new Sphere(70, new Point3D(0,10,150), new Color(87, 87, 87, 255)));
//
//
//        scene.addGeometry(new Triangle(new Point3D(0,-40,100), new Point3D(-45,80,100), new Point3D(45,80,100), new Color(87, 87, 87, 255)));
//        scene.addGeometry(new Triangle(new Point3D(-50,-10,100), new Point3D(-65,30,100), new Point3D(55,70,100), new Color(87, 87, 87, 255)));
//        scene.addGeometry(new Triangle(new Point3D(50,-10,100), new Point3D(65,30,100), new Point3D(-55,70,100), new Color(87, 87, 87, 255)));
//
//        ImageWriter imageWriter = new ImageWriter("my2DPicture", 500, 500, 500, 500);
//        Renderer render = new Renderer(scene, imageWriter);
//
//        render.renderImage();
//    }

//    @Test
//    public void t2(){
//        Camera c = new Camera(Point3D.ZERO, new Vector(0, 0, 1), new Vector(0, -1, 0));
//        System.out.println(c.tt(500,500,0,0,100,500,500));
//        System.out.println(c.tt(500,500,250,250,100,500,500));
//        System.out.println(c.tt(500,500,500,500,100,500,500));
//        System.out.println(Point3D.ZERO.distance(new Point3D(0,70,60)));
//        System.out.println(Point3D.ZERO.distance(new Point3D(0,25,110)));
//    }

//    @Test
//    public void clouds() {
//        Scene scene = new Scene("Test scene");
//        scene.setCamera(new Camera(Point3D.ZERO, new Vector(0, 0, 1), new Vector(0, -1, 0)));
//        scene.setScreenDistance(100) ;
//        scene.setBackground(new Color(5, 30, 56, 255));
//
////        scene.addGeometry(new Sphere(300, new Point3D(350,0,1800), new Color(255, 255, 255)));
////        scene.addGeometry(new Sphere(30, new Point3D(100,0,180), new Color(255, 255, 255)));
////        scene.addGeometry(new Sphere(5000, new Point3D(17000,-10000,18000), new Color(255, 255, 255)));
//
//        scene.addGeometry(new Sphere(120, new Point3D(0,0,200), new Color(255, 70, 70, 255)));
//        scene.addGeometry(new Sphere(110, new Point3D(0,0,140), new Color(255, 143, 93, 255)));
//
//        ImageWriter imageWriter = new ImageWriter("my2DPictureClouds", 500, 500, 500, 500);
//        Renderer render = new Renderer(scene, imageWriter);
//
//        render.renderImage();
//    }
}
