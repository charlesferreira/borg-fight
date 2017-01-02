package br.pucpr.borgfight.scene;

import br.pucpr.borgfight.prefabs.MySkybox;
import br.pucpr.borgfight.prefabs.PlayerShip;
import br.pucpr.borgfight.prefabs.TempEnemy;
import br.pucpr.gema.core.GameObject;
import br.pucpr.gema.core.GameScene;
import br.pucpr.gema.core.objects.Cube;
import br.pucpr.gema.graphics.materials.DefaultMaterial;
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
        GameObject cube = GameObject.instantiate(Cube.class);
        cube.renderer.material = new DefaultMaterial(new Vector3f(1, 0, 0));
        cube.setParent(player);
        camera.setParent(cube);

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
