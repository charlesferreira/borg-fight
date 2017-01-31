package br.pucpr.borgfight.prefabs;

import br.pucpr.borgfight.scripts.enemy.EnemyMovement;
import br.pucpr.borgfight.scripts.Tags;
import br.pucpr.cg.MeshFactory;
import br.pucpr.gema.core.GameObject;
import br.pucpr.gema.core.components.Transform;
import br.pucpr.gema.graphics.materials.DefaultMaterial;
import br.pucpr.gema.physics.RigidBody;
import br.pucpr.gema.physics.SphereCollider;
import org.joml.Vector3f;

public class Enemy extends GameObject {
    @Override
    protected void init() {
        float r = (float) Math.random();
        float g = (float) Math.random();
        float b = (float) Math.random();
//        renderer.mesh = MeshFactory.createSphere(10, 10);
        renderer.mesh = MeshFactory.createCube();
        renderer.material = new DefaultMaterial(new Vector3f(r, g, b));

        setTag(Tags.Enemy.toString());
        addComponent(RigidBody.class);
        addComponent(SphereCollider.class);
        addComponent(EnemyMovement.class);
    }

    public Enemy randomize(float distance) {
        transform
                .translate(new Vector3f(
                        (float) Math.random() * 2f - 1f,
                        (float) Math.random() * 2f - 1f,
                        (float) Math.random() * 2f - 1f).mul(distance))
                .rotateLocal(
                        (float) (Math.random() * 2f * Math.PI),
                        transform.getLocalPosition()
                );
        return this;
    }

}