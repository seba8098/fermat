package com.bitdubai.fermat_dmp_plugin.layer.module.wallet_store.developer.bitdubai.version_1.structure;

import com.bitdubai.fermat_api.layer.all_definition.enums.Languages;
import com.bitdubai.fermat_api.layer.all_definition.enums.NicheWallet;
import com.bitdubai.fermat_api.layer.all_definition.enums.WalletCategory;
import com.bitdubai.fermat_api.layer.all_definition.util.Version;
import com.bitdubai.fermat_api.layer.dmp_middleware.wallet_language.exceptions.CantGetWalletLanguageException;
import com.bitdubai.fermat_api.layer.dmp_middleware.wallet_store.enums.CatalogItems;
import com.bitdubai.fermat_api.layer.dmp_middleware.wallet_store.enums.InstallationStatus;
import com.bitdubai.fermat_api.layer.dmp_middleware.wallet_store.exceptions.CantGetItemInformationException;
import com.bitdubai.fermat_api.layer.dmp_middleware.wallet_store.exceptions.CantSetInstallationStatusException;
import com.bitdubai.fermat_api.layer.dmp_middleware.wallet_store.interfaces.DealsWithWalletStoreMiddleware;
import com.bitdubai.fermat_api.layer.dmp_middleware.wallet_store.interfaces.WalletStoreManager;
import com.bitdubai.fermat_api.layer.dmp_module.wallet_store.exceptions.CantGetRefinedCatalogException;
import com.bitdubai.fermat_api.layer.dmp_module.wallet_store.exceptions.CantGetSkinVideoPreviewException;
import com.bitdubai.fermat_api.layer.dmp_module.wallet_store.exceptions.CantGetWalletsFromCatalogueException;
import com.bitdubai.fermat_api.layer.dmp_module.wallet_store.exceptions.CantStartInstallationException;
import com.bitdubai.fermat_api.layer.dmp_module.wallet_store.exceptions.CantStartLanguageInstallationException;
import com.bitdubai.fermat_api.layer.dmp_module.wallet_store.exceptions.CantStartSkinInstallationException;
import com.bitdubai.fermat_api.layer.dmp_module.wallet_store.exceptions.CantStartUninstallLanguageException;
import com.bitdubai.fermat_api.layer.dmp_module.wallet_store.exceptions.CantStartUninstallSkinException;
import com.bitdubai.fermat_api.layer.dmp_module.wallet_store.exceptions.CantStartUninstallWalletException;
import com.bitdubai.fermat_api.layer.dmp_module.wallet_store.exceptions.DatailedInformationNotFoundException;
import com.bitdubai.fermat_api.layer.dmp_module.wallet_store.interfaces.WalletCatalogueFilter;
import com.bitdubai.fermat_api.layer.dmp_module.wallet_store.interfaces.WalletStoreCatalogue;
import com.bitdubai.fermat_api.layer.dmp_module.wallet_store.interfaces.WalletStoreCatalogueItem;
import com.bitdubai.fermat_api.layer.dmp_module.wallet_store.interfaces.WalletStoreDetailedCatalogItem;
import com.bitdubai.fermat_api.layer.dmp_module.wallet_store.interfaces.WalletStoreLanguage;
import com.bitdubai.fermat_api.layer.dmp_module.wallet_store.interfaces.WalletStoreSkin;
import com.bitdubai.fermat_api.layer.dmp_network_service.wallet_store.exceptions.CantGetCatalogItemException;
import com.bitdubai.fermat_api.layer.dmp_network_service.wallet_store.exceptions.CantGetDesignerException;
import com.bitdubai.fermat_api.layer.dmp_network_service.wallet_store.exceptions.CantGetDeveloperException;
import com.bitdubai.fermat_api.layer.dmp_network_service.wallet_store.exceptions.CantGetLanguageException;
import com.bitdubai.fermat_api.layer.dmp_network_service.wallet_store.exceptions.CantGetLanguagesException;
import com.bitdubai.fermat_api.layer.dmp_network_service.wallet_store.exceptions.CantGetSkinException;
import com.bitdubai.fermat_api.layer.dmp_network_service.wallet_store.exceptions.CantGetSkinsException;
import com.bitdubai.fermat_api.layer.dmp_network_service.wallet_store.exceptions.CantGetTranslatorException;
import com.bitdubai.fermat_api.layer.dmp_network_service.wallet_store.exceptions.CantGetWalletDetailsException;
import com.bitdubai.fermat_api.layer.dmp_network_service.wallet_store.exceptions.CantGetWalletIconException;
import com.bitdubai.fermat_api.layer.dmp_network_service.wallet_store.exceptions.CantGetWalletsCatalogException;
import com.bitdubai.fermat_api.layer.dmp_network_service.wallet_store.interfaces.CatalogItem;
import com.bitdubai.fermat_api.layer.dmp_network_service.wallet_store.interfaces.DealsWithWalletStoreNetworkService;
import com.bitdubai.fermat_api.layer.dmp_network_service.wallet_store.interfaces.Designer;
import com.bitdubai.fermat_api.layer.dmp_network_service.wallet_store.interfaces.DetailedCatalogItem;
import com.bitdubai.fermat_api.layer.dmp_network_service.wallet_store.interfaces.Developer;
import com.bitdubai.fermat_api.layer.dmp_network_service.wallet_store.interfaces.Language;
import com.bitdubai.fermat_api.layer.dmp_network_service.wallet_store.interfaces.Skin;
import com.bitdubai.fermat_api.layer.dmp_network_service.wallet_store.interfaces.WalletCatalog;
import com.bitdubai.fermat_api.layer.osa_android.logger_system.DealsWithLogger;
import com.bitdubai.fermat_api.layer.osa_android.logger_system.LogManager;
import com.bitdubai.fermat_pip_api.layer.pip_platform_service.error_manager.DealsWithErrors;
import com.bitdubai.fermat_pip_api.layer.pip_platform_service.error_manager.ErrorManager;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by rodrigo on 7/29/15.
 */
