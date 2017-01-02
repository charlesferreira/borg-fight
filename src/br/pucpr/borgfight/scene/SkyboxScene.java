package br.pucpr.borgfight.scene;

import br.pucpr.borgfight.prefabs.MySkybox;
import br.pucpr.gema.core.GameObject;
import br.pucpr.gema.core.GameScene;
import br.pucpr.gema.util.scripts.DemoController;
import org.joml.Vector3f;

public class SkyboxScene extends GameScene {

    @Override
    protected Vector3f getStartingCameraPos() {
        return new Vector3f();
    }

    @Override
    protected void onSceneLoad() {
        // permite controlar a câmera
        camera.addComponent(DemoController.class);

        // skybox
        GameObject skybox = GameObject.instantiate(MySkybox.class);
    }
}
