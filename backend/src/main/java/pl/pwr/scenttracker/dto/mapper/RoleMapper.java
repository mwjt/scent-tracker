package pl.pwr.scenttracker.dto.mapper;

import pl.pwr.scenttracker.dto.entity.RoleDTO;
import pl.pwr.scenttracker.model.Role;

public class RoleMapper {

    public static RoleDTO toRoleDTO(Role role) {
        return RoleDTO.builder()
                .name(role.getName())
                .build();
    }
}
