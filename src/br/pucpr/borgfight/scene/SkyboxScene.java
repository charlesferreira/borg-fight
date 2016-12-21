package br.pucpr.borgfight.scene;

import br.pucpr.gema.core.GameObject;
import br.pucpr.gema.core.GameScene;
import br.pucpr.gema.core.Time;
import br.pucpr.gema.core.objects.Cube;
import br.pucpr.gema.core.objects.EmptyObject;
import br.pucpr.gema.core.objects.Sphere;
import br.pucpr.gema.graphics.materials.DefaultMaterial;
import br.pucpr.gema.util.Mathf;
import br.pucpr.mage.Keyboard;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

public class SkyboxScene extends GameScene {
    private GameObject cube1;

    @Override
    protected void onSceneLoad() {
        cube1 = addChild(new Cube());
        cube1.renderer.material = new DefaultMaterial(new Vector3f(1, 0, 0));
    }

    @Override
    protected Vector3f getStartingCameraPos() {
        return new Vector3f(0, 0, 5);
    }

    @Override
    protected void onUpdate() {
        Vector3f movement = new Vector3f().zero();

        /*
        if (Keyboard.getInstance().isDown(GLFW.GLFW_KEY_W)) movement.add(cube1.transform.getForward());
        if (Keyboard.getInstance().isDown(GLFW.GLFW_KEY_S)) movement.add(cube1.transform.getBack());
        if (Keyboard.getInstance().isDown(GLFW.GLFW_KEY_A)) movement.add(cube1.transform.getRight());
        if (Keyboard.getInstance().isDown(GLFW.GLFW_KEY_D)) movement.add(cube1.transform.getLeft());
        if (Keyboard.getInstance().isDown(GLFW.GLFW_KEY_KP_8)) movement.add(cube1.transform.getUp());
        if (Keyboard.getInstance().isDown(GLFW.GLFW_KEY_KP_5)) movement.add(cube1.transform.getDown());

        cube1.transform.translate(movement.mul(2 * Time.deltaTime));

        float angle = 0f;
        if (Keyboard.getInstance().isDown(GLFW.GLFW_KEY_KP_7)) angle++;
        if (Keyboard.getInstance().isDown(GLFW.GLFW_KEY_KP_9)) angle--;
        angle *= Time.deltaTime;

        //cube1.transform.getLocalRotation().rotateAxis(angle, new Vector3f(0, 1, 0));
        Quaternionf invert = new Quaternionf();
        cube1.transform.getLocalRotation().invert(invert);
        cube1.transform.getLocalRotation().rotateAxis(angle, new Vector3f(0, 1, 0).rotate(invert));

        angle = 0f;
        if (Keyboard.getInstance().isDown(GLFW.GLFW_KEY_KP_4)) angle--;
        if (Keyboard.getInstance().isDown(GLFW.GLFW_KEY_KP_6)) angle++;
        angle *= Time.deltaTime;

        cube1.transform.getLocalRotation().rotateAxis(angle, new Vector3f(0, 0, 1));*/
    }
}
