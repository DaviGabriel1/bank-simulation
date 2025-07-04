package com.davi.bank;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;

public class ModularityTests {
    static ApplicationModules modules = ApplicationModules.of(BankApplication.class);

    @Test
    void verifyModuleStructure() {
        modules.verify();
    }
}