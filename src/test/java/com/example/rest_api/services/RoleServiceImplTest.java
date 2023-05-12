package com.example.rest_api.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.rest_api.daos.RoleRepo;
import com.example.rest_api.entities.Role;
import com.example.rest_api.utils.SecurityLevels;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {RoleServiceImpl.class})
@ExtendWith(SpringExtension.class)
class RoleServiceImplTest {
    @MockBean
    private RoleRepo roleRepo;

    @Autowired
    private RoleServiceImpl roleServiceImpl;

    /**
     * Method under test: {@link RoleServiceImpl#saveRole(Role)}
     */
    @Test
    void testSaveRole() {
        Role role = new Role();
        when(roleRepo.save((Role) any())).thenReturn(role);
        assertSame(role, roleServiceImpl.saveRole(new Role()));
        verify(roleRepo).save((Role) any());
    }

    /**
     * Method under test: {@link RoleServiceImpl#saveRole(Role)}
     */
    @Test
    void testSaveRole2() {
        when(roleRepo.save((Role) any())).thenThrow(new IllegalArgumentException("foo"));
        assertThrows(IllegalArgumentException.class, () -> roleServiceImpl.saveRole(new Role()));
        verify(roleRepo).save((Role) any());
    }

    /**
     * Method under test: {@link RoleServiceImpl#getRoles()}
     */
    @Test
    void testGetRoles() {
        ArrayList<Role> roleList = new ArrayList<>();
        when(roleRepo.findAll()).thenReturn(roleList);
        List<Role> actualRoles = roleServiceImpl.getRoles();
        assertSame(roleList, actualRoles);
        assertTrue(actualRoles.isEmpty());
        verify(roleRepo).findAll();
    }

    /**
     * Method under test: {@link RoleServiceImpl#getRoles()}
     */
    @Test
    void testGetRoles2() {
        when(roleRepo.findAll()).thenThrow(new IllegalArgumentException("foo"));
        assertThrows(IllegalArgumentException.class, () -> roleServiceImpl.getRoles());
        verify(roleRepo).findAll();
    }

    /**
     * Method under test: {@link RoleServiceImpl#updateRole(Role, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdateRole() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "String.isEmpty()" because the return value of "com.example.rest_api.entities.Role.getRoleName()" is null
        //       at com.example.rest_api.services.RoleServiceImpl.updateRole(RoleServiceImpl.java:39)
        //   In order to prevent updateRole(Role, String)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   updateRole(Role, String).
        //   See https://diff.blue/R013 to resolve this issue.

        when(roleRepo.findRoleByRoleName((String) any())).thenReturn(Optional.of(new Role()));
        roleServiceImpl.updateRole(new Role(), "Role ID");
    }

    /**
     * Method under test: {@link RoleServiceImpl#updateRole(Role, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdateRole2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "java.util.Optional.get()" because "role1" is null
        //       at com.example.rest_api.services.RoleServiceImpl.updateRole(RoleServiceImpl.java:38)
        //   In order to prevent updateRole(Role, String)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   updateRole(Role, String).
        //   See https://diff.blue/R013 to resolve this issue.

        when(roleRepo.findRoleByRoleName((String) any())).thenReturn(null);
        roleServiceImpl.updateRole(new Role(), "Role ID");
    }

    /**
     * Method under test: {@link RoleServiceImpl#updateRole(Role, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdateRole3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "String.isEmpty()" because the return value of "com.example.rest_api.entities.Role.getRoleDescription()" is null
        //       at com.example.rest_api.services.RoleServiceImpl.updateRole(RoleServiceImpl.java:39)
        //   In order to prevent updateRole(Role, String)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   updateRole(Role, String).
        //   See https://diff.blue/R013 to resolve this issue.

        when(roleRepo.findRoleByRoleName((String) any())).thenReturn(Optional.of(new Role()));

        Role role = new Role();
        role.setRoleName("Role Name");
        roleServiceImpl.updateRole(role, "Role ID");
    }

    /**
     * Method under test: {@link RoleServiceImpl#updateRole(Role, String)}
     */
    @Test
    void testUpdateRole4() {
        when(roleRepo.findRoleByRoleName((String) any())).thenReturn(Optional.of(new Role()));

        Role role = new Role();
        role.setRoleName("");
        ResponseEntity<String> actualUpdateRoleResult = roleServiceImpl.updateRole(role, "Role ID");
        assertEquals("Invalid input", actualUpdateRoleResult.getBody());
        assertEquals(400, actualUpdateRoleResult.getStatusCodeValue());
        assertTrue(actualUpdateRoleResult.getHeaders().isEmpty());
        verify(roleRepo).findRoleByRoleName((String) any());
    }

