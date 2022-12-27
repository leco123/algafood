package com.algaworks.algafood.core.security;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

public @interface CheckSecurity {

	public @interface Cozinhas {
		
		@PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDITAR_COZINHAS')")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeEditar { }

		@PreAuthorize("hasAuthority('SCOPE_READ') and isAuthenticated()")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeConsultar { }
		
	}

	public @interface Restaurantes {

		@PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDITAR_RESTAURANTES')")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeEditar { }

		@PreAuthorize("hasAuthority('SCOPE_READ') and isAuthenticated()")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeConsultar { }

	}
	
}
