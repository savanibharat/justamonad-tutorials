package com.justamonad.tutorials.spring.dependency.injection.fakes;

import java.net.URI;
import java.util.Map;

import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Configuration;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

public class FakeInvoiceWebTarget implements WebTarget {

	private String invoiceId;
	private final String path;

	public FakeInvoiceWebTarget(String path) {
		this.path = path;
	}

	@Override
	public Configuration getConfiguration() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WebTarget property(String arg0, Object arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WebTarget register(Class<?> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WebTarget register(Object arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WebTarget register(Class<?> arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WebTarget register(Class<?> arg0, Class<?>... arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WebTarget register(Class<?> arg0, Map<Class<?>, Integer> arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WebTarget register(Object arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WebTarget register(Object arg0, Class<?>... arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WebTarget register(Object arg0, Map<Class<?>, Integer> arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public URI getUri() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UriBuilder getUriBuilder() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WebTarget matrixParam(String arg0, Object... arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WebTarget path(String path) {
		return null;
	}

	@Override
	public WebTarget queryParam(String arg0, Object... arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Builder request() {
		String[] arr = path.split("/");
		invoiceId = arr[arr.length - 1];
		return new FakeInvoiceBuilder(invoiceId);
	}

	@Override
	public Builder request(String... arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Builder request(MediaType... arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WebTarget resolveTemplate(String arg0, Object arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WebTarget resolveTemplate(String arg0, Object arg1, boolean arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WebTarget resolveTemplateFromEncoded(String arg0, Object arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WebTarget resolveTemplates(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WebTarget resolveTemplates(Map<String, Object> arg0, boolean arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WebTarget resolveTemplatesFromEncoded(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
