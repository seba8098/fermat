package unit.com.bitdubai.fermat_dmp_plugin.layer.module.intra_user.developer.bitdubai.version_1.IntraUserModulePluginRoot;

import com.bitdubai.fermat_api.layer.all_definition.IntraUsers.IntraUserSettings;
import com.bitdubai.fermat_api.layer.all_definition.util.XMLParser;
import com.bitdubai.fermat_ccp_api.layer.actor.intra_wallet_user.interfaces.IntraWalletUserManager;
import com.bitdubai.fermat_api.layer.dmp_identity.intra_user.interfaces.IntraUserIdentity;
import com.bitdubai.fermat_api.layer.dmp_module.intra_user.exceptions.CantGetIntraUsersListException;
import com.bitdubai.fermat_api.layer.dmp_module.intra_user.interfaces.IntraUserInformation;
import com.bitdubai.fermat_ccp_api.layer.network_service.intra_actor.interfaces.IntraUserManager;
import com.bitdubai.fermat_api.layer.osa_android.file_system.FileLifeSpan;
import com.bitdubai.fermat_api.layer.osa_android.file_system.FilePrivacy;
import com.bitdubai.fermat_api.layer.osa_android.file_system.PluginFileSystem;
import com.bitdubai.fermat_api.layer.osa_android.file_system.PluginTextFile;
import com.bitdubai.fermat_dmp_plugin.layer.module.intra_user.developer.bitdubai.version_1.IntraWalletUserModulePluginRoot;
import com.bitdubai.fermat_pip_api.layer.pip_platform_service.error_manager.ErrorManager;

import junit.framework.TestCase;

import org.fest.assertions.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;
import java.util.UUID;

import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/**
 * Created by natalia on 20/08/15.
 */

@RunWith(MockitoJUnitRunner.class)
public class GetIntraUsersWaitingTheirAcceptanceTest extends TestCase {
    /**
     * DealsWithErrors interface Mocked
     */
    @Mock
    private ErrorManager mockErrorManager;

    /**
     * UsesFileSystem Interface member variables.
     */
    @Mock
    private PluginFileSystem mockPluginFileSystem;


    /**
     * DealWithActorIntraUserManager Interface member variables.
     */
    @Mock
    private IntraWalletUserManager mockIntraWalletUserManager;


    /**
     * DealWithIntraUserNetworkServiceManager Interface member variables.
     */
    @Mock
    private IntraUserManager mockIntraUserNetworkServiceManager;

    @Mock
    private PluginTextFile mockIntraUserLoginXml;


    private IntraWalletUserModulePluginRoot testIntraUserModulePluginRoot;

    @Mock
    IntraUserIdentity mockIntraUserIdentity;


    private IntraUserSettings intraUserSettings;
    private UUID pluginId;


    private List<IntraUserInformation> intraUserInformationList;


    @Before
    public void setUp() throws Exception{

        MockitoAnnotations.initMocks(this);

        pluginId= UUID.randomUUID();
        testIntraUserModulePluginRoot = new IntraWalletUserModulePluginRoot();
        testIntraUserModulePluginRoot.setPluginFileSystem(mockPluginFileSystem);
        testIntraUserModulePluginRoot.setErrorManager(mockErrorManager);
        testIntraUserModulePluginRoot.setIntraWalletUserManager(mockIntraWalletUserManager);
        testIntraUserModulePluginRoot.setIntraUserNetworkServiceManager(mockIntraUserNetworkServiceManager);

        setUpMockitoRules();
        testIntraUserModulePluginRoot.setId(pluginId);

        testIntraUserModulePluginRoot.start();

    }

    public void setUpMockitoRules()  throws Exception{
        intraUserSettings = new IntraUserSettings();
        intraUserSettings.setLoggedInPublicKey(UUID.randomUUID().toString());

        when(mockPluginFileSystem.getTextFile(pluginId, pluginId.toString(), "intraUsersLogin", FilePrivacy.PRIVATE, FileLifeSpan.PERMANENT)).thenReturn(mockIntraUserLoginXml);
        when(mockIntraUserLoginXml.getContent()).thenReturn(XMLParser.parseObject(intraUserSettings));
    }

    @Test
    public void getAllIntraUsersTest_GetOk_throwsCantGetIntraUsersListException() throws Exception{


        intraUserInformationList = testIntraUserModulePluginRoot.getIntraUsersWaitingTheirAcceptance(0,10);

        Assertions.assertThat(intraUserInformationList)
                .isNotNull();

    }

    @Test
    public void getIntraUsersWaitingTheirAcceptanceTest_GetError_throwsCantGetIntraUsersListException() throws Exception{

        testIntraUserModulePluginRoot.setIntraWalletUserManager(null);

        catchException(testIntraUserModulePluginRoot).getIntraUsersWaitingTheirAcceptance(0,10);

        assertThat(caughtException())
                .isNotNull()
                .isInstanceOf(CantGetIntraUsersListException.class);


    }
}

