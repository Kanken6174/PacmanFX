package model.graphics;

import model.graphics.Loaders.ImageLoader;
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