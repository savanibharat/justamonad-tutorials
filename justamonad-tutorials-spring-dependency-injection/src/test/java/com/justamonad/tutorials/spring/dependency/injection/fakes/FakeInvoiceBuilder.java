package com.justamonad.tutorials.spring.dependency.injection.fakes;

import java.util.Locale;

import javax.ws.rs.client.AsyncInvoker;
import javax.ws.rs.client.CompletionStageRxInvoker;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.RxInvoker;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

public class FakeInvoiceBuilder implements Invocation.Builder {

	private final String invoiceId;
	
	public FakeInvoiceBuilder(String invoiceId) {
		this.invoiceId = invoiceId;
	}
	
	@Override
	public Response delete() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T delete(Class<T> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T delete(GenericType<T> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response get() {
		return new FakeResponse(invoiceId);
	}

	@Override
	public <T> T get(Class<T> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T get(GenericType<T> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response head() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response method(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T method(String arg0, Class<T> arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T method(String arg0, GenericType<T> arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response method(String arg0, Entity<?> arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T method(String arg0, Entity<?> arg1, Class<T> arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T method(String arg0, Entity<?> arg1, GenericType<T> arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response options() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T options(Class<T> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T options(GenericType<T> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response post(Entity<?> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T post(Entity<?> arg0, Class<T> arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T post(Entity<?> arg0, GenericType<T> arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response put(Entity<?> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T put(Entity<?> arg0, Class<T> arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T put(Entity<?> arg0, GenericType<T> arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response trace() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T trace(Class<T> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T trace(GenericType<T> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Builder accept(String... arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Builder accept(MediaType... arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Builder acceptEncoding(String... arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Builder acceptLanguage(Locale... arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Builder acceptLanguage(String... arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AsyncInvoker async() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Invocation build(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Invocation build(String arg0, Entity<?> arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Invocation buildDelete() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Invocation buildGet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Invocation buildPost(Entity<?> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Invocation buildPut(Entity<?> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Builder cacheControl(CacheControl arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Builder cookie(Cookie arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Builder cookie(String arg0, String arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Builder header(String arg0, Object arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Builder headers(MultivaluedMap<String, Object> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Builder property(String arg0, Object arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CompletionStageRxInvoker rx() {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public <T extends RxInvoker> T rx(Class<T> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