    /**
     * Method under test: {@link RoleServiceImpl#updateRole(Role, String)}
     */
    @Test
    void testUpdateRole5() {
        when(roleRepo.save((Role) any())).thenReturn(new Role());
        when(roleRepo.findRoleByRoleName((String) any())).thenReturn(Optional.of(new Role()));

        Role role = new Role(1L, "Role Name", SecurityLevels.ROOT, "Role Description");
        role.setRoleName("42");
        ResponseEntity<String> actualUpdateRoleResult = roleServiceImpl.updateRole(role, "Role ID");
        assertEquals("Update role", actualUpdateRoleResult.getBody());
        assertEquals(200, actualUpdateRoleResult.getStatusCodeValue());
        assertTrue(actualUpdateRoleResult.getHeaders().isEmpty());
        verify(roleRepo).save((Role) any());
        verify(roleRepo).findRoleByRoleName((String) any());
    }

    /**
     * Method under test: {@link RoleServiceImpl#updateRole(Role, String)}
     */
    @Test
    void testUpdateRole6() {
        Role role = mock(Role.class);
        doThrow(new IllegalArgumentException("foo")).when(role).setRoleDescription((String) any());
        doThrow(new IllegalArgumentException("foo")).when(role).setRoleName((String) any());
        doThrow(new IllegalArgumentException("foo")).when(role).setSecurity_level((SecurityLevels) any());
        Optional<Role> ofResult = Optional.of(role);
        when(roleRepo.save((Role) any())).thenReturn(new Role());
        when(roleRepo.findRoleByRoleName((String) any())).thenReturn(ofResult);

        Role role1 = new Role(1L, "Role Name", SecurityLevels.ROOT, "Role Description");
        role1.setRoleName("42");
        assertThrows(IllegalArgumentException.class, () -> roleServiceImpl.updateRole(role1, "Role ID"));
        verify(roleRepo).findRoleByRoleName((String) any());
        verify(role).setRoleName((String) any());
    }

    /**
     * Method under test: {@link RoleServiceImpl#updateRole(Role, String)}
     */
    @Test
    void testUpdateRole7() {
        Role role = mock(Role.class);
        doThrow(new IllegalArgumentException("foo")).when(role).setRoleDescription((String) any());
        doThrow(new IllegalArgumentException("foo")).when(role).setRoleName((String) any());
        doThrow(new IllegalArgumentException("foo")).when(role).setSecurity_level((SecurityLevels) any());
        Optional<Role> ofResult = Optional.of(role);
        when(roleRepo.save((Role) any())).thenReturn(new Role());
        when(roleRepo.findRoleByRoleName((String) any())).thenReturn(ofResult);

        Role role1 = new Role(1L, "Role Name", SecurityLevels.ROOT, "");
        role1.setRoleName("42");
        ResponseEntity<String> actualUpdateRoleResult = roleServiceImpl.updateRole(role1, "Role ID");
        assertEquals("Invalid input", actualUpdateRoleResult.getBody());
        assertEquals(400, actualUpdateRoleResult.getStatusCodeValue());
        assertTrue(actualUpdateRoleResult.getHeaders().isEmpty());
        verify(roleRepo).findRoleByRoleName((String) any());
    }

