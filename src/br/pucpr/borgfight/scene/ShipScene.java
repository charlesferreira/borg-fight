package br.pucpr.borgfight.scene;

import br.pucpr.borgfight.prefabs.MySkybox;
import br.pucpr.borgfight.prefabs.Ship;
import br.pucpr.borgfight.prefabs.TempEnemy;
import br.pucpr.gema.core.GameObject;
import br.pucpr.gema.core.GameScene;
import br.pucpr.gema.core.objects.Cube;
import br.pucpr.gema.graphics.materials.DefaultMaterial;
import org.joml.Vector3f;

public class ShipScene extends GameScene {
    @Override
    protected Vector3f getStartingCameraPos() {
        return new Vector3f(0, 0, 0.5f);
    }

    @Override
    protected void onSceneLoad() {
        // player
        GameObject player = GameObject.instantiate(Ship.class);
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
