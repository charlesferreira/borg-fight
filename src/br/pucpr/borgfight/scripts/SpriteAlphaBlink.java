package br.pucpr.borgfight.scripts;

import br.pucpr.gema.core.GameComponent;
import br.pucpr.gema.core.Time;
import br.pucpr.gema.graphics.materials.SpriteMaterial;

public class SpriteAlphaBlink extends GameComponent {
    private float min;
    private float max;
    private float speed;
    private boolean reverse;

    public SpriteAlphaBlink setup(float min, float max, float speed) {
        this.min = min;
        this.max = max;
        this.speed = speed;

        return this;
    }

    @Override
    public void update() {
        SpriteMaterial material = (SpriteMaterial) gameObject.renderer.material;
        float alpha = material.getAlpha() + (reverse ? -1 : 1) * speed * Time.deltaTime;
        if (alpha > max) {
            alpha = max;
            reverse = !reverse;
        }
        else if (alpha < min) {
            alpha = min;
            reverse = !reverse;
        }

        material.setAlpha(alpha);
    }
}
