package br.com.erudio.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.erudio.converter.Converter;
import br.com.erudio.exception.UnsupportedMathOperationException;
import br.com.erudio.math.Calc;

@RestController
public class MathController {
	
	@RequestMapping(value = "/sum/{numberOne}/{numberTwo}", method=RequestMethod.GET)
	public Double sum( @PathVariable("numberOne") String numberOne, 
						@PathVariable("numberTwo") String numberTwo) throws Exception {
	
		if (!Converter.isNumeric(numberOne) || !Converter.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Favor informar um valor numérico");
		}
		return Calc.sumCalc(Converter.convertToDouble(numberOne), 
							Converter.convertToDouble(numberTwo));
	}	
	
	@RequestMapping(value = "/sub/{numberOne}/{numberTwo}", method=RequestMethod.GET)
	public Double sub( @PathVariable("numberOne") String numberOne, 
					   @PathVariable("numberTwo") String numberTwo) throws Exception {
	
		if (!Converter.isNumeric(numberOne) || !Converter.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Favor informar um valor numérico");
		}
		return Calc.subCalc(Converter.convertToDouble(numberOne), 
							Converter.convertToDouble(numberTwo));
	}
	
	@RequestMapping(value = "/div/{numberOne}/{numberTwo}", method=RequestMethod.GET)
	public Double div( @PathVariable("numberOne") String numberOne, 
					   @PathVariable("numberTwo") String numberTwo) throws Exception {
	
		if (!Converter.isNumeric(numberOne) || !Converter.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Favor informar um valor numérico");
		}
		return Calc.divCalc(Converter.convertToDouble(numberOne), 
							Converter.convertToDouble(numberTwo));
	}
	
	@RequestMapping(value = "/mul/{numberOne}/{numberTwo}", method=RequestMethod.GET)
	public Double mul( @PathVariable("numberOne") String numberOne, 
					   @PathVariable("numberTwo") String numberTwo) throws Exception {
	
		if (!Converter.isNumeric(numberOne) || !Converter.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Favor informar um valor numérico");
		}
		return Calc.mulCalc(Converter.convertToDouble(numberOne), 
							Converter.convertToDouble(numberTwo));
	}
	
	@RequestMapping(value = "/squ/{numberOne}", method=RequestMethod.GET)
	public Double squ( @PathVariable("numberOne") String numberOne) throws Exception {
	
		if (!Converter.isNumeric(numberOne)) {
			throw new UnsupportedMathOperationException("Favor informar um valor numérico");
		}
		return Calc.squCalc(Converter.convertToDouble(numberOne));
	}
	
	@RequestMapping(value = "/ave/{numberOne}/{numberTwo}", method=RequestMethod.GET)
	public Double ave( @PathVariable("numberOne") String numberOne, 
					   @PathVariable("numberTwo") String numberTwo) throws Exception {
	
		if (!Converter.isNumeric(numberOne) || !Converter.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Favor informar um valor numérico");
		}
		return Calc.aveCalc(Converter.convertToDouble(numberOne), 
							Converter.convertToDouble(numberTwo));
	}
}
