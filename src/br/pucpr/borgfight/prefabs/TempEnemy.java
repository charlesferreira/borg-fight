package br.pucpr.borgfight.prefabs;

import br.pucpr.borgfight.scripts.EnemyShipController;
import br.pucpr.borgfight.scripts.ShipMovement;
import br.pucpr.cg.MeshFactory;
import br.pucpr.gema.core.GameObject;
import br.pucpr.gema.graphics.materials.DefaultMaterial;
import br.pucpr.gema.physics.RigidBody;
import org.joml.Vector3f;

public class TempEnemy extends GameObject {
    @Override
    protected void init() {
        float r = (float) Math.random();
        float g = (float) Math.random();
        float b = (float) Math.random();
        renderer.mesh = MeshFactory.createCube();
        renderer.material = new DefaultMaterial(new Vector3f(r, g, b));

        addComponent(RigidBody.class);
        addComponent(ShipMovement.class);
        addComponent(EnemyShipController.class);
    }

    public TempEnemy randomize(float distance) {
        transform
                .translate(new Vector3f(
                        (float) Math.random() * 2f - 1f,
                        (float) Math.random() * 2f - 1f,
                        (float) Math.random() * 2f - 1f).mul(distance))
//                .scale(new Vector3f(
//                        (float) (Math.random()) * 0.5f + 0.5f,
//                        (float) (Math.random()) * 0.5f + 0.5f,
//                        (float) (Math.random()) * 0.5f + 0.5f
//                ))
                .rotate(
                        (float) (Math.random() * 2f * Math.PI),
                        transform.getLocalPosition()
                );
        return this;
    }

}
