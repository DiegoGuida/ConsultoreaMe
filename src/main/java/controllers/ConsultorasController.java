package controllers;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Consultora;
import model.RepositorioConsultoras;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class ConsultorasController {

	public ModelAndView getConsultoras(Request request, Response response) {
		Map<String, Object> modelo = new HashMap<>();
		String nombreBuscado = request.queryParams("filtro");
		
		if (nombreBuscado!= null ) {
			modelo.put("consultoras", RepositorioConsultoras.instancia.buscarPorNombre(nombreBuscado));
		}else {
			modelo.put("consultoras", RepositorioConsultoras.instancia.listarOrdenadas());
		}
		
		return new ModelAndView(modelo, "consultoras.html.hbs");
		
/*		Map<String, Object> modelo = new HashMap<>();
		modelo.put("anio", LocalDate.now().getYear());
		modelo.put("consultoras", RepositorioConsultoras.instancia.listarOrdenadas());
*/
//      modelo.put("consultoras", RepositorioConsultoras.instancia.listar().parallelStream().sorted(
//      Comparator.comparing(Consultora::getCantidadEmpleados)).collect(Collectors.toList()));
		
//		return new ModelAndView(modelo, "consultoras.html.hbs");
	}

	public ModelAndView getConsultora(Request request, Response response) {
	//	Map<String, Object> modelo = new HashMap<>();
	//	modelo.put("anio", LocalDate.now().getYear());
	//	modelo.put("consultora", RepositorioConsultoras.instancia.buscar(new Integer(request.params(":id"))));

		try {
			Consultora consultora = RepositorioConsultoras.instancia.buscar(new Integer(request.params(":id"))) ;	
			return new ModelAndView(consultora, "consultora.html.hbs");
		} catch(NumberFormatException e) {
			response.status(400);
			return new ModelAndView(null, "error.html.hbs");
		}
		
		
	}
}
