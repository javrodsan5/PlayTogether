<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%-- The above 4 meta tags must come first in the head; any other head content must come after these tags --%>

<spring:url value="/resources/images/favicon.png" var="favicon" />
<link rel="shortcut icon" type="image/x-icon" href="${favicon}">


<%-- CSS generated from LESS --%>
<spring:url value="/css/style.css" var="css" />
<link href="${css}" rel="stylesheet" />

<title>PlayTogether</title>


<spring:url value="/webjars/bootstrap/4.6.0-1/css/bootstrap.min.css"
	var="bootstrap" />

<link href="${bootstrap}" rel="stylesheet" />
<script src="/webjars/bootstrap/4.6.0-1/js/bootstrap.bundle.min.js"></script>
<script src="/webjars/jquery/3.5.1/jquery.slim.min.js"></script>
<script src="/webjars/poppers.js/1.16.0/popper.min.js"></script>

<spring:url
	value="/webjars/datetimepicker/2.5.20-1/build/jquery.datetimepicker.full.min.js"
	var="DTPjS" />
<script src="${DTPjS}"></script>
<spring:url
	value="/webjars/datetimepicker/2.5.20-1/jquery.datetimepicker.css"
	var="jQueryDTPCss" />
<link href="${jQueryDTPCss}" rel="stylesheet" />

</head>