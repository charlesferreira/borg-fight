package br.pucpr.borgfight.scene;

import br.pucpr.gema.core.GameObject;
import br.pucpr.gema.core.GameScene;
import br.pucpr.gema.core.Space;
import br.pucpr.gema.core.Time;
import br.pucpr.gema.core.objects.Cube;
import br.pucpr.gema.graphics.materials.DefaultMaterial;
import br.pucpr.mage.Keyboard;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

public class TransformScene extends GameScene {
    private GameObject sphere;
    private GameObject center;
    private float sphereScale = 1;
    private float centerScale = 1;
    private Space space;

    @Override
    protected Vector3f getStartingCameraPos() {
        return new Vector3f(0, 2, 5);
    }

    @Override
    protected void onSceneLoad() {
        center = GameObject.instantiate(Cube.class);
        center.renderer.material = new DefaultMaterial(new Vector3f(1, 1, 1));

        sphere = GameObject.instantiate(Cube.class, center);
        sphere.renderer.material = new DefaultMaterial(new Vector3f(0.6f, 0.6f, 0.6f));
        sphere.transform.translate(new Vector3f(0, 0, 2));

        camera.setParent(sphere);
    }

    @Override
    protected void onUpdate() {
        space = Keyboard.getInstance().isDown(GLFW.GLFW_KEY_LEFT_CONTROL) || Keyboard.getInstance().isDown(GLFW.GLFW_KEY_RIGHT_CONTROL)
                ? Space.WORLD
                : Space.SELF;

        Vector3f movement = new Vector3f();

        if (Keyboard.getInstance().isDown(GLFW.GLFW_KEY_W)) movement.add(0, 0, -1);
        if (Keyboard.getInstance().isDown(GLFW.GLFW_KEY_S)) movement.add(0, 0, 1);
        if (Keyboard.getInstance().isDown(GLFW.GLFW_KEY_A)) movement.add(-1, 0, 0);
        if (Keyboard.getInstance().isDown(GLFW.GLFW_KEY_D)) movement.add(1, 0, 0);
        if (Keyboard.getInstance().isDown(GLFW.GLFW_KEY_KP_8)) movement.add(0, 1, 0);
        if (Keyboard.getInstance().isDown(GLFW.GLFW_KEY_KP_5)) movement.add(0, -1, 0);

        sphere.transform.translate(movement.mul(Time.deltaTime), space);

        float angle = 0f;
        if (Keyboard.getInstance().isDown(GLFW.GLFW_KEY_KP_7)) angle++;
        if (Keyboard.getInstance().isDown(GLFW.GLFW_KEY_KP_9)) angle--;
        angle *= Time.deltaTime;

        sphere.transform.rotate(angle, new Vector3f(0, 1, 0), space);

        angle = 0f;
        if (Keyboard.getInstance().isDown(GLFW.GLFW_KEY_KP_4)) angle--;
        if (Keyboard.getInstance().isDown(GLFW.GLFW_KEY_KP_6)) angle++;
        angle *= Time.deltaTime;

        sphere.transform.rotate(angle, new Vector3f(0, 0, 1), space);

        if (Keyboard.getInstance().isDown(GLFW.GLFW_KEY_KP_ADD)) sphereScale += Time.deltaTime;
        if (Keyboard.getInstance().isDown(GLFW.GLFW_KEY_KP_SUBTRACT)) sphereScale -= Time.deltaTime;

        sphere.transform.setScale(sphereScale);

        // cubo 2

        angle = 0f;
        if (Keyboard.getInstance().isDown(GLFW.GLFW_KEY_LEFT)) angle++;
        if (Keyboard.getInstance().isDown(GLFW.GLFW_KEY_RIGHT)) angle--;
        angle *= Time.deltaTime;

        center.transform.rotate(angle, new Vector3f(0, 1, 0), space);

        angle = 0f;
        if (Keyboard.getInstance().isDown(GLFW.GLFW_KEY_UP)) angle--;
        if (Keyboard.getInstance().isDown(GLFW.GLFW_KEY_DOWN)) angle++;
        angle *= Time.deltaTime;

        center.transform.rotate(angle, new Vector3f(1, 0, 0), space);

        movement = new Vector3f();

        if (Keyboard.getInstance().isDown(GLFW.GLFW_KEY_HOME)) movement.add(0, 0, -1);
        if (Keyboard.getInstance().isDown(GLFW.GLFW_KEY_END)) movement.add(0, 0, 1);
        if (Keyboard.getInstance().isDown(GLFW.GLFW_KEY_DELETE)) movement.add(-1, 0, 0);
        if (Keyboard.getInstance().isDown(GLFW.GLFW_KEY_PAGE_DOWN)) movement.add(1, 0, 0);

        center.transform.translate(movement.mul(Time.deltaTime), space);

        if (Keyboard.getInstance().isDown(GLFW.GLFW_KEY_EQUAL)) centerScale += Time.deltaTime;
        if (Keyboard.getInstance().isDown(GLFW.GLFW_KEY_MINUS)) centerScale -= Time.deltaTime;

        center.transform.setScale(centerScale);

        // camera

        if (Keyboard.getInstance().isDown(GLFW.GLFW_KEY_I)) movement.add(0, 0, -1);
        if (Keyboard.getInstance().isDown(GLFW.GLFW_KEY_K)) movement.add(0, 0, 1);
        if (Keyboard.getInstance().isDown(GLFW.GLFW_KEY_J)) movement.add(-1, 0, 0);
        if (Keyboard.getInstance().isDown(GLFW.GLFW_KEY_L)) movement.add(1, 0, 0);

        camera.transform.translate(movement.mul(Time.deltaTime), space);

        angle = 0f;
        if (Keyboard.getInstance().isDown(GLFW.GLFW_KEY_U)) angle++;
        if (Keyboard.getInstance().isDown(GLFW.GLFW_KEY_O)) angle--;
        angle *= Time.deltaTime;

        camera.transform.rotate(angle, new Vector3f(0, 1, 0), space);
    }
}
