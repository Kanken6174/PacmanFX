/**@author Yorick Geoffre
 * @brief Ce fichier contient les sources du Deplaceur d'entités pour Fantome*/

package model.mouvement.Deplaceurs;

import model.Events.EventEmitter;
import model.entites.Entite;
import model.enums.Orients;
import model.mouvement.Positions.PositionGraphique;
import model.mouvement.Positions.PositionLogique;
import model.terrain.Case;
import model.terrain.EspaceDeJeu;
import tools.OrientTools;

import java.util.ArrayList;
import java.util.Random;

/**Ce Deplaceur est spécialisé pour les fantômes*/
public class DeplaceurFantome extends Deplaceur{

    public DeplaceurFantome(EspaceDeJeu EJ, Entite aGerer, EventEmitter em) {
        super(EJ, aGerer, em);
    }

    @Override protected Case deplacement(){
        Random r = new Random();    //on prend un générateur de nombres alétatoires

        PositionLogique Posl = geree.getPositionLogique();  //quelle case dans le double tableau du terrain
        PositionGraphique Posg = geree.getPositionGraphique();  //offset graphique de -4 à 4 par rapport à la case

        Orients DirectionActuelle =  Posl.getOrient();  //l'orientation de l'entité
        Orients DirectionVoulue = Orients.values()[r.nextInt(3)];  //la direction souhaitée par l'entité (devrait être donnée par une classe IA qui n'est pas implémentée)
        Orients DirectionInverse = OrientTools.invertOrient(DirectionActuelle); //on inverse la direction actuelle pour savoir où le fantôme n'a pas le droit d'aller

        if(Posg.getx() > 4 || Posg.getx() < -4 || Posg.gety() > 4 || Posg.gety() < -4){ //si on est au bord d'une case
            ArrayList<Case> Cardinales = EJ.getCardinals(Posl); //on récupère les 4 cases autout de celle de l'entité (de sa position logique)
            Case Destination = null;    //on met la case de destination à NULL
            //Case Precedente = Cardinales.get(DirectionInverse.ordinal());   //on met la case précédente à la case dans la direction inverse de l'actuelle
            int index = 0;
            for(Case c : Cardinales){
                if(!(c == null || (c.isObstacle() && !c.isGhostHouseDoor()) /*&& index!=DirectionInverse.ordinal()*/)) {    //si ce n'est pas un obstacle, et pas la case précédente
                    Destination = c;    //on a trouvé une potentielle destination
                    //System.out.println("Ghost found valid goal at: "+index);
                    if(index == DirectionVoulue.ordinal())  //si c'est la direction voulue, on la prend et on arrête la recherche
                        break;
                }
                index++;
            }
            if(Destination == null) { //si on a pas trouvé de case de destination valide
                /*if(Precedente != null && !(Precedente.isObstacle() || !Precedente.isGhostHouseDoor())) {    //si la case précédente est valide
                    Destination = Precedente;   //on y retourne (cas où le fantôme s'est retrouvé dans un couloir impasse)
                    System.out.println("Ghost defaulted to last one");
                    }else{*/ //si il n'y a aucune issue
                    System.out.println("Ghost stuck");  //le fantome est coincé
                    return null;
                //}
            }
            Case source = EJ.getCaseOrNull(Posl);   //on demande au terrain la case actuelle qui contient le fantôme
            if(source == null)  //si ça a retourné null c'est une erreur
                return null;

            Destination.ReceiveEntity(source.passEntity(geree));    //on demande à la case actuelle de "passer" l'entité à la case de destination
            geree.setLogicX(Destination.getPositionLog().getCaseRow());   //on met à jour la position logique de l'entité
            geree.setLogicY(Destination.getPositionLog().getCaseColumn());

            switch (DirectionActuelle){ //on gère les changements sur les offsets graphiques
                case DROITE:
                    if(Posl.getCaseColumn() >= EJ.getMaxY())
                        return null;
                    Posg.setx(-4);
                    Posg.sety(0);   //on remet ces offsets à 0 pour éviter les décalages, ou à une valeur spécifique pour que la transition soit fluide
                    break;
                case GAUCHE:
                    if(Posl.getCaseColumn() <= 0)
                        return null;
                    Posg.setx(4);
                    Posg.sety(0);
                    break;
                case HAUT:
                    if(Posl.getCaseRow() >= EJ.getMaxX())
                        return null;
                    Posg.sety(-4);
                    Posg.setx(0);
                    break;
                case BAS:
                    if(Posl.getCaseRow() <= 0)
                        return null;
                    Posg.sety(4);
                    Posg.setx(0);
                    break;
                default:
                    break;
            }
            return Destination;
        }else{  //si on n'a pas encore attein le bord d'une case
            switch (DirectionVoulue){
                case DROITE:
                    Posg.setx(Posg.getx()+1);
                    break;
                case GAUCHE:
                    Posg.setx(Posg.getx()-1);
                    break;
                case HAUT:
                    Posg.sety(Posg.gety()+1);
                    break;
                case BAS:
                    Posg.sety(Posg.gety()-1);
                    break;
                default:
                    break;
            }

        }
        return null;
    }

    @Override
    protected void resolveEntityStates(Case locale) {

    }

    /**Implémentation de la méthode requise par l'interface Abonne, appellé depuis une boucle de jeu*/
    @Override public void doAction(){
        deplacerEntite();
    }
}
