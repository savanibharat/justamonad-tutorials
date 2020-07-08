//package com.justamonad.tutorials.spring.rest;
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;
//
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestHeader;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//import com.justamonad.api.platform.auth.Application;
//import com.justamonad.auth.commons.exception.AuthorizationException;
//
//@RequestMapping(path = "/v1/oauth2/applications")
//public interface IdentityResource {
//
//        @RequestMapping(
//                        method = RequestMethod.POST,
//                        consumes = MediaType.APPLICATION_JSON,
//                        produces = MediaType.APPLICATION_JSON)
//        public Response createApplication(
//                        @RequestBody Application application) throws AuthorizationException;
//
//        @RequestMapping(
//                        method = RequestMethod.GET)
//        public Response validateCredentials(
//                        @RequestHeader("Authorization") String authorization) throws AuthorizationException;
