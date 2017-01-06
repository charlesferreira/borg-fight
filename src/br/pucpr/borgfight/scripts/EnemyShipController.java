package br.pucpr.borgfight.scripts;

import br.pucpr.gema.core.GameComponent;

public class EnemyShipController extends GameComponent {
    private ShipMovement movement;

    @Override
    public void start() {
        movement = getComponent(ShipMovement.class);
    }

    @Override
    public void update() {
//        movement.pitch(1f).yaw(1f).roll(1f);
    }
}
