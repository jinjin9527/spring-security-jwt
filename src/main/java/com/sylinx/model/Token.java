package com.sylinx.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Token implements Serializable {

	private static final long serialVersionUID = 6314027741784310221L;

	private String token;

	private Long loginTime;
}
