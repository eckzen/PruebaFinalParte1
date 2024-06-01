package com.mycompany.vehiculo;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class Vehiculos_BBDD {

    private static VehiculoDAO vehiculoDAO = new VehiculoDAO();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Insertar algunos datos de prueba
        insertarDatosDePrueba();

        // Pruebas para cada método
        probarConsultarTodosLosVehiculos();
        probarConsultarVehiculoPorId(1); // Prueba con ID 1
        probarActualizarDatosDeVehiculo(1); // Prueba actualización con ID 1
        probarEliminarVehiculoPorId(2); // Prueba eliminación con ID 2
        probarObtenerPotenciaMediaPorTipo("coche");
        probarObtenerPotenciaMaximaPorTipo("coche");

        // Bucle de interacción con el usuario
        while (true) {
            System.out.println("\nOpciones:");
            System.out.println("1. Consultar todos los vehículos");
            System.out.println("2. Consultar vehículo por ID");
            System.out.println("3. Insertar un nuevo vehículo");
            System.out.println("4. Eliminar vehículo por ID");
            System.out.println("5. Actualizar datos de un vehículo");
            System.out.println("6. Obtener potencia media por tipo de vehículo");
            System.out.println("7. Obtener potencia máxima por tipo de vehículo");
            System.out.println("8. Salir");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // salto de línea

            switch (opcion) {
                case 1:
                    consultarTodosLosVehiculos();
                    break;
                case 2:
                    consultarVehiculoPorId();
                    break;
                case 3:
                    insertarNuevoVehiculo();
                    break;
                case 4:
                    eliminarVehiculoPorId();
                    break;
                case 5:
                    actualizarDatosDeVehiculo();
                    break;
                case 6:
                    obtenerPotenciaMediaPorTipo();
                    break;
                case 7:
                    obtenerPotenciaMaximaPorTipo();
                    break;
                case 8:
                    System.out.println("Saliendo...");
                    System.exit(0);
                default:
                    System.out.println("Opción no válida");
            }
        }
    }

    private static void insertarDatosDePrueba() {
        System.out.println("Insertando datos de prueba...");

        Vehiculo vehiculo1 = new Vehiculo(0, "coche", "Toyota", 120, Date.valueOf("2020-01-01"));
        Vehiculo vehiculo2 = new Vehiculo(0, "moto", "Honda", 80, Date.valueOf("2021-06-15"));
        Vehiculo vehiculo3 = new Vehiculo(0, "coche", "Ford", 150, Date.valueOf("2019-07-20"));

        vehiculoDAO.insertarVehiculo(vehiculo1);
        vehiculoDAO.insertarVehiculo(vehiculo2);
        vehiculoDAO.insertarVehiculo(vehiculo3);

        System.out.println("Datos de prueba insertados.\n");
    }

    private static void probarConsultarTodosLosVehiculos() {
        System.out.println("Probando consultar todos los vehículos...");
        consultarTodosLosVehiculos();
        System.out.println();
    }

    private static void probarConsultarVehiculoPorId(int id) {
        System.out.println("Probando consultar vehículo por ID...");
        Vehiculo vehiculo = vehiculoDAO.obtenerVehiculoPorId(id);
        if (vehiculo != null) {
            System.out.println(vehiculo);
        } else {
            System.out.println("Vehículo no encontrado");
        }
        System.out.println();
    }

    private static void probarActualizarDatosDeVehiculo(int id) {
        System.out.println("Probando actualizar datos de vehículo...");
        Vehiculo vehiculo = new Vehiculo(id, "coche", "Tesla", 200, Date.valueOf("2022-01-01"));
        vehiculoDAO.actualizarVehiculo(vehiculo);
        System.out.println("Vehículo actualizado.");
        probarConsultarVehiculoPorId(id);
    }

    private static void probarEliminarVehiculoPorId(int id) {
        System.out.println("Probando eliminar vehículo por ID...");
        vehiculoDAO.eliminarVehiculoPorId(id);
        System.out.println("Vehículo eliminado.");
        probarConsultarVehiculoPorId(id);
    }

    private static void probarObtenerPotenciaMediaPorTipo(String tipo) {
        System.out.println("Probando obtener potencia media por tipo de vehículo...");
        double potenciaMedia = vehiculoDAO.obtenerPotenciaMediaPorTipo(tipo);
        System.out.println("La potencia media para el tipo " + tipo + " es: " + potenciaMedia);
        System.out.println();
    }

    private static void probarObtenerPotenciaMaximaPorTipo(String tipo) {
        System.out.println("Probando obtener potencia máxima por tipo de vehículo...");
        int potenciaMaxima = vehiculoDAO.obtenerPotenciaMaximaPorTipo(tipo);
        System.out.println("La potencia máxima para el tipo " + tipo + " es: " + potenciaMaxima);
        System.out.println();
    }

    private static void consultarTodosLosVehiculos() {
        List<Vehiculo> vehiculos = vehiculoDAO.obtenerTodosLosVehiculos();
        for (Vehiculo vehiculo : vehiculos) {
            System.out.println(vehiculo);
        }
    }

    private static void consultarVehiculoPorId() {
        System.out.print("Ingrese el ID del vehículo: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // salto de línea
        Vehiculo vehiculo = vehiculoDAO.obtenerVehiculoPorId(id);
        if (vehiculo != null) {
            System.out.println(vehiculo);
        } else {
            System.out.println("Vehículo no encontrado");
        }
    }

    private static void insertarNuevoVehiculo() {
        System.out.print("Ingrese el tipo de vehículo: ");
        String tipo = scanner.nextLine();
        System.out.print("Ingrese la marca del vehículo: ");
        String marca = scanner.nextLine();
        System.out.print("Ingrese la potencia del vehículo: ");
        int potencia = scanner.nextInt();
        System.out.print("Ingrese la fecha de compra (yyyy-mm-dd): ");
        String fechaCompraStr = scanner.next();
        Date fechaCompra = Date.valueOf(fechaCompraStr);

        Vehiculo vehiculo = new Vehiculo(0, tipo, marca, potencia, fechaCompra);
        vehiculoDAO.insertarVehiculo(vehiculo);
        System.out.println("Vehículo insertado con éxito");
    }

    private static void eliminarVehiculoPorId() {
        System.out.print("Ingrese el ID del vehículo a eliminar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // salto de línea
        vehiculoDAO.eliminarVehiculoPorId(id);
        System.out.println("Vehículo eliminado con éxito");
    }

    private static void actualizarDatosDeVehiculo() {
        System.out.print("Ingrese el ID del vehículo a actualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // salto de línea

        System.out.print("Ingrese el nuevo tipo de vehículo: ");
        String tipo = scanner.nextLine();
        System.out.print("Ingrese la nueva marca del vehículo: ");
        String marca = scanner.nextLine();
        System.out.print("Ingrese la nueva potencia del vehículo: ");
        int potencia = scanner.nextInt();
        System.out.print("Ingrese la nueva fecha de compra (yyyy-mm-dd): ");
        String fechaCompraStr = scanner.next();
        Date fechaCompra = Date.valueOf(fechaCompraStr);

        Vehiculo vehiculo = new Vehiculo(id, tipo, marca, potencia, fechaCompra);
        vehiculoDAO.actualizarVehiculo(vehiculo);
        System.out.println("Vehículo actualizado con éxito");
    }

    private static void obtenerPotenciaMediaPorTipo() {
        System.out.print("Ingrese el tipo de vehículo: ");
        String tipo = scanner.nextLine();
        double potenciaMedia = vehiculoDAO.obtenerPotenciaMediaPorTipo(tipo);
        System.out.println("La potencia media para el tipo " + tipo + " es: " + potenciaMedia);
    }

    private static void obtenerPotenciaMaximaPorTipo() {
        System.out.print("Ingrese el tipo de vehículo: ");
        String tipo = scanner.nextLine();
        int potenciaMaxima = vehiculoDAO.obtenerPotenciaMaximaPorTipo(tipo);
        System.out.println("La potencia máxima para el tipo " + tipo + " es: " + potenciaMaxima);
    }
}
