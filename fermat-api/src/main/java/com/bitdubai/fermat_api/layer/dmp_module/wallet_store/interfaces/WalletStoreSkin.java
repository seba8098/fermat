package com.bitdubai.fermat_api.layer.dmp_module.wallet_store.interfaces;

import com.bitdubai.fermat_api.layer.dmp_middleware.wallet_store.enums.InstallationStatus;
import com.bitdubai.fermat_api.layer.dmp_module.wallet_store.exceptions.ImageNotFoundException;
import com.bitdubai.fermat_api.layer.dmp_module.wallet_store.exceptions.UrlNotFoundException;
import com.bitdubai.fermat_api.layer.dmp_network_service.wallet_store.exceptions.CantGetDesignerException;
import com.bitdubai.fermat_api.layer.dmp_network_service.wallet_store.interfaces.Skin;

import java.net.URL;
import java.util.List;
import java.util.UUID;

/**
 * Created by eze on 2015.07.18..
 */
public interface WalletStoreSkin extends Skin{
    public InstallationStatus getInstallationStatus();
    public String getSkinDesignerName() throws CantGetDesignerException;
    public String getSkinDesignerPublicKey() throws CantGetDesignerException;
}