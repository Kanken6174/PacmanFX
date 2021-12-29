package model.terrain;

public class EspaceDeJeu {
    private Case tiles[][];

    public EspaceDeJeu(){

    }

    public void LoadStage(String StageName){
        tiles = new Case[10][28];   //taille pacman original
        tiles = collisionLoader.loadCollisions(StageName, 10, 28);
        tiles = entityLoader.loadEntities(StageName, tiles,10, 28);
    }
}
