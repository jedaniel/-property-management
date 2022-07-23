package com.mycompany.controller;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ColeccionDatos {
	private String valor1;
	private String valor2;
	private String valor3;
	@JsonProperty("valor4mod")
	private String valor4;
}
