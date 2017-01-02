package br.pucpr.borgfight.prefabs;

import br.pucpr.borgfight.scripts.PlayerShipController;
import br.pucpr.borgfight.scripts.ShipMovement;
import br.pucpr.gema.core.GameObject;
import br.pucpr.gema.physics.RigidBody;

public class PlayerShip extends GameObject {
    @Override
    protected void init() {
        addComponent(RigidBody.class);
        addComponent(ShipMovement.class);
        addComponent(PlayerShipController.class);
    }
}
