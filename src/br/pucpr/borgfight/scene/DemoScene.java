package br.pucpr.borgfight.scene;

import br.pucpr.borgfight.prefabs.Ship;
import br.pucpr.borgfight.scripts.ShipMovement;
import br.pucpr.gema.core.GameObject;
import br.pucpr.gema.core.GameScene;
import br.pucpr.gema.core.objects.Cube;
import br.pucpr.gema.core.objects.Skybox;
import br.pucpr.gema.graphics.materials.DefaultMaterial;
import br.pucpr.mage.Keyboard;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

public class DemoScene extends GameScene {
    private ShipMovement playerMovement;

    @Override
    protected Vector3f getStartingCameraPos() {
        return new Vector3f(0, 2, 5);
    }

    @Override
    protected void onSceneLoad() {
        // player
        GameObject player = addChild(new Ship());
        playerMovement = (ShipMovement) player.GetComponent(ShipMovement.class);
        player.transform.translate(new Vector3f(0, 0, 10));
        camera.moveToParent(player);

        // centro
        GameObject center = addChild(new Cube());
        center.renderer.material = new DefaultMaterial(new Vector3f(1, 1, 1));

        // fake skybox
        addChild(new Skybox(200));

        // objetos aleatórios
        float size = 100f;
        float count = 200f;
        for (int i = 0; i < count; i++) {
            GameObject random = addChild(new Cube());
            float r = (float) Math.random();
            float g = (float) Math.random();
            float b = (float) Math.random();
            random.renderer.material = new DefaultMaterial(new Vector3f(r, g, b));
            random.transform
                    .translate(new Vector3f(
                            (float) (Math.random() * size) - size / 2f,
                            (float) (Math.random() * size) - size / 2f,
                            (float) (Math.random() * size) - size / 2f
                    ))
                    .scale(new Vector3f(
                            (float) (Math.random()) * 0.5f + 0.5f,
                            (float) (Math.random()) * 0.5f + 0.5f,
                            (float) (Math.random()) * 0.5f + 0.5f
                    ))
                    .rotate(
                            (float) (Math.random() * 2f * Math.PI),
                            random.transform.getLocalPosition()
                    );
        }
    }

    @Override
    protected void onUpdate() {
        // pitch
        float pitch = 0f;
        if (Keyboard.getInstance().isDown(GLFW.GLFW_KEY_UP)) pitch++;
        if (Keyboard.getInstance().isDown(GLFW.GLFW_KEY_DOWN)) pitch--;

        // yaw
        float yaw = 0f;
        if (Keyboard.getInstance().isDown(GLFW.GLFW_KEY_A)) yaw++;
        if (Keyboard.getInstance().isDown(GLFW.GLFW_KEY_D)) yaw--;

        // roll
        float roll = 0f;
        if (Keyboard.getInstance().isDown(GLFW.GLFW_KEY_LEFT)) roll++;
        if (Keyboard.getInstance().isDown(GLFW.GLFW_KEY_RIGHT)) roll--;

        // aplica rotações
        playerMovement.pitch(pitch).yaw(yaw).roll(roll);

        // impulso
        Vector3f thrust = new Vector3f();
        if (Keyboard.getInstance().isDown(GLFW.GLFW_KEY_W)) thrust.add(0, 0, -1);
        if (Keyboard.getInstance().isDown(GLFW.GLFW_KEY_S)) thrust.add(0, 0, 1);

        playerMovement.thrust(thrust);

    }
}
