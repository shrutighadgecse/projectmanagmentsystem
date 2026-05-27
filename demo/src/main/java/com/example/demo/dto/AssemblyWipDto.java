package com.example.demo.dto;

public class AssemblyWipDto {

    private String machine;
    private long carryForward;
    private long machineInBuild;
    private long dispatch;
    private long balanceWip;  // NEW: CCNs started but not yet dispatched in selected year

    // ── Constructors ──────────────────────────────────────────────────────

    public AssemblyWipDto() {}

    /** Old 4-arg constructor kept for backward compatibility */
    public AssemblyWipDto(String machine, long carryForward,
                          long machineInBuild, long dispatch) {
        this.machine        = machine;
        this.carryForward   = carryForward;
        this.machineInBuild = machineInBuild;
        this.dispatch       = dispatch;
        this.balanceWip     = 0;
    }

    /** New 5-arg constructor — use this going forward */
    public AssemblyWipDto(String machine, long carryForward,
                          long machineInBuild, long dispatch, long balanceWip) {
        this.machine        = machine;
        this.carryForward   = carryForward;
        this.machineInBuild = machineInBuild;
        this.dispatch       = dispatch;
        this.balanceWip     = balanceWip;
    }

    // ── Getters & Setters ─────────────────────────────────────────────────

    public String getMachine()               { return machine; }
    public void   setMachine(String machine) { this.machine = machine; }

    public long getCarryForward()                  { return carryForward; }
    public void setCarryForward(long carryForward) { this.carryForward = carryForward; }

    public long getMachineInBuild()                    { return machineInBuild; }
    public void setMachineInBuild(long machineInBuild) { this.machineInBuild = machineInBuild; }

    public long getDispatch()               { return dispatch; }
    public void setDispatch(long dispatch)  { this.dispatch = dispatch; }

    public long getBalanceWip()                { return balanceWip; }   // NEW
    public void setBalanceWip(long balanceWip) { this.balanceWip = balanceWip; } // NEW
}