<ui:composition xmlns="http://www.w3.org/1999/xhtml"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:ui="http://java.sun.com/jsf/facelets"
	xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:a="http://xmlns.jcp.org/jsf/passthrough"
	template="/Template/template.xhtml">
$(document).ready(function(){
    $(".az95").click(function(e){
        e.preventDefault();
        var txt = $(".comment1").html();
        $(".comment1").replaceWith("<h:inputText value='" + txt + "' />");
        $(this).replaceWith("<h:commandButton value='modifier' class='primary-btn submit_btn'/> | <h:commandButton value='suprrimer' class='primary-btn submit_btn'/>");
    });
});