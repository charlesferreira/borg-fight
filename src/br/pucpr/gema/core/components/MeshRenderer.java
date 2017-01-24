package br.pucpr.gema.core.components;

import br.pucpr.gema.core.GameComponent;
import br.pucpr.gema.core.GameScene;
import br.pucpr.gema.core.SceneManager;
import br.pucpr.gema.core.objects.Camera;
import br.pucpr.gema.graphics.materials.DefaultMaterial;
import br.pucpr.mage.Material;
import br.pucpr.mage.Mesh;
import br.pucpr.mage.Shader;
import org.joml.Matrix4f;

public class MeshRenderer extends GameComponent {
    public Mesh mesh;
    public Material material;

    @Override
    public void awake() {
        material = new DefaultMaterial();
    }

    public void draw(Matrix4f world) {
        if (mesh == null) return;
        GameScene scene = SceneManager.getActiveScene();
        Camera camera = scene.camera.getComponent(Camera.class);
        Shader shader = material.getShader();
        shader.bind()
                .setUniform("uView", camera.getView())
                .setUniform("uProjection", camera.getProjection())
                .setUniform("uCameraPosition", camera.getPosition());

        scene.light.apply(shader);
        shader.unbind();

        mesh.setUniform("uWorld", world);
        mesh.draw(material);
    }

    public Mesh getMesh() {
        return mesh;
    }

    public MeshRenderer setMesh(Mesh mesh) {
        this.mesh = mesh;
        return this;
    }

    public Material getMaterial() {
        return material;
    }

    public MeshRenderer setMaterial(Material material) {
        this.material = material;
        return this;
    }
}
