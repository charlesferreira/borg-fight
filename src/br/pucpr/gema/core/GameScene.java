package br.pucpr.gema.core;

import br.pucpr.gema.core.objects.Camera;
import br.pucpr.mage.Keyboard;
import br.pucpr.mage.Scene;
import br.pucpr.mage.phong.DirectionalLight;
import org.joml.Matrix4f;
import org.joml.Vector3f;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public abstract class GameScene implements Scene {
    public GameObject camera;
    public DirectionalLight light;
    private GameObject sceneGraph = new GameObject();

    protected GameScene() {
    }

    protected abstract Vector3f getStartingCameraPos();

    @Override
    public void init() {
        glEnable(GL_DEPTH_TEST);
        glEnable(GL_CULL_FACE);
        glDepthFunc(GL_LEQUAL);
        glPolygonMode(GL_FRONT_FACE, GL_LINE);
        glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

        sceneGraph.preInit();
        createCamera();
        createLight();

        onSceneLoad();
    }

    private void createCamera() {
        // criação da câmera
        camera = GameObject.instantiate();
        camera.addComponent(Camera.class);
        camera.transform.setPosition(getStartingCameraPos(), Space.WORLD);
    }

    private void createLight() {
        // criação da luz
        // todo: por favor né, tá horrível isso... ¬¬
        light = (new DirectionalLight(
                new Vector3f(1.0f, 1.0f, -1.0f),    // direction
                new Vector3f(0.5f, 0.5f, 0.5f),     // ambient
                new Vector3f(0.2f, 0.2f, 0.2f),     // diffuse
                new Vector3f(1f, 1f, 1f)));         // specular
    }

    @Override
    public final void update(float secs) {
        if (Keyboard.getInstance().isPressed(GLFW_KEY_ESCAPE)) {
            glfwSetWindowShouldClose(glfwGetCurrentContext(), GLFW_TRUE);
            return;
        }

        Time.deltaTime = secs;
        Time.time += secs;

        LifeCycleManager.getInstance().start();
        sceneGraph.fixedUpdate();
        sceneGraph.update();
        sceneGraph.lateUpdate();
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
        child.setParent(sceneGraph);
        return child;
    }

    protected abstract void onSceneLoad();

    private void onSceneUnload() {
    }
}
