package cinema.service.impl;

import cinema.dao.RoleDao;
import cinema.model.Role;
import cinema.service.RoleService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    private RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public Role add(Role roleName) {
        return roleDao.add(roleName);
    }

    @Override
    public Role getByName(String roleName) {
        return roleDao.getByName(roleName).orElseThrow(() ->
                new UsernameNotFoundException("Can't get role by name: " + roleName));
    }
}
