import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.nio.charset.StandardCharsets;

public class DGrafo implements Graph<Moneda> {
    // Declaramos las variables, arreglos y tablas que vayamos a utilizar
    private HashMap<String, Moneda> vertices;
    private ArrayList<edges> connect;
    public static final int inf = Integer.MAX_VALUE;
    private List<Moneda> camino = new ArrayList<>();

    public DGrafo() {
        // Constructor de la clase.
        vertices = new HashMap<>();
        connect = new ArrayList<>();
    }

    // Método para agregar un vértice al grafo
    public boolean add(Moneda vertex) {

        if (!vertices.containsKey(vertex.getNombre())) {
            // Agregar el vértice al hashtable
            vertices.put(vertex.getNombre(), vertex);
            // Vértice agregado exitosamente
            return true;
        }
        // El vértice ya existe en el grafo
        return false;
    }

    // Método para verificar si un vértice existe en el grafo dado su identificador
    public boolean contains(Moneda vertex) {
        if (vertices.containsKey(vertex.getNombre())) {
            // El vértice existe en el grafo
            return true;
        }
        // El vértice no existe en el grafo
        return false;
    }

    // Método para verificar si existe un lado entre dos vértices
    public boolean containsconnect(Moneda from, Moneda to) {
        // Iterar sobre todos los lados
        for (edges a : connect) {
            // Verificar si los vértices extremos coinciden en cualquier dirección
            if (a.getExtremoInicial().equals(from) && a.getExtremoFinal().equals(to)) {
                // Existe un lado entre los vértices
                return true;
            }
        }
        // No existe un lado entre los vértices
        return false;
    }

    public boolean connect(Moneda from, Moneda to, double value) {
        // Esta función establece la relación entre dos vértices si existe una conexión
        // entre ellos.
        if (!containsconnect(from, to)) {
            edges arista = new edges("" + from.getNombre() + to.getNombre() + "", value, from, to);
            // System.out.println(arista);
            connect.add(arista);
            return true;
        }
        return false;
    }

    public boolean disconnect(Moneda from, Moneda to) {
        // Esta función elimina la relación entre dos vértices si existe una conexión
        // entre ellos.
        if (contains(from) && contains(to) && containsconnect(from, to)) {
            for (edges a : connect) {
                if (a.getExtremoInicial().equals(from) && a.getExtremoFinal().equals(to)) {
                    connect.remove(a);
                    return true;
                }
            }
        }
        return false;
    }

    public List<Moneda> getInwardEdges(Moneda to) {
        // Esta función devuelve una lista de vértices predecesores que tienen una
        // conexión con el vértice dado.
        List<Moneda> inwardEdges = new ArrayList<>();
        for (edges a : connect) {
            if (a.getExtremoFinal().getNombre().equals(to.getNombre())) {
                inwardEdges.add(a.getExtremoInicial());
            }
        }
        return inwardEdges;
    }

    public List<Moneda> getOutwardEdges(Moneda from) {
        // Esta función devuelve una lista de vértices sucesores que tienen una conexión
        // con el vértice dado.
        List<Moneda> outwardEdges = new ArrayList<>();
        for (edges a : connect) {
            if (a.getExtremoInicial().getNombre().equals(from.getNombre())) {
                outwardEdges.add(a.getExtremoFinal());
            }
        }
        return outwardEdges;
    }

    public List<Moneda> getVerticesConnectedTo(Moneda vertex) {
        // Esta función devuelve una lista de vértices que tienen una conexión con el
        // vértice dado.
        List<Moneda> verticesConnectedTo = new ArrayList<>();
        for (edges a : connect) {

            if (a.getExtremoInicial().getNombre().equals(vertex.getNombre())) {

                verticesConnectedTo.add(a.getExtremoFinal());
            }
        }
        return verticesConnectedTo;
    }

    public List<Moneda> getAllVertices() {
        // Esta función devuelve una lista de todos los vértices del grafo.
        List<Moneda> allVertices = new ArrayList<>();
        for (String key : vertices.keySet()) {
            allVertices.add(vertices.get(key));
        }
        return allVertices;
    }

    public boolean remove(Moneda vertex) {
        // Esta función elimina un vértice del grafo.
        if (contains(vertex)) {
            vertices.remove(vertex.getNombre());
            return true;
        }
        return false;
    }

