package proyectoswing_completo;


import ej1_ventanas.VentanaBasica;
import ej2_componentes.VentanaComponentes;
import ej3_layouts.VentanaLayouts;
import ej4_eventos.VentanaEventos;
import ej5_dialogos.VentanaDialogos;
import ej6_graficos.VentanaGraficos;
import ej7_mvc.ControladorPrincipal;
import ej7_mvc.ModeloPrincipal;
import ej7_mvc.VistaPrincipal;

import javax.swing.SwingUtilities;

public class ProyectoSWING_completo {

   
    public static void main(String[] args) {
      
        SwingUtilities.invokeLater(() -> {
            
  
            //VentanaBasica ej1 = new VentanaBasica();
            //ej1.setVisible(true);

          
            //VentanaComponentes ej2 = new VentanaComponentes();
            //ej2.setVisible(true);

          
            //VentanaLayouts ej3 = new VentanaLayouts();
            //ej3.setVisible(true);

            
            //VentanaEventos ej4 = new VentanaEventos();
            //ej4.setVisible(true);

           
            //VentanaDialogos ej5 = new VentanaDialogos();
            //ej5.setVisible(true);

           
            //VentanaGraficos ej6 = new VentanaGraficos();
            //ej6.setVisible(true);

          
            ModeloPrincipal modelo = new ModeloPrincipal();
            VistaPrincipal vista = new VistaPrincipal();
            
            ControladorPrincipal controlador = new ControladorPrincipal(modelo, vista); 
            
            vista.setVisible(true); 

        });
    }
 }
    

