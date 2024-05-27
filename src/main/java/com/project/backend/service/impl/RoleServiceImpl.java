package com.project.backend.service.impl;

import com.project.backend.dto.RoleDto;
import com.project.backend.entity.Role;
import com.project.backend.exception.ResourceNotFoundException;
import com.project.backend.mapper.RoleMapper;
import com.project.backend.repository.RoleRepository;
import com.project.backend.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;
    @Override
    public RoleDto createRole(RoleDto roleDto) {
        Role role = RoleMapper.MapToRole(roleDto);
        Role savedRole = roleRepository.save(role);
        return RoleMapper.MaptoRoleDto(savedRole);
    }

    @Override
    public RoleDto getRoleById(Long roleId) {
        Role role = roleRepository.findById(roleId).orElseThrow(() -> new ResourceNotFoundException("Role is not exist with given id " + roleId));
        return RoleMapper.MaptoRoleDto(role);
    }

    @Override
    public RoleDto updateRole(Long roleId, RoleDto roleDto) {
        Role role = roleRepository.findById(roleId).orElseThrow(() -> new ResourceNotFoundException("Role is not exist with given id " + roleId));
        role.setId(roleDto.getId());
        role.setName(roleDto.getName());
        return RoleMapper.MaptoRoleDto(role);
    }

    @Override
    public List<RoleDto> getAllRole() {
        List<Role> roles = roleRepository.findAll();
        return roles.stream().map((role) -> RoleMapper.MaptoRoleDto(role)).collect(Collectors.toList());
    }

    @Override
    public void deleteRole(Long roleId) {
        Role role = roleRepository.findById(roleId).orElseThrow(() -> new ResourceNotFoundException("Role is not exist with given id " + roleId));
        roleRepository.deleteById(roleId);
    }


}
