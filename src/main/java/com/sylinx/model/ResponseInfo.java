package com.sylinx.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseInfo implements Serializable {

	private static final long serialVersionUID = -4417715614021482064L;

	private String code;
	private String message;
}
