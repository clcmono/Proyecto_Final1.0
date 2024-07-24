package Modelo;

public class Celda {
    int row; // Fila de la celda
    int col; // Columna de la celda

    // Constructor que inicializa una celda con la fila y columna especificadas
    public Celda(int row, int col) {
        this.row = row;
        this.col = col;
    }

    // Método hashCode para generar un código hash único para cada instancia de Cell
    @Override
    public int hashCode() {
        final int prime = 31; // Constante arbitraria para generar el código hash
        int result = 1; // Valor inicial del resultado
        result = prime * result + row; // Incorporamos la fila al código hash
        result = prime * result + col; // Incorporamos la columna al código hash
        return result; // Devolvemos el código hash generado
    }

    // Método equals para comparar si dos instancias de Cell son iguales
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true; // Verificamos si ambos objetos son la misma instancia
        if (obj == null)
            return false; // Verificamos si el objeto comparado es nulo
        if (getClass() != obj.getClass())
            return false; // Verificamos si el objeto comparado es de la misma clase
        Celda other = (Celda) obj; // Convertimos el objeto comparado a la clase Cell
        if (row != other.row)
            return false; // Comparamos las filas
        if (col != other.col)
            return false; // Comparamos las columnas
        return true; // Si todas las comparaciones son iguales, los objetos son iguales
    }
    @Override
    public String toString() {
        return "(" + row + "," + col + ")";
    }  
}

