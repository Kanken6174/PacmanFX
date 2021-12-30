package model.graphics.Loaders;

import model.enums.FantomeNom;
import model.graphics.tools.ImageFlipper;
import model.graphics.tools.ImageRotate;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class ImageMasterTest {

    @Test
    public void TestImageMasterFantome(){
        ImageMaster im = new ImageMaster();
        assertNotNull(im);
        BufferedImage blinkySpritesheet = im.getSpritesheetForGhost(FantomeNom.BLINKY); //fantome rouge
        assertNotNull(blinkySpritesheet);

        BufferedImage inkySpritesheet = im.getSpritesheetForGhost(FantomeNom.INKY); //fantome rouge
        assertNotNull(inkySpritesheet);

        BufferedImage pinkySpritesheet = im.getSpritesheetForGhost(FantomeNom.PINKY); //fantome rouge
        assertNotNull(pinkySpritesheet);

        BufferedImage clydeSpritesheet = im.getSpritesheetForGhost(FantomeNom.CLYDE); //fantome rouge
        assertNotNull(clydeSpritesheet);


    }

    @Test
    public void TestImageMasterGhostFreightened(){
        ImageMaster im = new ImageMaster();
        assertNotNull(im);
        BufferedImage freghtened = im.getSpritesheetGhostFreightened();
        assertNotNull(freghtened);
    }

    @Test
    public void TestImageMasterTiles(){
        ImageMaster im = new ImageMaster();
        assertNotNull(im);


        BufferedImage straightUp = im.getTerrainTiles(2,3);
        BufferedImage straightDown = ImageFlipper.flipVertical(straightUp);
        BufferedImage straightBottom = ImageRotate.RotateDeg(straightDown,-90);
        BufferedImage straightTop = ImageFlipper.flipHorizontal(straightBottom);

        BufferedImage cornerRightDown = im.getTerrainTiles(2,2);
        BufferedImage cornerDownLeft = ImageRotate.RotateDeg(cornerRightDown,90);
        BufferedImage cornerLeftTop = ImageRotate.RotateDeg(cornerDownLeft,90);
        BufferedImage cornerTopRight = ImageRotate.RotateDeg(cornerLeftTop,90);

        assertNotNull(straightTop);
        assertNotNull(cornerTopRight);
    }

}