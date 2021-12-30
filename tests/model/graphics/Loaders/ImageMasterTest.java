package model.graphics.Loaders;

import model.enums.FantomeNom;
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
    public void TestImageMasterTiles(){
        ImageMaster im = new ImageMaster();
        assertNotNull(im);
    }

}