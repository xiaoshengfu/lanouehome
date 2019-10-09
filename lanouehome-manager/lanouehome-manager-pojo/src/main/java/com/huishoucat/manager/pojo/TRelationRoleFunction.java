package com.huishoucat.manager.pojo;

import java.io.Serializable;

public class TRelationRoleFunction implements Serializable{

	private static final long serialVersionUID = 1L;
	
    private Long roleId;

    private Long functionId;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getFunctionId() {
        return functionId;
    }

    public void setFunctionId(Long functionId) {
        this.functionId = functionId;
    }
}