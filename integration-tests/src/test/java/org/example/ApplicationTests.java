package org.example;

import org.junit.jupiter.api.Test;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.*;

public class ApplicationTests extends BaseIT {

    @LocalServerPort
    private Integer port;

    @Test
    void accessApplication() {
        System.out.println(port);
        assertNotNull(port);
    }
}
