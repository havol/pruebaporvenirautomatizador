# language: es
# author: Hector Alejandro Vargas Ochoa
# email: havargas8a@gmail.com
# Escenario corresponde al Requerimiento 1
# Automatización de pruebas para los servicios web de https://reqres.in/.
# Escenario del gherkin se encuentra en español, Nombramientos de feature, y Test en Español,
# El resto se encuentra codificación en idioma inglés se hace más que nada por prácticidad y estandarización universal.

@ApiWebRegres
Característica: Validar los servicios web de Reqres

  Yo como TAE(Test automation engineer)
  Quiero realizar una prueba de los servicios web de Reqres
  Para comprobar el estado, la respuesta, la robustes y control de errores del servicio web
  En Listar Usuarios, Crear nuevo usuario, Actualizar un usuario existente y Eliminar un usuario existente

  Antecedentes:
  Dado que accedo al servicio de ReqRes

  @ListUser
  Escenario: Listar usuarios
    Cuando realizo una solicitud GET al endpoint de "Listar Usuarios"
    Entonces la respuesta debe contener la lista de usuarios en la página "2" y el estado del endpoint Listar usuarios debe ser "200".

  @CreateUser
  Escenario: Crear un nuevo usuario
    Dado que tengo los datos para crear un nuevo usuario
    Cuando realizo una solicitud POST al endpoint Crear Usuario con los datos del usuario
    Entonces la respuesta debe indicar que el usuario fue creado exitosamente con un estado de "201" Y debe devolver el ID del usuario creado.

  @UpdateUser
  Escenario: Actualizar un usuario existente
    Dado que tengo un ID de usuario existente
    Cuando realizo una solicitud PUT al endpoint Actualizar Usuario con datos actualizados
    Entonces la respuesta debe indicar que el usuario fue actualizado exitosamente con un estado de "200" y debe devolver los datos actualizados.

  @DeleteUser
  Escenario: Eliminar un usuario existente
    Dado que tengo un ID de usuario existente
    Cuando realizo una solicitud DELETE al endpoint Eliminar Usuario
    Entonces la respuesta debe devolver un estado de "204", indicando que el usuario fue eliminado exitosamente.