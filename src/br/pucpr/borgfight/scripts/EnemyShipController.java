package br.pucpr.borgfight.scripts;

import br.pucpr.gema.core.GameComponent;
import br.pucpr.gema.core.GameObject;
import br.pucpr.mage.Keyboard;
import org.lwjgl.glfw.GLFW;

public class EnemyShipController extends GameComponent {
    private ShipMovement movement;

    public EnemyShipController(GameObject gameObject) {
        super(gameObject);
    }

    @Override
    public void start() {
        movement = (ShipMovement) gameObject.getComponent(ShipMovement.class);
    }

    @Override
    public void fixedUpdate() {
        movement.pitch(1f).yaw(1f).roll(1f);
    }
}
