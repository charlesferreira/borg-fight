package br.pucpr.borgfight.prefabs;

import br.pucpr.borgfight.scripts.PlayerShipController;
import br.pucpr.borgfight.scripts.ShipMovement;
import br.pucpr.gema.core.GameObject;
import br.pucpr.gema.core.objects.Cube;
import br.pucpr.gema.graphics.materials.DefaultMaterial;
import br.pucpr.gema.physics.RigidBody;
import org.joml.Vector3f;

public class PlayerShip extends GameObject {
    @Override
    protected void init() {
        addComponent(RigidBody.class);
        addComponent(ShipMovement.class);
        addComponent(PlayerShipController.class);

        // "model"
        GameObject model = GameObject.instantiate(Cube.class);
        model.renderer.material = new DefaultMaterial(new Vector3f(1, 0, 0));
        model.setParent(this);
    }
}
