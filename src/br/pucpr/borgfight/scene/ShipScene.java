package br.pucpr.borgfight.scene;

import br.pucpr.borgfight.prefabs.MySkybox;
import br.pucpr.borgfight.prefabs.PlayerShip;
import br.pucpr.borgfight.prefabs.TempEnemy;
import br.pucpr.borgfight.scripts.SmoothFollower;
import br.pucpr.gema.core.GameObject;
import br.pucpr.gema.core.GameScene;
import org.joml.Vector3f;

public class ShipScene extends GameScene {
    @Override
    protected Vector3f getStartingCameraPos() {
        return new Vector3f(0, 1f, 5f);
    }

    @Override
    protected void onSceneLoad() {
        // player
        GameObject player = GameObject.instantiate(PlayerShip.class);
        camera.addComponent(SmoothFollower.class);
        camera.setParent(player);

        // skybox
        GameObject.instantiate(MySkybox.class);

        // enemies
        createEnemies(50, 20);
    }

    private void createEnemies(int count, float radius) {
        for (int i = 0; i < count; i++) {
            TempEnemy enemy = (TempEnemy) GameObject.instantiate(TempEnemy.class);
            enemy.randomize(radius);
        }
    }
}
