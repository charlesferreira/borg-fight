package br.pucpr.gema.core;

import br.pucpr.mage.Window;

public class Application {

    public Application() {
    }

    public <T extends GameScene> Application(Class<T> startingSceneClass) {
        SceneManager.setStartingScene(startingSceneClass);
        new Window(SceneManager.getInstance()).show();
    }

    public <T extends GameScene> Application(Class<T> startingSceneClass, String title, int width, int height) {
        SceneManager.setStartingScene(startingSceneClass);
        new Window(SceneManager.getInstance(), title, width, height).show();
    }
}
