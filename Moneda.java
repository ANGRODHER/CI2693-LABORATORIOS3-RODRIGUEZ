public class Moneda {
      // variables de la clase.
      private String Nombre;
      private Boolean Visitar;
      private int numero;

      // Constructor de la clase.
      public Moneda(String id) {
            this.Nombre = id;
            this.Visitar = false;
            this.numero = 0;

      }

      // obtener id del vertice.
      public String getNombre() {
            return this.Nombre;
      }

      // obtener el estado visitado
      public Boolean getVisitar() {
            return this.Visitar;
      }

      // cambiar el valor booleano al visitado
      public boolean setVisitar(boolean p) {
            this.Visitar = p;
            return this.Visitar;
      }

      // obtener el valor del numero
      public int getNumero() {
            return this.numero;
      }

      // cambiar el valor del numero
      public int setNumero(int p) {
            this.numero = p;
            return this.numero;
      }

      // Devuelve una representación en forma de cadena del vértice.
      public String toString() {
            return getNombre();
      }
}