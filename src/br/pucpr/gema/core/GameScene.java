package br.pucpr.gema.core;

import br.pucpr.gema.core.objects.Camera;
import br.pucpr.gema.physics.CollisionsManager;
import br.pucpr.mage.Keyboard;
import br.pucpr.mage.phong.DirectionalLight;
import org.joml.Vector3f;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public abstract class GameScene {
    public GameObject camera;
    public DirectionalLight light;
    private GameObject sceneGraph = new GameObject();
    private float fixedUpdateTime;

    protected GameScene() {
    }

    protected abstract Vector3f getStartingCameraPos();

    public final void init() {
        sceneGraph.initComponents();
        createCamera();
        createLight();

        onSceneLoad();
    }

    private void createCamera() {
        // criação da câmera
        camera = GameObject.instantiate();
        camera.addComponent(Camera.class);
        camera.transform.setLocalPosition(getStartingCameraPos());
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

    public final void update(float secs) {
        if (Keyboard.getInstance().isPressed(GLFW_KEY_ESCAPE)) {
            glfwSetWindowShouldClose(glfwGetCurrentContext(), GLFW_TRUE);
            return;
        }

        Time.time += secs;
        Time.deltaTime = secs;

        // atualiza os objetos que possam ter sido criados na inicialização da cena
        // todo: não sei se isso está certo... fiz com pressa
        sceneGraph.updateNewborns();
        LifeCycleManager.getInstance().start();

        fixedUpdateTime += Time.deltaTime;
        while (fixedUpdateTime > Time.fixedDeltaTime) {
            fixedUpdateTime -= Time.fixedDeltaTime;
            sceneGraph.fixedUpdate();
            CollisionsManager.getInstance().update();
        }

        sceneGraph.update();
        sceneGraph.lateUpdate();
    }

    public final void draw() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        sceneGraph.draw();
    }

    public final void deinit() {
    }

    protected abstract void onSceneLoad();

    protected GameObject addChild(GameObject child) {
        child.setParent(sceneGraph);
        return child;
    }

    public GameObject findObjectWithTag(String tag) {
        return sceneGraph.findObjectWithTag(tag);
    }
}
