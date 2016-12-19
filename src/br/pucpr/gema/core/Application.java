package br.pucpr.gema.core;

import br.pucpr.mage.Window;

public class Application {
    public Application(Class<? extends GameScene> startingSceneClass) {
        GameScene scene = SceneManager.loadScene(startingSceneClass);
        new Window(scene).show();
    }
}
