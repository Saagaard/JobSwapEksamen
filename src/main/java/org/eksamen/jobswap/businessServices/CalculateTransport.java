package org.eksamen.jobswap.businessServices;

import org.eksamen.jobswap.domain.TransportDetails;

public interface CalculateTransport {

    public TransportDetails calculateTransportDetails(String homeAddress, String workAddress, int homeZip, int workZip) throws Exception;

}