    public int size() {
        // Esta función devuelve el número de vértices en el grafo.
        return vertices.size();
    }

    @Override
    public String toString() {
        // Este método devuelve una representación en forma de cadena del grafo.
        StringBuilder sb = new StringBuilder();
        // Iterar sobre todos los vertices en el grafo
        for (Moneda a : vertices.values()) {
            sb.append("Nombre:").append(a.getNombre()).append("\n");
        }
        // Iterar sobre todas las aristas en el grafo
        for (edges l : connect) {
            sb.append(l).append("\n");
        }
        // Devolver la representación en forma de cadena del grafo
        return sb.toString();
    }

    public boolean cargarGrafo(String archivo) {
        // Esta función cargará los datos de un .txt
        try (BufferedReader lista = new BufferedReader(new FileReader(archivo, StandardCharsets.UTF_8))) {
            String linea;
            while ((linea = lista.readLine()) != null) {
                // mientras no exista linea vacia, leemos
                String[] datos = linea.split(" ");
                // se cargaran las lineas en el arreglo "datos"
                String moneda1 = datos[0];
                String moneda2 = datos[1];
                String transaccion = datos[2];
                // moneda 1 y 2 representa los pares a trabajar y transacción es el cambio
                // posible entre ellos
                double number = Double.parseDouble(transaccion);
                // instanciamos a y b para convertirlos en nodos
                Moneda a = new Moneda(moneda1);
                Moneda b = new Moneda(moneda2);
                // añadimos los nodos a Monedas y a la hastable
                add(a);
                add(b);
                // hacemos la coneccion del primero con el segundo y le asignamos el arco
                // ponderado
                connect(a, b, number);
            }

            return true;
        } catch (IOException | NumberFormatException e) {
            return false;
        }
    }

    public void DFS(Moneda start) {

        // implemetaremo una busqueda en profundidad para ver si existe al menos 1
        // componente fuertemente conexa en el grafo
        if (start.getVisitar() == false) {
            // si el vertice inicial es falso, cambialo a verdadero y suma 1 a su valor
            // asignado
            start.setVisitar(true);
            start.setNumero(start.getNumero() + 1);
        }
        for (Moneda child : getOutwardEdges(start)) {
            // iteramos sobre los hijos del vertice
            if (camino.isEmpty()) {
                // mientras la lista este vacia quiere decir que no ha encontrado ciclo
                if (!child.getVisitar()) {
                    // si el hijo no ha sido visitado, entonces llama a DFS recursivamente
                    DFS(child);
                    if (!camino.isEmpty()) {
                        // despues de que termina la llamada recursiva y encontro un ciclo entonces
                        // añade el vertice actual a la lista
                        camino.add(start);
                    }
                } else if (child.getVisitar()) {
                    // si el hijo ya fue visitado, se agrega al camino el vertice actual
                    child.setNumero(child.getNumero() + 1);
                    // Aumenta su valor en 1
                    camino.add(start);
                }
            }

        }
        if (getOutwardEdges(start).isEmpty()) {
            // vericamos que el vertice actual tenga hijo, en caso contrario lo desmarcamos
            // como visitado y si valor se reanuda en 0
            start.setVisitar(false);
            start.setNumero(0);
        }
        if (camino.isEmpty()) {
            // si sale del for y aun no encuentra un camino, resetea el vertice actual a
            // false y 0
            start.setVisitar(false);
            start.setNumero(0);
        }
    }

    // end
    public void print() {
        for (Moneda r : vertices.values()) {
            if (!camino.isEmpty()) {
                // si encuentra un camino cierra el for e imprime resultado
                System.out.print("DINERO FACIL DESDE TU CASA");
                break;
            }
            // Mientras no se encuere camino. creamos una nueva instancia de r
            Moneda nuevaInstancia = new Moneda(r.getNombre()); // Crear una nueva instancia de Moneda
            nuevaInstancia.setVisitar(r.getVisitar()); // Copiar el estado de visitar
            nuevaInstancia.setNumero(r.getNumero()); // Copiar el número
            // llamamos a DFS
            DFS(nuevaInstancia);

        }
        // si el for cierra y el camino sigue vacio, imprime
        if (camino.isEmpty()) {
            System.out.print("TODO GUAY DEL PARAGUAY");
        }
    }
}