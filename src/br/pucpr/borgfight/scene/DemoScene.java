package br.pucpr.borgfight.scene;

import br.pucpr.gema.core.GameObject;
import br.pucpr.gema.core.GameScene;
import br.pucpr.gema.core.Time;
import br.pucpr.gema.core.objects.Cube;
import br.pucpr.gema.core.objects.EmptyObject;
import br.pucpr.gema.core.objects.Sphere;
import br.pucpr.gema.graphics.materials.DefaultMaterial;
import br.pucpr.gema.util.Mathf;
import org.joml.Vector3f;

public class DemoScene extends GameScene {
    private GameObject cube1;
    private GameObject cube2;
    private GameObject sphere;
    private GameObject cameraMan;

    @Override
    protected void onSceneLoad() {
        cameraMan = addChild(new EmptyObject());
        cameraMan.addChild(camera);

        cube1 = addChild(new Cube());
        cube1.renderer.material = new DefaultMaterial(new Vector3f(1, 0, 0));

        cube2 = cube1.addChild(new Cube());
        cube2.renderer.material = new DefaultMaterial(new Vector3f(1, 1, 0));
        cube2.transform.translate(0, 0, 2);

        sphere = cube2.addChild(new Sphere());
        sphere.renderer.material = new DefaultMaterial(new Vector3f(0, 0, 1));
        sphere.transform.translate(0, 2f, 0);
        sphere.transform.scale(0.5f);
        sphere.transform.rotate(Mathf.toRadians(-90), 0, 0);

        GameObject cube3 = sphere.addChild(new Cube());
        cube3.renderer.material = new DefaultMaterial(new Vector3f(0, 1, 0)
        );
        cube3.transform.translate(0, 0, 0);
        cube3.transform.setScale(5, 0.5f, 0.5f);
    }

    @Override
    protected void onUpdate() {
        cube1.transform.rotate(0, Time.deltaTime * 1.333f, 0);
        cube2.transform.rotate(0, 0, Time.deltaTime * 2.15f);
        //cube2.transform.setScale(0.75f + Mathf.sin(Time.time) / 8f);
        sphere.transform.rotate(0, 0, 10 * Time.deltaTime);
    }
}
