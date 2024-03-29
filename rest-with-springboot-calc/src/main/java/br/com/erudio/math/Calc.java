package br.com.erudio.math;

import br.com.erudio.exception.UnsupportedMathOperationException;

public class Calc {
	
	public static Double sumCalc(Double n1, Double n2) {
		return (n1 + n2);
	}
	
	public static Double subCalc(Double n1, Double n2) {
		return (n1 - n2);
	}
	
	public static Double divCalc(Double n1, Double n2) {
		if (n2 == 0) {
			throw new UnsupportedMathOperationException("O segundo numero não pode ser zero");
		}
		return (n1 / n2);
	}
	
	public static Double mulCalc(Double n1, Double n2) {
		return (n1 * n2);
	}
	
	public static Double squCalc(Double n1) {
		return Math.sqrt(n1);
	}
	
	public static Double aveCalc(Double n1, Double n2) {
		return (n1 + n2) / 2;
	}
	
}