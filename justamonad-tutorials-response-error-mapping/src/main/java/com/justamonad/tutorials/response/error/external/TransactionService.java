package com.justamonad.tutorials.response.error.external;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import com.justamonad.tutorials.response.error.mapping.Error;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.justamonad.tutorials.response.error.mapping.IResponseHandler;
import com.justamonad.tutorials.response.error.objects.Transaction;

@Service
public class TransactionService {

	@Inject
	private InvoiceService invoiceService;

	@Inject
	private PaymentService paymentService;

	@Inject
	@Qualifier(IResponseHandler.TRANSACTION_RESPONSE_MAPPER)
	private IResponseHandler txnResponseMapper;

	@Inject
	@Qualifier(IResponseHandler.INVOICE_RESPONSE_MAPPER)
	private IResponseHandler invoiceResponseMapper;

	public Response executeTransaction(Transaction transaction) {
		Error error = null;
		Response invoiceResponse = invoiceService.checkInvoice(transaction.getInvoice());
		if (invoiceResponse.getStatusInfo().getFamily() == Response.Status.Family.SUCCESSFUL) {
			Response txnResponse = paymentService.executePayment(transaction);
			error = invoiceResponseMapper.mapError(txnResponse.readEntity(Error.class));
		} else {
			// configure invoice error.
			error = invoiceResponseMapper.mapError(invoiceResponse.readEntity(Error.class));
		}
		return Response.status(error.getCode()).entity(error).build();
	}

}
