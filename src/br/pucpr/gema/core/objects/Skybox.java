package br.pucpr.gema.core.objects;

import br.pucpr.gema.core.GameObject;
import br.pucpr.gema.graphics.materials.DefaultMaterial;
import org.joml.Vector3f;

// todo: criar um skybox de verdade! xD
public class Skybox extends GameObject {
    @Override
    protected void init() {
        float size = 200f;

        // frente
        GameObject front = GameObject.instantiate(Cube.class);
        front.renderer.material = new DefaultMaterial(new Vector3f(0, 0.33f, 0));
        front.transform.translate(new Vector3f(0, 0, -size));
        front.transform.scale(size);

        // trás
        GameObject back = GameObject.instantiate(Cube.class);
        back.renderer.material = new DefaultMaterial(new Vector3f(0.33f, 0, 0.33f));
        back.transform.translate(new Vector3f(0, 0, size));
        back.transform.scale(size);

        // esquerda
        GameObject left = GameObject.instantiate(Cube.class);
        left.renderer.material = new DefaultMaterial(new Vector3f(0.33f, 0, 0));
        left.transform.translate(new Vector3f(-size, 0, 0));
        left.transform.scale(size);

        // direita
        GameObject right = GameObject.instantiate(Cube.class);
        right.renderer.material = new DefaultMaterial(new Vector3f(0, 0.33f, 0.33f));
        right.transform.translate(new Vector3f(size, 0, 0));
        right.transform.scale(size);

        // cima
        GameObject up = GameObject.instantiate(Cube.class);
        up.renderer.material = new DefaultMaterial(new Vector3f(0.33f, 0.33f, 0));
        up.transform.translate(new Vector3f(0, size, 0));
        up.transform.scale(size);

        // baixo
        GameObject down = GameObject.instantiate(Cube.class);
        down.renderer.material = new DefaultMaterial(new Vector3f(0, 0, 0.33f));
        down.transform.translate(new Vector3f(0, -size, 0));
        down.transform.scale(size);
    }
}
