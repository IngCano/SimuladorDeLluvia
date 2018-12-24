
package principal;
import java.awt.*;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class Ventana extends JFrame{

    private Panel panel;
    
    public Ventana(){
        
        this.setSize(800, 600);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        
        panel = new Panel(800, 600);
        this.getContentPane().add(panel);
        panel.setDoubleBuffered(true);
        panel.generarLluvia();
        panel.dibujarLluvia();
    }
    
    
    
    
    private class Panel extends JPanel{
        
        private int anchoLluvia = 1;
        private int altoLluvia = 8;
        private int anchoVentana;
        private int altoVentana;
        private int numeroDeGotas;
        private int tiS = 4;
        private int taS = 8;
        private int[][] posicionGotas;
        
        //posicionGotas[][0] = X
        //posicionGotas[][1] = Y
        //posicionGotas[][2] = velocidad
        
        
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
                posicionGotas[i][2] = (int)(Math.random() * 6);
                
                if(posicionGotas[i][2] < 2){
                    posicionGotas[i][2] = 2;
                }
            }
        }
        
        public void generarSalpicadura(Graphics g, int x, int y){
            g.drawLine(x, y, x+taS, y-3);
            g.drawLine(x, y, x-taS, y-3);
        }
        
        public void dibujarLluvia(){
            Graphics g;
            Color c;
            while(true){
                
                this.repaint();
                
                g = this.getGraphics();
                g.setColor(Color.GREEN);
                g.drawLine(0, altoVentana-100, anchoVentana, altoVentana-100);
                
                for(int i = 0; i < numeroDeGotas; i++){
                    if(posicionGotas[i][1] >= altoVentana - (100 + altoLluvia)){
                        
                        generarSalpicadura(g, posicionGotas[i][0],
                                posicionGotas[i][1]+altoLluvia);
                        posicionGotas[i][1] = -altoLluvia;
                    }
                    
                    /*g.drawLine(posicionGotas[i][0], posicionGotas[i][1],
                            posicionGotas[i][0] + anchoLluvia,
                            posicionGotas[i][1] + altoLluvia);*/
                    
                    if(posicionGotas[i][2] == 2){
                        c = new Color(67, 120, 225);
                    } else if(posicionGotas[i][2] == 3){
                        c = new Color(60, 89, 182);
                    } else if(posicionGotas[i][2] == 4){
                        c = new Color(50, 77, 158);
                    } else {
                        c = new Color(45, 63, 144);
                    }
                    
                    
                    g.setColor(c);
                    
                    g.fillRect(posicionGotas[i][0], posicionGotas[i][1],
                            anchoLluvia, altoLluvia);
                    
                    posicionGotas[i][1] += posicionGotas[i][2];
                }
                
                try{
                    Thread.sleep(10);
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