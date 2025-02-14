package org.example;

import org.example.persistencia.dao.ProductoDAO;

import java.util.Scanner;

public class Main
{
    private static Scanner consola = new Scanner(System.in);
    private static ProductoDAO productoDAO = null;

    public static void main(String[] args)
    {
        menuPrincipal();
    }

    public static void menuPrincipal()
    {
        int opcion;
        boolean continuar = true;


    }

    public static int leerEntero(int desde, int hasta)
    {
        int entero = Integer.MAX_VALUE;
        if (desde > hasta)
        {
            int aux = desde;
            hasta = desde;
            desde = aux;
        }

        do
        {
            try
            {
                if (hasta == Integer.MAX_VALUE)
                {
                    System.out.println("Ingrese un número (mínimo = " + desde + ")");
                }
                else
                {
                    System.out.println("Ingrese un entero entre " + desde + " y " + hasta);
                }
                entero = Integer.valueOf(consola.nextLine().trim());
            } catch (Exception e)
            {
                System.out.println("El valor introducido no es un número");
            }
        } while(entero < desde || entero > hasta);
        return entero;
    }

}