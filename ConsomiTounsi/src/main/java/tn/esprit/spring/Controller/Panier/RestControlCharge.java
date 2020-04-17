package tn.esprit.spring.Controller.Panier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

import tn.esprit.spring.Model.ChargeRequest;
import tn.esprit.spring.Service.Panier.StripeService;



@RestController
public class RestControlCharge {

	@Autowired
	private StripeService stripeService;

	@PostMapping("/customer1/{idUser}")
	@ResponseBody
	public String createCustomer(@PathVariable("idUser") int idUser) {
		return stripeService.createStripeCustomer(idUser);
	}

	// http://localhost:8081/customer/retour_ta3_methode_create_customer/4242424242424242/11/2026/123
	@PostMapping("/customer/{customerId}/{carta}/{expMonth}/{expYear}/{cvc}")
	@ResponseBody
	// @PreAuthorize("hasRole('USER')")
	public String createCustomer(@PathVariable("customerId") String customerId, @PathVariable("carta") String carta,
			@PathVariable("expMonth") String expMonth, @PathVariable("expYear") String expYear,
			@PathVariable("cvc") String cvc) throws StripeException {
		return stripeService.createCustumorStripe(customerId, carta, expMonth, expYear, cvc);
	}

	// http://localhost:8081/paymentintent
	/*
	 * { "description":"test la methode payment", "amount":"10000",
	 * "currency":"eur" }
	 */
	@PostMapping("/paymentintent")
	// @PreAuthorize("hasRole('USER')")
	public String payment(@RequestBody ChargeRequest chargeRequest) throws StripeException {
		return stripeService.paymentIntent(chargeRequest);
	}

	// http://localhost:8081/confirm/{id}/{idCommande}/{idUser}
	@PostMapping("/confirm/{id}/{idCommande}/{idUser}")
	// @PreAuthorize("hasRole('USER')")
	public ResponseEntity<String> confirm(@PathVariable("id") String id,@PathVariable("idCommande") int idCommande , @PathVariable("idUser") int idUser) throws StripeException {
		PaymentIntent paymentIntent = stripeService.confirm(id,idCommande,idUser);
		String paymentStr = paymentIntent.toJson();
		return new ResponseEntity<String>(paymentStr, HttpStatus.OK);
		
	}
}
