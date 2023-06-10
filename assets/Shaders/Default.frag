#version 330 core
#ifdef GL_ES
precision mediump float;
#endif

struct Material {
    vec3 ambient;
    vec4 diffuse;
    vec3 specular;
    float shininess;

    sampler2D diffuseMap;

}; 

struct PointLight {
    vec3 position;
  
    float constant;
    float linear;
    float quadratic; 
    float intensity;

    vec3 ambient;
    vec3 diffuse;
    vec3 specular;
    
};

struct DirLight {
     vec3 direction;

     vec3 ambient;
     vec3 diffuse;
     vec3 specular;

};

in vec3 v_normal;
in vec3 v_fragPos;
in vec2 texCoords;

uniform vec3 viewPos;

uniform Material material;
uniform bool hasTexture;

#define MAX_LIGHT_ARR 16
uniform PointLight pointLights[MAX_LIGHT_ARR];
uniform int pointLightsSize;

uniform DirLight dirLights[MAX_LIGHT_ARR];
uniform int dirLightsSize;

const float screenGamma = 2.2; // Assume the monitor is calibrated to the sRGB color space  

out vec4 fragColor;



vec4 CalcPointLight(PointLight light, vec3 normal, vec3 fragPos, vec3 viewDir);

vec4 CalcDirLight(DirLight light, vec3 normal, vec3 viewDir);

void main()
{    
   
    vec3 norm = normalize(v_normal);
    vec3 viewDir = normalize(viewPos - v_fragPos);

    vec4 result = vec4(0,0,0,0);

    for(int i = 0; i < dirLightsSize; i++){
             result += CalcDirLight(dirLights[i], norm, viewDir);
    }

    for(int i = 0; i < pointLightsSize; i++){
     result += CalcPointLight(pointLights[i], norm, v_fragPos, viewDir);
    }

    //fragColor = vec4(CalcPointLight(pointLights[0],norm, v_fragPos, viewDir),1);

    if(result.a == 0){
        discard;
    }else{
        fragColor = result;
    }
}

vec4 CalcPointLight(PointLight light, vec3 normal, vec3 fragPos, vec3 viewDir){

    vec3 lightDir = normalize(light.position - fragPos);
    // diffuse shading
    float diff = max(dot(normal, lightDir), 0.0);
    // specular shading
    vec3 reflectDir = reflect(-lightDir, normal);
    float spec = pow(max(dot(viewDir, reflectDir), 0.0), material.shininess);
    // attenuation
    float distance    = length(light.position - fragPos);

    float attenuation = 1.0 / (light.constant + light.linear * distance + light.quadratic * (distance * distance));    
    // combine results
    vec3 ambient;
    vec4 diffuse;
    vec3 specular;

    if(hasTexture){
        ambient  = light.ambient * vec3(texture(material.diffuseMap,texCoords));
        diffuse  = vec4((light.diffuse  * diff),1) * texture(material.diffuseMap,texCoords);
        specular = light.specular * spec * material.specular;
    }else{
         ambient  = light.ambient  * material.diffuse.rgb * vec3(texture(material.diffuseMap,texCoords));
         diffuse  = vec4((light.diffuse  * diff),1) * material.diffuse * texture(material.diffuseMap,texCoords);
         specular = light.specular * spec * material.specular;

    }
    ambient  = ambient * attenuation;
    diffuse  = diffuse * attenuation;
    specular = specular * attenuation;
    return (vec4(ambient,0) + diffuse + vec4(specular,0));

}

vec4 CalcDirLight(DirLight light, vec3 normal, vec3 viewDir)
{
    vec3 lightDir = normalize(-light.direction);
    // diffuse shading
    float diff = max(dot(normal, lightDir), 0.0);
    // specular shading
    vec3 reflectDir = reflect(-lightDir, normal);
    float spec = pow(max(dot(viewDir, reflectDir), 0.0), material.shininess);
    // combine results
       vec3 ambient;
       vec4 diffuse;
       vec3 specular;

       if(hasTexture){
           ambient  = light.ambient * vec3(texture(material.diffuseMap,texCoords));
           diffuse  = vec4((light.diffuse  * diff),1) * texture(material.diffuseMap,texCoords);
           specular = light.specular * spec * material.specular;
       }else{
            ambient  = light.ambient  * material.diffuse.rgb * vec3(texture(material.diffuseMap,texCoords));
            diffuse  = vec4((light.diffuse  * diff),1) * material.diffuse * texture(material.diffuseMap,texCoords);
            specular = light.specular * spec * material.specular;

       }
    return (vec4(ambient,0) + diffuse + vec4(specular,0));
}