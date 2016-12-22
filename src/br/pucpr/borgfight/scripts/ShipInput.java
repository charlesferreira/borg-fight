package br.pucpr.borgfight.scripts;

import br.pucpr.gema.core.GameComponent;
import br.pucpr.gema.core.GameObject;
import br.pucpr.mage.Keyboard;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

public class ShipInput extends GameComponent {
    private ShipMovement playerMovement;

    public ShipInput(GameObject gameObject) {
        super(gameObject);
    }

    @Override
    public void start() {
        playerMovement = (ShipMovement) gameObject.GetComponent(ShipMovement.class);
    }

    @Override
    public void update() {
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
