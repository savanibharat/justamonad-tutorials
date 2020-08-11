package com.justamonad.tutorials.spring.dependency.injection;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import org.springframework.stereotype.Component;

/**
 * Change in application path doesn't take effect.
 */
@ApplicationPath("/")
@Component
public class ApplicationConfig extends Application {
}
