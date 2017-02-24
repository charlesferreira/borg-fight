package br.pucpr.borgfight.scene;

import br.pucpr.borgfight.prefabs.Enemy;
import br.pucpr.borgfight.prefabs.MySkybox;
import br.pucpr.borgfight.prefabs.PlayerShip;
import br.pucpr.borgfight.scripts.CameraMan;
import br.pucpr.borgfight.scripts.GameManager;
import br.pucpr.borgfight.scripts.HUD;
import br.pucpr.gema.core.GameObject;
import br.pucpr.gema.core.GameScene;
import org.joml.Vector3f;

public class MainScene extends GameScene {
    @Override
    protected Vector3f getStartingCameraPos() {
        return new Vector3f(0, 1f, 2f);
    }

    @Override
    protected void onSceneLoad() {
        // skybox
        GameObject.instantiate(MySkybox.class);

        // enemies
        createEnemies(GameManager.getInstance().startingEnemies, 100);

        // player
        GameObject player = GameObject.instantiate(PlayerShip.class);
        GameObject cameraMan = GameObject.instantiate();
        cameraMan.addComponent(CameraMan.class).setTarget(player.transform);
        camera.setParent(cameraMan);

        // hud
        GameObject.instantiate()
                .setParent(camera)
                .addComponent(HUD.class);
    }

    private void createEnemies(int count, float radius) {
        for (int i = 0; i < count; i++) {
            Enemy enemy = GameObject.instantiate(Enemy.class);
            enemy.randomize(radius);
        }
    }
}
