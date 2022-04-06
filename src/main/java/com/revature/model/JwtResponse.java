package com.revature.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class JwtResponse implements Serializable {

	private static final long serialVersionUID = 4461526783054931057L;

	private final String jwt;
}
