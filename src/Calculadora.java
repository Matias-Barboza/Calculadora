import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculadora extends JFrame{

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
    private JButton ansButton = new JButton("Ans");
    private JButton borrarUnoButton = new JButton("CE");
    private JButton borrarTodoButton = new JButton("C");
    private JButton divisionButton = new JButton("÷");
    private JButton multiplicacionButton = new JButton("x");
    private JButton restaButton = new JButton("-");
    private JButton sumaButton = new JButton("+");
    private JButton igualButton = new JButton("=");
    private JButton puntoButton = new JButton(".");
    /*
    Label donde se reproduce el tecleo de la operacion y posteriormente su resultado
    --------------------------------------------------------------------------------------------------------------------
     */
    private JLabel pantalla = new JLabel();

    private String[] expresion = new String[20];
    private int indiceActual = 0;
    private int cantidadEnPantalla = 0;
    private String resultado = "";

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
        pantalla.setFont(new Font("Calibri",Font.PLAIN,40));

        ansButton.setBounds(120,90,100,40);
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

        /*
        Añado los componentes al frame
        ----------------------------------------------------------------------------------------------------------------
         */
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
        this.add(ansButton);
        this.add(borrarUnoButton);
        this.add(borrarTodoButton);
        this.add(divisionButton);
        this.add(multiplicacionButton);
        this.add(restaButton);
        this.add(sumaButton);
        this.add(igualButton);
        this.add(puntoButton);
        this.add(pantalla);

        inicializarAccionesDeBotones();
        inicializarExpresion();
    }

    private void inicializarAccionesDeBotones(){

        ansButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(!resultado.equals("")){
                    if(cantidadEnPantalla < 19){
                        pantalla.setText("");
                        pantalla.setText(pantalla.getText() + resultado);
                        expresion[indiceActual] += resultado;
                        cantidadEnPantalla = pantalla.getText().length();
                        System.out.println(cantidadEnPantalla);
                    }
                }
            }
        });

        borrarUnoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!pantalla.getText().isEmpty()) {
                    /*
                    Para la operacion de borrar un caracter, primero obtengo el String presente en el label pantalla y
                    el caracter que va a ser eliminado, seteo el texto nuevamente en el label con un caracter menos con
                    el metodo substring()
                    ----------------------------------------------------------------------------------------------------
                     */
                    String operacion = pantalla.getText();
                    char borrado = operacion.charAt(operacion.length() - 1);

                    operacion = operacion.substring(0, operacion.length() - 1);
                    pantalla.setText(operacion);

                    if(borradoEsOperacion(borrado)){
                        expresion[indiceActual] = "";
                        indiceActual -= 2;
                    }else{
                        expresion[indiceActual] = expresion[indiceActual].substring(0,expresion[indiceActual].length() - 1);
                    }
                    cantidadEnPantalla--;
                }else{
                    pantalla.setText("");
                }
                System.out.println(cantidadEnPantalla);
            }
        });

        borrarTodoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pantalla.setText("");
                inicializarExpresion();
                indiceActual = 0;
                cantidadEnPantalla = 0;
            }
        });

        sieteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(cantidadEnPantalla < 19) {
                    pantalla.setText(pantalla.getText() + "7");
                    expresion[indiceActual] += "7";
                    cantidadEnPantalla++;
                }else{
                    JOptionPane.showMessageDialog(null,"La calculadora admite hasta 19 caracteres" +
                            " en pantalla.","Aviso",JOptionPane.INFORMATION_MESSAGE);
                }
                System.out.println(cantidadEnPantalla);
            }
        });

        ochoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(cantidadEnPantalla < 19) {
                    pantalla.setText(pantalla.getText() + "8");
                    expresion[indiceActual] += "8";
                    cantidadEnPantalla++;
                }else{
                    JOptionPane.showMessageDialog(null,"La calculadora admite hasta 19 caracteres" +
                            " en pantalla.","Aviso",JOptionPane.INFORMATION_MESSAGE);
                }
                System.out.println(cantidadEnPantalla);
            }
        });

        nueveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(cantidadEnPantalla < 19) {
                    pantalla.setText(pantalla.getText() + "9");
                    expresion[indiceActual] += "9";
                    cantidadEnPantalla++;
                }else{
                    JOptionPane.showMessageDialog(null,"La calculadora admite hasta 19 caracteres" +
                            " en pantalla.","Aviso",JOptionPane.INFORMATION_MESSAGE);
                }
                System.out.println(cantidadEnPantalla);
            }
        });

        divisionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(cantidadEnPantalla != 0) {
                    pantalla.setText(pantalla.getText() + "÷");
                    indiceActual++;
                    expresion[indiceActual] = "÷";
                    indiceActual++;
                    cantidadEnPantalla++;
                    System.out.println(cantidadEnPantalla);
                }
            }
        });

        cuatroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(cantidadEnPantalla < 19) {
                    pantalla.setText(pantalla.getText() + "4");
                    expresion[indiceActual] += "4";
                    cantidadEnPantalla++;
                }else{
                    JOptionPane.showMessageDialog(null,"La calculadora admite hasta 19 caracteres" +
                            " en pantalla.","Aviso",JOptionPane.INFORMATION_MESSAGE);
                }
                System.out.println(cantidadEnPantalla);
            }
        });

        cincoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(cantidadEnPantalla < 19) {
                    pantalla.setText(pantalla.getText() + "5");
                    expresion[indiceActual] += "5";
                    cantidadEnPantalla++;
                }else{
                    JOptionPane.showMessageDialog(null,"La calculadora admite hasta 19 caracteres" +
                            " en pantalla.","Aviso",JOptionPane.INFORMATION_MESSAGE);
                }
                System.out.println(cantidadEnPantalla);
            }
        });

        seisButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(cantidadEnPantalla < 19) {
                    pantalla.setText(pantalla.getText() + "6");
                    expresion[indiceActual] += "6";
                    cantidadEnPantalla++;
                }else{
                    JOptionPane.showMessageDialog(null,"La calculadora admite hasta 19 caracteres" +
                            " en pantalla.","Aviso",JOptionPane.INFORMATION_MESSAGE);
                }
                System.out.println(cantidadEnPantalla);
            }
        });

        multiplicacionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(cantidadEnPantalla != 0) {
                    pantalla.setText(pantalla.getText() + "x");
                    indiceActual++;
                    expresion[indiceActual] = "x";
                    indiceActual++;
                    cantidadEnPantalla++;
                    System.out.println(cantidadEnPantalla);
                }
            }
        });

        unoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(cantidadEnPantalla < 19) {
                    pantalla.setText(pantalla.getText() + "1");
                    expresion[indiceActual] += "1";
                    cantidadEnPantalla++;
                }else{
                    JOptionPane.showMessageDialog(null,"La calculadora admite hasta 19 caracteres" +
                            " en pantalla.","Aviso",JOptionPane.INFORMATION_MESSAGE);
                }
                System.out.println(cantidadEnPantalla);
            }
        });

        dosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(cantidadEnPantalla < 19) {
                    pantalla.setText(pantalla.getText() + "2");
                    expresion[indiceActual] += "2";
                    cantidadEnPantalla++;
                }else{
                    JOptionPane.showMessageDialog(null,"La calculadora admite hasta 19 caracteres" +
                            " en pantalla.","Aviso",JOptionPane.INFORMATION_MESSAGE);
                }
                System.out.println(cantidadEnPantalla);
            }
        });

        tresButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(cantidadEnPantalla < 19) {
                    pantalla.setText(pantalla.getText() + "3");
                    expresion[indiceActual] += "3";
                    cantidadEnPantalla++;
                }else{
                    JOptionPane.showMessageDialog(null,"La calculadora admite hasta 19 caracteres" +
                            " en pantalla.","Aviso",JOptionPane.INFORMATION_MESSAGE);
                }
                System.out.println(cantidadEnPantalla);
            }
        });

        restaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(cantidadEnPantalla != 0){
                    indiceActual++;
                    expresion[indiceActual] = "-";
                    indiceActual++;
                }else{
                    expresion[indiceActual] += "-";
                }
                pantalla.setText(pantalla.getText() + "-");
                cantidadEnPantalla++;
                System.out.println(cantidadEnPantalla);
            }
        });

        ceroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(cantidadEnPantalla < 19) {
                    pantalla.setText(pantalla.getText() + "0");
                    expresion[indiceActual] += "0";
                    cantidadEnPantalla++;
                }else{
                    JOptionPane.showMessageDialog(null,"La calculadora admite hasta 19 caracteres" +
                            " en pantalla.","Aviso",JOptionPane.INFORMATION_MESSAGE);
                }
                System.out.println(cantidadEnPantalla);
            }
        });

        puntoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(cantidadEnPantalla < 19) {
                    pantalla.setText(pantalla.getText() + ".");
                    expresion[indiceActual] += ".";
                    cantidadEnPantalla++;
                }else{
                    JOptionPane.showMessageDialog(null,"La calculadora admite hasta 19 caracteres" +
                            " en pantalla.","Aviso",JOptionPane.INFORMATION_MESSAGE);
                }
                System.out.println(cantidadEnPantalla);
            }
        });

        igualButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pantalla.setText("");
                indiceActual++;
                imprimirExpresion();

                realizarCalculo();
                pantalla.setText(resultado);

                inicializarExpresion();
                indiceActual = 0;
            }
        });

        sumaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(cantidadEnPantalla != 0) {
                    indiceActual++;
                    expresion[indiceActual] = "+";
                    indiceActual++;
                }else{
                    expresion[indiceActual] += "+";
                }
                pantalla.setText(pantalla.getText() + "+");
                cantidadEnPantalla++;
                System.out.println(cantidadEnPantalla);
            }
        });

    }

    private void inicializarExpresion(){
        /*
        Inicializo todas las posiciones del arreglo con "", para que no sean null, y por ende provoque errores
        ----------------------------------------------------------------------------------------------------------------
         */
        for (int i = 0; i < expresion.length; i++) {
            expresion[i] = "";
        }
    }

    private void imprimirExpresion(){
        for (int i = 0; i < indiceActual; i++){
            System.out.print(expresion[i] + "[" + i + "]");
        }
        System.out.println();
    }

    private boolean borradoEsOperacion(char borrado){
        return Character.compare(borrado,'÷') == 0 || Character.compare(borrado,'x') == 0
                || Character.compare(borrado,'+') == 0 || Character.compare(borrado,'-') == 0;
    }

    private void realizarCalculo(){

        int divisiones = 0, multiplicaciones = 0, sumas = 0, restas = 0;

        for(int i = 0; i < indiceActual; i++){

            if(expresion[i].equals("÷")){
                divisiones++;
            }else if(expresion[i].equals("x")){
                multiplicaciones++;
            }else if(expresion[i].equals("+")){
                sumas++;
            }else if(expresion[i].equals("-")){
                restas++;
            }
        }

        if(divisiones != 0){
            int i = 0;
            double resul;

            while(divisiones != 0  && i < indiceActual){

                if(expresion[i].equals("÷")){
                    resul = Double.parseDouble(expresion[i-1]) / Double.parseDouble(expresion[i+1]);
                    expresion[i-1] = String.valueOf(resul);
                    reIndexarExpresion(i);
                    divisiones--;
                }else{
                    i++;
                }
            }

            imprimirExpresion();
            System.out.println(indiceActual);
            System.out.println(i);
        }

        if(multiplicaciones != 0){
            int i = 0;
            double resul;

            while(multiplicaciones != 0 && i < indiceActual){

                if(expresion[i].equals("x")){
                    resul = Double.parseDouble(expresion[i-1]) * Double.parseDouble(expresion[i+1]);
                    expresion[i-1] = String.valueOf(resul);
                    reIndexarExpresion(i);
                    multiplicaciones--;
                }else{
                    i++;
                }
            }

            imprimirExpresion();
            System.out.println(indiceActual);
            System.out.println(i);
        }

        if(restas != 0){
            int i = 0;
            double resul;

            while(restas != 0 && i < indiceActual){

                if(expresion[i].equals("-")){
                    resul = Double.parseDouble(expresion[i-1]) - Double.parseDouble(expresion[i+1]);
                    expresion[i-1] = String.valueOf(resul);
                    reIndexarExpresion(i);
                    restas--;
                }else{
                    i++;
                }
            }

            imprimirExpresion();
            System.out.println(indiceActual);
            System.out.println(i);
        }

        if(sumas != 0){
            int i = 0;
            double resul;

            while(sumas != 0 && i < indiceActual){

                if(expresion[i].equals("+")){
                    resul = Double.parseDouble(expresion[i-1]) + Double.parseDouble(expresion[i+1]);
                    expresion[i-1] = String.valueOf(resul);
                    reIndexarExpresion(i);
                    sumas--;
                }else{
                    i++;
                }
            }

            imprimirExpresion();
            System.out.println(indiceActual);
            System.out.println(i);
        }

        if(Double.parseDouble(expresion[0]) % 1 == 0.0){
            int result = (int) Double.parseDouble(expresion[0]);
            resultado = String.valueOf(result);
        }else {
            resultado = expresion[0];
        }
    }

    private void reIndexarExpresion(int indiceInicio){

        for(int i = indiceInicio+2; i < indiceActual; i++){
            expresion[i-2] = expresion[i];
        }

        indiceActual -= 2;
    }
}
