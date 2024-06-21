import java.util.List;

public class Nodo {
    public final Integer id;
    public List<Integer> aristas;

    public Nodo(Integer id, List<Integer> aristas) {
        this.id = id;
        this.aristas = aristas;
    }
}
