package com.acme.verification.confroles;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.acme.model.UMLOperation;
import com.acme.model.UMLRole;
import com.acme.model.impl.UMLRoleImpl;
import com.acme.verification.confroles.ConflictingRolesPolicy.GRANT;

import org.junit.Test;

public class ConflictingRolesPolicyTest {

    ConflictingRolesPolicy policy = new ConflictingRolesPolicy();

    @Test
    public void testNoRole() {

        List<UMLRole> userRoles = Collections.emptyList();

        UMLOperation operation = mock(UMLOperation.class);
        when(operation.roles()).thenReturn(Collections.emptyList());

        assertThat(policy.apply(userRoles, operation, GRANT.GRANT_ALL), is(empty()));
        assertThat(policy.apply(userRoles, operation, GRANT.DENY_ALL), is(empty()));
    }

    @Test
    public void testSingleMatchingRole() {

        List<UMLRole> userRoles = asRoles("admin");

        UMLOperation operation = mock(UMLOperation.class);
        when(operation.roles()).thenReturn(asRoles("admin"));

        assertThat(policy.apply(userRoles, operation, GRANT.GRANT_ALL), is(empty()));
        assertThat(policy.apply(userRoles, operation, GRANT.DENY_ALL), is(empty()));
    }

    @Test
    public void testRolelessOperationWithSingleRole() {
        List<UMLRole> userRoles = asRoles("admin");

        UMLOperation operation = mock(UMLOperation.class);
        when(operation.roles()).thenReturn(Collections.emptyList());

        assertThat(policy.apply(userRoles, operation, GRANT.GRANT_ALL), is(empty()));
        assertThat(policy.apply(userRoles, operation, GRANT.DENY_ALL), is(empty()));
    }

    @Test
    public void testRolelessOperationWithMultipleRoles() {
        List<UMLRole> userRoles = asRoles("admin", "user");

        UMLOperation operation = mock(UMLOperation.class);
        when(operation.roles()).thenReturn(Collections.emptyList());

        assertThat(policy.apply(userRoles, operation, GRANT.GRANT_ALL), is(empty()));
        assertThat(policy.apply(userRoles, operation, GRANT.DENY_ALL), is(empty()));
    }

    @Test
    public void testSingleRoleOperationWithNoRole() {
        List<UMLRole> userRoles = Collections.emptyList();

        UMLOperation operation = mock(UMLOperation.class);
        when(operation.roles()).thenReturn(asRoles("admin"));

        assertThat(policy.apply(userRoles, operation, GRANT.GRANT_ALL), hasItem(asRole("admin")));
        assertThat(policy.apply(userRoles, operation, GRANT.DENY_ALL), hasItem(asRole("admin")));
    }

    @Test
    public void testSingleRoleOperationWithMismatchingRole() {
        List<UMLRole> userRoles = asRoles("user");

        UMLOperation operation = mock(UMLOperation.class);
        when(operation.roles()).thenReturn(asRoles("admin"));

        assertThat(policy.apply(userRoles, operation, GRANT.GRANT_ALL), hasItem(asRole("admin")));
        assertThat(policy.apply(userRoles, operation, GRANT.DENY_ALL), hasItem(asRole("admin")));
    }

    @Test
    public void testSingleRoleOperationWithMultipleMismatchingRoles() {
        List<UMLRole> userRoles = asRoles("user", "officer");

        UMLOperation operation = mock(UMLOperation.class);
        when(operation.roles()).thenReturn(asRoles("admin"));

        assertThat(policy.apply(userRoles, operation, GRANT.GRANT_ALL), hasItem(asRole("admin")));
        assertThat(policy.apply(userRoles, operation, GRANT.DENY_ALL), hasItem(asRole("admin")));
    }

    @Test
    public void testSingleRoleOperationWithSingleMatchingRoles() {
        List<UMLRole> userRoles = asRoles("user", "officer", "admin");

        UMLOperation operation = mock(UMLOperation.class);
        when(operation.roles()).thenReturn(asRoles("admin"));

        assertThat(policy.apply(userRoles, operation, GRANT.GRANT_ALL), is(empty()));
        assertThat(policy.apply(userRoles, operation, GRANT.DENY_ALL), is(empty()));
    }

    @Test
    public void testMultipleRoleOperationWithSingleMatchingRoles() {
        List<UMLRole> userRoles = asRoles("user", "officer", "admin");

        UMLOperation operation = mock(UMLOperation.class);
        when(operation.roles()).thenReturn(asRoles("admin", "officer"));

        assertThat(policy.apply(userRoles, operation, GRANT.GRANT_ALL), is(empty()));
        assertThat(policy.apply(userRoles, operation, GRANT.DENY_ALL), is(empty()));
    }

    @Test
    public void testMultipleRoleOperationWithNoMatchingRole() {
        List<UMLRole> userRoles = asRoles("user", "reporter");

        UMLOperation operation = mock(UMLOperation.class);
        when(operation.roles()).thenReturn(asRoles("admin", "officer"));

        assertThat(policy.apply(userRoles, operation, GRANT.GRANT_ALL),
                hasItems(asRoles("admin", "officer").toArray(new UMLRole[0])));
        
        assertThat(policy.apply(userRoles, operation, GRANT.DENY_ALL),
                hasItems(asRoles("admin", "officer").toArray(new UMLRole[0])));
    }

    // -----------------------------------------------------------------------------------------------

    private static UMLRole asRole(String role) {
        return new UMLRoleImpl(role);
    }

    private static List<UMLRole> asRoles(String... roles) {
        return Arrays.asList(roles).stream().map(UMLRoleImpl::new).collect(Collectors.toList());
    }

}