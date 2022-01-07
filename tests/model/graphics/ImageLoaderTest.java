package model.graphics;

import model.graphics.Loaders.ImageLoader;
import org.junit.jupiter.api.Test;

import .image.WritableImage;

import static org.junit.jupiter.api.Assertions.*;

class ImageLoaderTest {

    @Test
    public void TestImageLoader(){
        WritableImage bi = ImageLoader.loadRessources();
        assertNotNull(bi);
    }
}