public class WalletStoreModuleManager implements DealsWithErrors, DealsWithLogger, DealsWithWalletStoreMiddleware, DealsWithWalletStoreNetworkService{

    /**
     * DealsWithErrors interface member variables
     */
    ErrorManager errorManager;

    /**
     * DealsWithjLogger interface member variable
     */
    LogManager logManager;


    /**
     * DealsWithWalletStoreMiddleware interface member variable
     */
    WalletStoreManager walletStoreManagerMiddleware;

    /**
     * DealsWithWalletStoreNetworkService interface member variable
     */
    com.bitdubai.fermat_api.layer.dmp_network_service.wallet_store.interfaces.WalletStoreManager walletStoreManagerNetworkService;

    /**
     * Constructor
     * @param errorManager
     * @param logManager
     * @param walletStoreManagerMiddleware
     * @param walletStoreManagerNetworkService
     */
    public WalletStoreModuleManager(ErrorManager errorManager, LogManager logManager, WalletStoreManager walletStoreManagerMiddleware, com.bitdubai.fermat_api.layer.dmp_network_service.wallet_store.interfaces.WalletStoreManager walletStoreManagerNetworkService) {
        this.errorManager = errorManager;
        this.logManager = logManager;
        this.walletStoreManagerMiddleware = walletStoreManagerMiddleware;
        this.walletStoreManagerNetworkService = walletStoreManagerNetworkService;
    }

    /**
     * DealsWithErrors interface implementation
     */
    @Override
    public void setErrorManager(ErrorManager errorManager) {
        this.errorManager = errorManager;
    }

    /**
     * DealsWithLogManager interface implementation
     */
    @Override
    public void setLogManager(LogManager logManager) {
        this.logManager = logManager;
    }

