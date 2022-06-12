## Store exercise

### Entorno:
- jdk8
- postgresql


### *Pasos para crear el contenedor:*
1. Abrir el *cmd* en la ruta base del proyecto
2. Escribir y ejecutar `mvn clean package -DskipTests`
3. Copiar el jar del target hacia la carpeta ubicada en *src/main/docker*
4. Ejecutar `docker-composer up`

### Para cerrar el contenedor:
- Abrir el cmd en la carpeta *src/main/docker* y ejecutar `docker-compose down`


