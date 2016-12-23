package br.pucpr.borgfight.scene;

import br.pucpr.gema.core.GameObject;
import br.pucpr.gema.core.GameScene;
import br.pucpr.gema.core.objects.Cube;
import br.pucpr.gema.graphics.materials.DefaultMaterial;
import org.joml.Vector3f;

public class SkyboxScene extends GameScene {

    @Override
    protected Vector3f getStartingCameraPos() {
        return new Vector3f(0, 0, 5);
    }

    @Override
    protected void onSceneLoad() {
        GameObject cube = GameObject.instantiate(Cube.class);
        cube.renderer.material.setColor(new Vector3f(1, 0, 0));
    }
}