    /**
     * DealsWithWalletStoreMiddleware interface implementation
     */
    @Override
    public void setWalletStoreManager(WalletStoreManager walletStoreManager) {
        this.walletStoreManagerMiddleware = walletStoreManager;
    }


    /**
     * DealsWithWalletStoreNetworkService interface implementation
     */
    @Override
    public void setWalletStoreManager(com.bitdubai.fermat_api.layer.dmp_network_service.wallet_store.interfaces.WalletStoreManager walletStoreManager) {
        this.walletStoreManagerNetworkService = walletStoreManager;
    }

    private InstallationStatus getWalletInstallationStatus(CatalogItem catalogItem) throws CantGetItemInformationException {
        return walletStoreManagerMiddleware.getInstallationStatus(CatalogItems.WALLET, catalogItem.getId());
    }

    private InstallationStatus getSkinInstallationStatus (UUID skinId) throws CantGetItemInformationException {
        return walletStoreManagerMiddleware.getInstallationStatus(CatalogItems.SKIN, skinId);
    }

    private InstallationStatus getLanguageInstallationStatus (UUID languageId) throws CantGetItemInformationException {
        return walletStoreManagerMiddleware.getInstallationStatus(CatalogItems.LANGUAGE, languageId);
    }

    private WalletStoreCatalogueItem getWalletCatalogueItem(final CatalogItem catalogItem, final InstallationStatus installationStatus){
        WalletStoreCatalogueItem walletStoreCatalogueItem = new WalletStoreCatalogueItem() {
            @Override
            public InstallationStatus getInstallationStatus() {
                return installationStatus;
            }

            @Override
            public WalletStoreDetailedCatalogItem getWalletDetailedCatalogItem() throws DatailedInformationNotFoundException {
                return null;
            }

            @Override
            public UUID getId() {
                return catalogItem.getId();
            }

            @Override
            public String getName() {
                return catalogItem.getName();
            }

            @Override
            public WalletCategory getCategory() {
                return catalogItem.getCategory();
            }

            @Override
            public String getDescription() {
                return catalogItem.getDescription();
            }

            @Override
            public int getDefaultSizeInBytes() {
                return catalogItem.getDefaultSizeInBytes();
            }

            @Override
            public byte[] getIcon() throws CantGetWalletIconException {
                return catalogItem.getIcon();
            }

            @Override
            public DetailedCatalogItem getDetailedCatalogItem() throws CantGetWalletDetailsException {
                return catalogItem.getDetailedCatalogItem();
            }
        };

        return walletStoreCatalogueItem;
    }

    private Designer getDesigner(UUID designerId) throws CantGetDesignerException {
        return walletStoreManagerNetworkService.getDesigner(designerId);
    }

    private WalletStoreSkin getWalletStoreSkin (final Skin skin, final InstallationStatus installationStatus){
        WalletStoreSkin walletStoreSkin = new WalletStoreSkin() {
            @Override
            public InstallationStatus getInstallationStatus() {
                return installationStatus;
            }

            @Override
            public String getSkinDesignerName() throws CantGetDesignerException {
                return walletStoreManagerNetworkService.getDesigner(skin.getSkinDesignerId()).getName();
            }

            @Override
            public String getSkinDesignerPublicKey() throws CantGetDesignerException {
                return walletStoreManagerNetworkService.getDesigner(skin.getSkinDesignerId()).getPublicKey();
            }

            @Override
            public UUID getSkinId() {
                return skin.getSkinId();
            }

            @Override
            public String getSkinName() {
                return skin.getSkinName();
            }

            @Override
            public UUID getWalletId() {
                return skin.getWalletId();
            }

            @Override
            public Version getVersion() {
                return skin.getVersion();
            }

            @Override
            public Version getInitialWalletVersion() {
                return skin.getInitialWalletVersion();
            }

            @Override
            public Version getFinalWalletVersion() {
                return skin.getFinalWalletVersion();
            }

            @Override
            public byte[] getPresentationImage() throws CantGetWalletIconException {
                return skin.getPresentationImage();
            }

            @Override
            public List<byte[]> getPreviewImageList() throws CantGetWalletIconException {
                return skin.getPreviewImageList();
            }

            @Override
            public boolean hasVideoPreview() {
                return skin.hasVideoPreview();
            }

            @Override
            public List<URL> getVideoPreviews() throws CantGetSkinVideoPreviewException {
                return skin.getVideoPreviews();
            }

            @Override
            public URL getSkinURL() {
                return skin.getSkinURL();
            }

            @Override
            public long getSkinSizeInBytes() {
                return skin.getSkinSizeInBytes();
            }

            @Override
            public UUID getSkinDesignerId() {
                return skin.getSkinDesignerId();
            }

            @Override
            public boolean isDefault() {
                return skin.isDefault();
            }
        };

        return walletStoreSkin;

    }

