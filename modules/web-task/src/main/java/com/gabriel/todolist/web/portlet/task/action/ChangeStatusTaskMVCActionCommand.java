package com.gabriel.todolist.web.portlet.task.action;

import com.gabriel.todolist.entity.service.TaskLocalService;
import com.gabriel.todolist.web.constants.TaskWebPortletKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

@Component(
        property = {
                "javax.portlet.name=" + TaskWebPortletKeys.TASKWEB,
                "mvc.command.name=/task/status"
        },
        service = MVCActionCommand.class
)
public class ChangeStatusTaskMVCActionCommand implements MVCActionCommand{

    @Reference
    private TaskLocalService _taskLocalService;

    @Override
    public boolean processAction(ActionRequest actionRequest, ActionResponse actionResponse) throws PortletException {

        long taskId = ParamUtil.getLong(actionRequest, "taskId");

        try {
            _taskLocalService.changeStatus(taskId);
        } catch (PortalException e) {
            throw new RuntimeException(e);
        }

        return true;
    }
}
