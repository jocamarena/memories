package com.web.memories.security.annotations;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@PreAuthorize(value = "hasAuthority('delete.memory')")
@Retention(RetentionPolicy.RUNTIME)
public @interface DeleteMemoryPermission {
}
