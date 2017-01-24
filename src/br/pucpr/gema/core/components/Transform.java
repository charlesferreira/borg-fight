package br.pucpr.gema.core.components;

import br.pucpr.gema.core.GameComponent;
import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public class Transform extends GameComponent {
    private Vector3f localPosition;
    private Quaternionf localRotation;
    private Vector3f localScale;

    @Override
    public void awake() {
        localPosition = new Vector3f();
        localRotation = new Quaternionf();
        localScale = new Vector3f(1f, 1f, 1f);
    }

    private Matrix4f getLocal() {
        return new Matrix4f().translationRotateScale(localPosition, localRotation, localScale);
    }

    public Matrix4f getWorld() {
        if (gameObject.getParent() == null)
            return getLocal();

        return getParentWorld().mul(getLocal());
    }

    private Matrix4f getParentWorld() {
        if (gameObject.getParent() == null)
            return new Matrix4f();

        return gameObject.getParent().transform.getWorld();
    }

    public Vector3f getLocalPosition() {
        return new Vector3f(localPosition);
    }

    public Vector3f getWorldPosition() {
        if (gameObject.getParent() == null)
            return getLocalPosition();

        return getParentWorld().transformPosition(getLocalPosition());
    }

    public Quaternionf getLocalRotation() {
        return new Quaternionf(localRotation);
    }

    public Quaternionf getWorldRotation() {
        if (gameObject.getParent() == null)
            return getLocalRotation();

        return getLocalRotation().mul(gameObject.getParent().transform.getWorldRotation());

    }

    public Vector3f getLocalScale() {
        return new Vector3f(localScale);
    }

    public Vector3f getWorldScale() {
        if (gameObject.getParent() == null)
            return localScale;

        return gameObject.getParent().transform.getWorldScale().fma(1f, localScale);
    }

    public Transform translate(Vector3f translation) {
        localPosition.add(translation);
        return this;
    }

    public Transform translate(float x, float y, float z) {
        localPosition.add(x, y, z);
        return this;
    }

    public Transform setLocalPosition(Vector3f position) {
        this.localPosition.set(position);
        return this;
    }

    public Transform setLocalPosition(float x, float y, float z) {
        setLocalPosition(new Vector3f(x, y, z));
        return this;
    }

    public Transform setWorldPosition(Vector3f position) {
        if (gameObject.getParent() != null)
            getParentWorld().invert().transformPosition(position);

        this.localPosition.set(position);
        return this;
    }

    public Transform rotateLocal(float radians, Vector3f axis) {
        this.localRotation.rotateAxis(radians, axis);
        return this;
    }

    public Transform setLocalRotation(float x, float y, float z) {
        localRotation.set(x, y, z);
        return this;
    }

    public Transform setLocalRotation(Quaternionf rotation) {
        this.localRotation.set(rotation);
        return this;
    }

    public Transform setWorldRotation(Quaternionf rotation) {
        if (gameObject.getParent() != null)
            rotation.mul(gameObject.getParent().transform.getWorldRotation().invert());

        this.localRotation.set(rotation);
        return this;
    }

    public Transform scale(float factor) {
        localScale.mul(factor);
        return this;
    }

    public Transform scale(float x, float y, float z) {
        localScale.mul(x, y, z);
        return this;
    }

    public Vector3f right() {
        return getWorld().transformDirection(new Vector3f(1, 0, 0));
    }

    public Vector3f left() {
        return getWorld().transformDirection(new Vector3f(-1, 0, 0));
    }

    public Vector3f up() {
        return getWorld().transformDirection(new Vector3f(0, 1, 0));
    }

    public Vector3f down() {
        return getWorld().transformDirection(new Vector3f(0, -1, 0));
    }

    public Vector3f back() {
        return getWorld().transformDirection(new Vector3f(0, 0, 1));
    }

    public Vector3f forward() {
        return getWorld().transformDirection(new Vector3f(0, 0, -1));
    }
}
