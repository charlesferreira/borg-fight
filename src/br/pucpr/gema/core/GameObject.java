package br.pucpr.gema.core;

import br.pucpr.gema.core.components.MeshRenderer;
import br.pucpr.gema.core.components.Transform;
import br.pucpr.gema.graphics.IDrawable;
import org.joml.Matrix4f;

import java.lang.reflect.InvocationTargetException;
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

    private void preInit() {
        renderer = (MeshRenderer) addComponent(MeshRenderer.class);
        transform = (Transform) addComponent(Transform.class);
    }

    protected void init() {
    }

    public final void update() {
        components.forEach(GameComponent::update);
        children.forEach(GameObject::update);
        components.forEach(GameComponent::lateUpdate);
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
        return this;
    }

    public GameObject setParent(GameObject parent) {
        removeFromParent();
        parent.children.add(this);
        this.parent = parent;
        return this;
    }

    public GameObject removeFromParent() {
        if (parent != null) {
            parent.removeChild(this);
            parent = null;
        }

        return this;
    }

    public GameObject getParent() {
        return parent;
    }

    public GameComponent addComponent(Class<? extends GameComponent> componentClass) {
        try {
            GameComponent component = componentClass.getDeclaredConstructor(GameObject.class).newInstance(this);
            component.awake();
            components.add(component);
            return component;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    public GameComponent getComponent(Class<? extends GameComponent> componentClass) {
        for (GameComponent c : components) {
            if (componentClass.isAssignableFrom(c.getClass()))
                return c;
        }
        return null;
    }

    public static GameObject instantiate() {
        return prepareInstance(new GameObject());
    }

    public static GameObject instantiate(Class<? extends GameObject> prefabClass) {
        try {
            return prepareInstance(prefabClass.newInstance());
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static GameObject instantiate(Class<? extends GameObject> prefabClass, GameObject parent) {
        GameObject instance = instantiate(prefabClass);
        if (instance != null)
            instance.setParent(parent);
        return instance;
    }

    private static GameObject prepareInstance(GameObject instance) {
        instance.preInit();
        instance.init();
        SceneManager.getActiveScene().addChild(instance);
        return instance;
    }
}
