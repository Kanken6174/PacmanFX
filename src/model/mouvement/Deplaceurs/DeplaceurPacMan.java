package model.mouvement.Deplaceurs;

import model.entites.Entite;
import model.mouvement.Navigateurs.Navigateur;
import model.terrain.EspaceDeJeu;

public class DeplaceurPacMan extends Deplaceur {

    public DeplaceurPacMan(EspaceDeJeu EJ, Entite aGerer, Navigateur nav) {
        super(EJ, aGerer);
    }
}
