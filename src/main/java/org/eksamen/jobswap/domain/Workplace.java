package org.eksamen.jobswap.domain;

public class Workplace {
    private final int workplaceID;
    private final String workAddress;
    private final Zip workAddressZip;

    public Workplace(int workplaceID, String workAddress, Zip workAddressZip) {
        this.workplaceID = workplaceID;
        this.workAddress = workAddress;
        this.workAddressZip = workAddressZip;
    }

    public int getWorkplaceID() {
        return workplaceID;
    }

    public String getWorkAddress() {
        return workAddress;
    }

    public Zip getWorkAddressZip() {
        return workAddressZip;
    }
}
