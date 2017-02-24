package br.pucpr.borgfight.prefabs;

import br.pucpr.borgfight.scripts.enemy.EnemyHealth;
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
        createMesh();

        setTag(Tags.Enemy.toString());
        addComponent(RigidBody.class);
        addComponent(SphereCollider.class);
        addComponent(EnemyMovement.class);
        addComponent(EnemyHealth.class);
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

    private void createMesh() {
        float r = 1;
        float g = 1;
        float b = 0;

        // body
        createPart(r, g, b).transform.scale(1.5f, 1.5f, 1.5f);

        // wings
        GameObject wings = createPart(r, g, b);
        wings.transform.scale(5, 0.1f, 0.75f);
    }

    private GameObject createPart(float r, float g, float b) {
        GameObject part = GameObject.instantiate();
        part.renderer.mesh = MeshFactory.createCube();
        part.renderer.material = new DefaultMaterial(new Vector3f(r, g, b));
        part.setParent(this);
        return part;
    }

}
