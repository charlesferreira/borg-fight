package br.pucpr.borgfight.scene;

import br.pucpr.borgfight.prefabs.MySkybox;
import br.pucpr.borgfight.prefabs.Ship;
import br.pucpr.gema.core.GameObject;
import br.pucpr.gema.core.GameScene;
import br.pucpr.gema.core.objects.Cube;
import br.pucpr.gema.core.objects.Skybox;
import br.pucpr.gema.graphics.materials.DefaultMaterial;
import org.joml.Vector3f;

public class DemoScene extends GameScene {
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

        // objetos aleatórios
        float size = 50f;
        float count = 100f;
        for (int i = 0; i < count; i++) {
            GameObject random = GameObject.instantiate(Cube.class);
            float r = (float) Math.random();
            float g = (float) Math.random();
            float b = (float) Math.random();
            random.renderer.material = new DefaultMaterial(new Vector3f(r, g, b));
            random.transform
                    .translate(new Vector3f(
                            (float) (Math.random() * size) - size / 2f,
                            (float) (Math.random() * size) - size / 2f,
                            (float) (Math.random() * size) - size / 2f
                    ))
                    .scale(new Vector3f(
                            (float) (Math.random()) * 0.5f + 0.5f,
                            (float) (Math.random()) * 0.5f + 0.5f,
                            (float) (Math.random()) * 0.5f + 0.5f
                    ))
                    .rotate(
                            (float) (Math.random() * 2f * Math.PI),
                            random.transform.getLocalPosition()
                    );
        }
    }
}
