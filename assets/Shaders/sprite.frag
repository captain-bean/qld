#version 330 core
#ifdef GL_ES
#define LOWP lowp
precision mediump float;
#else
#define LOWP
#endif
in LOWP vec4 v_color;
in vec2 v_texCoords;
uniform sampler2D u_texture;

    void main()
    {
      gl_FragColor =  texture2D(u_texture, v_texCoords);

    }