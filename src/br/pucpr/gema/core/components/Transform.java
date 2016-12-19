package br.pucpr.gema.core.components;

import br.pucpr.gema.core.GameComponent;
import br.pucpr.gema.core.GameObject;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public class Transform extends GameComponent {
    public Vector3f position;
    public Vector3f scale;
    public Quaternionf rotation;

    public Transform(GameObject gameObject) {
        super(gameObject);
        position = new Vector3f();
        rotation = new Quaternionf();
        scale = new Vector3f(1f, 1f, 1f);
    }
}
