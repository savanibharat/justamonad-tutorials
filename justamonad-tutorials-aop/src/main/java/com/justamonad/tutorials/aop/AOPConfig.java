package com.justamonad.tutorials.aop;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan({ "com.justamonad.tutorials.aop" })
@EnableAspectJAutoProxy
public class AOPConfig {

}
