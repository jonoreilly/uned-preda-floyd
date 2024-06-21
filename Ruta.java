import java.util.List;

public class Ruta {
    public final Integer id;
    public List<Direccion> direcciones;

    public Ruta(Nodo nodo) {
        this.id = nodo.id;
        this.direcciones = Conversor.deAristasADirecciones(nodo.aristas);
    }

    public void setDireccion(Ruta destino, Integer distancia, Ruta siguiente) {
        Direccion direccion = new Direccion(distancia, siguiente.id);

        this.direcciones.set(destino.id, direccion);
    }

    public Direccion direccionA(Ruta destino) {
        return this.direcciones.get(destino.id);
    }

    public Integer distanciaA(Ruta destino) {
        Direccion direccion = this.direccionA(destino);

        if (direccion != null) {
            return direccion.distancia;
        }
        return null;
    }
}
