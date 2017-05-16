package com.jychan.notbad.domain;

/**
 * Created by chenjinying on 2017/5/7.
 * mail: 415683089@qq.com
 */
public class RoleInfo {

    private int id;
    private String roleName;
    private String permission;

    public RoleInfo() {
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RoleInfo{");
        sb.append("id=").append(id);
        sb.append(", roleName='").append(roleName).append('\'');
        sb.append(", permission='").append(permission).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}
