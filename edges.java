public class edges extends connect {
  // Variables de Arista.
  private Moneda extremoInicial;
  private Moneda extremoFinal;

  // Crear Arista.
  public edges(String id, double ponderado, Moneda extremoInicial, Moneda extremoFinal) {
    super(id, ponderado);
    this.extremoInicial = extremoInicial;
    this.extremoFinal = extremoFinal;
  }

  // Obtener Extremo1.
  public Moneda getExtremoInicial() {
    return this.extremoInicial;
  }

  // Obtener Extremo2.
  public Moneda getExtremoFinal() {
    return this.extremoFinal;
  }

  // Mostrar la arista.
  @Override
  public String toString() {
    return "Aristas: (" + extremoInicial.getNombre() + " -> " + extremoFinal.getNombre() + ")";
  }
}
