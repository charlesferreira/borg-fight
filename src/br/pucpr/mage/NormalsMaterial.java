package br.pucpr.mage;

import br.pucpr.mage.phong.SimpleMaterial;

public class NormalsMaterial extends SimpleMaterial {
    public NormalsMaterial() {
        super("/br/pucpr/mage/resource/geometry/normals.vert",
              "/br/pucpr/mage/resource/geometry/normals.geom",
              "/br/pucpr/mage/resource/geometry/normals.frag"
        );
    }
}
