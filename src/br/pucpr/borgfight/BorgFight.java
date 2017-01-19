package br.pucpr.borgfight;

import br.pucpr.borgfight.scene.TitleScene;
import br.pucpr.gema.core.Application;

public class BorgFight extends Application {

    public static void main(String[] args) {
        new Application(TitleScene.class, "Borg Fight", 1280, 720);
    }
}
