package model.collisions;

import javafx.geometry.Pos;
import model.Observers.Abonne;
import model.mouvement.Position;

public class Collisionneur implements Abonne {

    public void miseAJour(){
        gererCollisions();
    }

    private void gererCollisions(){}

    public boolean isInCollision(Position P1, Position P2, Hitbox C1, Hitbox C2){
        if(C1.getSurfaceType() == -1 || C2.getSurfaceType() == -1 || !C1.CollisionEnabled || !C2.CollisionEnabled)
            return false;

        int collisionStrategy = (C1.getSurfaceType()+C2.getSurfaceType());

        switch (collisionStrategy){
            case 0: //2 cercles (0+0 = 0)
                return CheckCollision2Circles(P1,P2,C1,C2);
            case 1: //1 cercle 1 carré (0+1 = 1)
                return CheckCollisionSquareCircle(P1,P2,C1,C2);
            case 2: //2 carrés (1+1 = 2)
                return CheckCollision2Square(P1,P2,C1,C2);
            default:
                return false;
        }
    }

    private boolean CheckCollision2Circles(Position P1, Position P2, Hitbox C1, Hitbox C2){
        float Xdistance =  Math.abs(P1.getX() - P2.getX());
        float Ydistance = Math.abs(P1.getY() - P2.getY());
        float collisionDistance = C1.getCollisionDistance() + C2.getCollisionDistance();
        boolean isInCollision = (Xdistance < collisionDistance || Ydistance < collisionDistance);
        return isInCollision;
    }

    private boolean CheckCollisionSquareCircle(Position PSquare, Position PCircle, Hitbox CSquare, Hitbox CCircle){
        Hitbox HC;
        Hitbox HS;
        Position PC;
        Position PS;
        if(CSquare instanceof HitboxSquare && CCircle instanceof HitboxCircle){
            PC = PCircle;
            PS = PSquare;
            HC = CCircle;
            HS = CSquare;
        }else if(CSquare instanceof HitboxCircle && CCircle instanceof HitboxSquare){//cas où on a inversé les 2 formes par accident
            PC = PSquare;
            PS = PCircle;
            HC = CSquare;
            HS = CCircle;
        }else{
            return false;   //cas d'erreur normalement impossible, les 2 sont des carrés ou les 2 sont des cercles...
        }

        return false;
    }

    private boolean CheckCollision2Square(Position P1, Position P2, Hitbox C1, Hitbox C2){
        float[][][] extremitesCarres = {getSquareExtremities(P1.getX(), P1.getY(), C1),getSquareExtremities(P2.getX(), P2.getY(), C2)};
        float[][] distanceCotes = {{0,0,0,0},{0,0,0,0}};
        for(int cote = 0; cote < 4; cote ++){
            distanceCotes[cote][0] = extremitesCarres[0][cote][0] - extremitesCarres[1][cote][0];
            distanceCotes[cote][1] = extremitesCarres[0][cote][1] - extremitesCarres[1][cote][1];
        }

        return false;
    }

    /**
     *
     * @param Xpos la position du carré dans l'espace (X)
     * @param Ypos la position du carré dans l'espace (X)
     * @param C la surface de collision du carré
     * @return  un double tableau de floats contenant les positions X et Y de chaque extrémité du carré
     */
    private float[][] getSquareExtremities(float Xpos, float Ypos, Hitbox C){
        int index = 0;

        float CotePosX[] = {0,0,0,0};
        float CotePosY[] = {0,0,0,0};

        for(int xSign =-1; xSign<2; xSign+=2)   //va de -1 à 1
            for(int ySign=-1; ySign<2; ySign+=2) {    //va de -1 à 1
                CotePosX[index] = Xpos + (C.getCollisionDistance()/2)*xSign;//on prend la moitié de la longeur d'un côté et on l'ajoute, ça met le point sur le milieu d'un
                //côté du carré.
                CotePosY[index] = Ypos + (C.getCollisionDistance()/2)*ySign;//Ensuite, on ajoute la moitié de la longueur à la hauteur du point. A la fin ce point est à
                //l'extrémité du carré (4 extrémités du coup)
                index++;
            }//on finit avec les points XY des 4 extrémités.

        float pointsCarre[][] = {CotePosX,CotePosY};//le chemin effectué par les valeurs ressemble à un H, avec ordre bas gauche, haut gauche, bas droit, haut droit
        return pointsCarre;
    }
}
