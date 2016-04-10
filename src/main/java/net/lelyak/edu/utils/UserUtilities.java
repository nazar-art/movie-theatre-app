package net.lelyak.edu.utils;

import net.lelyak.edu.entity.enums.UserRole;
import org.apache.commons.lang.Validate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class UserUtilities {
    public static List<UserRole> userRolesStub(UserRole ... params) {
        Validate.notEmpty(params, "User Roles can not be empty");

        return Arrays.stream(params)
                .collect(Collectors.toList());
    }
}
