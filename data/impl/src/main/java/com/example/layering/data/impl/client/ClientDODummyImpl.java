package com.example.layering.data.impl.client;

import com.example.layering.data.api.client.ClientDO;
import com.example.layering.data.impl.client.exception.UnableToSaveClientException;
import com.example.layering.model.api.client.Client;

public class ClientDODummyImpl implements ClientDO {
    @Override
    public Client getClientDetails(long clientId) {
        return null;
    }

    @Override
    public void saveClient(Client client) {
        if(client.getName().isEmpty())
            throw new UnableToSaveClientException();
    }
}
