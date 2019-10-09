package com.huishoucat.manager.pojo;

import java.io.Serializable;

public class TRelationManagerRole implements Serializable{

	private static final long serialVersionUID = 1L;
	
    private Long managerId;

    private Long roleId;

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}