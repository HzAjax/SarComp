package ru.volodin.SarComp.controller.autorization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.volodin.SarComp.entity.authorization.Role;
import ru.volodin.SarComp.service.autorization.RoleService;


import java.util.UUID;

@RestController
@RequestMapping("/sarcomp/roles")
@SuppressWarnings({"unused"})
public class RoleController {
    @Autowired
    private RoleService roleService;

    @PostMapping
    public ResponseEntity<?> addRole(@RequestBody Role role) {
        return ResponseEntity.ok(roleService.addRole(role));
    }

    @GetMapping("/{roleId}")
    public ResponseEntity<?> getRole(@PathVariable(value = "roleId") UUID roleId) {
        return ResponseEntity.ok(roleService.getRoleById(roleId));
    }

    @PutMapping
    public ResponseEntity<?> editRole(@RequestBody Role role) {
        return ResponseEntity.ok(roleService.editRoleById(role));
    }

    @DeleteMapping("/{roleId}")
    public ResponseEntity<?> deleteRoleById(@PathVariable("roleId") UUID roleId) {
        Role deletedRole = roleService.getRoleById(roleId);
        roleService.deleteRoleById(roleId);
        return ResponseEntity.ok(deletedRole);
    }
}
