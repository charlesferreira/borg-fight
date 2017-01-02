package br.pucpr.gema.core;

public class SceneManager {
    private static GameScene activeScene;

    public static <T extends GameScene> T loadScene(Class<T> sceneClass) {
        try {
            activeScene = sceneClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return (T) activeScene;
    }

    public static GameScene getActiveScene() {
        return activeScene;
    }
}
