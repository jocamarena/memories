package com.web.memories.security.annotations;

import org.springframework.security.access.prepost.PreAuthorize;

@PreAuthorize(value = "hasAuthority('update.memory')")
public @interface UpdateMemoryPermission {
}
