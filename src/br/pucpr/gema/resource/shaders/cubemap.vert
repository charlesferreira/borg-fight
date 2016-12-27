#version 330

uniform mat4 uProjection;
uniform mat4 uView;

in vec3 aPosition;

out vec3 vTexCoord;

void main() {
    gl_Position =  uProjection * uView * vec4(aPosition, 1.0);
    vTexCoord = aPosition;
}