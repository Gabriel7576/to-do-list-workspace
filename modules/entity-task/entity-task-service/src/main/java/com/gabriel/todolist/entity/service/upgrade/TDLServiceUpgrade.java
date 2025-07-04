package com.gabriel.todolist.entity.service.upgrade;

import com.gabriel.todolist.entity.service.upgrade.UpgradePathImageColumn;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ReleaseLocalService;
import com.liferay.portal.kernel.upgrade.UpgradeException;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.upgrade.UpgradeStep;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.Date;


@Component(
        immediate = true,
        service = UpgradeStep.class
)
public class TDLServiceUpgrade implements UpgradeStep {

    @Override
    public void upgrade() throws UpgradeException {

            _releaseLocalService.updateRelease(
                    "com.gabriel.todolist.entity.service",
                    "1.0.1", // proxima
                    "1.0.0" // anterior
            );

        new UpgradePathImageColumn().upgrade();
    }

    @Reference
    private ReleaseLocalService _releaseLocalService;
}