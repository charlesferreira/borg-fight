package br.pucpr.borgfight.scene;

import br.pucpr.gema.core.GameObject;
import br.pucpr.gema.core.GameScene;
import br.pucpr.gema.core.Time;
import br.pucpr.gema.core.objects.Cube;
import br.pucpr.gema.core.objects.Sphere;
import br.pucpr.gema.graphics.materials.DefaultMaterial;
import br.pucpr.gema.util.Mathf;
import org.joml.Vector3f;

public class MainScene extends GameScene {
    private GameObject cube1;
    private GameObject cube2;
    private GameObject sphere;

    @Override
    protected void onSceneLoad() {
        cube1 = addChild(new Cube());
        cube1.renderer.material = new DefaultMaterial(
                new Vector3f(1, 0, 0),
                new Vector3f(1, 0, 0)
        );

        cube2 = cube1.addChild(new Cube());
        cube2.renderer.material = new DefaultMaterial(
                new Vector3f(1, 1, 0),
                new Vector3f(1, 1, 0)
        );
        cube2.transform.position.add(0, 0, 2);

        sphere = cube2.addChild(new Sphere());
        sphere.renderer.material = new DefaultMaterial(
                new Vector3f(0, 0, 1),
                new Vector3f(0, 0, 1)
        );
        sphere.transform.position.add(0, 2f, 0);
        sphere.transform.scale.mul(0.5f);
        sphere.transform.rotation.rotate(Mathf.toRadians(-90), 0, 0);

        GameObject cube3 = sphere.addChild(new Cube());
        cube3.renderer.material = new DefaultMaterial(
                new Vector3f(0, 1, 0),
                new Vector3f(0, 1, 0)
        );
        cube3.transform.position.add(0, 0, 0);
        cube3.transform.scale.set(5, 0.5f, 0.5f);
    }

    @Override
    protected void onUpdate() {
        cube1.transform.rotation.rotate(Time.deltaTime / 4, Time.deltaTime / 2, 0);
        cube2.transform.rotation.rotate(Time.deltaTime * 1.5f, Time.deltaTime / 2f, Time.deltaTime);
        cube2.transform.scale.set(0.75f + Mathf.sin(Time.time) / 8f);
        sphere.transform.rotation.rotate(0, Time.deltaTime / 2f, 10 * Time.deltaTime);
    }
}
