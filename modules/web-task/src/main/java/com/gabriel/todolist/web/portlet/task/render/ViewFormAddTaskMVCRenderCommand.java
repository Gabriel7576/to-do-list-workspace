package com.gabriel.todolist.web.portlet.task.render;

import com.gabriel.todolist.web.constants.TaskWebPortletKeys;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import org.osgi.service.component.annotations.Component;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

@Component(
        property = {
                "javax.portlet.name=" + TaskWebPortletKeys.TASKWEB,
                "mvc.command.name=/task/add_form"
        },
        service = MVCRenderCommand.class
)
public class ViewFormAddTaskMVCRenderCommand implements MVCRenderCommand{

    @Override
    public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
        return "/add.jsp";
    }

}
