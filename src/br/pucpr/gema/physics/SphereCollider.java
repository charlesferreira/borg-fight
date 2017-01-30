package br.pucpr.gema.physics;

import br.pucpr.gema.core.Collider;
import br.pucpr.gema.core.GameComponent;
import br.pucpr.gema.core.Time;
import br.pucpr.gema.core.components.Transform;
import org.joml.Vector3f;

public class SphereCollider extends Collider {
    private float radius = 1f;

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius > 0 ? radius : 0f;
    }

    @Override
    public boolean isCollidingWith(Collider other) {
        if (other instanceof SphereCollider) {
            float sqrDistance = transform.getWorldPosition().distanceSquared(other.transform.getWorldPosition());
            return sqrDistance < Math.pow(radius + ((SphereCollider) other).radius, 2);
        }

        return false;
    }
}
