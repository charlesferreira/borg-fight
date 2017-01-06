package br.pucpr.borgfight.prefabs;

import br.pucpr.cg.MeshFactory;
import br.pucpr.gema.core.GameObject;
import br.pucpr.gema.graphics.materials.DefaultMaterial;
import br.pucpr.gema.physics.RigidBody;
import org.joml.Vector3f;

public class BulletPrefab extends GameObject {
    @Override
    protected void init() {
        addComponent(RigidBody.class);
        addComponent(Bullet.class);

        renderer.mesh = MeshFactory.createSphere(20, 20);
        ((DefaultMaterial)renderer.material).setColor(new Vector3f(0, 2, 2)).setSpecularPower(1f);
        transform.scale(0.1f);
    }
}
