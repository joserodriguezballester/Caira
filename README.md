
# Caira

## Vistas

### SplashScreen

 <img src="./images(README)/Captura6.PNG " alt="drawing" width="450"/>

### Bienvenida y login

- Distinto formato en horizontal y vertical
- Con pager view y scroll

<img src="./images(README)/Captura3.PNG " alt="drawing" width="150"/>
<img src="./images(README)/Captura4.PNG " alt="drawing" width="165"/>
<img src="./images(README)/Captura5.PNG " alt="drawing" width="400"/>

### Register

- Distinto formato en horizontal y vertical

<img src="./images(README)/Captura8.PNG " alt="drawing" width="200"/>
<img src="./images(README)/Captura7.PNG " alt="drawing" width="400"/>

### Dashboard

<img src="./images(README)/Captura12.PNG " alt="drawing" width="200"/>

## Al modificar Datos del Servidor

### ApiService.kt
- la ruta de las imagenes es BASE_IMG_URL
- la ruta de la Api es BASE_URL

## Realizado

- SplashScreen
  - Inicio si hay share preferents
- Bienvenida
- Login
  - register
  - remember me
  - validado de campos
- Registro
  - validado de campos
- Dashboard
  - Listado de cursos
  - Detalle del curso
- Navigation Drawer
- Menu overflow (BodyAppActivity.kt)
  - Logout
- Agregadas fuentes

## Pendiente Futuro inmediato

- ~~Validar campos formularios~~
- ~~Inicio cuando hay share preferents~~
  - ~~Cuando se registra hacer login para obtener token~~(Falta comprobar, servidor en update)
- ~~Diseño en pantalla horizontal~~
- ~~Actualizar la app a la nueva API (1.1.0)~~


## Pendiente Futuro cercano

1. Centros
  1.1. Listado cursos x centro
1.2. Feed
1.3. Info

2. Forgot password
3. Account Settings
4. Social profile

## Pendiente Futuro lejano

0. chat
1. ¿Hacer y subir Fotos?
2. Como subir la aplicacion.
3. Como realizar actualizaciones

### TODO

- ~~¿Controlar conexion a Internet?~~ (Verificar e indicar al usuario)
- ~~Controlar Datos de entrada antes de hacer peticion servidor en formularios~~
- Control de Errores
  - Imagen no encontrada
- ~~Poner Loadings~~
- Refrescar Token
- Quitar loading cuando no esta logueado
- HTTP 503 Service Unavailable

- Para produccion
      - ~~Eliminar MainActivity~~
      - Eliminar Logs  (buscar por "msg*****")
      - Eliminar Tokens, usuarios o demas puestos a pelo
      - Eliminar fotos, draws inservibles

#### En modo desarrollo controlar

- Ir directamente a la Activity de prueba (SplashActivity goTo)

- Usuario registrado
  jar@mail.com
  123

#### Comentarios

- La API con: "correo que no existe" -> devuelve (code=500, message=Internal Server Error, result=null, status=Internal Server Error)


- error 403 -> 401

  - EndPoint Feed
  - beared
