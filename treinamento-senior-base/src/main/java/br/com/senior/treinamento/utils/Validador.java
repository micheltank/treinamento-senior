package br.com.senior.treinamento.utils;

import javax.validation.ValidationException;

import org.springframework.stereotype.Component;

import com.google.common.base.Strings;

@Component
public class Validador {

	public void validaTamanhoMinimoString(String valor, int tamanhoMinimo) {
		if (Strings.isNullOrEmpty(valor) || valor.length() < tamanhoMinimo) {
			throw new ValidationException("O valor não possui o tamanho mínimo");
		}
	}
}
