package com.sparta.spring_session_authn_authz_sample.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class UnauthorizedException extends ResponseStatusException {

  public UnauthorizedException(HttpStatusCode status, String reason) {
    super(status, reason);
  }
}
