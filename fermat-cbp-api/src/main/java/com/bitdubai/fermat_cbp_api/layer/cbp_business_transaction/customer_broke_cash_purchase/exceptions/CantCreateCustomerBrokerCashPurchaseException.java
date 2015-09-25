package com.bitdubai.fermat_cbp_api.layer.cbp_business_transaction.customer_broke_cash_purchase.exceptions;

import com.bitdubai.fermat_api.FermatException;

/**
 * Created by Yordin Alayn on 18.09.15.
 */

public class CantCreateCustomerBrokerCashPurchaseException extends FermatException {
    public static final String DEFAULT_MESSAGE = "Falled To Create Business Transaction Customer Broker Cash Purchase.";
    public CantCreateCustomerBrokerCashPurchaseException(String message, Exception cause, String context, String possibleReason) {
        super(message, cause, context, possibleReason);
    }
}
