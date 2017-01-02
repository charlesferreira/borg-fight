package br.pucpr.gema.core.objects;

import br.pucpr.gema.core.GameComponent;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.BufferUtils;

import java.nio.IntBuffer;

import static org.lwjgl.glfw.GLFW.glfwGetCurrentContext;
import static org.lwjgl.glfw.GLFW.glfwGetWindowSize;

public class Camera extends GameComponent {
    private float fov = (float) Math.toRadians(60);
    private float near = 0.1f;
    private float far = 1000.0f;
    private Vector3f position = new Vector3f();
    private float aspect;
    private Matrix4f view = new Matrix4f();
    private Matrix4f projection = new Matrix4f();

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

    @Override
    public void lateUpdate() {
        updatePosition();
        updateAspect();
        updateViewMatrix();
        updateProjectionMatrix();
    }

    private void updatePosition() {
        position = gameObject.transform.getWorldPosition();
    }

    private void updateAspect() {
        IntBuffer w = BufferUtils.createIntBuffer(1);
        IntBuffer h = BufferUtils.createIntBuffer(1);
        long window = glfwGetCurrentContext();
        glfwGetWindowSize(window, w, h);
        aspect = w.get() / (float) h.get();
    }

    private void updateViewMatrix() {
        Matrix4f world = gameObject.transform.getWorld();

        // posição e direção da câmera no mundo
        Vector3f eye = world.transformPosition(new Vector3f());
        Vector3f target = world.transformDirection(new Vector3f(0, 0, -1)).add(eye);
        Vector3f direction = new Vector3f(target).sub(eye);

        // correção do vetor "para cima"
        Vector3f up = world.transformDirection(new Vector3f(0, 1, 0));
        Vector3f side = new Vector3f();
        direction.cross(up, side);
        side.cross(direction, up);
        up.normalize();

        view = new Matrix4f().lookAt(eye, target, up);
    }

    public void updateProjectionMatrix() {
        projection = new Matrix4f().setPerspective(fov, getAspect(), near, far);
    }

    public Vector3f getPosition() {
        return position;
    }

    public float getAspect() {
        return aspect;
    }

    public Matrix4f getView() {
        return view;
    }

    public Matrix4f getProjection() {
        return projection;
    }
}
