package Testing;

import java.awt.Color;
import org.junit.jupiter.api.Test;
import Renderer.ImageWriter;

class testImageWriter {

    @Test
    public void test() {
        ImageWriter imageWriter = new ImageWriter("imgWriterTest", 1600, 1000, 800, 500);
        int nX = imageWriter.getNx();
        int nY = imageWriter.getNy();

        for (int i = 0; i < nY; ++i) {
            for (int j = 0; j < nX; ++j)
                imageWriter.writePixel(j, i, new Color(255, 144, 242));
        }
        //For the left part of E
        for(int i = 100; i < 400; i++){
            for( int j = 80; j < 100; j ++ )
                imageWriter.writePixel(j, i, new Color(0, 0, 0));
        }
        //For the 3 horizontal lines of E
        for(int k = 0; k < 3; k++) {
            for (int i = k * 150 + 100; i < k * 150 + 110; i++) {
                for (int j = 80; j < 160; j++)
                    imageWriter.writePixel(j, i, new Color(0, 0, 0));
            }
        }
        //For the letter l
        for(int i = 60; i < 410; i++){
            for( int j = 180; j < 200; j ++ )
                imageWriter.writePixel(j, i, new Color(0, 0, 0));
        }
        //For the vertical part of i
        for (int i = 150; i < 410; i++) {
            for (int j = 220; j <  240; j++)
                imageWriter.writePixel(j, i, new Color(0, 0, 0));
        }
        //For the circle of i
        for(int i = 110; i < 130; i++){
            for( int j = 220; j < 240; j ++ )
                imageWriter.writePixel(j, i, new Color(0, 0, 0));
        }
        //For the horizontal parts of r and a
        for(int h = 0; h < 2; h++) {
            for (int k = 0; k < 2; k++) {
                for (int i = k * 150 + 100; i < k * 150 + 110; i++) {
                    for (int j = h * 100 + 260; j < h * 100 +  340; j++)
                        imageWriter.writePixel(j, i, new Color(0, 0, 0));
                }
            }
        }
        //For the diagonal line in r
        for(int i = 260; i < 410; i++){
            for(int j = 290; j < 340; j++){
                if( 1200 <= 5 * j - i &&  5 * j - i <= 1270){
                    imageWriter.writePixel(j, i, new Color(0, 0, 0));
                }
            }
        }
//        for(int k = 0; k < 150; k++) {
//            for (int i = 260; i < 261 + k; i+=4 ) {
//                for(int j = 300 + k; j < 301 + k ; j+=20 )           // stripes
//                    imageWriter.writePixel(j, i, new Color(255, 0, 0));
//            }
//        }
        //For the left side of r
        for (int i = 100; i < 410; i++) {
                for (int j = 260; j < 280; j++)
                    imageWriter.writePixel(j, i, new Color(0, 0, 0));
        }
        //For the right side of r
        for (int i = 100; i < 250; i++) {
            for (int j = 320; j < 340; j++)
                imageWriter.writePixel(j, i, new Color(0, 0, 0));
        }
        //For the vertical parts of a
        for(int k = 0; k < 2; k++ ) {
            for (int i = 100; i < 410; i++) {
                for (int j = k * 60 + 360; j < k * 60 + 380; j++)
                    imageWriter.writePixel(j, i, new Color(0, 0, 0));
            }
        }
        //For the horizontal parts of z
        for(int k = 0; k < 2; k++ ){
            for(int i = k * 300 + 100; i < k * 300 + 110; i++){
                for(int j = 460; j < 540; j++){
                    imageWriter.writePixel(j, i, new Color(0, 0, 0));
                }
            }
        }
        //FOt the diagonal part of z
        for(int i = 100; i < 400; i++){
            for(int j = 460; j < 540; j++){
                if(2470 <= i + 4.5 * j && i + 4.5 * j <= 2540 )
                imageWriter.writePixel(j, i, new Color(0, 0, 0));
            }
        }
        imageWriter.writeToImage();
    }
}