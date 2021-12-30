package model.collisions;

import model.Observers.Abonne;
import model.mouvement.PositionGraphique;

public class Collisionneur implements Abonne {

    public void miseAJour(){
        gererCollisions();
    }

    private void gererCollisions(){}

    public boolean isInCollision(PositionGraphique P1, PositionGraphique P2, Hitbox C1, Hitbox C2){
        if(C1.getSurfaceType() == -1 || C2.getSurfaceType() == -1 || !C1.CollisionEnabled || !C2.CollisionEnabled){
            return false;
        }

        int collisionStrategy = (C1.getSurfaceType()+C2.getSurfaceType());

        switch (collisionStrategy){
            case 0: //2 cercles (0+0 = 0)
                return CheckCollision2Circles(P1,P2,C1,C2);
            case 1: //1 cercle 1 carré (0+1 = 1)
                System.out.println("1/1");
                return CheckCollisionSquareCircle(P1,P2,C1,C2);
            case 2: //2 carrés (1+1 = 2)
                return CheckCollision2Square(P1,P2,C1,C2);
            default:
                System.out.println("Error: defaulted");
                return false;
        }
    }


    private boolean CheckCollision2Circles(PositionGraphique P1, PositionGraphique P2, Hitbox C1, Hitbox C2){
        double pointDistance = getAbsDistance(P1,P2);
        double collisionDistance = C1.getCollisionDistance()+C2.getCollisionDistance();
        boolean isInCollision = (pointDistance < collisionDistance);
        return isInCollision;
    }

    private boolean CheckCollisionSquareCircle(PositionGraphique PSquare, PositionGraphique PCircle, Hitbox CSquare, Hitbox CCircle){
        Hitbox HC;
        Hitbox HS;
        PositionGraphique PC;
        PositionGraphique PS;
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
            System.out.println("inversé");
        }else{
            return false;   //cas d'erreur normalement impossible, les 2 sont des carrés ou les 2 sont des cercles...
        }

        double testX = PC.getX();
        double testY = PC.getY();

        if (PC.getX() < PS.getX())
            testX = PS.getX();      // test left edge
        else if (PC.getX() > PS.getX()+HS.getCollisionDistance())
            testX = PS.getX()+HS.getCollisionDistance();   // right edge

        if (PC.getY() < PS.getX())
            testY = PS.getY();      // top edge
        else if (PC.getY() > (PS.getY()+HS.getCollisionDistance()))
            testY = PS.getY()+HS.getCollisionDistance();   // bottom edge

        double distX = PC.getX()-testX;
        double distY = PC.getY()-testY;
        double distance = Math.sqrt( Math.pow(distX,2) + Math.pow(distY,2));
        boolean toReturn = (distance <= HC.getCollisionDistance());
        return toReturn;  //si la distance entre les 2 points est inférieur au rayon du cercle... collision
    }

    private boolean CheckCollision2Square(PositionGraphique P1, PositionGraphique P2, Hitbox C1, Hitbox C2){
        return(P1.getX() + C1.getCollisionDistance() >= P2.getX() &&    // r1 right edge past r2 left
                P1.getX() <= P2.getX() + C2.getCollisionDistance() &&    // r1 left edge past r2 right
                P1.getY() + C1.getCollisionDistance() >= P2.getY() &&    // r1 top edge past r2 bottom
                P1.getY() <= P2.getY() + C2.getCollisionDistance());
    }

    private double getAbsDistance(PositionGraphique P1, PositionGraphique P2){
        double Xdistance =  Math.abs(P1.getX() - P2.getX());
        double Ydistance = Math.abs(P1.getY() - P2.getY());
        double pointDistance = Math.sqrt( Math.pow(Xdistance,2) + Math.pow(Ydistance,2));

        return pointDistance;
    }

    private PositionGraphique getClosestEdge(Hitbox HS, PositionGraphique Square, PositionGraphique Point){    //inutilisé
        float CheckX = Point.getX(), CheckY = Point.getY();

        PositionGraphique[] edges = getSquareEdges(Square.getX(), Square.getY(), HS);    //retourne la position absolue de chaque coin du carré
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
        PositionGraphique toReturn =  new PositionGraphique(edges[minDistanceIndex].getX(),edges[minDistanceIndex].getY(), minDistanceIndex); //on détourne Z pour savoir dans quelle direction tester...
        // bof
        return toReturn; //on retourne le coin de HS (carré) le moins loin de la position point
    }
    private double getInteriorExteriorDistance(PositionGraphique P1, PositionGraphique P2){   //inutilisé et non-fonctionnel
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
    /**
     * @param Xpos la position du carré dans l'espace (X)
     * @param Ypos la position du carré dans l'espace (X)
     * @param C la surface de collision du carré
     * @return  un tableau contenant les positions de chaque extrémité du carré
     */
    private PositionGraphique[] getSquareEdges(float Xpos, float Ypos, Hitbox C){
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

        PositionGraphique[] pointsCarre = new PositionGraphique[4];
        for(int i = 0; i < 4; i++)
            pointsCarre[i] = new PositionGraphique(CotePosX[i],CotePosY[i]);

        /*on finit avec les points XY des 4 extrémités, dans cet ordre:
        pointsCarre[0] = bas    gauche  ->  [1]-----[3]
        pointsCarre[1] = haut   gauche   ->  |       |
        pointsCarre[2] = bas    droit  ->    |       |
        pointsCarre[3] = haut   droit   ->  [0]-----[2]
         */
        return pointsCarre;
    }

    //-------------------------------------INTUILISES-------------------------------------------------------------------
    private boolean Old_CheckCollision2Square(PositionGraphique P1, PositionGraphique P2, Hitbox C1, Hitbox C2){

        PositionGraphique Pext1 = getClosestEdge(C1,P1,P2);  //on prend le coin de C1 le plus proche de P2
        PositionGraphique Pext2 = getClosestEdge(C2,P2,P1);  //on prend le coin de C2 le plus proche de P1
        System.out.println(Pext1.print());
        System.out.println(Pext2.print());

        double dist = getInteriorExteriorDistance(Pext1,Pext2);
        System.out.println(dist);
        // ici on a 2 points dans l'espace, mais il faut connaître l'orientation dans laquelle on doit tester leur distance pour que ça marche...
        //on a détourné la dimension Z pour conserver cette information

        return (dist < 0 || dist == -0.0);  //on inclut le zero negatif
    }
    private boolean Old_CheckCollisionSquareCircle(PositionGraphique PSquare, PositionGraphique PCircle, Hitbox CSquare, Hitbox CCircle){
        Hitbox HC;
        Hitbox HS;
        PositionGraphique PC;
        PositionGraphique PS;
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
            System.out.println("inversé");
        }else{
            return false;   //cas d'erreur normalement impossible, les 2 sont des carrés ou les 2 sont des cercles...
        }

        PositionGraphique Pext = getClosestEdge(HS, PS, PC);
        double distance = getAbsDistance(Pext, PC);

        return (distance < HS.getCollisionDistance());
    }
}
