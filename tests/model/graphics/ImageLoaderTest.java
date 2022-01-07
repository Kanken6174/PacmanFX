package model.graphics;

import javafx.scene.image.WritableImage;
import model.graphics.Loaders.ImageLoader;
import model.graphics.tools.ImageConverter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class ImageLoaderTest {

    @Test
    public void TestImageLoader(){
        WritableImage bi = ImageConverter.ImageToWriteableImage(ImageLoader.loadRessources());
        assertNotNull(bi);
    }
}