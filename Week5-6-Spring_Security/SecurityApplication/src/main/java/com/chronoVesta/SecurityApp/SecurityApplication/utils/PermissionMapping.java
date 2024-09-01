package com.chronoVesta.SecurityApp.SecurityApplication.utils;

import com.chronoVesta.SecurityApp.SecurityApplication.entity.enums.Permission;
import com.chronoVesta.SecurityApp.SecurityApplication.entity.enums.Role;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.chronoVesta.SecurityApp.SecurityApplication.entity.enums.Permission.*;
import static com.chronoVesta.SecurityApp.SecurityApplication.entity.enums.Role.*;

public class PermissionMapping {

    private static final Map<Role, Set<Permission>> map = Map.of(
            USER, Set.of(USER_VIEW, POST_VIEW),
            CREATOR, Set.of(POST_CREATE, USER_UPDATE, POST_UPDATE),
            ADMIN, Set.of(POST_DELETE, USER_DELETE, USER_CREATE, POST_CREATE, USER_UPDATE, POST_UPDATE)

    );

    public static Set<SimpleGrantedAuthority> getAuthoritiesForRole(Role role) {
        return map.get(role).stream()
                .map(permission -> new SimpleGrantedAuthority(permission.name()))
                .collect(Collectors.toSet());
    }

}
