import java.util.Iterator;

public class Floyd {
    public static Mapa obtenerMapa(Grafo grafo, Parametros parametros, Traza traza) {
        // Registrar llamada
        if (parametros.quiereTraza) {
            traza.registrar("Llamada a obtenerMapa");
        }

        Mapa mapa = new Mapa(grafo);

        // Algoritmo de Floyd
        Iterator<Ruta> itaradorPivote = mapa.rutas.iterator();
        while (itaradorPivote.hasNext()) {
            Ruta pivote = itaradorPivote.next();

            Iterator<Ruta> iteradorInicio = mapa.rutas.iterator();
            while (iteradorInicio.hasNext()) {
                Ruta inicio = iteradorInicio.next();

                Iterator<Ruta> itaradorDestino = mapa.rutas.iterator();
                while (itaradorDestino.hasNext()) {
                    Ruta destino = itaradorDestino.next();

                    // Ignorar la ruta de un nodo a si mismo o pasando por si mismo
                    if (inicio.id == destino.id || inicio.id == pivote.id || destino.id == pivote.id) {
                        continue;
                    }

                    // Registrar llamada
                    if (parametros.quiereTraza) {
                        traza.registrar("Calculando Ruta desde " + inicio.id + " a " + destino.id + " pasando por "
                                + pivote.id);
                    }

                    if (inicio.direccionA(pivote) != null && pivote.direccionA(destino) != null) {
                        Integer distanciaPorB = inicio.distanciaA(pivote) + pivote.distanciaA(destino);

                        if (inicio.direccionA(destino) == null || inicio.distanciaA(destino) > distanciaPorB) {
                            inicio.setDireccion(destino, distanciaPorB, pivote);
                        }
                    }
                }
            }
        }

        return mapa;
    }
}
