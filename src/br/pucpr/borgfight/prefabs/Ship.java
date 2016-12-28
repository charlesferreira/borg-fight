package br.pucpr.borgfight.prefabs;

import br.pucpr.borgfight.scripts.ShipInput;
import br.pucpr.borgfight.scripts.ShipMovement;
import br.pucpr.gema.core.GameObject;
import br.pucpr.gema.physics.RigidBody;

public class Ship extends GameObject {
    @Override
    protected void init() {
        addComponent(RigidBody.class);
        addComponent(ShipInput.class);
        addComponent(ShipMovement.class);
    }
}
