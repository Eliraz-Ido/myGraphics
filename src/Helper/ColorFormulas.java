package Helper;

import java.awt.*;

public class ColorFormulas {
    public static Color colorMultiplyDouble(Color c, double scale) {
        if (scale <= 0 )
            return new Color(0,0,0);

        return new Color(
                (int) (Math.min(c.getRed() * scale, 255)),
                (int) (Math.min(c.getGreen() * scale, 255)),
                (int) (Math.min(c.getBlue() * scale, 255))
        );
    }

    public static Color addingColors(Color ... colors) {
        Color returnedColor = new Color(0,0,0);
        for(Color c : colors) {
            returnedColor = new Color(
                    Math.min(returnedColor.getRed() + c.getRed(), 255),
                    Math.min(returnedColor.getGreen() + c.getGreen(), 255),
                    Math.min(returnedColor.getBlue() + c.getBlue(), 255)
            );
        }
        return returnedColor;
    }
}
