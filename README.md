# Laboratorio jenkins CI/CD

## Tabla de contenido

1. [Introducción](#Introduccion)
2. [Configuracion Ambiente](#Configuracion-ambiente)


## Introduccion

En este laboratorio, se llevará a cabo un ejercicio de CI/CD en el ciclo de desarrollo de software. El objetivo es proporcionar una experiencia práctica y didáctica para explicar la creación de un pipeline declarativo en Jenkins, así como el análisis con SonarQube y el despliegue de fuentes desde GitHub a un servidor de Apache o Nginx.

Durante este ejercicio, los participantes podrán aprender cómo configurar y automatizar un flujo de trabajo de desarrollo de software utilizando herramientas comunes en el ámbito de CI/CD. El enfoque se centra en mostrar cómo se pueden integrar diferentes etapas del proceso, como la construcción, el análisis de calidad y el despliegue, utilizando Jenkins como plataforma principal.

Se realizarán demostraciones prácticas y se explicarán los conceptos clave relacionados con la configuración de un pipeline declarativo en Jenkins. También se destacará la importancia del análisis de código estático utilizando SonarQube para mejorar la calidad del código y reducir posibles problemas en el desarrollo.

Además, se presentará cómo desplegar las fuentes del repositorio de GitHub en un servidor de Apache o Nginx, lo que permitirá a los participantes comprender cómo se puede automatizar el proceso de implementación de aplicaciones.

Este laboratorio proporcionará a los profesionales funcionales y a los especialistas en calidad en TIC una visión general de las prácticas y herramientas utilizadas en CI/CD, y cómo estas contribuyen a mejorar la eficiencia y la calidad en el desarrollo de software.

## Configuracion ambiente

1. Infraestructura utilizada: 
    - docker-compose
    - docker
    - dockerfile
    - sonarqube
    - jenkins
    - nginx
    - pipeline declarativo
    - lenguaje groovy
    - bash/sh
2. Configuracion red local docker. Ejecutar el siguiente comando

```bash
docker network create --subnet 10.20.2.0/24 --gateway 10.20.2.254 charter
```

3. Creacion y configuracion **docker-compose.yml** de las herramientas sonarqube, jenkins
    - Generar imagen jenkins con los plugin ya pre-instalados esto se realiza armando una imagen y los plugin.txt seguir los pasos acontinuacion.

    Este comando genera el build de la imagen del dockerfile que se encuentra en la carpeta **jenkins-dockerfile**
```bash
docker build -t repository/jenkins-charter:1.0 [rutaDockerfile]
```
    - Al configurar el docker-compose.yml con la imagen generada de jenkins y red local proceder a desplegar el ambiente.

```bash
#comando para desplegar el docker compose ejecutar en la raiz del archivo docker-compose.yml
docker compose up -d



docker ps 
#ouput validacion del despliegue
laboratorio-charter docker ps
CONTAINER ID   IMAGE                           COMMAND                  CREATED              STATUS              PORTS                               NAMES
1ed60014827d   sonarqube:lts-community         "/opt/sonarqube/dock…"   About a minute ago   Up About a minute   0.0.0.0:9000->9000/tcp              sonarqubeCharter
8f5d88df416c   maikol555/jenkins-charter:1.0   "/usr/bin/tini -- /u…"   About a minute ago   Up 59 seconds       0.0.0.0:8080->8080/tcp, 50000/tcp   jenkinsCharter

```


    - al iniciar jenkins realizar las recomendaciones de la interfaz.

4. Configuracion sonarqube en jenkins
    integrar sonarqube con jenkins.
    [video tutorial configuracion jenkins sonarqube](https://www.youtube.com/watch?v=KsTMy0920go&ab_channel=CloudBeesTV)
5. Configurar agent kubernetes en jenkins
    agente kubernetes en jenkins
    [video tutorial](https://www.youtube.com/watch?v=ZXaorni-icg&t=51s&ab_channel=CloudBeesTV)