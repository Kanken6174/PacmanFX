package views.Loaders;

import javafx.scene.image.WritableImage;
import model.enums.FantomeNom;
import org.junit.jupiter.api.Test;
import tools.ImageRotate;
import views.viewClasses.Loaders.ImageMaster;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static testUtilities.jfxInititer.initInternalGraphics;

class ImageMasterTest {

    @Test
    public void TestImageMasterFantome(){
        initInternalGraphics();
        ImageMaster im = new ImageMaster();
        assertNotNull(im);
        WritableImage blinkySpritesheet = im.getSpritesheetForGhost(FantomeNom.BLINKY); //fantome rouge
        assertNotNull(blinkySpritesheet);

        WritableImage inkySpritesheet = im.getSpritesheetForGhost(FantomeNom.INKY); //fantome rouge
        assertNotNull(inkySpritesheet);

        WritableImage pinkySpritesheet = im.getSpritesheetForGhost(FantomeNom.PINKY); //fantome rouge
        assertNotNull(pinkySpritesheet);

        WritableImage clydeSpritesheet = im.getSpritesheetForGhost(FantomeNom.CLYDE); //fantome rouge
        assertNotNull(clydeSpritesheet);


    }

    @Test
    public void TestImageMasterGhostFreightened(){
        initInternalGraphics();
        ImageMaster im = new ImageMaster();
        assertNotNull(im);
        WritableImage freghtened = im.getSpritesheetGhostFreightened();
        assertNotNull(freghtened);
    }

    @Test
    public void TestImageMasterTiles(){
        initInternalGraphics();
        ImageMaster im = new ImageMaster();
        assertNotNull(im);


        WritableImage straightUp = im.getTerrainTiles(3,2);
        WritableImage straightDown = ImageRotate.RotateDegx90(straightUp, 180);
;        WritableImage straightBottom = ImageRotate.RotateDegx90(straightDown,-90);
        WritableImage straightTop = ImageRotate.RotateDegx90(straightBottom,90);

        WritableImage cornerRightDown = im.getTerrainTiles(2,2);
        WritableImage cornerDownLeft = ImageRotate.RotateDegx90(cornerRightDown,90);
        WritableImage cornerLeftTop = ImageRotate.RotateDegx90(cornerDownLeft,90);
        WritableImage cornerTopRight = ImageRotate.RotateDegx90(cornerLeftTop,90);

        assertNotNull(straightTop);
        assertNotNull(cornerTopRight);
    }

}