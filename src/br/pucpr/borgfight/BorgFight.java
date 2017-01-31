package br.pucpr.borgfight;

import br.pucpr.borgfight.scene.MainScene;
import br.pucpr.gema.core.Application;

public class BorgFight extends Application {

    public static void main(String[] args) {
        new Application(MainScene.class, "Borg Fight", 1280, 720);
    }
}
