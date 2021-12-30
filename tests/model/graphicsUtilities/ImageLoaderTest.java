package model.graphicsUtilities;

import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;

class ImageLoaderTest {
    @Test
    public void TestImageLoader(){
        BufferedImage bi = ImageLoader.loadRessources();
        assertNotNull(bi);
    }
}