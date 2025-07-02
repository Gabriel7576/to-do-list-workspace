<%@ include file="/init.jsp" %>

<portlet:actionURL name="/task/add" var="addTaskURL" />

<aui:form action="${addTaskURL}" method="post">

    <aui:input name="title" label="Titulo" required="true" />
    <aui:input type="textarea" name="description" label="Descrição" rows="4" />

    <aui:input name="priority" label="Prioridade" type="number" max="2" />
    <aui:input name="status" label="Status" type="number" min="0" max="1" />

    <aui:button type="submit" value="Salvar Tarefa" />
</aui:form>