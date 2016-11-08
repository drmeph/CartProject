package com.drmeph.cartProject.security;

import com.drmeph.cartProject.configuration.SecurityConfig;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * Project: stepfighterz-rest-api
 * File: SecurityWebInitializer
 * Created by kevindorfer on 2016-04-19 at 8:11 PM
 */

public class SecurityWebInitializer extends AbstractSecurityWebApplicationInitializer {

    public SecurityWebInitializer() {
        super(SecurityConfig.class);
    }
}
