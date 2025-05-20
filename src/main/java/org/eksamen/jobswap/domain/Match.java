package org.eksamen.jobswap.domain;

public class Match {
    Employee employee1;
    int employee1NewTransportTime;
    Employee employee2;
    int employee2NewTransportTime;

    public Match(Employee employee1, int employee1NewTransportTime, Employee employee2, int employee2NewTransportTime) {
        this.employee1 = employee1;
        this.employee1NewTransportTime = employee1NewTransportTime;
        this.employee2 = employee2;
        this.employee2NewTransportTime = employee2NewTransportTime;
    }

    public Employee getEmployee1() {
        return employee1;
    }

    public int getEmployee1NewTransportTime() {
        return employee1NewTransportTime;
    }

    public Employee getEmployee2() {
        return employee2;
    }

    public int getEmployee2NewTransportTime() {
        return employee2NewTransportTime;
    }
}
