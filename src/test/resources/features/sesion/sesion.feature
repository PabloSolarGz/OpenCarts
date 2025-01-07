Feature: Validación de inicio de sesión mediante archivos json

  Scenario: Iniciar sesión de manera satisfactoria
    Given Ingresar al sitio web Opencart
    When Iniciar sesión con un usuario registrado
    And Validar My Account cuando se inicie sesion de manera satisfactoria
    And Cerrar Sesión Activa
    Then Cerrar el navegador