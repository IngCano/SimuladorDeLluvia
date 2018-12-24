
package principal;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class Ventana extends JFrame{

    private Panel panel;
    
    public Ventana(){
        
        this.setSize(800, 600);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setVisible(true);
        
        panel = new Panel(800, 600);
        this.getContentPane().add(panel);
        //panel.setDoubleBuffered(false);
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
        //posicionGotas[][3] = profundidad
        
        
        public Panel(int anchoV, int altoV){
            anchoVentana = anchoV;
            altoVentana = altoV;
            numeroDeGotas = 300;
        }
        
        public Panel(){
            anchoVentana = 800;
            altoVentana = 600;
            numeroDeGotas = 100;
        }
        
        public void generarLluvia(){
            posicionGotas = new int[numeroDeGotas][4];
            
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
            for(int i = 0; i < numeroDeGotas; i++){
                switch(posicionGotas[i][2]){
                    case 2:
                        posicionGotas[i][3] = (int)(Math.random() * 15) + 1;
                        break;
                    case 3:
                        posicionGotas[i][3] = (int)(Math.random() * 30) + 1;
                        break;
                    case 4:
                        posicionGotas[i][3] = (int)(Math.random() * 50) + 1;
                        break;
                    default:
                        posicionGotas[i][3] = (int)(Math.random() * 80) + 1;
                        break;
                }
            }
        }
        
        public void generarSalpicadura(Graphics g, int x, int y){
            g.drawLine(x, y, x+taS, y-3);
            g.drawLine(x, y, x-taS, y-3);
        }
        
        public void dibujarLluvia(){
            BufferedImage bi = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);
            Graphics g;
            Color c;
            int contador;
            while(true){
                contador = 0;
                g = bi.getGraphics();
                g.setColor(this.getBackground());
                g.fillRect(0, 0, anchoVentana, altoVentana);
                while(contador < 2){

                    g = bi.getGraphics();
                    g.setColor(Color.GREEN);
                    g.drawLine(0, altoVentana-100, anchoVentana, altoVentana-100);

                    for(int i = 0; i < numeroDeGotas; i++){
                        if(posicionGotas[i][1] >= altoVentana - (100 + altoLluvia - posicionGotas[i][3])){

                            generarSalpicadura(g, posicionGotas[i][0],
                                    posicionGotas[i][1]+altoLluvia);
                            posicionGotas[i][1] = -altoLluvia;
                        }

                        /*g.drawLine(posicionGotas[i][0], posicionGotas[i][1],
                        posicionGotas[i][0] + anchoLluvia,
                        posicionGotas[i][1] + altoLluvia);*/

                        switch (posicionGotas[i][2]) {
                            case 2:
                                c = new Color(67, 120, 225);
                                break;
                            case 3:
                                c = new Color(60, 89, 182);
                                break;
                            case 4:
                                c = new Color(50, 77, 158);
                                break;
                            default:
                                c = new Color(45, 63, 144);
                                break;
                        }


                        g.setColor(c);

                        g.fillRect(posicionGotas[i][0], posicionGotas[i][1],
                                anchoLluvia, altoLluvia);

                        //posicionGotas[i][1] += posicionGotas[i][2];
                    }

                    try{
                        Thread.sleep(5);
                    } catch(Exception ex){}

                    g = this.getGraphics();
                    g.drawImage(bi, 0, 0, panel);
                    
                    contador++;
                }
                
                for(int i = 0; i < numeroDeGotas; i++){
                    posicionGotas[i][1] += posicionGotas[i][2];
                }
                
                //this.repaint();
            }
            
        }
        
        /*@Override
        protected void paintComponent(Graphics g){
            //g.setColor(this.getBackground());
            //g.fillRect(0, 0, anchoVentana, altoVentana);
        }*/
    }
    
}