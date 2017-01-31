package br.pucpr.gema.physics;

import org.joml.Vector3f;

public class Steering {
    public static Vector3f wander(Vector3f velocity, float distance, float radius, float phi, float theta) {
        Vector3f center = new Vector3f(velocity).normalize().mul(distance);
        Vector3f displacement = new Vector3f(
                (float) (Math.sin(phi) * Math.cos(theta)),
                (float) (Math.sin(phi) * Math.sin(theta)),
                (float) Math.cos(theta))
                .mul(radius);

        return center.add(displacement);
    }
}
