package model.graphics.Loaders;

import model.enums.FantomeNom;
import model.graphics.tools.ImageFlipper;
import model.graphics.tools.ImageRotate;
import org.junit.jupiter.api.Test;

import .image.WritableImage;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class ImageMasterTest {

    @Test
    public void TestImageMasterFantome(){
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
        ImageMaster im = new ImageMaster();
        assertNotNull(im);
        WritableImage freghtened = im.getSpritesheetGhostFreightened();
        assertNotNull(freghtened);
    }

    @Test
    public void TestImageMasterTiles(){
        ImageMaster im = new ImageMaster();
        assertNotNull(im);


        WritableImage straightUp = im.getTerrainTiles(3,2);
        WritableImage straightDown = ImageRotate.RotateDeg(straightUp, 180);
;        WritableImage straightBottom = ImageRotate.RotateDeg(straightDown,-90);
        WritableImage straightTop = ImageFlipper.flipVertical(straightBottom);

        WritableImage cornerRightDown = im.getTerrainTiles(2,2);
        WritableImage cornerDownLeft = ImageRotate.RotateDeg(cornerRightDown,90);
        WritableImage cornerLeftTop = ImageRotate.RotateDeg(cornerDownLeft,90);
        WritableImage cornerTopRight = ImageRotate.RotateDeg(cornerLeftTop,90);

        assertNotNull(straightTop);
        assertNotNull(cornerTopRight);
    }

}