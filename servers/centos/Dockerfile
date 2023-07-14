FROM centos:7

# Establecer el usuario y la contraseña
RUN useradd -m devops && \
    echo 'devops:1234' | chpasswd

# Instalar Nginx y OpenSSH
RUN yum -y install epel-release && \
    yum -y install nginx openssh-server

#Crear folder /home
RUN mkdir /usr/share/nginx/html/home/ && chown devops:devops /usr/share/nginx/html/home/
# Copiar la configuración personalizada de Nginx
COPY nginx.conf /etc/nginx/nginx.conf
COPY index.html /usr/share/nginx/html/home/index.html

# Configurar el acceso SSH
RUN ssh-keygen -A && \
    sed -i 's/#PermitRootLogin yes/PermitRootLogin yes/' /etc/ssh/sshd_config

# Exponer los puertos 80 (HTTP) y 22 (SSH)
EXPOSE 80 22

# Iniciar Nginx y SSH cuando se inicie el contenedor
CMD /usr/sbin/sshd -D & nginx -g "daemon off;"
