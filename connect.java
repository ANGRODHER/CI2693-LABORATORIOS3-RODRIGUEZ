
public abstract class connect {
  // Clase abstracta

  // Propiedades de la clase
  private String id;
  private double ponderado;

  public connect(String id, double ponderado) {
    // Constructor de la clase abstracta
    this.id = id;
    this.ponderado = ponderado;
  }

  public String getId() {
    // obtener el id
    return this.id;
  }

  public double getPonderado() {
    // obtener el id

    return this.ponderado;
  }

  public abstract String toString();
}