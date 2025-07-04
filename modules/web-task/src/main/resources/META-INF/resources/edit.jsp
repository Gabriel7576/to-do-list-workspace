<%@ include file="/init.jsp" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

<portlet:actionURL name="/task/update" var="updateTaskURL" />

<%
Task task = (Task) request.getAttribute("task");
%>

<aui:form action="${updateTaskURL}" method="post">

    <aui:input name="taskId" label="Código" value="${task.taskId}" readonly="true"/>
    <aui:input name="title" label="Titulo" required="true" value="${task.title}"/>
    <aui:input type="textarea" name="description" label="Descrição" rows="4"  value="${task.description}"/>

    <c:if test="${not empty task.pathImage}">
        <img src="${task.pathImage}" alt="Imagem da tarefa" width="auto" height="200"  class="image"/>
    </c:if>

    <aui:input name="file" label="Imagem" type="file" accept="image/*" required="true" />
    <aui:select name="priority" label="Prioridade" showEmptyOption="false" required="true" value="${task.priority}">
        <aui:option value="2">Alta</aui:option>
        <aui:option value="1">Media</aui:option>
        <aui:option value="0">Baixa</aui:option>
    </aui:select>



    <aui:select name="parentId" label="Tarefa principal" showEmptyOption="false" value="${task.parentId}">
        <aui:option value="0"></aui:option>

        <c:forEach var="task" items="${parentTasks}">
            <aui:option value="${task.taskId}">${task.title}</aui:option>
        </c:forEach>
    </aui:select>

    <aui:button type="submit" value="Atualizar"/>

    <portlet:renderURL var="cancelURL">
        <portlet:param name="mvcRenderCommandName" value="/"/>
    </portlet:renderURL>
    <a href="${cancelURL}" class="btn btn-outline-danger">Cancelar</a>
</aui:form>