    private WalletStoreLanguage getWalletStoreLanguage (final Language language, final InstallationStatus installationStatus){
        WalletStoreLanguage walletStoreLanguage = new WalletStoreLanguage() {
            @Override
            public InstallationStatus getInstallationStatus() {
                return installationStatus;
            }

            @Override
            public String getTranslatorName() throws CantGetTranslatorException {
                return walletStoreManagerNetworkService.getTranslator(language.getTranslatorId()).getName();
            }

            @Override
            public String getTranslatorPublicKey() throws CantGetTranslatorException {
                return walletStoreManagerNetworkService.getTranslator(language.getTranslatorId()).getPublicKey();
            }

            @Override
            public UUID getLanguageId() {
                return language.getLanguageId();
            }

            @Override
            public UUID getWalletId() {
                return language.getWalletId();
            }

            @Override
            public Languages getLanguageName() {
                return language.getLanguageName();
            }

            @Override
            public String getLanguageLabel() {
                return language.getLanguageLabel();
            }

            @Override
            public int getLanguagePackageSizeInBytes() {
                return language.getLanguagePackageSizeInBytes();
            }

            @Override
            public URL getFileURL() {
                return language.getFileURL();
            }

            @Override
            public Version getVersion() {
                return language.getVersion();
            }

            @Override
            public Version getInitialWalletVersion() {
                return language.getInitialWalletVersion();
            }

            @Override
            public Version getFinalWalletVersion() {
                return language.getFinalWalletVersion();
            }

            @Override
            public UUID getTranslatorId() {
                return language.getTranslatorId();
            }

            @Override
            public boolean isDefault() {
                return language.isDefault();
            }
        };
        return walletStoreLanguage;
    }

    private WalletStoreDetailedCatalogItem getWalletStoreDetailedCatalogItem (final DetailedCatalogItem detailedCatalogItem) throws CantGetDeveloperException {

        WalletStoreDetailedCatalogItem walletStoreDetailedCatalogItem = new WalletStoreDetailedCatalogItem() {
            @Override
            public String getDeveloperName() throws CantGetDeveloperException {
                return detailedCatalogItem.getDeveloper().getName();
            }

            @Override
            public String getDeveloperPublicKey() throws CantGetDeveloperException {
                return detailedCatalogItem.getDeveloper().getPublicKey();
            }

            @Override
            public WalletStoreSkin getSkin(UUID skinId) throws CantGetSkinException, CantGetItemInformationException {
                return getWalletStoreSkin(walletStoreManagerNetworkService.getSkin(skinId), getSkinInstallationStatus(skinId));
            }

            @Override
            public WalletStoreLanguage getLanguage(UUID languageId) throws CantGetWalletLanguageException, CantGetItemInformationException {
                return getWalletStoreLanguage(walletStoreManagerNetworkService.getLanguage(languageId), getLanguageInstallationStatus(languageId));
            }

            @Override
            public Language getDefaultLanguage() throws CantGetLanguageException {
                return detailedCatalogItem.getDefaultLanguage();
            }

            @Override
            public List<Language> getLanguages() throws CantGetLanguagesException {
                return detailedCatalogItem.getLanguages();
            }

            @Override
            public Skin getDefaultSkin() throws CantGetSkinException {
                return detailedCatalogItem.getDefaultSkin();
            }

            @Override
            public List<Skin> getSkins() throws CantGetSkinsException {
                return detailedCatalogItem.getSkins();
            }

            @Override
            public Version getVersion() {
                return detailedCatalogItem.getVersion();
            }

            @Override
            public Version getPlatformInitialVersion() {
                return detailedCatalogItem.getPlatformInitialVersion();
            }

            @Override
            public Version getPlatformFinalVersion() {
                return detailedCatalogItem.getPlatformFinalVersion();
            }

            @Override
            public Developer getDeveloper() {
                return detailedCatalogItem.getDeveloper();
            }
        };

        return walletStoreDetailedCatalogItem;
    }

