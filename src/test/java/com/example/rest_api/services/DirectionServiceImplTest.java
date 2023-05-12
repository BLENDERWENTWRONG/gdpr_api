package com.example.rest_api.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.rest_api.daos.DirectionRepo;
import com.example.rest_api.entities.Direction;

import java.util.Optional;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {DirectionServiceImpl.class})
@ExtendWith(SpringExtension.class)
class DirectionServiceImplTest {
    @MockBean
    private DirectionRepo directionRepo;

    @Autowired
    private DirectionServiceImpl directionServiceImpl;

    /**
     * Method under test: {@link DirectionServiceImpl#deleteDirection(String)}
     */
    @Test
    void testDeleteDirection() {
        doNothing().when(directionRepo).deleteById((Long) any());
        when(directionRepo.findDirectionByDirectionName((String) any())).thenReturn(Optional.of(new Direction()));
        when(directionRepo.existsDirectionByDirectionName((String) any())).thenReturn(true);
        ResponseEntity<String> actualDeleteDirectionResult = directionServiceImpl.deleteDirection("42");
        assertEquals("Deleted Succesfully ", actualDeleteDirectionResult.getBody());
        assertEquals(200, actualDeleteDirectionResult.getStatusCodeValue());
        assertTrue(actualDeleteDirectionResult.getHeaders().isEmpty());
        verify(directionRepo).existsDirectionByDirectionName((String) any());
        verify(directionRepo).findDirectionByDirectionName((String) any());
        verify(directionRepo).deleteById((Long) any());
    }

    /**
     * Method under test: {@link DirectionServiceImpl#deleteDirection(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testDeleteDirection2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.util.NoSuchElementException: No value present
        //       at java.util.Optional.get(Optional.java:143)
        //       at com.example.rest_api.services.DirectionServiceImpl.deleteDirection(DirectionServiceImpl.java:35)
        //   In order to prevent deleteDirection(String)
        //   from throwing NoSuchElementException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   deleteDirection(String).
        //   See https://diff.blue/R013 to resolve this issue.

        doNothing().when(directionRepo).deleteById((Long) any());
        when(directionRepo.findDirectionByDirectionName((String) any())).thenReturn(Optional.empty());
        when(directionRepo.existsDirectionByDirectionName((String) any())).thenReturn(true);
        directionServiceImpl.deleteDirection("42");
    }

    /**
     * Method under test: {@link DirectionServiceImpl#deleteDirection(String)}
     */
    @Test
    void testDeleteDirection3() {
        doNothing().when(directionRepo).deleteById((Long) any());
        when(directionRepo.findDirectionByDirectionName((String) any())).thenReturn(Optional.of(new Direction()));
        when(directionRepo.existsDirectionByDirectionName((String) any())).thenReturn(false);
        ResponseEntity<String> actualDeleteDirectionResult = directionServiceImpl.deleteDirection("42");
        assertEquals("Direction Not found", actualDeleteDirectionResult.getBody());
        assertEquals(400, actualDeleteDirectionResult.getStatusCodeValue());
        assertTrue(actualDeleteDirectionResult.getHeaders().isEmpty());
        verify(directionRepo).existsDirectionByDirectionName((String) any());
    }
}

