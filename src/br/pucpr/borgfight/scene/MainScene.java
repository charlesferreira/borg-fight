package br.pucpr.borgfight.scene;

import br.pucpr.gema.core.GameScene;
import org.joml.Vector3f;

public class MainScene extends GameScene {
    @Override
    protected Vector3f getStartingCameraPos() {
        return new Vector3f(0, 0, 5);
    }

    @Override
    protected void onSceneLoad() {

    }
}
