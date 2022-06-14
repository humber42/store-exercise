## Store exercise

### Entorno:
- jdk8
- postgresql
- keycloak


### *Pasos para crear el contenedor:*
1. Abrir el *cmd* en la ruta base del proyecto
2. Escribir y ejecutar `mvn clean package -DskipTests`
3. Ejecutar `docker-composer up`
4. Ir a la dirección http://localhost:8080 y seleccionar *Administrative console*
5. Ingresar:
   1. user: `admin`
   2. password: `password`
6. Crear un realm llamado `SpringBootKeycloak`
7. Crear un cliente llamado `store-exercise`
8. Crear un usuario como estime conveniente, luego asignarle una contraseña deshabilitar Temporary

### Usar API:
1. Ir a la dirección url: http://localhost:8081/swagger-ui/index.html
   1. Usar el servicio de login para obtener el JWT
   2. Copiar el token y luego tocar el botón que aparece antes del primer servicio, **Authorize**.
   3. En el diálogo que aparece ingresar `Bearer `+ *token*, dar clic en el botón Authorize
   4. Luego usar el swagger a conveniencia
2. Puede importar desde el *Postman* o *Insomnia* la API desde la dirección:
   - http://localhost:8081/v2/api-docs

### Para cerrar el contenedor:
- Abrir el cmd en la carpeta *src/main/docker* y ejecutar `docker-compose down`




