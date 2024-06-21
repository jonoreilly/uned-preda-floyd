import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Conversor {
    public static Nodo deLineaDelFicheroDeEntradaANodo(Integer id, String linea) {
        String[] valores = linea.split(" ");

        List<Integer> aristas = new ArrayList<Integer>();

        for (Integer i = 0; i < valores.length; i++) {
            // Para si mismo, un nodo no tiene arista
            if (id == i) {
                aristas.add(null);
                continue;
            }

            String valor = valores[i];

            try {
                Integer arista = Integer.parseInt(valor);

                aristas.add(arista);
            } catch (NumberFormatException nfe) {
                aristas.add(null);
            }
        }
        Nodo nodo = new Nodo(id, aristas);

        return nodo;
    }

    public static String deMapaAFicheroDeSalida(Mapa mapa) {
        List<Camino> caminos = mapa.obtenerTodosLosCaminos();
        String resultado = "";

        Iterator<Camino> iteradorCaminos = caminos.iterator();
        while (iteradorCaminos.hasNext()) {
            Camino camino = iteradorCaminos.next();
            String linea = Conversor.deCaminoALineaDeFicheroDeSalida(camino);
            resultado += linea + "\n";
        }

        return resultado;
    }

    public static String deCaminoALineaDeFicheroDeSalida(Camino camino) {
        String linea = "[" + camino.inicio + ", " + camino.destino + "]: ";

        // Si la ruta no es posible
        if (camino.distancia == null) {
            linea += "No hay camino posible";
        } else {
            // Concatenar pasos
            for (Integer i = 0; i < camino.pasos.size(); i++) {
                if (i != 0) {
                    linea += ", ";
                }
                linea += camino.pasos.get(i);
            }

            linea += " : " + camino.distancia;
        }

        return linea;
    }

    public static List<Direccion> deAristasADirecciones(List<Integer> aristas) {
        List<Direccion> direcciones = new ArrayList<Direccion>();

        for (Integer i = 0; i < aristas.size(); i++) {
            Integer distancia = aristas.get(i);

            // Si existe arista anadir a direcciones y si no anadir null
            if (distancia != null) {
                Direccion direccion = new Direccion(distancia, i);

                direcciones.add(direccion);
            } else {
                direcciones.add(null);
            }
        }

        return direcciones;
    }

    public static String deMapaATabla(Mapa mapa) {
        String resultado = "\n\nTabla de rutas indicando para cada par de nodos, cual es el siguiente paso\n";
        resultado += "en formato: fila = inicio, columna = destino\n\n";

        // Numero de columnas
        resultado += "        ";
        for (Integer i = 0; i < mapa.rutas.size(); i++) {
            resultado += String.format("%5d", i) + "  ";
        }
        resultado += "\n";

        // Filas
        Iterator<Ruta> iteradorRutas = mapa.rutas.iterator();
        while (iteradorRutas.hasNext()) {
            Ruta ruta = iteradorRutas.next();

            resultado += String.format("%5d", ruta.id) + " [ ";

            Iterator<Direccion> iteradorDirecciones = ruta.direcciones.iterator();
            while (iteradorDirecciones.hasNext()) {
                Direccion direccion = iteradorDirecciones.next();

                if (direccion == null) {
                    resultado += "  -  , ";
                } else {
                    resultado += String.format("%5d", direccion.siguiente) + ", ";
                }
            }

            resultado += "]\n";
        }

        return resultado;
    }
}
