package com.example.rest_api.services;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

class TaskServiceImplTest {
    /**
     * Method under test: {@link TaskServiceImpl#getDemandsByOperation(String)}
     */
    @Test
    void testGetDemandsByOperation() {
        assertFalse((new TaskServiceImpl()).getDemandsByOperation("Type").isPresent());
    }
}

