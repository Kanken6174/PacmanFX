package model.mouvement.Deplaceurs;

import model.entites.Entite;
import model.entites.PacmanObject;
import model.terrain.EspaceDeJeu;

public class DeplaceurPacMan extends Deplaceur {

    public DeplaceurPacMan(EspaceDeJeu EJ, Entite aGerer) {
        super(EJ, aGerer);
    }

    public DeplaceurPacMan(PacmanObject pacman, EspaceDeJeu ej) {
        super(ej, pacman);
    }
}
