package br.pucpr.gema.physics;

import br.pucpr.gema.core.Collider;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class CollisionsManager {

    private List<Collider> colliders = new ArrayList<>();
    private List<Pair<Collider, Collider>> staying = new ArrayList<>();
    private List<Pair<Collider, Collider>> exiting = new ArrayList<>();

    private static CollisionsManager instance = new CollisionsManager();

    public static CollisionsManager getInstance() {
        return instance;
    }

    public void add(Collider collider) {
        colliders.add(collider);
    }

    public void remove(Collider collider) {
        colliders.remove(collider);
    }

    public void update() {
        updateCollisionsStaying();
        updateCollisionsExiting();
        updateCollisionsEntering();
    }

    private void updateCollisionsEntering() {
        for (int i = 0; i < colliders.size() - 1; i++) {
            for (int j = i + 1; j < colliders.size(); j++) {
                Collider left = colliders.get(i);
                Collider right = colliders.get(j);
                if (!left.isCollidingWith(right)) continue;

                Pair<Collider, Collider> collision = new Pair<>(left, right);
                if (staying.contains(collision))
                    continue;
                notifyCollisionEnter(left, right);
                staying.add(collision);
            }
        }
    }

    private void updateCollisionsStaying() {
        for (Pair<Collider, Collider> collision : staying) {
            Collider left = collision.getKey();
            Collider right = collision.getValue();
            if (left.isCollidingWith(right)) {
                notifyCollisionStay(left, right);
            } else {
                exiting.add(collision);
            }
        }
    }

    private void updateCollisionsExiting() {
        for (Pair<Collider, Collider> collision : exiting) {
            Collider left = collision.getKey();
            Collider right = collision.getValue();
            notifyCollisionExit(left, right);
            staying.remove(collision);
        }
        exiting.clear();
    }

    private void notifyCollisionEnter(Collider left, Collider right) {
        left.gameObject.onCollisionEnter(right);
        right.gameObject.onCollisionEnter(left);
    }

    private void notifyCollisionStay(Collider left, Collider right) {
        left.gameObject.onCollisionStay(right);
        right.gameObject.onCollisionStay(left);
    }

    private void notifyCollisionExit(Collider left, Collider right) {
        left.gameObject.onCollisionExit(right);
        right.gameObject.onCollisionExit(left);
    }
}
