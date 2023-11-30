public class Arbitraje {
    public static void main(String[] args) {
        DGrafo grafo = new DGrafo();
        String archivo = "tasas.txt";
        grafo.cargarGrafo(archivo);
        grafo.print();
    }
}
