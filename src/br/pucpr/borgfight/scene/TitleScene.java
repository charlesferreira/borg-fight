package br.pucpr.borgfight.scene;

import br.pucpr.borgfight.prefabs.MySkybox;
import br.pucpr.borgfight.scripts.TitleController;
import br.pucpr.gema.core.GameObject;
import br.pucpr.gema.core.GameScene;
import org.joml.Vector3f;

public class TitleScene extends GameScene {

    @Override
    protected Vector3f getStartingCameraPos() {
        return new Vector3f(0, 0, 12.8f);
    }

    @Override
    protected void onSceneLoad() {
        GameObject.instantiate(MySkybox.class);

        GameObject.instantiate().addComponent(TitleController.class);
    }
}
