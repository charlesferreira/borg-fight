package br.pucpr.gema.core.components;

import br.pucpr.gema.core.GameComponent;
import br.pucpr.gema.core.GameObject;
import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public class Transform extends GameComponent {
    private Vector3f localPosition;
    private Vector3f localScale;
    private Quaternionf localRotation;

    public Transform(GameObject gameObject) {
        super(gameObject);
        localPosition = new Vector3f();
        localRotation = new Quaternionf();
        localScale = new Vector3f(1f, 1f, 1f);
    }

    public Vector3f getLocalPosition() {
        return localPosition;
    }

    public Vector3f getPosition() {
        return getWorldMatrix().transformPosition(localPosition);
    }

    public Vector3f getLocalScale() {
        return localScale;
    }

    public Vector3f getScale() {
        Vector3f scale = new Vector3f();
        getWorldMatrix().scale(localScale).getScale(scale);
        return scale;
    }

    public Quaternionf getLocalRotation() {
        return localRotation;
    }

    public Quaternionf getRotation() {
        Quaternionf rotation = new Quaternionf();
        getWorldMatrix().rotate(localRotation).getUnnormalizedRotation(rotation);
        return rotation;
    }

    public Matrix4f getWorldMatrix() {
        GameObject parent = gameObject.getParent();
        return parent != null
                ? parent.transform.getWorldMatrix().mul(parent.transform.getLocalMatrix())
                : new Matrix4f();
    }

    public Matrix4f getLocalMatrix() {
        return new Matrix4f().translationRotateScale(
                localPosition, localRotation, localScale);
    }

    public Vector3f getRight() {
        return new Vector3f(1, 0, 0).rotate(getRotation());
    }

    public Vector3f getLeft() {
        return new Vector3f(-1, 0, 0).rotate(getRotation());
    }

    public Vector3f getUp() {
        return new Vector3f(0, 1, 0).rotate(getRotation());
    }

    public Vector3f getDown() {
        return new Vector3f(0, -1, 0).rotate(getRotation());
    }

    public Vector3f getForward() {return new Vector3f(0, 0, 1).rotate(getRotation());}

    public Vector3f getBack() {
        return new Vector3f(0, 0, -1).rotate(getRotation());
    }

    public Transform translate(float x, float y, float z) {
        localPosition.add(x, y, z);
        return this;
    }

    public Transform translate(Vector3f offset) {
        localPosition.add(offset);
        return this;
    }

    public Transform scale(float factor) {
        localScale.mul(factor);
        return this;
    }

    public Transform setScale(float x, float y, float z) {
        localScale.set(x, y, z);
        return this;
    }

    public Transform setScale(float v) {
        localScale.set(v, v, v);
        return this;
    }

    public Transform rotate(float x, float y, float z) {
        localRotation.rotate(x, y, z);
        return this;
    }
}
