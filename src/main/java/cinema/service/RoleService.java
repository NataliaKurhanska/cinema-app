package cinema.service;

import cinema.model.Role;

public interface RoleService {
    Role add(Role roleName);

    Role getByName(String roleName);
}
