public class Main {
    public static void main(String[] args) throws Exception {
        Parametros parametros = new Parametros(args);

        if (parametros.quiereAyuda) {
            Parametros.printAyuda();
            return;
        }

        Grafo grafo = Disco.leerGrafo(parametros.ficheroEntrada);

        Traza traza = new Traza();

        Mapa mapa = Floyd.obtenerMapa(grafo, parametros, traza);

        traza.terminar();

        if (parametros.quiereRutas) {
            System.out.println(Conversor.deMapaATabla(mapa));
        }

        if (parametros.ficheroSalida == null) {
            System.out.println(Conversor.deMapaAFicheroDeSalida(mapa));
        } else {
            Disco.guardarMapa(mapa, parametros.ficheroSalida);
        }
    }
}
