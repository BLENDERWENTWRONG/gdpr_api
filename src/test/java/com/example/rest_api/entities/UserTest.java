package com.example.rest_api.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.rest_api.utils.SecurityLevels;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;

class UserTest {
    /**
     * Method under test: {@link User#getAuthorities()}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetAuthorities() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.example.rest_api.entities.Role.getSecurity_level()" because "this.role" is null
        //       at com.example.rest_api.entities.User.getAuthorities(User.java:86)
        //   In order to prevent getAuthorities()
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   getAuthorities().
        //   See https://diff.blue/R013 to resolve this issue.

        (new User()).getAuthorities();
    }

    /**
     * Method under test: {@link User#getAuthorities()}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetAuthorities2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.example.rest_api.utils.SecurityLevels.name()" because the return value of "com.example.rest_api.entities.Role.getSecurity_level()" is null
        //       at com.example.rest_api.entities.User.getAuthorities(User.java:86)
        //   In order to prevent getAuthorities()
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   getAuthorities().
        //   See https://diff.blue/R013 to resolve this issue.

        User user = new User();
        user.setRole(new Role());
        user.getAuthorities();
    }

    /**
     * Method under test: {@link User#getAuthorities()}
     */
    @Test
    void testGetAuthorities3() {
        Role role = new Role();
        role.setSecurity_level(SecurityLevels.ROOT);

        User user = new User();
        user.setRole(role);
        Collection<? extends GrantedAuthority> actualAuthorities = user.getAuthorities();
        assertEquals(1, actualAuthorities.size());
        assertEquals("ROOT", ((List<? extends GrantedAuthority>) actualAuthorities).get(0).getAuthority());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link User#User()}
     *   <li>{@link User#getPassword()}
     *   <li>{@link User#getUsername()}
     *   <li>{@link User#isAccountNonExpired()}
     *   <li>{@link User#isAccountNonLocked()}
     *   <li>{@link User#isCredentialsNonExpired()}
     *   <li>{@link User#isEnabled()}
     * </ul>
     */
    @Test
    void testConstructor() {
        User actualUser = new User();
        assertNull(actualUser.getPassword());
        assertNull(actualUser.getUsername());
        assertTrue(actualUser.isAccountNonExpired());
        assertTrue(actualUser.isAccountNonLocked());
        assertTrue(actualUser.isCredentialsNonExpired());
        assertTrue(actualUser.isEnabled());
    }

    /**
     * Method under test: {@link User#equals(Object)}
     */
    @Test
    void testEquals() {
        assertNotEquals(new User(), null);
        assertNotEquals(new User(), "Different type to User");
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link User#equals(Object)}
     *   <li>{@link User#hashCode()}
     * </ul>
     */
    @Test
    void testEquals2() {
        User user = new User();
        assertEquals(user, user);
        int expectedHashCodeResult = user.hashCode();
        assertEquals(expectedHashCodeResult, user.hashCode());
    }

    /**
     * Method under test: {@link User#equals(Object)}
     */
    @Test
    void testEquals3() {
        User user = new User();
        assertNotEquals(user, new User());
    }

    /**
     * Method under test: {@link User#equals(Object)}
     */
    @Test
    void testEquals4() {
        ArrayList<Token> tokens = new ArrayList<>();
        Role role = new Role();
        Agency agency = new Agency();
        Direction direction = new Direction();
        User user = new User(123L, "janedoe", "User Pass", "Jane", "Doe", "jane.doe@example.org", tokens, role, agency,
                direction, new ArrayList<>());
        assertNotEquals(user, new User());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link User#equals(Object)}
     *   <li>{@link User#hashCode()}
     * </ul>
     */
    @Test
    void testEquals5() {
        ArrayList<Token> tokens = new ArrayList<>();
        Role role = new Role();
        Agency agency = new Agency();
        Direction direction = new Direction();
        User user = new User(123L, "janedoe", "User Pass", "Jane", "Doe", "jane.doe@example.org", tokens, role, agency,
                direction, new ArrayList<>());
        ArrayList<Token> tokens1 = new ArrayList<>();
        Role role1 = new Role();
        Agency agency1 = new Agency();
        Direction direction1 = new Direction();
        User user1 = new User(123L, "janedoe", "User Pass", "Jane", "Doe", "jane.doe@example.org", tokens1, role1, agency1,
                direction1, new ArrayList<>());

        assertEquals(user, user1);
        int expectedHashCodeResult = user.hashCode();
        assertEquals(expectedHashCodeResult, user1.hashCode());
    }
}

