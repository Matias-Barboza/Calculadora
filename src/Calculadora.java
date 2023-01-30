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
    private JLabel nombre = new JLabel("B-MAT LAB");

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
        pantalla.setBounds(10,30,390,50);
        nombre.setBounds(190,5,70,20);

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
        this.setResizable(false);
        this.setLayout(null);

        pantalla.setForeground(new Color(0x000000));
        pantalla.setBackground(new Color(0x808080));
        pantalla.setOpaque(true);
        pantalla.setFont(new Font("Calibri",Font.PLAIN,40));
        nombre.setForeground(new Color(0xffff99));
        nombre.setFont(new Font("Haettenschweiler",Font.PLAIN,20));
        this.getContentPane().setBackground(new Color(0x39424a));

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
        this.add(nombre);

        inicializarAccionesDeBotones();
        inicializarExpresion();
        this.setVisible(true);
    }

    private void inicializarAccionesDeBotones(){

        ansButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*
                Para que el botón de resultado realice alguna acción se tiene que haber hecho alguna operación y que
                esta haya finalizado sin errores
                --------------------------------------------------------------------------------------------------------
                 */
                if(!resultado.equals("")){
                    if(pantalla.getText().equals("SyntaxERROR")){
                        pantalla.setText("");
                    }

                    /*
                    Cuando se presione el botón de "ans", debe haber capacidad en la pantalla, de haberlo se guarda lo
                    que hay en el atributo resultado y a la expresion en el indice actual se le setea ese String como
                    valor
                    ----------------------------------------------------------------------------------------------------
                     */
                    if((cantidadEnPantalla + ansButton.getText().length()) < 19){
                        if(indiceActual % 2 == 0){
                            /*
                            Lo acción para el botón "ans" es reemplazar lo que esté escrito en el Jlabel pantalla y en
                            el arreglo que va guardando las partes de la expresión para hacer el calculo correspondiente
                            --------------------------------------------------------------------------------------------
                            Si el indice actual es 0, se setea el resultado en la pantalla, pero para el caso de no ser
                            0 es que está la variable aBorrar, ya que me permite manipular lo que hay en el Jlabel, de
                            forma que lo que vea el usuario sea el resultado que se guardó anteriormente en la operación
                            actual
                             */
                            int aBorrar = expresion[indiceActual].length();
                            expresion[indiceActual] = resultado;
                            if(indiceActual == 0){
                                pantalla.setText(resultado);
                            }else{
                                String operacion = pantalla.getText();
                                operacion = operacion.substring(0,operacion.length() - aBorrar);
                                pantalla.setText(operacion + resultado);
                            }
                        }
                        cantidadEnPantalla = pantalla.getText().length();
                        System.out.println("La cantidad que hay en pantalla es: " + cantidadEnPantalla);
                    }
                }
            }
        });

        borrarUnoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(pantalla.getText().equals("SyntaxERROR")){
                    pantalla.setText("");
                }

                if(!pantalla.getText().isEmpty()) {
                    String operacion = pantalla.getText();
                    /*
                    Para la operacion de borrar un caracter, primero obtengo el String presente en el Jlabel pantalla y,
                    seteo el texto nuevamente en el label con un caracter menos con el metodo substring()
                    ----------------------------------------------------------------------------------------------------
                    Para la eliminación en la expresión, hay dos condiciones: que el indíce actual sea par, o impar;
                    Que la expresión en ese indice esté vacío = "", o no
                     */
                    if(indiceActual % 2 == 0){
                        /*
                        Si el indice es par, y la expresion en ese indice, no está vacío se sustrae un caracter de ese
                        String
                        ------------------------------------------------------------------------------------------------
                        Si está vacio, y el indice a su vez es mayor a 0, se resta el indice, se setea a la expresión
                        en "", ya que es un operador, y se resta otra vez el indice
                         */
                        if(!expresion[indiceActual].equals("")){
                            expresion[indiceActual] = expresion[indiceActual].substring(0, expresion[indiceActual].length() - 1);
                        }else{
                            if(indiceActual > 0) {
                                indiceActual--;
                                expresion[indiceActual] = "";
                                indiceActual--;
                            }
                        }
                    }else{
                        /*
                        Si el indice no es par, se setea la expresion en "", y se resta el indice para poder seguir
                        modificando la operación
                        ------------------------------------------------------------------------------------------------
                         */
                        expresion[indiceActual] = "";
                        indiceActual--;
                    }
                    operacion = operacion.substring(0,operacion.length() - 1);
                    pantalla.setText(operacion);
                    cantidadEnPantalla--;
                    System.out.println("La cantidad que hay en pantalla es: " + cantidadEnPantalla);
                }
            }
        });

        borrarTodoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*
                Este botón setea la pantalla a vacío, el indice actual y la cantidad en pantalla a 0 y se reinicia la
                expresión, es decir, todos sus lugares en "", para ingresar otra operación
                --------------------------------------------------------------------------------------------------------
                 */
                if(pantalla.getText().equals("SyntaxERROR")){
                    pantalla.setText("");
                }

                pantalla.setText("");
                inicializarExpresion();
                indiceActual = 0;
                cantidadEnPantalla = 0;
            }
        });

        sieteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(pantalla.getText().equals("SyntaxERROR")){
                    pantalla.setText("");
                }

                if(cantidadEnPantalla < 19) {
                    pantalla.setText(pantalla.getText() + "7");
                    expresion[indiceActual] += "7";
                    cantidadEnPantalla++;
                }else{
                    JOptionPane.showMessageDialog(null,"La calculadora admite hasta 19 caracteres" +
                            " en pantalla.","Aviso",JOptionPane.INFORMATION_MESSAGE);
                }
                System.out.println("La cantidad que hay en pantalla es: " + cantidadEnPantalla);
            }
        });

        ochoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(pantalla.getText().equals("SyntaxERROR")){
                    pantalla.setText("");
                }

                if(cantidadEnPantalla < 19) {
                    pantalla.setText(pantalla.getText() + "8");
                    expresion[indiceActual] += "8";
                    cantidadEnPantalla++;
                }else{
                    JOptionPane.showMessageDialog(null,"La calculadora admite hasta 19 caracteres" +
                            " en pantalla.","Aviso",JOptionPane.INFORMATION_MESSAGE);
                }
                System.out.println("La cantidad que hay en pantalla es: " + cantidadEnPantalla);
            }
        });

        nueveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(pantalla.getText().equals("SyntaxERROR")){
                    pantalla.setText("");
                }

                if(cantidadEnPantalla < 19) {
                    pantalla.setText(pantalla.getText() + "9");
                    expresion[indiceActual] += "9";
                    cantidadEnPantalla++;
                }else{
                    JOptionPane.showMessageDialog(null,"La calculadora admite hasta 19 caracteres" +
                            " en pantalla.","Aviso",JOptionPane.INFORMATION_MESSAGE);
                }
                System.out.println("La cantidad que hay en pantalla es: " + cantidadEnPantalla);
            }
        });

        divisionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*
                El obelo no se puede ingresar como primer caracter ni como ultimo, tampoco delante de ningún otro operador
                --------------------------------------------------------------------------------------------------------
                 */
                if(pantalla.getText().equals("SyntaxERROR")){
                    pantalla.setText("");
                }

                if(cantidadEnPantalla < 18) {

                    if (cantidadEnPantalla != 0) {
                        String anterior = pantalla.getText().substring(pantalla.getText().length()-1);

                        if(!anterior.equals("÷") && !anterior.equals("x") && !anterior.equals("+") && !anterior.equals("-")) {
                            pantalla.setText(pantalla.getText() + "÷");
                            indiceActual++;
                            expresion[indiceActual] = "÷";
                            indiceActual++;
                            cantidadEnPantalla++;
                            System.out.println("La cantidad que hay en pantalla es: " + cantidadEnPantalla);
                        }
                    }
                }
            }
        });

        cuatroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(pantalla.getText().equals("SyntaxERROR")){
                    pantalla.setText("");
                }

                if(cantidadEnPantalla < 19) {
                    pantalla.setText(pantalla.getText() + "4");
                    expresion[indiceActual] += "4";
                    cantidadEnPantalla++;
                }else{
                    JOptionPane.showMessageDialog(null,"La calculadora admite hasta 19 caracteres" +
                            " en pantalla.","Aviso",JOptionPane.INFORMATION_MESSAGE);
                }
                System.out.println("La cantidad que hay en pantalla es: " + cantidadEnPantalla);
            }
        });

        cincoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(pantalla.getText().equals("SyntaxERROR")){
                    pantalla.setText("");
                }

                if(cantidadEnPantalla < 19) {
                    pantalla.setText(pantalla.getText() + "5");
                    expresion[indiceActual] += "5";
                    cantidadEnPantalla++;
                }else{
                    JOptionPane.showMessageDialog(null,"La calculadora admite hasta 19 caracteres" +
                            " en pantalla.","Aviso",JOptionPane.INFORMATION_MESSAGE);
                }
                System.out.println("La cantidad que hay en pantalla es: " + cantidadEnPantalla);
            }
        });

        seisButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(pantalla.getText().equals("SyntaxERROR")){
                    pantalla.setText("");
                }

                if(cantidadEnPantalla < 19) {
                    pantalla.setText(pantalla.getText() + "6");
                    expresion[indiceActual] += "6";
                    cantidadEnPantalla++;
                }else{
                    JOptionPane.showMessageDialog(null,"La calculadora admite hasta 19 caracteres" +
                            " en pantalla.","Aviso",JOptionPane.INFORMATION_MESSAGE);
                }
                System.out.println("La cantidad que hay en pantalla es: " + cantidadEnPantalla);
            }
        });

        multiplicacionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*
                La x tiene el mismo funcionamiento que el obelo
                --------------------------------------------------------------------------------------------------------
                 */
                if(pantalla.getText().equals("SyntaxERROR")){
                    pantalla.setText("");
                }

                if(cantidadEnPantalla < 18) {

                    if (cantidadEnPantalla != 0) {
                        String anterior = pantalla.getText().substring(pantalla.getText().length() - 1 );

                        if(!anterior.equals("÷") && !anterior.equals("x") && !anterior.equals("+") && !anterior.equals("-")) {
                            pantalla.setText(pantalla.getText() + "x");
                            indiceActual++;
                            expresion[indiceActual] = "x";
                            indiceActual++;
                            cantidadEnPantalla++;
                            System.out.println("La cantidad que hay en pantalla es: " + cantidadEnPantalla);
                        }
                    }
                }
            }
        });

        unoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(pantalla.getText().equals("SyntaxERROR")){
                    pantalla.setText("");
                }

                if(cantidadEnPantalla < 19) {
                    pantalla.setText(pantalla.getText() + "1");
                    expresion[indiceActual] += "1";
                    cantidadEnPantalla++;
                }else{
                    JOptionPane.showMessageDialog(null,"La calculadora admite hasta 19 caracteres" +
                            " en pantalla.","Aviso",JOptionPane.INFORMATION_MESSAGE);
                }
                System.out.println("La cantidad que hay en pantalla es: " + cantidadEnPantalla);
            }
        });

        dosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(pantalla.getText().equals("SyntaxERROR")){
                    pantalla.setText("");
                }

                if(cantidadEnPantalla < 19) {
                    pantalla.setText(pantalla.getText() + "2");
                    expresion[indiceActual] += "2";
                    cantidadEnPantalla++;
                }else{
                    JOptionPane.showMessageDialog(null,"La calculadora admite hasta 19 caracteres" +
                            " en pantalla.","Aviso",JOptionPane.INFORMATION_MESSAGE);
                }
                System.out.println("La cantidad que hay en pantalla es: " + cantidadEnPantalla);
            }
        });

        tresButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(pantalla.getText().equals("SyntaxERROR")){
                    pantalla.setText("");
                }

                if(cantidadEnPantalla < 19) {
                    pantalla.setText(pantalla.getText() + "3");
                    expresion[indiceActual] += "3";
                    cantidadEnPantalla++;
                }else{
                    JOptionPane.showMessageDialog(null,"La calculadora admite hasta 19 caracteres" +
                            " en pantalla.","Aviso",JOptionPane.INFORMATION_MESSAGE);
                }
                System.out.println("La cantidad que hay en pantalla es: " + cantidadEnPantalla);
            }
        });

        restaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(pantalla.getText().equals("SyntaxERROR")){
                    pantalla.setText("");
                }

                if(cantidadEnPantalla < 18) {

                    if (cantidadEnPantalla != 0) {
                        String anterior;

                        if(cantidadEnPantalla > 1) {
                            anterior = pantalla.getText().substring(pantalla.getText().length() - 1);
                            String anteriorDelAnterior = pantalla.getText().substring(pantalla.getText().length() - 2, pantalla.getText().length() - 1);

                            /*
                            Si el anterior del anterior no es un Operador Aritmetico, es decir, es un numero
                            --------------------------------------------------------------------------------------------
                             */
                            if (!anteriorDelAnterior.equals("÷") && !anteriorDelAnterior.equals("x") && !anteriorDelAnterior.equals("+") && !anteriorDelAnterior.equals("-")) {
                                /*
                                Si el anterior del anterior es un número y el anterior es un operador aritmetico, este,
                                ya incrementó el indice actual, y por ende estamos formando otro numero en la expresión
                                ----------------------------------------------------------------------------------------
                                Si el anterior del anterior es un número y el anterior también, se incrementa el indice
                                actual,y se lo toma como un operando que "no es parte de un número"
                                ----------------------------------------------------------------------------------------
                                 */
                                if(anterior.equals("+") || anterior.equals("-") || anterior.equals("÷") || anterior.equals("x")) {
                                    expresion[indiceActual] += "-";
                                }else {
                                    indiceActual++;
                                    expresion[indiceActual] = "-";
                                    indiceActual++;
                                }
                                pantalla.setText(pantalla.getText() + "-");
                                cantidadEnPantalla++;
                            }else{
                                if(!anterior.equals("+") && !anterior.equals("-") && !anterior.equals("÷") && !anterior.equals("x") && !anterior.equals(".")) {
                                    indiceActual++;
                                    expresion[indiceActual] = "-";
                                    indiceActual++;
                                    pantalla.setText(pantalla.getText() + "-");
                                    cantidadEnPantalla++;
                                }
                            }
                        }else if(cantidadEnPantalla == 1){
                            anterior = pantalla.getText().substring(pantalla.getText().length() - 1);
                            if(!anterior.equals("+") && !anterior.equals("-") && !anterior.equals("÷") && !anterior.equals("x")){
                                indiceActual++;
                                expresion[indiceActual] = "-";
                                indiceActual++;
                                pantalla.setText(pantalla.getText() + "-");
                                cantidadEnPantalla++;
                            }
                        }
                    } else {
                        expresion[indiceActual] += "-";
                        pantalla.setText(pantalla.getText() + "-");
                        cantidadEnPantalla++;
                    }
                    System.out.println("La cantidad que hay en pantalla es: " + cantidadEnPantalla);
                }
            }
        });

        ceroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(pantalla.getText().equals("SyntaxERROR")){
                    pantalla.setText("");
                }

                if(cantidadEnPantalla < 19) {
                    pantalla.setText(pantalla.getText() + "0");
                    expresion[indiceActual] += "0";
                    cantidadEnPantalla++;
                }else{
                    JOptionPane.showMessageDialog(null,"La calculadora admite hasta 19 caracteres" +
                            " en pantalla.","Aviso",JOptionPane.INFORMATION_MESSAGE);
                }
                System.out.println("La cantidad que hay en pantalla es: " + cantidadEnPantalla);
            }
        });

        puntoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(pantalla.getText().equals("SyntaxERROR")){
                    pantalla.setText("");
                }

                if(cantidadEnPantalla < 19) {
                    pantalla.setText(pantalla.getText() + ".");
                    expresion[indiceActual] += ".";
                    cantidadEnPantalla++;
                }else{
                    JOptionPane.showMessageDialog(null,"La calculadora admite hasta 19 caracteres" +
                            " en pantalla.","Aviso",JOptionPane.INFORMATION_MESSAGE);
                }
                System.out.println("La cantidad que hay en pantalla es: " + cantidadEnPantalla);
            }
        });

        igualButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*
                Para cuando se presiona el boton de igual, se comprueba mediante el método existeAlMenosUnaCuenta() que
                no se generen errores (NumberFormatException por el formateo incorrecto de alguna parte de la expresion)
                y que haya al menos dos operandos
                --------------------------------------------------------------------------------------------------------
                 */
                if(existeAlMenosUnaCuenta()) {
                    pantalla.setText("");
                    indiceActual++;
                    imprimirExpresion();

                    realizarCalculo();
                    pantalla.setText(resultado);

                    inicializarExpresion();
                    indiceActual = 0;
                    expresion[indiceActual] = resultado;
                    cantidadEnPantalla = resultado.length();
                }else{
                    pantalla.setText("");
                    pantalla.setText("SyntaxERROR");
                    inicializarExpresion();
                    indiceActual = 0;
                    cantidadEnPantalla = 0;
                }
            }
        });

        sumaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*
                Se controla que no se ponga en primer lugar, ni despues de cualquier operando, ya que es redundante, no
                así para el caso del signo "-"
                --------------------------------------------------------------------------------------------------------
                 */
                if(pantalla.getText().equals("SyntaxERROR")){
                    pantalla.setText("");
                }

                if(cantidadEnPantalla < 18) {

                    if (cantidadEnPantalla != 0) {
                        String anterior = pantalla.getText().substring(pantalla.getText().length()-1);

                        if(!anterior.equals("÷") && !anterior.equals("x") && !anterior.equals("+") && !anterior.equals("-")){
                            indiceActual++;
                            expresion[indiceActual] = "+";
                            indiceActual++;
                            pantalla.setText(pantalla.getText() + "+");
                            cantidadEnPantalla++;
                        }
                        System.out.println("La cantidad que hay en pantalla es: " + cantidadEnPantalla);
                    }
                }
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

    private void realizarCalculo(){

        int divisiones = 0, multiplicaciones = 0, sumas = 0, restas = 0;

        /*
        Determino cuantas operaciones hay de cada una
        ----------------------------------------------------------------------------------------------------------------
         */
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
                    imprimirExpresion();
                    divisiones--;
                }else{
                    i++;
                }
            }
        }

        if(multiplicaciones != 0){
            int i = 0;
            double resul;

            while(multiplicaciones != 0 && i < indiceActual){

                if(expresion[i].equals("x")){
                    resul = Double.parseDouble(expresion[i-1]) * Double.parseDouble(expresion[i+1]);
                    expresion[i-1] = String.valueOf(resul);
                    reIndexarExpresion(i);
                    imprimirExpresion();
                    multiplicaciones--;
                }else{
                    i++;
                }
            }
        }

        if(restas != 0){
            int i = 0;
            double resul;

            while(restas != 0 && i < indiceActual){

                if(expresion[i].equals("-")){
                    resul = Double.parseDouble(expresion[i-1]) - Double.parseDouble(expresion[i+1]);
                    expresion[i-1] = String.valueOf(resul);
                    reIndexarExpresion(i);
                    imprimirExpresion();
                    restas--;
                }else{
                    i++;
                }
            }
        }

        if(sumas != 0){
            int i = 0;
            double resul;

            while(sumas != 0 && i < indiceActual){

                if(expresion[i].equals("+")){
                    resul = Double.parseDouble(expresion[i-1]) + Double.parseDouble(expresion[i+1]);
                    expresion[i-1] = String.valueOf(resul);
                    reIndexarExpresion(i);
                    imprimirExpresion();
                    sumas--;
                }else{
                    i++;
                }
            }
        }

        if(Double.parseDouble(expresion[0]) % 1 == 0.0){
            if(Double.parseDouble(expresion[0]) < 1000000000) {
                int resultInt = (int) Double.parseDouble(expresion[0]);
                resultado = String.valueOf(resultInt);
            }else{
                double d = Double.parseDouble(expresion[0]);
                long resultLong = (long) d;
                resultado = String.valueOf(resultLong);
            }
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

    private boolean existeAlMenosUnaCuenta(){
        int operandos = 0, errores = 0;

        for (int i = 0; i < indiceActual + 1; i++){
            if(!expresion[i].equals("")){
                if(i % 2 == 0){
                    try{
                        String strNum = expresion[i];
                        Double.parseDouble(strNum);
                        operandos++;
                    }catch (NumberFormatException e){
                        System.out.println("El contenido del índice: " + i + " , no es un número");
                        errores++;
                    }
                }
            }
        }
        return operandos > 1 && errores == 0;
    }
}
