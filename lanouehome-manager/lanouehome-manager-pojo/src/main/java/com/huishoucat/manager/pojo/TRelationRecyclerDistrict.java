package com.huishoucat.manager.pojo;

import java.io.Serializable;

public class TRelationRecyclerDistrict implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long districtId;

	private Long recyclerId;

	public Long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}

	public Long getRecyclerId() {
		return recyclerId;
	}

	public void setRecyclerId(Long recyclerId) {
		this.recyclerId = recyclerId;
	}
}