
package principal;
import java.awt.*;
import javax.swing.*;

public class Ventana extends JFrame{

    Panel panel;
    
    public Ventana(){
        
        this.setSize(800, 600);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        
        panel = new Panel(800, 600);
        this.getContentPane().add(panel);
        panel.generarLluvia();
        panel.dibujarLluvia();
    }
    
    
    
    
    private class Panel extends JPanel{
        
        private int anchoLluvia = 1;
        private int altoLluvia = 8;
        private int anchoVentana;
        private int altoVentana;
        private int numeroDeGotas;
        private int[][] posicionGotas;
        
        
        public Panel(int anchoV, int altoV){
            anchoVentana = anchoV;
            altoVentana = altoV;
            numeroDeGotas = 200;
        }
        
        public Panel(){
            anchoVentana = 800;
            altoVentana = 600;
            numeroDeGotas = 100;
        }
        
        public void generarLluvia(){
            posicionGotas = new int[numeroDeGotas][3];
            
            for(int i = 0; i < numeroDeGotas; i++){
                int posX = (int)(Math.random() * anchoVentana);
                int posY = (int)(Math.random() * altoVentana);

                posicionGotas[i][0] = posX;
                posicionGotas[i][1] = -posY;
                posicionGotas[i][2] = (int)(Math.random() * 5);
            }
        }
        
        public void dibujarLluvia(){
            Graphics g;
            while(true){
                
                this.repaint();
                
                g = this.getGraphics();
                g.setColor(Color.blue);
                
                for(int i = 0; i < numeroDeGotas; i++){
                    if(posicionGotas[i][1] >= altoVentana + altoLluvia){
                        posicionGotas[i][1] = -altoLluvia;
                    }
                    
                    /*g.drawLine(posicionGotas[i][0], posicionGotas[i][1],
                            posicionGotas[i][0] + anchoLluvia,
                            posicionGotas[i][1] + altoLluvia);*/
                    
                    g.fillRect(posicionGotas[i][0], posicionGotas[i][1],
                            anchoLluvia, altoLluvia);
                    
                    posicionGotas[i][1] += posicionGotas[i][2];
                }
                
                try{
                    Thread.sleep(2);
                } catch(Exception ex){}
                
            }
            
        }
        
        @Override
        protected void paintComponent(Graphics g){
            g.setColor(this.getBackground());
            g.fillRect(0, 0, anchoVentana, altoVentana);
        }
    }
    
}