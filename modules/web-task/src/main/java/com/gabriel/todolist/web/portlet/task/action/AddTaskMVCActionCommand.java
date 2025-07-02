package com.gabriel.todolist.web.portlet.task.action;

import com.gabriel.todolist.entity.model.Task;
import com.gabriel.todolist.entity.service.TaskLocalService;
import com.gabriel.todolist.web.constants.TaskWebPortletKeys;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.util.ParamUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.MutableRenderParameters;
import javax.portlet.PortletException;

@Component(
        property = {
                "javax.portlet.name=" + TaskWebPortletKeys.TASKWEB,
                "mvc.command.name=/task/add"
        },
        service = MVCActionCommand.class
)
public class AddTaskMVCActionCommand implements MVCActionCommand {

    @Reference
    private TaskLocalService _taskLocalService;

    @Override
    public boolean processAction(ActionRequest actionRequest, ActionResponse actionResponse) throws PortletException {

        String title = ParamUtil.getString(actionRequest, "title");
        String description = ParamUtil.getString(actionRequest, "description");
        int priority = ParamUtil.getInteger(actionRequest, "priority");
        int status = ParamUtil.getInteger(actionRequest, "status");

        try {
            ServiceContext serviceContext = ServiceContextFactory.getInstance(Task.class.getName(), actionRequest);

            _taskLocalService.addTask(
                    title,
                    description,
                    status,
                    priority,
                    "",
                    0L,
                    serviceContext
            );

            MutableRenderParameters renderParams = actionResponse.getRenderParameters();
            renderParams.setValue("mvcRenderCommandName", "/");
        } catch (Exception e) {
            throw new PortletException(e);
        }

        return true;
    }
}