    /**
     * Method under test: {@link RoleServiceImpl#deleteRole(String)}
     */
    @Test
    void testDeleteRole() {
        doNothing().when(roleRepo).deleteById((Long) any());
        when(roleRepo.findRoleByRoleName((String) any())).thenReturn(Optional.of(new Role()));
        ResponseEntity<String> actualDeleteRoleResult = roleServiceImpl.deleteRole("Roleid");
        assertEquals("Deleted Role", actualDeleteRoleResult.getBody());
        assertEquals(200, actualDeleteRoleResult.getStatusCodeValue());
        assertTrue(actualDeleteRoleResult.getHeaders().isEmpty());
        verify(roleRepo, atLeast(1)).findRoleByRoleName((String) any());
        verify(roleRepo).deleteById((Long) any());
    }

    /**
     * Method under test: {@link RoleServiceImpl#deleteRole(String)}
     */
    @Test
    void testDeleteRole2() {
        doThrow(new IllegalArgumentException("Deleted Role")).when(roleRepo).deleteById((Long) any());
        when(roleRepo.findRoleByRoleName((String) any())).thenReturn(Optional.of(new Role()));
        assertThrows(IllegalArgumentException.class, () -> roleServiceImpl.deleteRole("Roleid"));
        verify(roleRepo, atLeast(1)).findRoleByRoleName((String) any());
        verify(roleRepo).deleteById((Long) any());
    }

    /**
     * Method under test: {@link RoleServiceImpl#deleteRole(String)}
     */
    @Test
    void testDeleteRole3() {
        Role role = mock(Role.class);
        when(role.getRoleid()).thenThrow(new IllegalArgumentException("foo"));
        Optional<Role> ofResult = Optional.of(role);
        doNothing().when(roleRepo).deleteById((Long) any());
        when(roleRepo.findRoleByRoleName((String) any())).thenReturn(ofResult);
        assertThrows(IllegalArgumentException.class, () -> roleServiceImpl.deleteRole("Roleid"));
        verify(roleRepo, atLeast(1)).findRoleByRoleName((String) any());
        verify(role).getRoleid();
    }

    /**
     * Method under test: {@link RoleServiceImpl#deleteRole(String)}
     */
    @Test
    void testDeleteRole4() {
        doNothing().when(roleRepo).deleteById((Long) any());
        when(roleRepo.findRoleByRoleName((String) any())).thenReturn(Optional.empty());
        new IllegalArgumentException("foo");
        ResponseEntity<String> actualDeleteRoleResult = roleServiceImpl.deleteRole("Roleid");
        assertEquals("Role not found ", actualDeleteRoleResult.getBody());
        assertEquals(400, actualDeleteRoleResult.getStatusCodeValue());
        assertTrue(actualDeleteRoleResult.getHeaders().isEmpty());
        verify(roleRepo).findRoleByRoleName((String) any());
    }

    /**
     * Method under test: {@link RoleServiceImpl#findRolebyname(String)}
     */
    @Test
    void testFindRolebyname() {
        Optional<Role> ofResult = Optional.of(new Role());
        when(roleRepo.findRoleByRoleName((String) any())).thenReturn(ofResult);
        Optional<Role> actualFindRolebynameResult = roleServiceImpl.findRolebyname("Name");
        assertSame(ofResult, actualFindRolebynameResult);
        assertTrue(actualFindRolebynameResult.isPresent());
        verify(roleRepo).findRoleByRoleName((String) any());
    }

    /**
     * Method under test: {@link RoleServiceImpl#findRolebyname(String)}
     */
    @Test
    void testFindRolebyname2() {
        when(roleRepo.findRoleByRoleName((String) any())).thenThrow(new IllegalArgumentException("foo"));
        assertThrows(IllegalArgumentException.class, () -> roleServiceImpl.findRolebyname("Name"));
        verify(roleRepo).findRoleByRoleName((String) any());
    }
}

