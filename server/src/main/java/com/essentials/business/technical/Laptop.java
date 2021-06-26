package com.essentials.business.technical;

import org.springframework.stereotype.Component;

@Component("lap1") // Named component.
public class Laptop {
    private String hardrive;

    public String getHardrive() {
        return hardrive;
    }

    public void setHardrive(String hardrive) {
        this.hardrive = hardrive;
    }
}
