## Store exercise

### Entorno:
- jdk8
- postgresql
- keycloak


### *Pasos para crear el contenedor:*
1. Abrir el *cmd* en la ruta base del proyecto
2. Escribir y ejecutar `mvn clean package -DskipTests`
3. Ejecutar `docker-compose up`
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
   4. Luego usar el swagger:
      1. Para crear un cliente puede usar el payload siguiente:
         ```json
         {
         "apellido": "Perez",
         "dni": "56071425686",
         "email": "juan.perez@mail.com",
         "nombre": "Juan",
         "telefono": "+5358987523"
         }
         ```
      2. Para crear un producto puede usar el payload siguiente:
         ```json
         {
         "nombre": "Cebolla",
         "precio": 20.3
         }
         ```
      3. Para crear una venta puede usar el payload siguiente:
         *(Para el cliente puede pasar el objeto entero o puede enviar solamente el id)*
      ```json  
        {
         "cliente": {
            "apellido": "Perez",
            "dni": "56071425686",
            "email": "juan.perez@mail.com",
            "id": 1,
            "nombre": "Juan",
            "telefono": "+5358987523"
         },
         "fecha": "2022-06-14"
         }
      ```
      o tambien
      ```json
        {
         "cliente": {
           "apellido": "",
           "dni": "",
           "email": "",
           "id": 1,
           "nombre": "",
           "telefono": ""
           },
        "fecha": "2022-06-14"
         }
        ```
      
      4. Para registrar un detalle venta puede usar el payload siguiente
        ```json 
      {
         "idProducto": 1,
         "idVenta": 1
         }
      ```
   
2. Puede importar desde el *Postman* o *Insomnia* la API desde la dirección:
   - http://localhost:8081/v2/api-docs

### Para cerrar el contenedor:
- Abrir el cmd en la carpeta *src/main/docker* y ejecutar `docker-compose down`




