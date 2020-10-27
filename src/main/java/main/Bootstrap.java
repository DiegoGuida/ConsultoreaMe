package main;

import model.Consultora;
import model.Proyecto;

import java.math.BigDecimal;

import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

/**
 * Ejecutar antes de levantar el servidor por primera vez
 * 
 * @author flbulgarelli
 */
public class Bootstrap implements WithGlobalEntityManager, EntityManagerOps, TransactionalOps {

  public static void main(String[] args) {
    new Bootstrap().run();
  }

  public void run() {
    withTransaction(() -> {
    	 
    	Proyecto proyecto1 = new Proyecto("proyecto1", new BigDecimal(100));
    	Proyecto proyecto2 = new Proyecto("proyecto2", new BigDecimal(300));
    	Proyecto proyecto3 = new Proyecto("proyecto3", new BigDecimal(100));
    	Proyecto proyecto4 = new Proyecto("proyecto4", new BigDecimal(100));
    	Consultora consultora1 = new Consultora("dblandit", 10) ;
    	Consultora consultora2 = new Consultora("2diseños", 15) ;
    	Consultora consultora3 = new Consultora("conProyectos", 25) ;
    	
    	consultora1.asignar(proyecto1);
    	consultora1.asignar(proyecto2);
    	consultora1.asignar(proyecto3);
    	consultora1.asignar(proyecto4);
    	consultora2.asignar(proyecto3);
    	consultora2.asignar(proyecto4);
    	consultora3.asignar(proyecto1);
    	consultora3.asignar(proyecto2);
    	consultora3.asignar(proyecto3);
    	consultora3.asignar(proyecto4);
    	
    	persist(proyecto1);
    	persist(proyecto2);
    	persist(proyecto3);
    	persist(proyecto4);  
    	persist(consultora1);
    	persist(consultora2);
    	persist(consultora3);
	    persist(new Consultora("chakanalabs", 2));
	    persist(new Consultora("homero", 1));	    
	    
    });
  }

}
