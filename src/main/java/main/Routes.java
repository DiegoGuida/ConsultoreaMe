package main;

import controllers.ConsultorasController;
import controllers.HomeController;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Routes {

  public static void main(String[] args) {
    System.out.println("Iniciando servidor");
    
    Spark.port(8080);
    Spark.staticFileLocation("/public");
    
    new Bootstrap().run(); 
    
    HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
    HomeController homeController = new HomeController();
    ConsultorasController consultorasController = new ConsultorasController();
    
    // opcion 1  // pasar un diccionario
    // opción 2  // pasar un objeto que tenga getters        
    // opcion 3  // pasar una lista y recorrerla mediante #lista
    Spark.get("/", (request, response) -> homeController.getHome() , engine);
    Spark.get("/consultoras", consultorasController::getConsultoras , engine);
    Spark.get("/consultoras/:id", consultorasController::getConsultora , engine);
    
  }



}
