package com.mycompany.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//http://localhost:8080/api/v1/calculator
@RestController
@RequestMapping("/api/v1/calculator") // Mapeo de una url a nivel de clase para una clase controller
public class CalculatorController {

	// http://localhost:8080/api/v1/calculator/param
	@GetMapping("/param") // Mapeo de una url a nivel de metodo para una función controller
	public String param() {
		return "vacio";
	}

	// http://localhost:8080/api/v1/calculator/param1?var1=10.5&var2=20.2
	@GetMapping("/param1") // Mapeo de una url a nivel de metodo para una función controller
	public String param1(@RequestParam("var1") String var1, @RequestParam("var2") String var2) {
		// "var1" podría ser diferente a "String var1"

		return var1 + " - " + var2; // R. 10.5 - 20.2
	}

	// http://localhost:8080/api/v1/calculator/param2/2.5/5.5
	@GetMapping("/param2/{var1}/{var2}")
	public String param2(@PathVariable("var1") String var1, @PathVariable("var2") String var2) {
		// {var1} debe ser igual a "var1", pero podrían ser diferentes a "String var1"

		return var1 + " - " + var2; // R: 2.5 - 5.5
	}

	// http://localhost:8080/api/v1/calculator/param3/30.5?var1=10.5&var2=20.2
	@GetMapping("/param3/{var3}")
	public String param3(@RequestParam("var1") String var1, @RequestParam("var2") String var2,
			@PathVariable("var3") String var3) {
		// {var3} debe ser igual a "var3", pero podrían ser diferentes a "String var3"

		return var1 + " - " + var2 + " - " + var3; // R: 10.5 - 20.2 - 30.5
	}

	// http://localhost:8080/api/v1/calculator/param4
	/**
	 * POST { "valor1":"A", "valor2":"B", "valor3":"C", "valor4mod":4 }
	 */
	//El resultado del estado Http por defecto es 200 (OK) 
	@PostMapping("/param4")
	public String param4(@RequestBody ColeccionDatos coleccionDatos) {
		return coleccionDatos.getValor1() + " " + coleccionDatos.getValor2() + " " + coleccionDatos.getValor3() + " "
				+ coleccionDatos.getValor4();
		
		//R: a B C 4
	}

	// http://localhost:8080/api/v1/calculator/param4
	/**
	 * POST { "valor1":"A", "valor2":"B", "valor3":"C", "valor4mod":4 }
	 */
	//Retornando un Http estado personalizado, ejm: 201 (CREATED)
	@PostMapping("/param5")	
	public ResponseEntity<String> param5(@RequestBody ColeccionDatos coleccionDatos) {
		String resultado = null;
		resultado = coleccionDatos.getValor1() + " " + coleccionDatos.getValor2() + " " + coleccionDatos.getValor3()
				+ " " + coleccionDatos.getValor4();

		ResponseEntity<String> responseEntity = new ResponseEntity<>(resultado, HttpStatus.CREATED);
		return responseEntity;
	}
	// POST cuando se quiere crear data en el servidor, se pasa la data por el body
	// PUT cuando va a actualizar data en el servidor, se pasa la data por el body
	// DELETE puedo pasar el valor por la url, no es necesario por el body
	// GET cuando se quiere consultar, solo pasar uno o dos valores, y datos no
	// confidenciales
}
