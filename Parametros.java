public class Parametros {
    public boolean quiereAyuda = false;
    public boolean quiereTraza = false;
    public boolean quiereRutas = false;
    public String ficheroEntrada = null;
    public String ficheroSalida = null;

    public Parametros(String[] args) throws IllegalArgumentException {
        if (args.length == 0) {
            quiereAyuda = true;
            return;
        }

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-h":
                    quiereAyuda = true;
                    return;

                case "-t":
                    quiereTraza = true;
                    break;

                case "-r":
                    quiereRutas = true;
                    break;

                default:
                    ficheroEntrada = args[i];
                    i++;
                    if (i < args.length) {
                        ficheroSalida = args[i];
                    }
                    return;
            }
        }
    }

    public static void printAyuda() {
        System.out.println("");
        System.out.println("SINTAXIS: floyd [-h][-t] [fichero entrada] [fichero salida]");
        System.out.println("    -h                  Muestra esta ayuda");
        System.out.println("    -t                  Traza cada llamada recursiva y sus parámetros");
        System.out.println(
                "    -r                  Dibuja en la consola una tabla de rutas indicando para cada par de nodos, cual es el siguiente");
        System.out.println("    [fichero entrada]   Conjunto de nodos del Grafo, en formato \"2 3 1 - 0 9 2 3\"");
        System.out
                .println("    [fichero salida]    Distancia entre cada par de nodos, en formato \"[1, 2]: 3,4,5: 45\"");
        System.out.println("");
        System.out.println("COMBINACIONES POSIBLES:");
        System.out.println("    skyline -h");
        System.out.println("    skyline [fichero entrada]");
        System.out.println("    skyline [fichero entrada] [fichero salida]");
        System.out.println("    skyline -t [fichero entrada]");
        System.out.println("    skyline -t [fichero entrada] [fichero salida]");
        System.out.println("    skyline -r [fichero entrada]");
        System.out.println("    skyline -r [fichero entrada] [fichero salida]");
        System.out.println("    skyline -r -t [fichero entrada]");
        System.out.println("    skyline -r -t [fichero entrada] [fichero salida]");
        System.out.println("");
    }
}