import java.util.List;

public class Camino {
    public Integer inicio;
    public Integer destino;
    public List<Integer> pasos;
    public Integer distancia;

    public Camino(Ruta inicio, Ruta destino, List<Integer> pasos, Integer distancia) {
        this.inicio = inicio.id;
        this.destino = destino.id;
        this.pasos = pasos;
        this.distancia = distancia;
    }
}
