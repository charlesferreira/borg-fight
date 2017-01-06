package br.pucpr.borgfight.scripts;

import br.pucpr.gema.core.GameComponent;
import br.pucpr.gema.core.Input;
import br.pucpr.gema.core.Time;
import org.joml.Vector2f;
import org.lwjgl.glfw.GLFW;

public class PlayerShipController extends GameComponent {
    private ShipMovement movement;
    private BasicWeapon weapon;
    private Vector2f lastMousePos;

    @Override
    public void start() {
        movement = getComponent(ShipMovement.class);
        weapon = getComponent(BasicWeapon.class);
        lastMousePos = Input.mousePosition();
    }

    @Override
    public void update() {
        if (Input.getKey(GLFW.GLFW_KEY_SPACE)) movement.reset();

        move();
        steer();
        fire();
    }

    private void move() {
        // turbo
        float turbo = Input.getKey(GLFW.GLFW_KEY_LEFT_SHIFT)
                ? 5f
                : 1f;

        // propulsão
        float thrust = 0f;
        if (Input.getKey(GLFW.GLFW_KEY_W)) thrust++;
        if (Input.getKey(GLFW.GLFW_KEY_S)) thrust--;
        movement.thrust(thrust * turbo);

        // strafe
        float strafe = 0f;
        if (Input.getKey(GLFW.GLFW_KEY_Q)) strafe--;
        if (Input.getKey(GLFW.GLFW_KEY_E)) strafe++;
        movement.strafe(strafe * turbo);
    }

    private void steer() {
        // pitch
        float pitch = (Input.mousePosition().y - lastMousePos.y) * Time.deltaTime;

        // yaw
        float yaw = (Input.mousePosition().x - lastMousePos.x) * Time.deltaTime;

        // roll
        float roll = 0f;
        if (Input.getKey(GLFW.GLFW_KEY_A)) roll--;
        if (Input.getKey(GLFW.GLFW_KEY_D)) roll++;

        // aplica rotações
        movement
                .pitch(pitch)
                .yaw(yaw)
                .roll(roll);

        // atualiza posição do mouse
        lastMousePos = Input.mousePosition();
    }

    private void fire() {
        if (Input.getMouseButtonDown(GLFW.GLFW_MOUSE_BUTTON_LEFT))
            weapon.fire();
    }
}
