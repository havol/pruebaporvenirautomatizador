# Floristería Mundo Flor – Floristerías en Medellín Website - Automatización usando el Patrón Screenplay + Cucumber Y Pruebas ReqRes con RestAssured y SerenityRest

## Introducción del proyecto

Es un Reto Técnico Porvenir QA generalista con automatización hecho en Selenium, Gradle, Cucumber, Servicios web RestAssured - SerenityRest y con el Patrón Screenplay en Java para Automatizar la página de Floristería Mundo Flor – Floristerías en Medellín.

## Escenarios de prueba del proyecto.

REQUERIMIENTO 1 - 
Automatizar la validacion de servicios ReqRes https://reqres.in/ con REST Assured y SerenityRest

Criterio de aceptación 1: Listar usuarios
Dado que accedo al servicio de listar usuarios de ReqRes https://reqres.in/
Cuando realizo una solicitud GET al endpoint /api/users?page=2
Entonces la respuesta debe contener la lista de usuarios en la página 2 y el estado de la respuesta debe ser 200.

Criterio de aceptación 2: Crear un nuevo usuario
Dado que tengo los datos para crear un nuevo usuario
Cuando realizo una solicitud POST al endpoint /api/users con los datos del usuario
Entonces la respuesta debe indicar que el usuario fue creado exitosamente con un estado de 201, y debe devolver el ID del usuario creado.

Criterio de aceptación 3: Actualizar un usuario existente
Dado que tengo un ID de usuario existente
Cuando realizo una solicitud PUT al endpoint /api/users/{id} con datos actualizados
Entonces la respuesta debe indicar que el usuario fue actualizado exitosamente con un estado de 200, y debe devolver los datos actualizados.

Criterio de aceptación 4: Eliminar un usuario existente
Dado que tengo un ID de usuario existente
Cuando realizo una solicitud DELETE al endpoint /api/users/{id}
Entonces la respuesta debe devolver un estado de 204, indicando que el usuario fue eliminado exitosamente.

REQUERIMIENTO 2.
Automatizar el proceso de agregar dos productos de la categoría "Agradecimientos" al carrito de compras y validar que se muestren correctamente en la opción "Carro".

Criterio de aceptación 1: Productos categoría agradecimientos
Dado que estoy en la página principal de la tienda https://www.floristeriamundoflor.com/
cuando selecciono la categoría "Agradecimientos"
entonces se visualizarán los productos para esta categoría

Criterio de aceptación 2: Añadir dos productos de la categoría Agradecimientos al carrito de compras
Dado que estoy en la categoría "Agradecimientos"
Cuando de clic en la opción “Añadir al carrito”
Entonces El producto se agregará al carrito de compras

Criterio de aceptación 3: Validar que los productos se muestran correctamente en la opción Carro
Dado que he agregado los productos de la categoría "Agradecimientos" al carrito
Cuando de clic en la opción “CARRO”
Entonces se deberán visualizar los productos agregados


## Pre requisitos – Testeada  
•	Java JDK v "17.0.12"

•	Gradle 7.5

•	Intellij IDEA 2024 2.0.2 (Community Edition)

•	Dentro del build.gradle se encuentra las librerías que se trabajó. “Frameworks”.


## EVIDENCIAS  
CON GOOGLE DRIVE
https://drive.google.com/drive/folders/1r4xZb5NJbRC7DeinTq8av81seGiazblT?usp=sharing
CON MICROSOFT ONE DRIVE
https://1drv.ms/f/c/f6cf1516d2f2270f/EpXRS8BD239LkrUH66yjyAkBq5wLTxBN1Jn5ZpDhKOxlGQ?e=4rrKdD

En el adjunto encontraras:
- Video adjunto de pruebas en vivo. 
- Proyecto adjunto completo con el Reporte Serenity en \target\site\serenity\Index.html
Archivo mas pesado 130 mb.
- Proyecto adjunto sin reportes 1.43 mb

Ejecutar para generar Reportes Serenity.
RUN:
./gradlew clean test --tests co.com.porvenir.projects.runners.AgregarProductosRunner
./gradlew clean test --tests co.com.porvenir.projects.runners.ValidarServiciosWebReqresRunner


## NOTAS

Primer Requerimiento:
SerenityRest: Está estrechamente integrado con Serenity BDD, lo que permite utilizar características como los actores, las tareas, las preguntas y los escenarios. Y por ende me ayuda a generar reportes de manera limpia.
además esta enfocado en Pruebas de Aceptación, que describen el comportamiento de la aplicación desde la perspectiva del usuario.
RestAssured: Es más adecuado para pruebas de API a nivel unitario o de integración, donde se necesita un mayor control sobre los detalles de la solicitud y la respuesta.
Sin embargo puedo utilizar de forma independiente o integrarse con SerenityRest. 
Pero con Serenity.recordReportData() está diseñado principalmente para capturar datos específicos de la prueba, como el nombre de la prueba, los pasos y los resultados. No está diseñado para capturar directamente las respuestas completas de RestAssured.
Y en Question puedo personalizar para mostrar la respuesta completa de RestAssured en un reporte de Serenity.

En mi enfoque actual en la prueba Tecnica, he decidido utilizar RestAssured en dos puntos de criterios de aceptación específicos (solo en Task), acorde a la petición de la prueba técnica (Listar y Eliminar), mientras que el resto de las pruebas las realizo con SerenityRest.
Segundo Requerimiento:

- Deje una clase UI los Tarjets utilizando de diferentes formas con selenium y Serenity. 
Soy conciente de la centralización excesiva de Targets en una única clase UI puede generar un acoplamiento fuerte entre las pruebas y la estructura de la interfaz, dificultando los cambios y el mantenimiento. En este caso se implemento de manera dinamico.
- Se que hay como tres clases que usa un bloque de codigo similar y por eso; implemneté una interacción como PaginationForEachProduct donde demuestraría un buen entendimiento de los principios de DRY (Don't Repeat Yourself). Sin embargo, es crucial integrar esta interacción; para luego consolidar la mejora en la calidad y eficiencia del código.
En otras palabras sigue los lineamientos del SOLID.

- Se uso una Libreria JAVAFAKER para generar informacion aleatoriamente, vinculado al modelo de datos Usario; por ende no esta integrado con el Excel de datos pero si se tiene la logica de codigo para asociar al modelo de datos; con la libreria Fillo.

