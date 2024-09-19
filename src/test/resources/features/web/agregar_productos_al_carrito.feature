# language: es
# author: Hector Alejandro Vargas Ochoa
# email: havargas8a@gmail.com
# Escenario corresponde al Requerimiento 2
# Automatizar el proceso de agregar dos productos de la categoría "Agradecimientos" de la pagina https://www.floristeriamundoflor.com/
# Escenario del gherkin se encuentra en español, Nombramientos de feature, y Test en Español,
# El resto se encuentra codificación en idioma inglés se hace más que nada por prácticidad y estandarización universal.


Característica: Agregar productos al carrito de compras en la categoría "Agradecimientos"

  Yo como TAE(Test automation engineer)
  Quiero realizar una prueba con el sitio web Floristería Mundo Flor – Floristerías en Medellín y
  agregar productos y validar que se muestren correctamente los productos en la opción "Carro", y Añadir Carrito
  desde el Detalle del Produto y desde la visualización "Grid" del Producto
  Para comprobar la robustes y control de errores del aplicativo web

  Antecedentes:
    Dado que "Hector" está en la página principal de la tienda de Floristeria Mundo Flor - Medellin

  @ViewProduct
  Escenario: Productos categoría agradecimientos
    Cuando selecciono la categoría "Agradecimientos"
    Entonces se visualizarán los productos para esta categoría
      | productos |
      | MDF 00047 |
      | MDF00161 |

  @AddToCart
  Escenario: Añadir dos productos de la categoría Agradecimientos al carrito de compras
    Cuando selecciono cada producto en la categoría "Agradecimientos" doy click en Añadir al carrito
      | productos |
      | MDF 00015 |
      | MDF 00020 |
    Entonces el producto se agregará al carrito de compras

  @BoxCar
  Escenario: Validar que los productos se muestran correctamente en la opción Carro
    Cuando he agregado los productos de la categoría "Agradecimientos" desde el detalle del produto
      | productos |
      | MDF 00015 |
      | MDF 00020 |
    Y doy clic en la opción “CARRO” en la parte superior derecha
    Entonces se deberán visualizar los productos agregados