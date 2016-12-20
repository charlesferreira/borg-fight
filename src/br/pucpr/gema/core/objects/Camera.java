package br.pucpr.gema.core.objects;

import br.pucpr.gema.core.GameObject;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.BufferUtils;

import java.nio.IntBuffer;

import static org.lwjgl.glfw.GLFW.glfwGetCurrentContext;
import static org.lwjgl.glfw.GLFW.glfwGetWindowSize;

public class Camera extends GameObject {
    private float fov = (float) Math.toRadians(60);
    private float near = 0.1f;
    private float far = 1000.0f;

    public Camera() {
        super(false);
    }

    public float getFov() {
        return fov;
    }

    public void setFov(float fov) {
        this.fov = fov;
    }

    public float getNear() {
        return near;
    }

    public void setNear(float near) {
        this.near = near;
    }

    public float getFar() {
        return far;
    }

    public void setFar(float far) {
        this.far = far;
    }

    private float getAspect() {
        IntBuffer w = BufferUtils.createIntBuffer(1);
        IntBuffer h = BufferUtils.createIntBuffer(1);
        long window = glfwGetCurrentContext();
        glfwGetWindowSize(window, w, h);
        return w.get() / (float) h.get();
    }

    public Matrix4f getViewMatrix() {
        return new Matrix4f().lookAt(
                transform.getPosition(),
                new Vector3f(),
                transform.getUp());
    }

    public Matrix4f getProjectionMatrix() {
        return new Matrix4f().perspective(fov, getAspect(), near, far);
    }
}
