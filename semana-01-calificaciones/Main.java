/**
 * SISTEMA DE CALIFICACIONES
 * Semana 1 - Curso Java
 * 
 * @author Andre Sebastian Uriona Fernandez
 */

import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
    
    static Scanner scanner = new Scanner(System.in);
    static final int TOTAL_ESTUDIANTES = 5;
    static final int NOTAS_POR_ESTUDIANTE = 3;
    static final int NOTA_MINIMA = 0;
    static final int NOTA_MAXIMA = 100;
    static final int NOTA_APROBACION = 51;
    
    public static void main(String[] args) {
        String[] nombres = new String[TOTAL_ESTUDIANTES];
        int[][] notas = new int[TOTAL_ESTUDIANTES][NOTAS_POR_ESTUDIANTE];
        
        System.out.println("========================================");
        System.out.println("    SISTEMA DE CALIFICACIONES");
        System.out.println("========================================\n");
        
        ingresarDatosEstudiantes(nombres, notas);
        mostrarResultados(nombres, notas);
        
        scanner.close();
    }
    
    public static void ingresarDatosEstudiantes(String[] nombres, int[][] notas) {
        for (int i = 0; i < TOTAL_ESTUDIANTES; i++) {
            System.out.println("\n--- Estudiante " + (i + 1) + " ---");
            
            if (i == 0) {
                scanner.nextLine();
            }
            
            System.out.print("Nombre: ");
            nombres[i] = scanner.nextLine();
            
            for (int j = 0; j < NOTAS_POR_ESTUDIANTE; j++) {
                notas[i][j] = leerNota("Nota " + (j + 1) + ": ");
            }
            
            scanner.nextLine();
        }
    }
    
    public static int leerNota(String mensaje) {
        int nota = 0;
        boolean notaValida = false;
        
        do {
            try {
                System.out.print(mensaje);
                nota = scanner.nextInt();
                
                if (nota >= NOTA_MINIMA && nota <= NOTA_MAXIMA) {
                    notaValida = true;
                } else {
                    System.out.println("ERROR: La nota debe estar entre " + 
                                     NOTA_MINIMA + " y " + NOTA_MAXIMA);
                }
            } catch (InputMismatchException e) {
                System.out.println("ERROR: Debes ingresar un número entero");
                scanner.nextLine();
            }
        } while (!notaValida);
        
        return nota;
    }
    
    public static double calcularPromedio(int[] notas) {
        int suma = 0;
        for (int nota : notas) {
            suma += nota;
        }
        return (double) suma / notas.length;
    }
    
    public static String determinarEstado(double promedio) {
        return (promedio >= NOTA_APROBACION) ? "APROBADO" : "REPROBADO";
    }
    
    public static int encontrarMaximo(int[][] notas) {
        int maximo = notas[0][0];
        for (int i = 0; i < notas.length; i++) {
            for (int j = 0; j < notas[i].length; j++) {
                if (notas[i][j] > maximo) {
                    maximo = notas[i][j];
                }
            }
        }
        return maximo;
    }
    
    public static int encontrarMinimo(int[][] notas) {
        int minimo = notas[0][0];
        for (int i = 0; i < notas.length; i++) {
            for (int j = 0; j < notas[i].length; j++) {
                if (notas[i][j] < minimo) {
                    minimo = notas[i][j];
                }
            }
        }
        return minimo;
    }
    
    public static int contarAprobados(double[] promedios) {
        int contador = 0;
        for (double promedio : promedios) {
            if (promedio >= NOTA_APROBACION) {
                contador++;
            }
        }
        return contador;
    }
    
    public static void mostrarResultados(String[] nombres, int[][] notas) {
        double[] promedios = new double[TOTAL_ESTUDIANTES];
        for (int i = 0; i < TOTAL_ESTUDIANTES; i++) {
            promedios[i] = calcularPromedio(notas[i]);
        }
        
        int notaMaxima = encontrarMaximo(notas);
        int notaMinima = encontrarMinimo(notas);
        int aprobados = contarAprobados(promedios);
        int reprobados = TOTAL_ESTUDIANTES - aprobados;
        
        double sumaGeneral = 0;
        for (double prom : promedios) {
            sumaGeneral += prom;
        }
        double promedioGeneral = sumaGeneral / TOTAL_ESTUDIANTES;
        
        System.out.println("\n\n========================================");
        System.out.println("           RESULTADOS");
        System.out.println("========================================\n");
        
        System.out.printf("%-15s %4s %4s %4s %8s %-10s\n", 
                         "NOMBRE", "N1", "N2", "N3", "PROM", "ESTADO");
        System.out.println("----------------------------------------");
        
        for (int i = 0; i < TOTAL_ESTUDIANTES; i++) {
            System.out.printf("%-15s %4d %4d %4d %8.2f %-10s\n",
                nombres[i],
                notas[i][0],
                notas[i][1],
                notas[i][2],
                promedios[i],
                determinarEstado(promedios[i]));
        }
        
        System.out.println("\n========================================");
        System.out.println("         ESTADÍSTICAS");
        System.out.println("========================================");
        System.out.printf("Promedio general del curso: %.2f\n", promedioGeneral);
        System.out.println("Nota más alta: " + notaMaxima);
        System.out.println("Nota más baja: " + notaMinima);
        System.out.printf("Aprobados: %d de %d (%.1f%%)\n", 
                         aprobados, TOTAL_ESTUDIANTES, 
                         (aprobados * 100.0 / TOTAL_ESTUDIANTES));
        System.out.printf("Reprobados: %d de %d (%.1f%%)\n", 
                         reprobados, TOTAL_ESTUDIANTES,
                         (reprobados * 100.0 / TOTAL_ESTUDIANTES));
    }
}