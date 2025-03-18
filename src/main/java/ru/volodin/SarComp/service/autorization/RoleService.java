package ru.volodin.SarComp.service.autorization;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.volodin.SarComp.entity.authorization.Role;
import ru.volodin.SarComp.repository.autorization.RoleRepository;

import java.util.List;
import java.util.UUID;

@Service
@SuppressWarnings({"unused"})
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public List<Role> findAllCategories() {
        return roleRepository.findAll();
    }

    public Role addRole(Role role) {
        if (StringUtils.isEmpty(role.getName())) {
            throw new IllegalStateException("Введены неполные данные категории!");
        } else {
            return roleRepository.save(role);
        }
    }

    public Role getRoleById(UUID id) {
        return roleRepository.findById(id).orElseThrow(() ->
                new IllegalStateException("Категория не была найдена в БД!"));
    }

    public Role editRoleById(Role role) {
        if (StringUtils.isEmpty(role.getName())) {
            throw new IllegalStateException("Введены неполные данные категории!");
        } else {
            return roleRepository.save(role);
        }
    }

    public void deleteRoleById(UUID id) {
        if (!roleRepository.existsById(id)) {
            throw new IllegalStateException("Категории с таким ID не существует!");
        } else {
            roleRepository.deleteById(id);
        }
    }
}
