#version 330 core
#ifdef GL_ES
precision mediump float;
#endif


in vec3 v_normal;
in vec3 v_fragPos;
in vec2 texCoords;

uniform sampler2D spriteTexture;
uniform vec3 viewPos;

uniform bool hasTexture;


const float screenGamma = 2.2; // Assume the monitor is calibrated to the sRGB color space

out vec4 fragColor;


void main()
{
    vec4 result = vec4(0,0,0,0);
    if(hasTexture)
      result = texture(spriteTexture,texCoords);

    if(result.a == 0) discard;

    fragColor = result;

}

