package br.pucpr.gema.core;

public class SceneManager {
    private static GameScene activeScene;

    public static GameScene loadScene(Class<? extends GameScene> sceneClass) {
        try {
            activeScene = sceneClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return activeScene;
    }

    public static GameScene getActiveScene() {
        return activeScene;
    }
}
