package com.sylinx.model;

import com.sun.xml.internal.bind.v2.model.core.ID;

import java.util.Date;

public class TokenModel {
	private static final long serialVersionUID = 4566334160572911795L;

	private ID id;
	private Date createTime = new Date();
	private Date updateTime = new Date();

	private Date expireTime;

	private String val;

	public Date getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}

	public String getVal() {
		return val;
	}

	public void setVal(String val) {
		this.val = val;
	}

	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
