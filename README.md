
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

## Realizado

- SplashScreen
  - Inicio cuando hay share preferents
- Bienvenida
- Login
  - register
  - remember me
- Registro
- Dashboard
  - Listado de cursos
  - Detalle del curso
- Navigation Drawer
- Menu overflow (BodyAppActivity.kt)
  - Logout

## Pendiente Futuro inmediato

- ~~Inicio cuando hay share preferents~~
  - Pendiente: Cuando se registra hacer login para obtener token
- ~~Diseño en pantalla horizontal~~
- Refrescar Token

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

- ¿Controlar conexion a Internet?
- Controlar Datos de entrada antes de hacer peticion servidor en formularios
- Control de Errores
- Poner Loadings
- Refrescar Token
- HTTP 503 Service Unavailable

- Para produccion
      - ~~Eliminar MainActivity~~
      - Eliminar Logs  (buscar por "msg*****")
      - Eliminar Tokens, usuarios o demas puestos a pelo
      - Eliminar fotos, draws inservibles

#### En modo desarrollo controlar

- Ir directamente a la Activity de prueba (SplashActivity goTo)

- Usuario registrado
  joserodriguezballester@gmail.com
  123

#### Comentarios

- La API con correo que no existe devuelve (code=500, message=Internal Server Error, result=null, status=Internal Server Error)
Modificar luego en LoginFragment
  - error 403 -> 401
  - EndPoint Centros
    - EndPoint Feed
    - beared
