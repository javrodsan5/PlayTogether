<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="playtogether" tagdir="/WEB-INF/tags"%>

<html>
<playtogether:layout pageName="welcome" invitaciones="${invitaciones}">
	<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Playtogether</title>
	</head>
	<body>
        <img class="logoLanding" src="<s:url value="/images/logo.png" />" />
        <h1>Política de cookies</h1>
        <div class="divLandingPage">
		<p style="text-align: justify; text-justify: inter-word">
            PlayTogether usa cookies propias y de terceros para mejorar la navegación, proporcionar el servicio y obtener estadísticas sobre el uso de la página web.<br><br>
        
            Las cookies son pequeños archivos de datos que se envían a su navegador o software relacionado desde un servidor web y se almacenan en su computadora o dispositivo. 
            Las cookies a menudo incluyen un identificador único anónimo y rastrean y almacenan sus preferencias de usuarios mientras usan el sitio web, así como información técnica 
            sobre su uso del sitio web.
        </p><br>

        <p style="text-align: justify; text-justify: inter-word">
            Los tipos de cookies utilizados en PlayTogether son los siguientes:
            <ul>
                <li type="disc">
                    <p style="text-align: justify; text-justify: inter-word">
                        Cookies propias: se envían al navegador del usuario desde nuestros dominio web.
                    </p>
                </li>
                <li type="disc">
                    <p style="text-align: justify; text-justify: inter-word">
                        Cookies de terceros: se envían al navegador del usuario desde un equipo o dominio web que gestionan otras páginas, como Paypal.
                    </p>
                </li>
                <li type="disc">
                    <p style="text-align: justify; text-justify: inter-word">
                        Cookies de sesión: permanecen en el archivo de cookies del navegador del usuario hasta que abandona la página web.
                    </p>
                </li>
                <li type="disc">
                    <p style="text-align: justify; text-justify: inter-word">
                        Cookies persistentes: permanecen en el dispositivo del usuario y nuestra web las lee cada vez que realizas una nueva visita. 
                        Estas cookies dejan de funcionar pasada una fecha concreta.
                    </p>
                </li>
                <li type="disc">
                    <p style="text-align: justify; text-justify: inter-word">
                        Cookies técnicas: mejoran la navegación y el buen funcionamiento de la web.
                    </p>
                </li>
                <li type="disc">
                    <p style="text-align: justify; text-justify: inter-word">
                        Cookies de personalización: permiten acceder al servicio con unas características predefinidas en función de una serie de criterios.
                    </p>
                </li>
                <li type="disc">
                    <p style="text-align: justify; text-justify: inter-word">
                        Cookies de análisis: permiten medir y analizar estadísticamente el uso que se hace del servicio prestado.
                    </p>
                </li>
            </ul>
        </p><br>

        <p style="text-align: justify; text-justify: inter-word">
            Al registrarse en la aplicación, el usuario debe marcar la casilla indicando que acepta instalar nuestras cookies para poder obtener estadísticas y 
            facilitar el uso de la aplicación.
        </p>         

        </div>
	</body>

</playtogether:layout>
</html>