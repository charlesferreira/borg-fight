package br.pucpr.borgfight.game;

import br.pucpr.borgfight.scene.SkyboxScene;
import br.pucpr.gema.core.Application;
import br.pucpr.gema.core.GameScene;

public class BorgFight extends Application {

    public BorgFight(Class<? extends GameScene> startingSceneClass) {
        super(startingSceneClass);
    }

    public static void main(String[] args) {
        new Application(SkyboxScene.class);
    }
}
