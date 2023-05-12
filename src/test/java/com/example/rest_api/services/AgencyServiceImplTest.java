package com.example.rest_api.services;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.rest_api.daos.AgencyRepo;
import com.example.rest_api.entities.Agency;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {AgencyServiceImpl.class})
@ExtendWith(SpringExtension.class)
class AgencyServiceImplTest {
    @MockBean
    private AgencyRepo agencyRepo;

    @Autowired
    private AgencyServiceImpl agencyServiceImpl;

    /**
     * Method under test: {@link AgencyServiceImpl#getAgencyByIdWithUsers(Long)}
     */
    @Test
    void testGetAgencyByIdWithUsers() {
        Optional<Agency> ofResult = Optional.of(new Agency());
        when(agencyRepo.findByIdWithUsers((Long) any())).thenReturn(ofResult);
        Optional<Agency> actualAgencyByIdWithUsers = agencyServiceImpl.getAgencyByIdWithUsers(123L);
        assertSame(ofResult, actualAgencyByIdWithUsers);
        assertTrue(actualAgencyByIdWithUsers.isPresent());
        verify(agencyRepo).findByIdWithUsers((Long) any());
    }

    /**
     * Method under test: {@link AgencyServiceImpl#getAgenciesByCity(String)}
     */
    @Test
    void testGetAgenciesByCity() {
        ArrayList<Agency> agencyList = new ArrayList<>();
        when(agencyRepo.findByAgencyCity((String) any())).thenReturn(agencyList);
        List<Agency> actualAgenciesByCity = agencyServiceImpl.getAgenciesByCity("Oxford");
        assertSame(agencyList, actualAgenciesByCity);
        assertTrue(actualAgenciesByCity.isEmpty());
        verify(agencyRepo).findByAgencyCity((String) any());
    }

    /**
     * Method under test: {@link AgencyServiceImpl#getAgenciesByDistrictOrderedByCity(String)}
     */
    @Test
    void testGetAgenciesByDistrictOrderedByCity() {
        ArrayList<Agency> agencyList = new ArrayList<>();
        when(agencyRepo.findByAgencyDistrictOrderByAgencyCity((String) any())).thenReturn(agencyList);
        List<Agency> actualAgenciesByDistrictOrderedByCity = agencyServiceImpl
                .getAgenciesByDistrictOrderedByCity("District");
        assertSame(agencyList, actualAgenciesByDistrictOrderedByCity);
        assertTrue(actualAgenciesByDistrictOrderedByCity.isEmpty());
        verify(agencyRepo).findByAgencyDistrictOrderByAgencyCity((String) any());
    }

    /**
     * Method under test: {@link AgencyServiceImpl#getAgenciesByUserCount(int)}
     */
    @Test
    void testGetAgenciesByUserCount() {
        ArrayList<Agency> agencyList = new ArrayList<>();
        when(agencyRepo.findByUserCount(anyInt())).thenReturn(agencyList);
        List<Agency> actualAgenciesByUserCount = agencyServiceImpl.getAgenciesByUserCount(10);
        assertSame(agencyList, actualAgenciesByUserCount);
        assertTrue(actualAgenciesByUserCount.isEmpty());
        verify(agencyRepo).findByUserCount(anyInt());
    }

    /**
     * Method under test: {@link AgencyServiceImpl#getAgencyById(Long)}
     */
    @Test
    void testGetAgencyById() {
        Optional<Agency> ofResult = Optional.of(new Agency());
        when(agencyRepo.findById((Long) any())).thenReturn(ofResult);
        Optional<Agency> actualAgencyById = agencyServiceImpl.getAgencyById(123L);
        assertSame(ofResult, actualAgencyById);
        assertTrue(actualAgencyById.isPresent());
        verify(agencyRepo).findById((Long) any());
    }

    /**
     * Method under test: {@link AgencyServiceImpl#getAgencyByNumber(Long)}
     */
    @Test
    void testGetAgencyByNumber() {
        Optional<Agency> ofResult = Optional.of(new Agency());
        when(agencyRepo.existsAgencyByAgencyNumber((Long) any())).thenReturn(ofResult);
        Optional<Agency> actualAgencyByNumber = agencyServiceImpl.getAgencyByNumber(1L);
        assertSame(ofResult, actualAgencyByNumber);
        assertTrue(actualAgencyByNumber.isPresent());
        verify(agencyRepo).existsAgencyByAgencyNumber((Long) any());
    }

    /**
     * Method under test: {@link AgencyServiceImpl#getAllAgencies()}
     */
    @Test
    void testGetAllAgencies() {
        ArrayList<Agency> agencyList = new ArrayList<>();
        when(agencyRepo.findAll()).thenReturn(agencyList);
        List<Agency> actualAllAgencies = agencyServiceImpl.getAllAgencies();
        assertSame(agencyList, actualAllAgencies);
        assertTrue(actualAllAgencies.isEmpty());
        verify(agencyRepo).findAll();
    }

    /**
     * Method under test: {@link AgencyServiceImpl#saveAgency(Agency)}
     */
    @Test
    void testSaveAgency() {
        when(agencyRepo.save((Agency) any())).thenReturn(new Agency());
        agencyServiceImpl.saveAgency(new Agency());
        verify(agencyRepo).save((Agency) any());
        assertTrue(agencyServiceImpl.getAllAgencies().isEmpty());
    }

    /**
     * Method under test: {@link AgencyServiceImpl#DeleteAgency(Long)}
     */
    @Test
    void testDeleteAgency() {
        doNothing().when(agencyRepo).deleteById((Long) any());
        agencyServiceImpl.DeleteAgency(123L);
        verify(agencyRepo).deleteById((Long) any());
        assertTrue(agencyServiceImpl.getAllAgencies().isEmpty());
    }
}

