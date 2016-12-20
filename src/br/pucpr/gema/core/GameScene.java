package br.pucpr.gema.core;

import br.pucpr.gema.core.objects.Camera;
import br.pucpr.gema.core.objects.EmptyObject;
import br.pucpr.mage.Keyboard;
import br.pucpr.mage.Scene;
import br.pucpr.mage.phong.DirectionalLight;
import org.joml.Matrix4f;
import org.joml.Vector3f;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public abstract class GameScene implements Scene {
    public final Camera camera;
    public final DirectionalLight light;
    private GameObject sceneGraph = new EmptyObject();

    protected GameScene() {
        camera = new Camera();
        camera.transform.translate(0, 0, -5);
        light = (new DirectionalLight(
                new Vector3f(0f, 1.0f, -1.0f),      // direction
                new Vector3f(0.8f, 0.8f, 0.8f),     // ambient
                new Vector3f(0.5f, 0.5f, 0.5f),     // diffuse
                new Vector3f(1f, 1f, 1f)));         // specular
    }

    @Override
    public void init() {
        glEnable(GL_DEPTH_TEST);
        glEnable(GL_CULL_FACE);
        glPolygonMode(GL_FRONT_FACE, GL_LINE);
        glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

        onSceneLoad();
    }

    @Override
    public final void update(float secs) {
        if (Keyboard.getInstance().isPressed(GLFW_KEY_ESCAPE)) {
            glfwSetWindowShouldClose(glfwGetCurrentContext(), GLFW_TRUE);
            return;
        }

        Time.deltaTime = secs;
        Time.time += secs;

        sceneGraph.update();
        onUpdate();
    }

    protected void onUpdate() {
    }

    @Override
    public final void draw() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        sceneGraph.draw(new Matrix4f());
    }

    @Override
    public final void deinit() {
        onSceneUnload();
    }

    protected GameObject addChild(GameObject child) {
        return sceneGraph.addChild(child);
    }

    protected abstract void onSceneLoad();

    private void onSceneUnload() {
    }
}
