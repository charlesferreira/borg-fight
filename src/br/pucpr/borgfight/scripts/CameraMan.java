package br.pucpr.borgfight.scripts;

import br.pucpr.gema.core.GameComponent;
import br.pucpr.gema.core.components.Transform;
import org.joml.Quaternionf;

public class CameraMan extends GameComponent {
    private Transform target;

    @Override
    public void lateUpdate() {
        Quaternionf lerpRotation = transform.getWorldRotation().nlerp(target.getWorldRotation(), 0.15f);
        transform
                .setWorldRotation(lerpRotation)
                .setWorldPosition(target.getWorldPosition())
        ;
    }

    public void setTarget(Transform target) {
        this.target = target;
    }
}
