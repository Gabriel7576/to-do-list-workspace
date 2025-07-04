package com.gabriel.todolist.web.portlet.task.action;

import com.gabriel.todolist.entity.model.Task;
import com.gabriel.todolist.entity.service.TaskLocalService;
import com.gabriel.todolist.web.constants.TaskWebPortletKeys;
import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.document.library.kernel.service.DLFolderLocalServiceUtil;
import com.liferay.document.library.kernel.util.DLUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import java.io.File;

@Component(
        property = {
                "javax.portlet.name=" + TaskWebPortletKeys.TASKWEB,
                "mvc.command.name=/task/update"
        },
        service = MVCActionCommand.class
)
public class UpdateTaskMVCActionCommand implements MVCActionCommand {

    @Reference
    private TaskLocalService _taskLocalService;

    @Override
    public boolean processAction(ActionRequest actionRequest, ActionResponse actionResponse) throws PortletException {

        long taskId = ParamUtil.getLong(actionRequest, "taskId");
        String title = ParamUtil.getString(actionRequest, "title");
        int status = ParamUtil.getInteger(actionRequest, "status");
        String description = ParamUtil.getString(actionRequest, "description");
        int priority = ParamUtil.getInteger(actionRequest, "priority");
        long parentId = ParamUtil.getLong(actionRequest, "parentId");

        try {
            UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(actionRequest);
            File file = uploadPortletRequest.getFile("file");
            String originalFileName = uploadPortletRequest.getFileName("file");

            String uniqueFileName = System.currentTimeMillis() + "_" + originalFileName;
            ServiceContext serviceContext = ServiceContextFactory.getInstance(Task.class.getName(), actionRequest);

            long folderId = getFolderId(serviceContext.getScopeGroupId(), "panic", serviceContext.getUserId(), actionRequest);
            FileEntry fileEntry = DLAppLocalServiceUtil.addFileEntry(serviceContext.getUserId(), serviceContext.getScopeGroupId(), folderId, uniqueFileName, "image/jpeg", uniqueFileName, "", "", file, serviceContext);
            String imagem = DLUtil.getPreviewURL(DLAppServiceUtil.getFileEntry(fileEntry.getFileEntryId()), DLAppServiceUtil.getFileEntry(fileEntry.getFileEntryId()).getFileVersion(), serviceContext.getThemeDisplay(), "");


            _taskLocalService.updateTask(taskId, title, status, description, priority, imagem, fileEntry.getFileEntryId(), parentId, serviceContext);
        } catch (PortalException e) {
            throw new RuntimeException(e);
        }

        return true;
    }

    private long getFolderId(long groupId, String folderName, long userId, ActionRequest actionRequest) throws PortalException {

        DLFolder dlFolder = null;
        ServiceContext dlServiceCtx = ServiceContextFactory.getInstance(DLFolder.class.getName(), actionRequest);
        try {
            dlFolder = DLFolderLocalServiceUtil.getFolder(groupId, DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, folderName);
        } catch (Exception e) {
            dlFolder = DLFolderLocalServiceUtil.addFolder(null, userId, groupId, DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, false, DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, folderName, folderName, false, dlServiceCtx);
        }

        return dlFolder.getFolderId();
    }
}
