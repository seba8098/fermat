package com.bitdubai.fermat_dap_plugin.digital_asset_transaction.redeem_point_redemption.bitdubai.version_1.structure.events;

import com.bitdubai.fermat_api.DealsWithPluginIdentity;
import com.bitdubai.fermat_api.layer.all_definition.enums.ServiceStatus;
import com.bitdubai.fermat_api.layer.dmp_world.Agent;
import com.bitdubai.fermat_api.layer.dmp_world.wallet.exceptions.CantStartAgentException;
import com.bitdubai.fermat_api.layer.osa_android.database_system.DealsWithPluginDatabaseSystem;
import com.bitdubai.fermat_api.layer.osa_android.database_system.PluginDatabaseSystem;
import com.bitdubai.fermat_api.layer.osa_android.logger_system.DealsWithLogger;
import com.bitdubai.fermat_api.layer.osa_android.logger_system.LogManager;
import com.bitdubai.fermat_pip_api.layer.pip_platform_service.error_manager.DealsWithErrors;
import com.bitdubai.fermat_pip_api.layer.pip_platform_service.error_manager.ErrorManager;
import com.bitdubai.fermat_pip_api.layer.pip_platform_service.event_manager.interfaces.DealsWithEvents;
import com.bitdubai.fermat_pip_api.layer.pip_platform_service.event_manager.interfaces.EventManager;

import java.util.UUID;

/**
 * Created by Víctor A. Mars M. (marsvicam@gmail.com) on 23/10/15.
 */
public class RedeemPointRedemptionMonitorAgent implements Agent, DealsWithLogger, DealsWithEvents, DealsWithErrors, DealsWithPluginDatabaseSystem, DealsWithPluginIdentity {

    private EventManager eventManager;
    private ServiceStatus status;
    private ErrorManager errorManager;
    private UUID pluginId;
    private LogManager logManager;

    @Override
    public void start() throws CantStartAgentException {
        this.status = ServiceStatus.STARTED;
    }

    @Override
    public void stop() {
        this.status = ServiceStatus.STOPPED;
    }

    @Override
    public void setErrorManager(ErrorManager errorManager) {
        this.errorManager = errorManager;
    }

    @Override
    public void setEventManager(EventManager eventManager) {
        this.eventManager = eventManager;
    }

    @Override
    public void setLogManager(LogManager logManager) {
        this.logManager = logManager;
    }

    @Override
    public void setPluginDatabaseSystem(PluginDatabaseSystem pluginDatabaseSystem) {
        //TODO: TODAVIA NO SE SI LO NECESITO.
    }

    @Override
    public void setPluginId(UUID pluginId) {
        this.pluginId = pluginId;
    }
}
