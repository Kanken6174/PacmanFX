package model.graphics;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import views.Loaders.ImageLoader;
import tools.ImageConverter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static testUtilities.jfxInititer.initInternalGraphics;

class ImageLoaderTest {

    @Test
    public void TestImageLoader(){
        initInternalGraphics();
        Image im = ImageLoader.loadRessources();
        WritableImage bi = ImageConverter.ImageToWriteableImage(im);
        assertNotNull(bi);
    }
}