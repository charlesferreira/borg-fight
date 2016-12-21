package br.pucpr.gema.core.objects;

import br.pucpr.gema.core.GameObject;
import br.pucpr.gema.graphics.materials.DefaultMaterial;
import org.joml.Vector3f;

public class Skybox extends GameObject {
    public Skybox(float size) {
        super(true);

        // todo: criar um skybox de verdade! xD

        // frente
        GameObject front = addChild(new Cube());
        front.renderer.material = new DefaultMaterial(new Vector3f(0, 0.33f, 0));
        front.transform.translate(new Vector3f(0, 0, -size));
        front.transform.scale(size);

        // trás
        GameObject back = addChild(new Cube());
        back.renderer.material = new DefaultMaterial(new Vector3f(0.33f, 0, 0.33f));
        back.transform.translate(new Vector3f(0, 0, size));
        back.transform.scale(size);

        // esquerda
        GameObject left = addChild(new Cube());
        left.renderer.material = new DefaultMaterial(new Vector3f(0.33f, 0, 0));
        left.transform.translate(new Vector3f(-size, 0, 0));
        left.transform.scale(size);

        // direita
        GameObject right = addChild(new Cube());
        right.renderer.material = new DefaultMaterial(new Vector3f(0, 0.33f, 0.33f));
        right.transform.translate(new Vector3f(size, 0, 0));
        right.transform.scale(size);

        // cima
        GameObject up = addChild(new Cube());
        up.renderer.material = new DefaultMaterial(new Vector3f(0.33f, 0.33f, 0));
        up.transform.translate(new Vector3f(0, size, 0));
        up.transform.scale(size);

        // baixo
        GameObject down = addChild(new Cube());
        down.renderer.material = new DefaultMaterial(new Vector3f(0, 0, 0.33f));
        down.transform.translate(new Vector3f(0, -size, 0));
        down.transform.scale(size);
    }
}
