Feature: SAC Interact Provisioning

  @ValidarIBG
  Scenario Outline: Verificar IBG post Alta CRM

    Given Ingreso a la url de SAC "<caso_prueba>"
    And se ingresa el usuario "<caso_prueba>"
    And la contrasenia "<caso_prueba>"
    And se da click en el boton Entrar
    When se da click en Consulta de Ordenes
    And se ingresa el numero de celular "<caso_prueba>"
    And se filtra la busqueda por antiguedad Una Semana
    Then se da click en el boton Buscar Ordenes
    When se da click en Gestor Ordenes IBG
    And se ingresa el numero de celular IBG"<caso_prueba>"
    Then se da click en el boton Buscar Ordenes "IBG"

    Examples:
      | caso_prueba |
      | 1           |
