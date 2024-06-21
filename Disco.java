import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Disco {
    public static Grafo leerGrafo(String fichero) throws Exception {
        try (BufferedReader ficheroEntrada = new BufferedReader(
                new InputStreamReader(new FileInputStream(fichero), "utf-8"))) {

            Iterator<String> iteradorLineas = ficheroEntrada.lines().iterator();
            List<Nodo> nodos = new ArrayList<Nodo>();

            Integer id = 0;
            while (iteradorLineas.hasNext()) {
                String linea = iteradorLineas.next();
                Nodo nodo = Conversor.deLineaDelFicheroDeEntradaANodo(id, linea);
                nodos.add(nodo);
                id++;
            }

            Grafo grafo = new Grafo(nodos);

            return grafo;
        }
    }

    public static void guardarMapa(Mapa mapa, String fichero) throws Exception {
        try (BufferedWriter ficheroSalida = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(fichero), "utf-8"))) {
            ficheroSalida.write(Conversor.deMapaAFicheroDeSalida(mapa));
        }
    }

    public static void guardarTabla(Mapa mapa, String fichero) throws Exception {
        try (BufferedWriter ficheroSalida = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(fichero + "_tabla"), "utf-8"))) {
            ficheroSalida.write(Conversor.deMapaATabla(mapa));
        }
    }
}
