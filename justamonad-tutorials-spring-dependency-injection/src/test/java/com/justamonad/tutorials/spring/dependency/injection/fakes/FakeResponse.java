package com.justamonad.tutorials.spring.dependency.injection.fakes;

import java.lang.annotation.Annotation;
import java.math.BigDecimal;
import java.net.URI;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.core.EntityTag;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.Link.Builder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

import com.justamonad.tutorials.spring.dependency.injection.api.InvoiceResponse;

public class FakeResponse extends Response {

	private final String invoiceId;

	public FakeResponse(String invoiceId) {
		this.invoiceId = invoiceId;
	}

	@Override
	public boolean bufferEntity() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		//
	}

	@Override
	public Set<String> getAllowedMethods() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, NewCookie> getCookies() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getEntity() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EntityTag getEntityTag() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getHeaderString(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Locale getLanguage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getLastModified() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getLength() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Link getLink(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Builder getLinkBuilder(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Link> getLinks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public URI getLocation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MediaType getMediaType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MultivaluedMap<String, Object> getMetadata() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getStatus() {
		return getStatusInfo().getStatusCode();
	}

	@Override
	public StatusType getStatusInfo() {
		return Response.status(Integer.valueOf(invoiceId)).build().getStatusInfo();
	}

	@Override
	public MultivaluedMap<String, String> getStringHeaders() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasEntity() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasLink(String arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T readEntity(Class<T> arg0) {
		if (getStatusInfo().getStatusCode() == Response.Status.OK.getStatusCode()) {
			InvoiceResponse invoiceResponse = new InvoiceResponse();
			invoiceResponse.setInvoiceId(String.valueOf(getStatusInfo().getStatusCode()));
			invoiceResponse.setInvoiceTotal(
					Money.of(CurrencyUnit.USD, 
							new BigDecimal(getStatusInfo().getStatusCode())));
			return (T) invoiceResponse;
		}
		return null;
	}

	@Override
	public <T> T readEntity(GenericType<T> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T readEntity(Class<T> arg0, Annotation[] arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T readEntity(GenericType<T> arg0, Annotation[] arg1) {
		// TODO Auto-generated method stub
		return null;
	}

}
