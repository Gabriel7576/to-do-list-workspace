<%@ include file="/init.jsp" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<%@ taglib prefix="clay" uri="http://liferay.com/tld/clay" %>

<portlet:renderURL var="addTaskURL" windowState="normal">
    <portlet:param name="mvcRenderCommandName" value="/task/add_form" />
</portlet:renderURL>

<c:if test="${themeDisplay.isSignedIn()}">
<liferay-ui:tabs names="Lista de Tarefas,Pendentes,Concluídas" refresh="<%= false %>">

    <liferay-ui:section>

    <div class="container mt-4">
                <table class="table table-bordered table-striped align-middle">
                    <thead class="table">
                        <tr>
                            <th>Título</th>
                            <th>Imagem</th>
                            <th>Descrição</th>
                            <th>Prioridade</th>
                            <th>Status</th>
                            <th style="width: 200px;">Ações</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="dto" items="${tasks}">
                            <!-- Tarefa principal -->
                            <tr class="${dto.task.status == 1 ? 'table-success' : ''}">
                                <td><strong>${dto.task.title}</strong></td>
                                <td><img src="${dto.task.pathImage}" alt="Imagem da tarefa" width="auto" height="50" /></td>
                                <td>${dto.task.description}</td>
                                <td>${TaskPriority.fromCodigo(dto.task.priority).getDescricao()}</td>
                                <td>${TaskStatus.fromCodigo(dto.task.status).getDescricao()}</td>
                                <td>
                                    <form action="<portlet:actionURL name='/task/status' />" method="post" class="d-inline">
                                        <input type="hidden" name="<portlet:namespace />taskId" value="${dto.task.taskId}" />
                                        <button class="btn btn-sm btn-success" title="Concluir" onclick="return confirm('Marcar como concluída?');">&#10003;</button>
                                     </form>

                                    <portlet:renderURL var="updateTaskURL" windowState="normal">
                                        <portlet:param name="mvcRenderCommandName" value="/task/update_form" />
                                        <portlet:param name="taskId" value="${dto.task.taskId}" />
                                    </portlet:renderURL>
                                    <a href="${updateTaskURL}" class="btn btn-sm btn-warning" title="Editar">&#9998;</a>

                                    <form action="<portlet:actionURL name='/task/remove' />" method="post" class="d-inline">
                                        <input type="hidden" name="<portlet:namespace />taskId" value="${dto.task.taskId}" />
                                        <button class="btn btn-sm btn-danger" title="Excluir" onclick="return confirm('Excluir tarefa?');">&#10006;</button>
                                    </form>
                                </td>
                            </tr>

                            <!-- Subtarefas -->
                            <c:forEach var="sub" items="${dto.subtasks}">
                                <tr class="${sub.status == 1 ? 'text-muted' : ''}">
                                    <td class="ps-4">↳ ${sub.title}</td>
                                    <td><img src="${sub.pathImage}" alt="Imagem da tarefa" width="auto" height="50" /></td>
                                    <td>${sub.description}</td>
                                    <td>${TaskPriority.fromCodigo(sub.priority).getDescricao()}</td>
                                    <td>${TaskStatus.fromCodigo(sub.status).getDescricao()}</td>
                            <td>
                                <form action="<portlet:actionURL name='/task/status' />" method="post" class="d-inline">
                                    <input type="hidden" name="<portlet:namespace />taskId" value="${sub.taskId}" />
                                    <button class="btn btn-sm btn-success" title="Concluir">&#10003;</button>
                                </form>

                                <portlet:renderURL var="updateSubtaskURL" windowState="normal">
                                    <portlet:param name="mvcRenderCommandName" value="/task/update_form" />
                                    <portlet:param name="taskId" value="${sub.taskId}" />
                                </portlet:renderURL>
                                <a href="${updateSubtaskURL}" class="btn btn-sm btn-warning" title="Editar">&#9998;</a>

                                <form action="<portlet:actionURL name='/task/remove' />" method="post" class="d-inline">
                                    <input type="hidden" name="<portlet:namespace />taskId" value="${sub.taskId}" />
                                    <button class="btn btn-sm btn-danger" title="Excluir">&#10006;</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </c:forEach>
            </tbody>
        </table>

        <div class="text-end mt-3">
            <a href="${addTaskURL}" class="btn btn-primary">+ Nova Tarefa</a>
        </div>
    </div>

    </liferay-ui:section>


    <liferay-ui:section>
                  <div class="sheet">
                     <div class="card text-white bg-warning mb-3" style="max-width: 18rem;">
                          <div class="card-header">Resumo</div>
                             <div class="card-body">
                             <h5 class="card-title">Tarefas Pendentes</h5>
                             <p class="card-text">
                                   Você tem <strong>${qtdPendentes}</strong> tarefas pendentes.
                             </p>
                         </div>
                     </div>
                  </div>
    </liferay-ui:section>
    <liferay-ui:section>

         <div class="sheet">
            <div class="card text-white bg-success mb-3" style="max-width: 18rem;">
                 <div class="card-header">Resumo</div>
                    <div class="card-body">
                    <h5 class="card-title">Tarefas Concluídas</h5>
                    <p class="card-text">
                        Você tem <strong>${qtdConcluidos}</strong> tarefas concluídas.
                    </p>
                </div>
            </div>
         </div>
    </liferay-ui:section>

</liferay-ui:tabs>
</c:if>
<c:if test="${!themeDisplay.isSignedIn()}">
    <div class="container-fluid">
        <div class="sheet">
            <a href="${loginUrl}" class="sheet-title">Faça login para continuar</a>
        </div>
    </div>
</c:if>