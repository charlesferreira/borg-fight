package br.pucpr.gema.core;

import br.pucpr.gema.core.components.MeshRenderer;
import br.pucpr.gema.core.components.Transform;
import br.pucpr.gema.graphics.IDrawable;
import org.joml.Matrix4f;

import java.util.ArrayList;
import java.util.List;

public class GameObject implements IDrawable {
    public MeshRenderer renderer;
    public Transform transform;
    private List<GameComponent> components = new ArrayList<>();
    private List<GameObject> children = new ArrayList<>();
    private GameObject parent;

    protected GameObject() {
    }

    void preInit() {
        transform = addComponent(Transform.class);
        renderer = addComponent(MeshRenderer.class);
    }

    protected void init() {
    }

    final void fixedUpdate() {
        float deltaTime = Time.deltaTime;
        while (deltaTime > Time.fixedDeltaTime) {
            deltaTime -= Time.fixedDeltaTime;
            components.forEach(GameComponent::fixedUpdate);
        }

        children.forEach(GameObject::fixedUpdate);
    }

    public final void update() {
        components.forEach(GameComponent::update);
        children.forEach(GameObject::update);
    }

    final void lateUpdate() {
        components.forEach(GameComponent::lateUpdate);
        children.forEach(GameObject::lateUpdate);
    }

    @Override
    public final void draw(Matrix4f world) {
        world.mul(transform.getLocalMatrix());
        if (renderer != null)
            renderer.draw(world);
        children.forEach(child -> child.draw(new Matrix4f(world)));
    }

    private GameObject removeChild(GameObject child) {
        children.remove(child);
        child.parent = null;
        return this;
    }

    public GameObject setParent(GameObject parent) {
        if (parent == null) {
            SceneManager.getActiveScene().addChild(this);
        } else {
            removeFromParent();
            parent.children.add(this);
            this.parent = parent;
        }
        return this;
    }

    private GameObject removeFromParent() {
        if (parent != null)
            parent.removeChild(this);

        return this;
    }

    public GameObject getParent() {
        return parent;
    }

    public <T extends GameComponent> T addComponent(Class<T> componentClass) {
        try {
            GameComponent component = componentClass.newInstance();
            component.init(this);
            component.awake();
            components.add(component);
            return (T) component;
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public <T extends GameComponent> T getComponent(Class<T> componentClass) {
        for (GameComponent component : components) {
            if (componentClass.isAssignableFrom(component.getClass()))
                return (T) component;
        }
        return null;
    }

    public static GameObject instantiate() {
        return prepareInstance(new GameObject());
    }

    public static <T extends GameObject> T instantiate(Class<T> prefabClass) {
        try {
            return prepareInstance(prefabClass.newInstance());
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static <T extends GameObject> T instantiate(Class<T> prefabClass, GameObject parent) {
        GameObject instance = instantiate(prefabClass);
        if (instance != null)
            instance.setParent(parent);
        return (T) instance;
    }

    private static <T extends GameObject> T prepareInstance(T instance) {
        instance.preInit();
        instance.init();
        SceneManager.getActiveScene().addChild(instance);
        return instance;
    }
}