    private Developer getDeveloper (UUID developerId) throws CantGetDeveloperException {
        return walletStoreManagerNetworkService.getDeveloper(developerId);
    }


    /**
     * Puts to installing status the specified language and its wallet.
     * @param walletCatalogueId
     * @param languageId
     * @throws CantStartLanguageInstallationException
     */
    public void installLanguage(UUID walletCatalogueId, UUID languageId) throws CantStartLanguageInstallationException {
        try {
            walletStoreManagerMiddleware.setInstallationStatus(CatalogItems.LANGUAGE, languageId, InstallationStatus.INSTALLING);
            walletStoreManagerMiddleware.setInstallationStatus(CatalogItems.WALLET, walletCatalogueId, InstallationStatus.INSTALLING);
        } catch (Exception exception) {
            throw new CantStartLanguageInstallationException(CantStartLanguageInstallationException.DEFAULT_MESSAGE, exception, null, null);
        }
    }


    /**
     * Puts to installing status the specified skin and its wallet.
     * @param walletCatalogueId
     * @param skinId
     * @throws CantStartSkinInstallationException
     */
    public void installSkin(UUID walletCatalogueId, UUID skinId) throws CantStartSkinInstallationException {
        try {
            walletStoreManagerMiddleware.setInstallationStatus(CatalogItems.SKIN, skinId, InstallationStatus.INSTALLING);
            walletStoreManagerMiddleware.setInstallationStatus(CatalogItems.WALLET, walletCatalogueId, InstallationStatus.INSTALLING);
        } catch (Exception exception) {
            throw new CantStartSkinInstallationException (CantStartSkinInstallationException .DEFAULT_MESSAGE, exception, null, null);
        }
    }


    /**
     * start the installation of the passed wallet.
     * @param walletCategory
     * @param nicheWallet
     * @param skinId
     * @param languageId
     * @param walletCatalogueId
     * @param version
     * @throws CantStartInstallationException
     */
    public void installWallet(WalletCategory walletCategory, NicheWallet nicheWallet, UUID skinId, UUID languageId, UUID walletCatalogueId, Version version) throws CantStartInstallationException {
        try {
            walletStoreManagerMiddleware.setInstallationStatus(CatalogItems.WALLET, walletCatalogueId, InstallationStatus.INSTALLING);
        } catch (Exception exception) {
            throw new CantStartInstallationException(CantStartInstallationException.DEFAULT_MESSAGE, exception, null, null);
        }
    }


    /**
     * unisntall the specified Language
     * @param walletCatalogueId
     * @param languageId
     * @throws CantStartUninstallLanguageException
     */
    public void uninstallLanguage(UUID walletCatalogueId, UUID languageId) throws CantStartUninstallLanguageException {
        try {
            walletStoreManagerMiddleware.setInstallationStatus(CatalogItems.LANGUAGE, languageId, InstallationStatus.UNINSTALLING);
            walletStoreManagerMiddleware.setInstallationStatus(CatalogItems.WALLET, walletCatalogueId, InstallationStatus.UNINSTALLING);
        } catch (Exception exception) {
            throw new CantStartUninstallLanguageException(CantStartUninstallLanguageException.DEFAULT_MESSAGE, exception, null, null);
        }
    }


