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

    public Matrix4f getAffineMatrix() {
        return new Matrix4f().translationRotateScale(position, rotation, scale);
    }

    public Matrix4f getWorld() {
        return gameObject.getParent() != null
                ? gameObject.getParent().transform.getAffineMatrix()
                : new Matrix4f();
    }

    public Matrix4f getWorldInverse() {
        return getWorld().invert();
    }

    public Vector3f getLocalPosition() {
        return new Vector3f(position);
    }

    public Vector3f getWorldPosition() {
        return getWorld().transformPosition(getLocalPosition());
    }

    public Quaternionf getLocalRotation() {
        return new Quaternionf(rotation);
    }

    // todo: testar esse método, fiz com sono
    public Quaternionf getWorldRotation() {
        Quaternionf rotation = getLocalRotation();
        AxisAngle4f axisAngle = new AxisAngle4f();
        getWorld().rotate(rotation).getRotation(axisAngle);
        axisAngle.get(rotation);
        return rotation;
    }

    public Vector3f getLocalScale() {
        return new Vector3f(scale);
    }

    // todo: testar esse método, fiz com sono
    public Vector3f getWorldScale() {
        Vector3f scale = new Vector3f();
        return getWorld().getScale(scale).fma(1, this.scale);
    }

    public Transform translate(Vector3f translation) {
        return translate(translation, Space.SELF);
    }

    public Transform translate(Vector3f translation, Space relativeTo) {
        if (relativeTo == Space.SELF)
            getAffineMatrix().transformDirection(translation);
        else
            getWorldInverse().transformDirection(translation);
        position.add(translation);
        return this;
    }

    public Transform setPosition(Vector3f position) {
        return setPosition(position, Space.SELF);
    }

    public Transform setPosition(Vector3f position, Space relativeTo) {
        if (relativeTo == Space.WORLD && gameObject.getParent() != null)
            getWorld().transformDirection(position);
        this.position.set(position);
        return this;
    }

    public Transform rotate(float radians, Vector3f axis) {
        return rotate(radians, axis, Space.SELF);
    }

    public Transform rotate(float radians, Vector3f axis, Space relativeTo) {
        if (relativeTo == Space.WORLD)
            getWorldInverse().transformDirection(axis);
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
            getWorld().rotate(rotation);
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
}
