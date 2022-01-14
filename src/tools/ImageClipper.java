package tools;


import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;

public class ImageClipper {
    public static WritableImage clipSprite(Image source, int x, int y){
        PixelReader px = source.getPixelReader();
        WritableImage wi = new WritableImage(px,x,y,16,16);
        return wi;
    }

    public static WritableImage clip(Image source, int x, int y, int w, int h){
        PixelReader px = source.getPixelReader();
        WritableImage wi = new WritableImage(px,x,y,w,h);
        return wi;
    }

    public static WritableImage clipGhostAnimation(Image source, int id){
        PixelReader px = source.getPixelReader();
        WritableImage wi = new WritableImage(px,441,49+(16*id),14,14*8);
        return wi;
    }

    public static WritableImage getFrame(Image source, int id){
        PixelReader px = source.getPixelReader();
        WritableImage wi = new WritableImage(px,id,0,(int)source.getHeight(),(int)source.getHeight());
        return wi;
    }

    public static WritableImage getFrame(Image source, int id, int sideSize){
        PixelReader px = source.getPixelReader();
        WritableImage wi = new WritableImage(px,id*sideSize,0,sideSize,sideSize);
        return wi;
    }
}