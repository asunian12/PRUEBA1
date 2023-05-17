public class Main {

    public static void main(String[] args) throws InterruptedException {
        int errores = 6;
        String palabra;
        char letra;
        char[] letrasUsadas = {};

        bienvenida();
        palabra = escogerPalabra();
        char[] mascara = mascaraf(palabra);
        enseñaMascara(mascara);
        err(errores);
        boolean comprobar = introduceLetra(mascara, palabra);
        adios();
    }


    //---------------------------------------------------------------------------------------------
    private static void bienvenida() throws InterruptedException {
        System.out.println("Bienvenido a el penjat");
        Thread.sleep(700);
        System.out.println();
    }
    private static String escogerPalabra() {
        String palabra;
        int numero = (int) (Math.random() * 30);
        String[] palabras = {"perro", "gato", "bicicleta", "cafe", "computadora", "libro", "montaña",
                "playa", "jardin", "celular", "avion", "ciudad", "nube", "pasto", "arbol", "lapiz",
                "coche", "futbol", "hamburguesa", "refresco", "cancion", "reloj", "silla", "ropa",
                "zapatos", "aventura", "felicidad", "amor", "familia", "amistad"};
        palabra = palabras[numero];
        return palabra;
    }
    private static char[] mascaraf(String palabra) {
        int numLet = palabra.length();
        char[] mascara = new char[numLet];
        for (int i = 0; i < mascara.length; i++) {
            mascara[i] = '_';
        }
        return mascara;
    }
    private static void enseñaMascara(char[] mascara) {
        System.out.println(mascara);
    }
    private static void err(int errores) {
        System.out.println("Te quedan " + errores + " intentos");
    }
    private static boolean introduceLetra(char[] mascara, String palabra) {
        boolean pierde = false, gana = false, comprobarLetra,comprobar, salir = false;
        char[] letrasUsadas = new char[palabra.length()];
        int errores = 0;
        boolean letraUsada = false;
        char letra;

        do {
            letra = pedirLetra();
            comprobar = comprobarLetra(palabra, letra, mascara, letrasUsadas);
            errores = letraIncorrecta(comprobar, errores);
            pierde = checkVidas(errores);
            gana = checkGana(mascara);
            salir = checkGanaJuego(pierde, gana);

        } while (!salir);
        enseñaMascara(mascara);
        return salir;
    }

    private static char pedirLetra() {
        char letra;
        System.out.println("Pon una letra: ");
        letra = Teclat.llegirChar();
        return letra;
    }
    private static boolean comprobarLetra(String palabra, char letra, char[] mascara, char[] letrasUsadas) {
        boolean acierto = false;
        for (int i = 0; i < palabra.length(); i++) {
            if (palabra.charAt(i) == letra) {
                mascara[i] = letra;
                acierto = true;
            }
        }
        if (!acierto) {
            System.out.println("Letra incorrecta, intenta otra vez.");
            if (!letraUsada(letra, letrasUsadas)) {
                letrasUsadas[letrasUsadas.length - 1] = letra;
            }
        } else {
            if (!letraUsada(letra, letrasUsadas)) {
                letrasUsadas[letrasUsadas.length - 1] = letra;
            }
        }
        enseñaMascara(mascara);
        return acierto;
    }

    private static boolean letraUsada(char letra, char[] letrasUsadas) {
        for (int i = 0; i < letrasUsadas.length; i++) {
            if (letrasUsadas[i] == letra) {
                System.out.println("Letra ya usada, intenta otra vez.");
                return true;
            }
        }
        return false;
    }



    private static int letraIncorrecta(boolean comprobar, int errores) {

        if (!comprobar) {
            errores++;
            System.out.println("Llevas " + errores + " errores");
        }
        return errores;
    }
    private static boolean checkVidas(int errores) {
        boolean finalizar = false;
        final int VIDAS = 6;

        if (errores >= VIDAS) {
            finalizar = true;
            System.out.println("Has perdido!!");
        }
        return finalizar;
    }
    private static boolean checkGana(char[] mascara) {

        boolean finalizar = false;
        if (!new String(mascara).contains("_")) {
            finalizar = true;
            System.out.println("Has ganado!");
        }
        return finalizar;
    }
    private static Boolean checkGanaJuego(boolean muerto, boolean gana) {
        if (muerto || gana)
            return true;
        else return false;
    }
    private static void adios() {
        System.out.println("ADIOS!");
    }
}


