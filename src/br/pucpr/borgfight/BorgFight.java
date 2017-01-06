package br.pucpr.borgfight;

import br.pucpr.borgfight.scene.ShipScene;
import br.pucpr.gema.core.Application;
import br.pucpr.gema.core.GameScene;

public class BorgFight <T extends GameScene> extends Application {

    public BorgFight(Class<T> startingSceneClass) {
        super(startingSceneClass);
    }

    public static void main(String[] args) {
        new Application(ShipScene.class, "Borg Fight", 1366, 768);
    }
}