    /**
     * uninstall the specified skin
     * @param walletCatalogueId
     * @param skinId
     * @throws CantStartUninstallSkinException
     */
    public void uninstallSkin(UUID walletCatalogueId, UUID skinId) throws CantStartUninstallSkinException {
        try {
            walletStoreManagerMiddleware.setInstallationStatus(CatalogItems.SKIN, skinId, InstallationStatus.UNINSTALLING);
            walletStoreManagerMiddleware.setInstallationStatus(CatalogItems.WALLET, walletCatalogueId, InstallationStatus.UNINSTALLING);
        } catch (Exception exception) {
            throw new CantStartUninstallSkinException (CantStartUninstallSkinException .DEFAULT_MESSAGE, exception, null, null);
        }
    }

    /**
     * unisntall the specified wallet
     * @param walletCatalogueId
     * @throws CantStartUninstallWalletException
     */
    public void uninstallWallet(UUID walletCatalogueId) throws CantStartUninstallWalletException {
        try {
            walletStoreManagerMiddleware.setInstallationStatus(CatalogItems.WALLET, walletCatalogueId, InstallationStatus.UNINSTALLING);
        } catch (Exception exception) {
            throw new CantStartUninstallWalletException (CantStartUninstallWalletException.DEFAULT_MESSAGE, exception, null, null);
        }
    }

    /**
     * returns the WalletStore Catalag
     * @return
     * @throws CantGetRefinedCatalogException
     */
    public WalletStoreCatalogue getCatalogue() throws CantGetRefinedCatalogException {
        try {
            final List<WalletStoreCatalogueItem> walletStoreCatalogueItemList = new ArrayList<WalletStoreCatalogueItem>();
            WalletCatalog walletCatalog = walletStoreManagerNetworkService.getWalletCatalogue();
            for (CatalogItem catalogItem : walletCatalog.getWalletCatalog(0, 0)){
                InstallationStatus installationStatus = getWalletInstallationStatus(catalogItem);
                WalletStoreCatalogueItem walletStoreCatalogueItem = getWalletCatalogueItem(catalogItem, installationStatus);
                walletStoreCatalogueItemList.add(walletStoreCatalogueItem);
            }

            WalletStoreCatalogue walletStoreCatalogue = new WalletStoreCatalogue() {
                @Override
                public List<WalletStoreCatalogueItem> getWalletCatalogue(int offset, int top) throws CantGetWalletsFromCatalogueException {
                    return walletStoreCatalogueItemList;
                }

                @Override
                public void addFilter(WalletCatalogueFilter walletFilter) {

                }

                @Override
                public void clearFilters() {

                }
            };

            return walletStoreCatalogue;
        } catch (Exception exception) {
            throw new CantGetRefinedCatalogException(CantGetRefinedCatalogException.DEFAULT_MESSAGE, exception, null, null);
        }

    }

    /**
     * Gets the WalletStoreDetailed CAtalogItem object for the passes wallet
     * @param walletCatalogId
     * @return
     * @throws CantGetWalletsCatalogException
     */
    public WalletStoreDetailedCatalogItem getCatalogItemDetails(UUID walletCatalogId) throws CantGetWalletsCatalogException {
        try {
            return getWalletStoreDetailedCatalogItem(walletStoreManagerNetworkService.getDetailedCatalogItem(walletCatalogId));
        } catch (Exception exception) {
            throw new CantGetWalletsCatalogException(CantGetWalletsCatalogException.DEFAULT_MESSAGE, exception, null, null);
        }
    }


}