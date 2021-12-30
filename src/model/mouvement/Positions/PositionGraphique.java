package model.mouvement.Positions;

public class PositionGraphique {
    private float x;
    private float y;
    private float z;

    public PositionGraphique(float x, float y, float z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public PositionGraphique(float x, float y){
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