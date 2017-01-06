package br.pucpr.borgfight.scene;

import br.pucpr.borgfight.prefabs.MySkybox;
import br.pucpr.borgfight.prefabs.PlayerShip;
import br.pucpr.borgfight.prefabs.TempEnemy;
import br.pucpr.borgfight.scripts.CameraMan;
import br.pucpr.gema.core.GameObject;
import br.pucpr.gema.core.GameScene;
import org.joml.Vector3f;

public class ShipScene extends GameScene {
    @Override
    protected Vector3f getStartingCameraPos() {
        return new Vector3f(0, 1f, 2f);
    }

    @Override
    protected void onSceneLoad() {
        // player
        GameObject player = GameObject.instantiate(PlayerShip.class);
        GameObject cameraMan = GameObject.instantiate();
        cameraMan.addComponent(CameraMan.class).setTarget(player.transform);
        camera.setParent(cameraMan);

        // skybox
        GameObject.instantiate(MySkybox.class);

        // enemies
        createEnemies(50, 20);
    }

    private void createEnemies(int count, float radius) {
        for (int i = 0; i < count; i++) {
            TempEnemy enemy = GameObject.instantiate(TempEnemy.class);
            enemy.randomize(radius);
        }
    }
}
