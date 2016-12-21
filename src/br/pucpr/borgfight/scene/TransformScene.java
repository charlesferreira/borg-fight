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
    private GameObject cube1;
    private GameObject cube2;
    private float scale1 = 1;
    private float scale2 = 1;

    @Override
    protected Vector3f getStartingCameraPos() {
        return new Vector3f(0, 5, 5);
    }

    @Override
    protected void onSceneLoad() {
        cube1 = addChild(new Cube());
        cube1.renderer.material = new DefaultMaterial(new Vector3f(1, 0, 0));

        cube2 = addChild(new Cube());
        cube2.renderer.material = new DefaultMaterial(new Vector3f(0, 1, 0));
        cube2.addChild(cube1);
        cube2.transform.translate(new Vector3f(0, 0, 2));
//        camera.moveToParent(cube2);
    }

    @Override
    protected void onUpdate() {
        Vector3f movement = new Vector3f();

        if (Keyboard.getInstance().isDown(GLFW.GLFW_KEY_W)) movement.add(0, 0, -1);
        if (Keyboard.getInstance().isDown(GLFW.GLFW_KEY_S)) movement.add(0, 0, 1);
        if (Keyboard.getInstance().isDown(GLFW.GLFW_KEY_A)) movement.add(-1, 0, 0);
        if (Keyboard.getInstance().isDown(GLFW.GLFW_KEY_D)) movement.add(1, 0, 0);
        if (Keyboard.getInstance().isDown(GLFW.GLFW_KEY_KP_8)) movement.add(0, 1, 0);
        if (Keyboard.getInstance().isDown(GLFW.GLFW_KEY_KP_5)) movement.add(0, -1, 0);

        //cube1.transform.position.add(movement.mul(2 * Time.deltaTime));
        cube1.transform.translate(movement.mul(Time.deltaTime), Space.WORLD);
//        cube1.transform.setPosition(movement.mul(2f), Space.WORLD);
//        cube1.transform.setPosition(movement.mul(2f), Space.SELF);

        float angle = 0f;
        if (Keyboard.getInstance().isDown(GLFW.GLFW_KEY_KP_7)) angle++;
        if (Keyboard.getInstance().isDown(GLFW.GLFW_KEY_KP_9)) angle--;
        angle *= Time.deltaTime;

        cube1.transform.rotate(angle, new Vector3f(0, 1, 0), Space.WORLD);

        angle = 0f;
        if (Keyboard.getInstance().isDown(GLFW.GLFW_KEY_KP_4)) angle--;
        if (Keyboard.getInstance().isDown(GLFW.GLFW_KEY_KP_6)) angle++;
        angle *= Time.deltaTime;

        cube1.transform.rotate(angle, new Vector3f(0, 0, 1));

        if (Keyboard.getInstance().isDown(GLFW.GLFW_KEY_KP_ADD)) scale1 += Time.deltaTime;
        if (Keyboard.getInstance().isDown(GLFW.GLFW_KEY_KP_SUBTRACT)) scale1 -= Time.deltaTime;

        cube1.transform.setScale(scale1);

        // cubo 2

        angle = 0f;
        if (Keyboard.getInstance().isDown(GLFW.GLFW_KEY_LEFT)) angle--;
        if (Keyboard.getInstance().isDown(GLFW.GLFW_KEY_RIGHT)) angle++;
        angle *= Time.deltaTime;

        cube2.transform.rotate(angle, new Vector3f(0, 1, 0));

        angle = 0f;
        if (Keyboard.getInstance().isDown(GLFW.GLFW_KEY_UP)) angle--;
        if (Keyboard.getInstance().isDown(GLFW.GLFW_KEY_DOWN)) angle++;
        angle *= Time.deltaTime;

        cube2.transform.rotate(angle, new Vector3f(1, 0, 0));

        movement = new Vector3f();

        if (Keyboard.getInstance().isDown(GLFW.GLFW_KEY_HOME)) movement.add(0, 0, -1);
        if (Keyboard.getInstance().isDown(GLFW.GLFW_KEY_END)) movement.add(0, 0, 1);
        if (Keyboard.getInstance().isDown(GLFW.GLFW_KEY_DELETE)) movement.add(-1, 0, 0);
        if (Keyboard.getInstance().isDown(GLFW.GLFW_KEY_PAGE_DOWN)) movement.add(1, 0, 0);

        cube2.transform.translate(movement.mul(Time.deltaTime));

        if (Keyboard.getInstance().isDown(GLFW.GLFW_KEY_EQUAL)) scale2 += Time.deltaTime;
        if (Keyboard.getInstance().isDown(GLFW.GLFW_KEY_MINUS)) scale2 -= Time.deltaTime;

        cube2.transform.setScale(scale2);
    }
}
