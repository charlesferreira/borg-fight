package br.pucpr.borgfight.scripts;

import br.pucpr.gema.core.GameComponent;
import br.pucpr.gema.core.GameObject;
import br.pucpr.mage.Keyboard;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

public class PlayerShipController extends GameComponent {
    private ShipMovement playerMovement;

    public PlayerShipController(GameObject gameObject) {
        super(gameObject);
    }

    @Override
    public void start() {
        playerMovement = (ShipMovement) gameObject.getComponent(ShipMovement.class);
    }

    @Override
    public void fixedUpdate() {
        // reset
        if (Keyboard.getInstance().isDown(GLFW.GLFW_KEY_SPACE))
            playerMovement.reset();

        // turbo
        float turbo = Keyboard.getInstance().isDown(GLFW.GLFW_KEY_LEFT_SHIFT)
                ? 5f
                : 1f;


        // pitch
        float pitch = 0f;
        if (Keyboard.getInstance().isDown(GLFW.GLFW_KEY_UP)) pitch--;
        if (Keyboard.getInstance().isDown(GLFW.GLFW_KEY_DOWN)) pitch++;

        // yaw
        float yaw = 0f;
        if (Keyboard.getInstance().isDown(GLFW.GLFW_KEY_A)) yaw++;
        if (Keyboard.getInstance().isDown(GLFW.GLFW_KEY_D)) yaw--;

        // roll
        float roll = 0f;
        if (Keyboard.getInstance().isDown(GLFW.GLFW_KEY_LEFT)) roll++;
        if (Keyboard.getInstance().isDown(GLFW.GLFW_KEY_RIGHT)) roll--;

        // aplica rotações
        playerMovement
                .pitch(pitch * turbo)
                .yaw(yaw * turbo)
                .roll(roll * turbo);

        // impulso
        float thrust = 0f;
        if (Keyboard.getInstance().isDown(GLFW.GLFW_KEY_W)) thrust++;
        if (Keyboard.getInstance().isDown(GLFW.GLFW_KEY_S)) thrust--;
        playerMovement.thrust(thrust * turbo);

        // strafe
        float strafe = 0f;
        if (Keyboard.getInstance().isDown(GLFW.GLFW_KEY_Q)) strafe--;
        if (Keyboard.getInstance().isDown(GLFW.GLFW_KEY_E)) strafe++;
        playerMovement.strafe(strafe * turbo);
    }
}
