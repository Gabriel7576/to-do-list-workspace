package com.gabriel.todolist.web.portlet.task.render;

import com.gabriel.todolist.entity.model.Task;
import com.gabriel.todolist.entity.service.TaskLocalService;
import com.gabriel.todolist.web.constants.TaskWebPortletKeys;
import com.gabriel.todolist.web.portlet.task.util.UrlLoginUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

@Component(
        property = {
                "javax.portlet.name=" + TaskWebPortletKeys.TASKWEB,
                "mvc.command.name=/task/update_form"
        },
        service = MVCRenderCommand.class
)
public class ViewFormEditTaskMVCRenderCommand implements MVCRenderCommand{

    @Reference
    private TaskLocalService _taskLocalService;

    @Override
    public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {

        long taskId = ParamUtil.getLong(renderRequest, "taskId");

        try {
            Task task = _taskLocalService.getTask(taskId);
            UrlLoginUtil.createUrlLogin(renderRequest);
            renderRequest.setAttribute("task", task);
        } catch (PortalException e) {
            throw new RuntimeException(e);
        }

        return "/edit.jsp";
    }
}
