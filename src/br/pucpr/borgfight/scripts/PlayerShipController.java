package br.pucpr.borgfight.scripts;

import br.pucpr.gema.core.GameComponent;
import br.pucpr.gema.core.Input;
import br.pucpr.gema.core.Time;
import org.joml.Vector2f;
import org.lwjgl.glfw.GLFW;

public class PlayerShipController extends GameComponent {
    private ShipMovement playerMovement;
    private Vector2f lastMousePos;

    @Override
    public void start() {
        playerMovement = gameObject.getComponent(ShipMovement.class);
        lastMousePos = Input.mousePosition();
    }

    @Override
    public void update() {
        // reset
        if (Input.getKey(GLFW.GLFW_KEY_SPACE))
            playerMovement.reset();

        // turbo
        float turbo = Input.getKey(GLFW.GLFW_KEY_LEFT_SHIFT)
                ? 5f
                : 1f;

        // pitch
        float pitch = (Input.mousePosition().y - lastMousePos.y) * Time.deltaTime;

        // yaw
        float yaw = (Input.mousePosition().x - lastMousePos.x) * Time.deltaTime;

        // roll
        float roll = 0f;
        if (Input.getKey(GLFW.GLFW_KEY_A)) roll--;
        if (Input.getKey(GLFW.GLFW_KEY_D)) roll++;

        // aplica rotações
        playerMovement
                .pitch(pitch * turbo)
                .yaw(yaw * turbo)
                .roll(roll * turbo);

        // impulso
        float thrust = 0f;
        if (Input.getKey(GLFW.GLFW_KEY_W)) thrust++;
        if (Input.getKey(GLFW.GLFW_KEY_S)) thrust--;
        playerMovement.thrust(thrust * turbo);

        // strafe
        float strafe = 0f;
        if (Input.getKey(GLFW.GLFW_KEY_Q)) strafe--;
        if (Input.getKey(GLFW.GLFW_KEY_E)) strafe++;
        playerMovement.strafe(strafe * turbo);

        // atualiza posição do mouse
        lastMousePos = Input.mousePosition();
    }
}
