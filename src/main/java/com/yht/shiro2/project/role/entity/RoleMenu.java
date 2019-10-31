package com.yht.shiro2.project.role.entity;

/**
 * 角色菜单关联
 */
public class RoleMenu {
    private Integer roleId;
    private Integer menuId;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }
}
