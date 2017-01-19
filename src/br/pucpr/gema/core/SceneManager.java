package br.pucpr.gema.core;

import br.pucpr.mage.Scene;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_LINE;
import static org.lwjgl.opengl.GL11.glClearColor;

public class SceneManager implements Scene {
    private static GameScene activeScene;

    private static SceneManager instance = new SceneManager();
    private Class<? extends GameScene> startingScene;

    public static SceneManager getInstance() {
        return instance;
    }

    public static <T extends GameScene> T loadScene(Class<T> sceneClass) {
        try {
            activeScene = sceneClass.newInstance();
            activeScene.init();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return (T) activeScene;
    }

    public static GameScene getActiveScene() {
        return activeScene;
    }

    public static <T extends GameScene> void setStartingScene(Class<T> startingScene) {
        instance.startingScene = startingScene;
    }

    @Override
    public void init() {
        glEnable(GL_BLEND);
        glBlendFunc (GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        glEnable(GL_DEPTH_TEST);
        glEnable(GL_CULL_FACE);
        glDepthFunc(GL_LEQUAL);
        glPolygonMode(GL_FRONT_FACE, GL_LINE);
        glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

        loadScene(startingScene);
    }

    @Override
    public void update(float secs) {
        activeScene.update(secs);
    }

    @Override
    public void draw() {
        activeScene.draw();
    }

    @Override
    public void deinit() {
        activeScene.deinit();
    }
}
