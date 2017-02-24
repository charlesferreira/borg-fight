package br.pucpr.borgfight.prefabs;

import br.pucpr.borgfight.scripts.*;
import br.pucpr.gema.core.GameObject;
import br.pucpr.gema.core.objects.Cube;
import br.pucpr.gema.graphics.materials.DefaultMaterial;
import br.pucpr.gema.physics.RigidBody;
import br.pucpr.mage.Texture;

public class PlayerShip extends GameObject {

    @Override
    protected void init() {
        GameManager.getInstance().setPlayerShip(this);

        setTag(Tags.Player.toString());
        addComponent(RigidBody.class);
        addComponent(ShipMovement.class);
        addComponent(PlayerShipController.class);
        addComponent(BasicWeapon.class)
                .setBulletPrefab(new BulletPrefab())
                .setTarget(Tags.Enemy.toString());

        // "model"
        GameObject model = GameObject.instantiate(Cube.class);
        model.transform.scale(0.8f);
        model.renderer.material = new DefaultMaterial()
                .setTexture(new Texture("br/pucpr/borgfight/assets/textures/cube.jpg"));
        model.setParent(this);
    }

    public void disableInputs() {
        getComponent(PlayerShipController.class).enabled = false;
    }
}
