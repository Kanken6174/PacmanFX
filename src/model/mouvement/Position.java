package model.mouvement;

public class Position {
    public float x;
    public float y;
    public float z;

    public Position(float x, float y, float z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Position(float x, float y){
        this.x = x;
        this.y = y;
        this.z = 0;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    public String print(){
        String toReturn = "X: " + Float.toString(x);
        toReturn = toReturn + " Y : "+Float.toString(y)+ " Z : " + Float.toString(z);
        return toReturn;
    }
}
