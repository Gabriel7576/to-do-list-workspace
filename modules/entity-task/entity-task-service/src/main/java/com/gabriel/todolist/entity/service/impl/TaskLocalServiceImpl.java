/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.gabriel.todolist.entity.service.impl;

import com.gabriel.todolist.entity.model.Task;
import com.gabriel.todolist.entity.service.base.TaskLocalServiceBaseImpl;

import com.liferay.portal.aop.AopService;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import org.osgi.service.component.annotations.Component;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = "model.class.name=com.gabriel.todolist.entity.model.Task",
	service = AopService.class
)
public class TaskLocalServiceImpl extends TaskLocalServiceBaseImpl {

	public Task addTask(String title, String description, int status, int priority, String pathImage, long parentId, ServiceContext serviceContext) throws PortalException, PortalException {

		Group group = GroupLocalServiceUtil.getGroup(serviceContext.getScopeGroupId());

		User user = UserLocalServiceUtil.getUser(serviceContext.getUserId());

		long taskId = counterLocalService.increment(Task.class.getName());

		Task task = createTask(taskId);
		task.setTitle(title);
		task.setDescription(description);
		task.setStatus(status);
		task.setPriority(priority);
		task.setPathImage(pathImage);
		task.setParentId(parentId);

		task.setGroupId(group.getGroupId());
		task.setCompanyId(group.getCompanyId());
		task.setUserId(user.getUserId());
		task.setUserName(user.getScreenName());
		task.setCreateDate(serviceContext.getCreateDate());
		task.setModifiedDate(serviceContext.getCreateDate());

		task = taskLocalService.addTask(task);

		return task;
	}
}