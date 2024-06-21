import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Mapa {
    public List<Ruta> rutas;

    public Mapa(Grafo grafo) {
        Iterator<Nodo> iteradorNodos = grafo.nodos.iterator();
        List<Ruta> rutas = new ArrayList<Ruta>();

        while (iteradorNodos.hasNext()) {
            Nodo nodo = iteradorNodos.next();
            Ruta ruta = new Ruta(nodo);

            rutas.add(ruta);
        }

        this.rutas = rutas;
    }

    public Camino caminoEntre(Ruta inicio, Ruta destino) {
        List<Integer> pasos = new ArrayList<Integer>();
        Integer distancia = null;

        Direccion direccionInicial = inicio.direccionA(destino);

        if (direccionInicial != null) {
            distancia = direccionInicial.distancia;

            // Reconstruir pasos
            Integer siguiente = direccionInicial.siguiente;
            while (siguiente != destino.id) {
                pasos.add(siguiente);

                Direccion direccionSiguiente = this.rutas.get(siguiente).direccionA(destino);

                siguiente = direccionSiguiente.siguiente;
            }
        }

        Camino camino = new Camino(inicio, destino, pasos, distancia);

        return camino;
    }

    public List<Camino> obtenerTodosLosCaminos() {
        List<Camino> caminos = new ArrayList<Camino>();

        // Para cada nodo/ruta obtener sus caminos a todos los demas
        Iterator<Ruta> iteradorInicio = this.rutas.iterator();
        while (iteradorInicio.hasNext()) {
            Ruta inicio = iteradorInicio.next();

            Iterator<Ruta> iteradorDestino = this.rutas.iterator();
            while (iteradorDestino.hasNext()) {
                Ruta destino = iteradorDestino.next();

                // Ignorar camino de una ruta a si misma
                if (inicio.id == destino.id) {
                    continue;
                }

                Camino camino = this.caminoEntre(inicio, destino);

                caminos.add(camino);
            }
        }

        return caminos;
    }
}
