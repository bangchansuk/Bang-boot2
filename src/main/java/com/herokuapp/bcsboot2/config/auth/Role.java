package com.herokuapp.bcsboot2.config.auth;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Role {
	GUEST("ROLE_GEST","손님"),
	USER("ROLE_USER","일반사용자"),
	ADMIN("ROLE_ADMIN","관리자");
	private final String key;
	private final String title;
}
