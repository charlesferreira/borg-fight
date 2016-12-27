#version 330

uniform samplerCube uCubemap;

in vec3 vTexCoord;

out vec4 outColor;

void main() {
    outColor = texture(uCubemap, vTexCoord);
}