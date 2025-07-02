<%@ include file="/init.jsp" %>

<div class="todo-container">
    <div class="todo-header">Minha Lista de Tarefas</div>

    <ul class="todo-list">
        <c:forEach var="task" items="${tasks}">
            <li class="todo-item ${task.status == 1 ? 'completed' : ''}">
                <span>${task.title}</span>
                <div class="todo-actions">
                    <button title="Concluir">&#10003;</button>
                    <button title="Editar">&#9998;</button>
                    <button title="Excluir">&#10006;</button>
                </div>
            </li>
        </c:forEach>
    </ul>

    <portlet:renderURL var="addTaskURL" windowState="normal">
        <portlet:param name="mvcRenderCommandName" value="/task/add_form" />
    </portlet:renderURL>

    <div class="todo-container-btn">
        <a href="${addTaskURL}" class="add-task-btn">+ Nova Tarefa</a>
    </div>
</div>