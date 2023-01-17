import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculadora extends JFrame implements ActionListener{

    private Toolkit toolkit;
    private Dimension dimensionPantalla;
    private int anchoPantalla;
    private int altoPantalla;

    /*
    Botones que va a tener la calculadora para las respectivas operaciones
    --------------------------------------------------------------------------------------------------------------------
     */
    private JButton ceroButton = new JButton("0");
    private JButton unoButton = new JButton("1");
    private JButton dosButton = new JButton("2");
    private JButton tresButton = new JButton("3");
    private JButton cuatroButton = new JButton("4");
    private JButton cincoButton = new JButton("5");
    private JButton seisButton = new JButton("6");
    private JButton sieteButton = new JButton("7");
    private JButton ochoButton = new JButton("8");
    private JButton nueveButton = new JButton("9");
    private JButton borrarUnoButton = new JButton("CE");
    private JButton borrarTodoButton = new JButton("C");
    private JButton divisionButton = new JButton("÷");
    private JButton multiplicacionButton = new JButton("x");
    private JButton restaButton = new JButton("-");
    private JButton sumaButton = new JButton("+");
    private JButton igualButton = new JButton("=");
    private JButton puntoButton = new JButton(".");
    /*
    Campo de texto donde se reproduce el tecleo de la operacion y posteriormente su resultado
    --------------------------------------------------------------------------------------------------------------------
     */
    private JTextField pantalla = new JTextField();

    public Calculadora(){
        super("Calculadora simple con Java");

        /*
        Esta seccion de codigo se encarga de tomar los datos de las distintas pantallas donde pueda ser ejecutado el
        programa, y lo adapta a las diferentes resoluciones
        ----------------------------------------------------------------------------------------------------------------
         */
        toolkit = Toolkit.getDefaultToolkit();
        dimensionPantalla = toolkit.getScreenSize();
        anchoPantalla = (int) dimensionPantalla.getWidth(); // WIDTH --> ancho
        altoPantalla = (int) dimensionPantalla.getHeight(); // HEIGHT --> alto

        /*
        Seteo posición, tamaño de la pantalla y de las filas de botones correspondientes a numeros y operaciones
        ----------------------------------------------------------------------------------------------------------------
         */
        pantalla.setBounds(10,10,390,70);

        borrarUnoButton.setBounds(230,90,100,40);
        borrarTodoButton.setBounds(340,90,60,40);

        sieteButton.setBounds(10,140,100,50);
        ochoButton.setBounds(120,140,100,50);
        nueveButton.setBounds(230,140,100,50);
        divisionButton.setBounds(340,140,60,50);

        cuatroButton.setBounds(10,200,100,50);
        cincoButton.setBounds(120,200,100,50);
        seisButton.setBounds(230,200,100,50);
        multiplicacionButton.setBounds(340,200,60,50);

        unoButton.setBounds(10,260,100,50);
        dosButton.setBounds(120,260,100,50);
        tresButton.setBounds(230,260,100,50);
        restaButton.setBounds(340,260,60,50);

        ceroButton.setBounds(10,320,100,50);
        puntoButton.setBounds(120,320,100,50);
        igualButton.setBounds(230,320,100,50);
        sumaButton.setBounds(340,320,60,50);

        this.setBounds((anchoPantalla/3),(altoPantalla/5),420,420);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
        this.setLayout(null);

        this.add(ceroButton);
        this.add(unoButton);
        this.add(dosButton);
        this.add(tresButton);
        this.add(cuatroButton);
        this.add(cincoButton);
        this.add(seisButton);
        this.add(sieteButton);
        this.add(ochoButton);
        this.add(nueveButton);
        this.add(borrarUnoButton);
        this.add(borrarTodoButton);
        this.add(divisionButton);
        this.add(multiplicacionButton);
        this.add(restaButton);
        this.add(sumaButton);
        this.add(igualButton);
        this.add(puntoButton);
        this.add(pantalla);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == borrarUnoButton){
            pantalla.setText("");
        }
    }
}