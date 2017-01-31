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
        if (Input.getKey(GLFW.GLFW_KEY_S)) movement.rb.velocity.set(0);

        move();
        steer();
        fire();
    }

    private void move() {
        // propulsão
        float thrust = 0f;
        if (Input.getKey(GLFW.GLFW_KEY_W)) thrust++;
        movement.thrust(thrust);

        // strafe
        float strafe = 0f;
        if (Input.getKey(GLFW.GLFW_KEY_Q)) strafe--;
        if (Input.getKey(GLFW.GLFW_KEY_E)) strafe++;
        movement.strafe(strafe);
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
        if (Input.getMouseButton(GLFW.GLFW_MOUSE_BUTTON_LEFT))
            weapon.fire();
    }
}
