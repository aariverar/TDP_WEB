Feature: Mi Movistar Flujo de Recargas

  @MiMovistar_Recargas
  Scenario Outline: Recargas en Mi Movistar

    Given se ingresa en la URL el token generado "<caso_prueba>"
    And se ingresa el numero celular "<caso_prueba>"
    And monto a recargar "<caso_prueba>"
    And se da click en el boton Continuar
    When se ingresa el email "<caso_prueba>" y se da click en continuar
    And se ingresa el numero de tarjeta "<caso_prueba>"
    And se ingresa el mes de vencimiento "<caso_prueba>"
    And se ingresa el anio de vencimiento "<caso_prueba>"
    And se ingresa el codigo de verificacion "<caso_prueba>"
    And se da click en el boton Pagar
    Then se verifica que se hizo la recarga correcta.

    Examples:
      | caso_prueba |
      |           1 |


