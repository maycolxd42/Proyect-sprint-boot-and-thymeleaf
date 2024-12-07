@echo off
REM Iniciar el túnel SSH a Serveo
start ssh -R dominio-pizza:80:localhost:8080 serveo.net

REM Esperar un poco para asegurarse de que el túnel está activo
timeout /t 8

REM Abrir el navegador con la URL de serveo
start https://serveo.net/

REM Iniciar la aplicación Spring Boot usando Maven
start mvn spring-boot:run

REM Esperar un poco para que se inicie springboot
timeout /t 10

REM Abrir el navegador con la URL del servidor para el cliente
start https://dominio-pizza.serveo.net/pizzeria/index

REM Abrir el navegador con la URL del servidor para el administrador
start https://dominio-pizza.serveo.net/admin-pizzeria/login