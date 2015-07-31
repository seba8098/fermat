package com.bitdubai.fermat_api.layer.dmp_module.intra_user.interfaces;

import com.bitdubai.fermat_api.layer.dmp_module.intra_user.exceptions.CantAcceptRequestException;
import com.bitdubai.fermat_api.layer.dmp_module.intra_user.exceptions.CantGetIntraUsersListException;
import com.bitdubai.fermat_api.layer.dmp_module.intra_user.exceptions.CantSolveRequestLaterException;
import com.bitdubai.fermat_api.layer.dmp_module.intra_user.exceptions.CantStartRequestException;
import com.bitdubai.fermat_api.layer.dmp_module.intra_user.exceptions.CouldNotCreateIntraUserException;
import com.bitdubai.fermat_api.layer.dmp_module.intra_user.exceptions.CouldSaveProfileImageException;
import com.bitdubai.fermat_api.layer.dmp_module.intra_user.exceptions.IntraUserConectionDenegationFailedException;
import com.bitdubai.fermat_api.layer.dmp_module.intra_user.exceptions.IntraUserDeletionFailedException;

import java.util.List;

/**
 * The interface <code>com.bitdubai.fermat_api.layer.dmp_module.intra_user.interfaces.IntraUserModule</code>
 * provides the methods for the Intra Users sub app.
 */
public interface IntraUserModule {

    /**
     * The method <code>createIntraUser</code> is used to create a new intra user
     *
     * @param intraUserName the name of the intra user to create
     * @return the public key generated for the said intra user.
     * @throws CouldNotCreateIntraUserException
     */
    public String createIntraUser(String intraUserName) throws CouldNotCreateIntraUserException;

    /**
     * The method <code>setProfileImage</code> let the current logged in intra user set its profile
     * picture.
     * @param image the profile picture to set
     * @throws CouldSaveProfileImageException
     */
    public void setProfileImage(byte[] image) throws CouldSaveProfileImageException;

    /**
     * The method <code>login</code> let an intra user log in
     *
     * @param intraUserPublicKey the public key of the intra user to log in
     */
    public void login(String intraUserPublicKey);

    /**
     * The method <code>getSuggestionsToContact</code> searches for intra users that the logged in
     * intra user could be interested to add.
     *
     * @return a list with information of intra users
     * @throws CantGetIntraUsersListException
     */
    public List<IntraUserInformation> getSuggestionsToContact() throws CantGetIntraUsersListException;

    /**
     * The method <code>searchIntraUser</code> gives us an interface to manage a search for a particular
     * intra user
     *
     * @return a searching interface
     */
    public IntraUserSearch searchIntraUser();

    /**
     * The method <code>askIntraUserForAcceptance</code> initialize the request of contact between
     * two intra users.
     *
     * @param intraUserToAddName      The name of the intra user to add
     * @param intraUserToAddPublicKey The public key of the intra user to add
     * @param profileImage            The profile image that the intra user has
     * @throws CantStartRequestException
     */
    public void askIntraUserForAcceptance(String intraUserToAddName, String intraUserToAddPublicKey, byte[] profileImage) throws CantStartRequestException;

    /**
     * The method <code>acceptIntraUser</code> takes the information of a connection request, accepts
     * the request and adds the intra user to the list managed by this plugin with ContactState CONTACT.
     *
     * @param intraUserToAddName      The name of the intra user to add
     * @param intraUserToAddPublicKey The public key of the intra user to add
     * @param profileImage            The profile image that the intra user has
     * @throws CantAcceptRequestException
     */
    public void acceptIntraUser(String intraUserToAddName, String intraUserToAddPublicKey, byte[] profileImage) throws CantAcceptRequestException;

    /**
     * The method <code>decideAcceptanceLater</code> marks the user information to decide its
     * acceptance later.
     *
     * @param intraUserToAddName      The name of the intra user to add
     * @param intraUserToAddPublicKey The public key of the intra user to add
     * @param profileImage            The profile image that the intra user has
     * @throws CantSolveRequestLaterException
     */
    public void decideAcceptanceLater(String intraUserToAddName, String intraUserToAddPublicKey, byte[] profileImage) throws CantSolveRequestLaterException;

    /**
     * The method <code>denyConnection</code> denies a conection request from other intra user
     *
     * @param intraUserToRejectPublicKey the public key of the user to deny its connection request
     * @throws IntraUserConectionDenegationFailedException
     */
    public void denyConnection(String intraUserToRejectPublicKey) throws IntraUserConectionDenegationFailedException;

    /**
     * The method <code>deleteIntraUSer</code> deletes an intra user from the list managed by this
     * plugin
     *
     * @param intraUserToRemovePublicKey the public key of the intra user to delete
     * @throws IntraUserDeletionFailedException
     */
    public void deleteIntraUSer(String intraUserToRemovePublicKey) throws IntraUserDeletionFailedException;

    /**
     * The method <code>getAllIntraUsers</code> returns the list of all intra users registered by the
     * logged in intra user
     *
     * @return the list of intra users connected to the logged in intra user
     * @throws CantGetIntraUsersListException
     */
    public List<IntraUserInformation> getAllIntraUsers() throws CantGetIntraUsersListException;

    /**
     * The method <code>getIntraUsersWaitingYourAcceptance</code> returns the list of intra users waiting to be accepted
     * or rejected by the logged in intra user
     *
     * @return the list of intra users waiting to be accepted or rejected by the  logged in intra user
     * @throws CantGetIntraUsersListException
     */
    public List<IntraUserInformation> getIntraUsersWaitingYourAcceptance() throws CantGetIntraUsersListException;

    /**
     * The method <code>getIntraUsersWaitingTheirAcceptance</code> list the intra users that haven't
     * answered to a sent connection request by the current logged in intra user.
     *
     * @return the list of intra users that haven't answered to a sent connection request by the current
     * logged in intra user.
     * @throws CantGetIntraUsersListException
     */
    public List<IntraUserInformation> getIntraUsersWaitingTheirAcceptance() throws CantGetIntraUsersListException;

}