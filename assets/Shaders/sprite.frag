#version 330 core
#ifdef GL_ES
#define LOWP lowp
precision mediump float;
#else
#define LOWP
#endif

in vec4 v_color;
in vec2 v_texCoords;
in vec4 position;

uniform sampler2D u_texture;
out vec4 fragColor;

void main()
{
  fragColor =  v_color * texture(u_texture, v_texCoords);
}