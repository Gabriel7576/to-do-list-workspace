package com.gabriel.todolist.web.portlet.task.render;

import com.gabriel.todolist.entity.model.Task;
import com.gabriel.todolist.entity.service.TaskLocalService;
import com.gabriel.todolist.web.constants.TaskWebPortletKeys;
import com.gabriel.todolist.web.portlet.task.util.UrlLoginUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.util.List;

@Component(
        property = {
                "javax.portlet.name=" + TaskWebPortletKeys.TASKWEB,
                "mvc.command.name=/task/add_form"
        },
        service = MVCRenderCommand.class
)
public class ViewFormAddTaskMVCRenderCommand implements MVCRenderCommand{

    @Reference
    private TaskLocalService _taskLocalService;

    @Override
    public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
        ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);

        long userId = themeDisplay.getUserId();
        long groupId = themeDisplay.getScopeGroupId();

        List<Task> tasksMain = _taskLocalService.getTaskByUserIdAndGroupIdAndParentId(userId,groupId,0);

        try {
            UrlLoginUtil.createUrlLogin(renderRequest);
        } catch (PortalException e) {
            throw new RuntimeException(e);
        }

        renderRequest.setAttribute("parentTasks",tasksMain);

        return "/add.jsp";
    }

}
