package br.pucpr.gema.core.components;

import br.pucpr.gema.core.GameComponent;
import br.pucpr.gema.core.GameObject;
import br.pucpr.gema.core.Space;
import org.joml.AxisAngle4f;
import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public class Transform extends GameComponent {
    private Vector3f position;
    private Quaternionf rotation;
    private Vector3f scale;

    public Transform(GameObject gameObject) {
        super(gameObject);
        position = new Vector3f();
        rotation = new Quaternionf();
        scale = new Vector3f(1f, 1f, 1f);
    }

    public Matrix4f getLocalMatrix() {
        return new Matrix4f().translationRotateScale(position, rotation, scale);
    }

    public Matrix4f getWorld() {
        Matrix4f parentWorld = gameObject.getParent() != null
                ? gameObject.getParent().transform.getWorld()
                : new Matrix4f();
        return parentWorld.mul(getLocalMatrix());
    }

    public Vector3f getLocalPosition() {
        return new Vector3f(position);
    }

    public Vector3f getWorldPosition() {
        GameObject parent = gameObject.getParent();
        if (parent != null) {
            Vector3f parentScale = parent.transform.getLocalScale();
            Vector3f parentPosition = parent.transform.getWorldPosition();
            return parentPosition.add(getLocalPosition().fma(1f, parentScale));
        } else {
            return getLocalPosition();
        }
    }

    public Quaternionf getLocalRotation() {
        return new Quaternionf(rotation);
    }

    public Quaternionf getWorldRotation() {
        Quaternionf parentRotation = gameObject.getParent() != null
                ? gameObject.getParent().transform.getWorldRotation()
                : new Quaternionf();
        return parentRotation.mul(rotation);
    }

    public Vector3f getLocalScale() {
        return new Vector3f(scale);
    }

    public Vector3f getWorldScale() {
        Vector3f parentScale = gameObject.getParent() != null
                ? gameObject.getParent().transform.getWorldScale()
                : new Vector3f(1, 1, 1);
        return parentScale.fma(1f, scale);
    }

    public Transform translate(Vector3f translation) {
        return translate(translation, Space.SELF);
    }

    public Transform translate(Vector3f translation, Space relativeTo) {
        if (relativeTo == Space.WORLD)
            translation.rotate(getWorldRotation().invert());
        position.add(translation);
        return this;
    }

    public Transform setPosition(Vector3f position) {
        return setPosition(position, Space.SELF);
    }

    public Transform setPosition(Vector3f position, Space relativeTo) {
        if (relativeTo == Space.WORLD)
            getWorld().invert().transformDirection(position); // todo: testar isso (relativeTo == Space.WORLD)
        this.position.set(position);
        return this;
    }

    public Transform rotate(float radians, Vector3f axis) {
        return rotate(radians, axis, Space.SELF);
    }

    public Transform rotate(float radians, Vector3f axis, Space relativeTo) {
        if (relativeTo == Space.WORLD)
            getWorld().invert().transformDirection(axis);
        this.rotation.rotateAxis(radians, axis);
        return this;
    }

    public Transform setRotation(float x, float y, float z) {
        return setRotation(new Quaternionf().rotateXYZ(x, y, z));
    }

    public Transform setRotation(Quaternionf rotation) {
        return setRotation(rotation, Space.SELF);
    }

    public Transform setRotation(Quaternionf rotation, Space relativeTo) {
        if (relativeTo == Space.WORLD && gameObject.getParent() != null)
            rotation = gameObject.getParent().transform.getWorldRotation().mul(rotation);
        this.rotation.set(rotation);
        return this;
    }

    public Transform scale(float factor) {
        scale.mul(factor);
        return this;
    }

    public Transform scale(float x, float y, float z) {
        scale.mul(x, y, z);
        return this;
    }

    public Transform scale(Vector3f fma) {
        scale.fma(1, fma);
        return this;
    }

    public Transform setScale(float x, float y, float z) {
        scale.set(x, y, z);
        return this;
    }

    public Transform setScale(Vector3f scale) {
        this.scale.set(scale);
        return this;
    }

    public Transform setScale(float scale) {
        this.scale.set(scale);
        return this;
    }

    public Vector3f right() {
        return new Vector3f(1, 0, 0).rotate(getWorldRotation());
    }

    public Vector3f left() {
        return new Vector3f(-1, 0, 0).rotate(getWorldRotation());
    }

    public Vector3f up() {
        return new Vector3f(0, 1, 0).rotate(getWorldRotation());
    }

    public Vector3f down() {
        return new Vector3f(0, -1, 0).rotate(getWorldRotation());
    }

    public Vector3f back() {
        return new Vector3f(0, 0, 1).rotate(getWorldRotation());
    }

    public Vector3f forward() {
        return new Vector3f(0, 0, -1).rotate(getWorldRotation());
    }
}
