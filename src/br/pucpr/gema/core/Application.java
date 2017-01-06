package br.pucpr.gema.core;

import br.pucpr.mage.Window;

public class Application {
    public <T extends GameScene> Application(Class<T> startingSceneClass) {
        GameScene scene = SceneManager.loadScene(startingSceneClass);
        new Window(scene).show();
    }

    public <T extends GameScene> Application(Class<T> startingSceneClass, String title, int width, int height) {
        GameScene scene = SceneManager.loadScene(startingSceneClass);
        new Window(scene, title, width, height).show();
    }
}
