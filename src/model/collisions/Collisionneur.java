package model.collisions;

import model.Observers.Abonne;
import model.mouvement.Position;

public class Collisionneur implements Abonne {

    public void miseAJour(){
        gererCollisions();
    }

    private void gererCollisions(){}

    public boolean isInCollision(Position P1, Position P2, Hitbox C1, Hitbox C2){
        if(C1.getSurfaceType() == -1 || C2.getSurfaceType() == -1 || !C1.CollisionEnabled || !C2.CollisionEnabled){
            return false;
        }

        int collisionStrategy = (C1.getSurfaceType()+C2.getSurfaceType());

        switch (collisionStrategy){
            case 0: //2 cercles (0+0 = 0)
                System.out.println("2 circles");
                return CheckCollision2Circles(P1,P2,C1,C2);
            case 1: //1 cercle 1 carré (0+1 = 1)
                System.out.println("1/1");
                return CheckCollisionSquareCircle(P1,P2,C1,C2);
            case 2: //2 carrés (1+1 = 2)
                System.out.println("2 squares");
                return CheckCollision2Square(P1,P2,C1,C2);
            default:
                System.out.println("Error: defaulted");
                return false;
        }
    }


    private boolean CheckCollision2Circles(Position P1, Position P2, Hitbox C1, Hitbox C2){
        double pointDistance = getAbsDistance(P1,P2);
        double collisionDistance = C1.getCollisionDistance()+C2.getCollisionDistance();
        boolean isInCollision = (pointDistance < collisionDistance);
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

        Position Pext = getClosestEdge(HS, PS, PC);
        double distance = getAbsDistance(Pext, PC);

        return (distance < HS.getCollisionDistance());
    }

    private boolean CheckCollision2Square(Position P1, Position P2, Hitbox C1, Hitbox C2){
        return(P1.getX() + C1.getCollisionDistance() >= P2.getX() &&    // r1 right edge past r2 left
                P1.getX() <= P2.getX() + C2.getCollisionDistance() &&    // r1 left edge past r2 right
                P1.getY() + C1.getCollisionDistance() >= P2.getY() &&    // r1 top edge past r2 bottom
                P1.getY() <= P2.getY() + C2.getCollisionDistance());
    }

    private double getAbsDistance(Position P1, Position P2){
        double Xdistance =  Math.abs(P1.getX() - P2.getX());
        double Ydistance = Math.abs(P1.getY() - P2.getY());
        double pointDistance = Math.sqrt( Math.pow(Xdistance,2) + Math.pow(Ydistance,2));

        return pointDistance;
    }

    //-------------------------------------INTUILISES-------------------------------------------------------------------
    private boolean OldCheckCollision2Square(Position P1, Position P2, Hitbox C1, Hitbox C2){

        Position Pext1 = getClosestEdge(C1,P1,P2);  //on prend le coin de C1 le plus proche de P2
        Position Pext2 = getClosestEdge(C2,P2,P1);  //on prend le coin de C2 le plus proche de P1
        System.out.println(Pext1.print());
        System.out.println(Pext2.print());

        double dist = getInteriorExteriorDistance(Pext1,Pext2);
        System.out.println(dist);
        // ici on a 2 points dans l'espace, mais il faut connaître l'orientation dans laquelle on doit tester leur distance pour que ça marche...
        //on a détourné la dimension Z pour conserver cette information

        return (dist < 0 || dist == -0.0);  //on inclut le zero negatif
    }
    /**
     * @param Xpos la position du carré dans l'espace (X)
     * @param Ypos la position du carré dans l'espace (X)
     * @param C la surface de collision du carré
     * @return  un tableau contenant les positions de chaque extrémité du carré
     */
    private Position[] getSquareEdges(float Xpos, float Ypos, Hitbox C){
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
            }

        Position[] pointsCarre = new Position[4];
        for(int i = 0; i < 4; i++)
            pointsCarre[i] = new Position(CotePosX[i],CotePosY[i]);

        /*on finit avec les points XY des 4 extrémités, dans cet ordre:
        pointsCarre[0] = bas    gauche  ->  [1]-----[3]
        pointsCarre[1] = haut   gauche   ->  |       |
        pointsCarre[2] = bas    droit  ->    |       |
        pointsCarre[3] = haut   droit   ->  [0]-----[2]
         */
        return pointsCarre;
    }
    private Position getClosestEdge(Hitbox HS, Position Square, Position Point){    //inutilisé
        float CheckX = Point.getX(), CheckY = Point.getY();

        Position[] edges = getSquareEdges(Square.getX(), Square.getY(), HS);    //retourne la position absolue de chaque coin du carré
        double[] distances = {0,0,0,0};
        double minDistance = -1;
        int minDistanceIndex = -1;
        for(int i = 0; i < edges.length; i++) {
            distances[i] = getAbsDistance(edges[i],Point);
            if(minDistance == -1) {
                minDistance = distances[i];
                minDistanceIndex = i;
            }
            if(minDistance > distances[i]) {
                minDistance = distances[i];
                minDistanceIndex = i;
            }
        }
        Position toReturn =  new Position(edges[minDistanceIndex].getX(),edges[minDistanceIndex].getY(), minDistanceIndex); //on détourne Z pour savoir dans quelle direction tester...
        // bof
        return toReturn; //on retourne le coin de HS (carré) le moins loin de la position point
    }
    private double getInteriorExteriorDistance(Position P1, Position P2){   //inutilisé et non-fonctionnel
        float X1 = P1.getX();
        float Y1 = P1.getY();
        float X2 = P2.getX();
        float Y2 = P2.getY();


        /*        rappel:
        pointsCarre[0] = bas    gauche  ->  [1]-----[3]
        pointsCarre[1] = haut   gauche   ->  |       |
        pointsCarre[2] = bas    droit  ->    |       |
        pointsCarre[3] = haut   droit   ->  [0]-----[2]
         */
        switch ((int)P1.getZ()) {
            case 0:
                X1 *= -1;
                Y1 *= -1;
                break;
            case 1:
                Y1 *= -1;
                break;
            case 2:
                X1 *= -1;
                break;
            case 3:
                break;
            default:
                break;
        }

        switch ((int)P2.getZ()) {
            case 0:
                X2 *= -1;
                Y2 *= -1;
                break;
            case 1:
                Y2 *= -1;
                break;
            case 2:
                X2 *= -1;
                break;
            case 3:
                break;
            default:
                break;
        }
        double D = (X1-X2)+(Y1-Y2);
        //double D = Math.sqrt( Math.pow(X1-X2,2) + Math.pow(Y1-Y2,2));

        return D;
    }

}
