#version 330

out vec4 outColor;

uniform sampler2D uRefraction;
in vec4 refractPos;

uniform sampler2D uReflection;
in vec4 reflectPos;

in vec3 vViewPath;

void main(void) 
{
    vec2 refractCoords = vec2(
            refractPos.x / refractPos.w / 2.0 + 0.5,
            refractPos.y / refractPos.w / 2.0 + 0.5
    );

    vec2 reflectCoords = vec2(
        reflectPos.x / reflectPos.w / 2.0 + 0.5,
        reflectPos.y / reflectPos.w / 2.0 + 0.5
    );

    vec4 refractColor = texture(uRefraction, refractCoords);
    vec4 reflectColor = texture(uReflection, reflectCoords);

    float fresnel = dot(normalize(vViewPath), vec3(0, 1, 0));
    vec4 dullColor = vec4(1, 1, 0, 1);

    outColor = mix(mix(reflectColor, refractColor, fresnel), dullColor, 0.5);
}