package com.gabriel.todolist.entity.service.upgrade;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;


public class UpgradePathImageColumn extends UpgradeProcess {


    @Override
    protected void doUpgrade() throws Exception {
        runSQL("ALTER TABLE ToDoList_Task MODIFY pathImage VARCHAR(1000);");
    }
}