package actividad2sistemagestionbiblioteca;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Actividad2SistemaGestionBiblioteca {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    

    public static void main(String[] args) {
     
        
        ArrayList<String[]> libros = new ArrayList<>();
        LinkedList<String[]> usuarios = new LinkedList<>();
        Stack<String[]> librosPrestados = new Stack<>();
        Queue<String[]> colaEspera = new LinkedList<>();
        
        Scanner entrada = new Scanner (System.in);
        
        int opcion;
        do{
            System.out.println(ANSI_BLUE + "=============================================" + ANSI_RESET);
            System.out.println(ANSI_BLUE + "    MARIA FERNANDA CASTILLO CC: 1003526557   " + ANSI_RESET);
            System.out.println(ANSI_BLUE + "=============================================" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "=============================================" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "       SISTEMA DE GESTION DE BIBLIOTECA      " + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "=============================================" + ANSI_RESET);
            System.out.println(ANSI_PURPLE + "1. Agregar libro" + ANSI_RESET);
            System.out.println(ANSI_PURPLE + "2. Registrar usuario" + ANSI_RESET);
            System.out.println(ANSI_PURPLE + "3. Prestar libro" + ANSI_RESET);
            System.out.println(ANSI_PURPLE + "4. Devolver libro" + ANSI_RESET);
            System.out.println(ANSI_PURPLE + "5. Mostrar libros disponibles" + ANSI_RESET);
            System.out.println(ANSI_PURPLE + "6. Mostrar usuarios registrados" + ANSI_RESET);
            System.out.println(ANSI_PURPLE + "7. Salir" + ANSI_RESET); 
            System.out.println(ANSI_CYAN + "Seleccione una opcion: " + ANSI_RESET);
            
            while(!entrada.hasNextInt()){
                System.out.println("Error: ingrese un numero valido!!");
                entrada.next();
                System.out.println("Seleccione una opcion: ");
            }
            
            opcion = entrada.nextInt();
            entrada.nextLine();
            switch(opcion){
                case 1:
                    System.out.println("Ingrese el ID del libro (unico) ");
                    String idLibro = entrada.nextLine();
                    boolean idDuplicado = false;
                    for(String[] libro: libros){
                        if(libro[0].equals(idLibro)){
                            idDuplicado = true;
                            break;
                        }
                    } 
                    if(idDuplicado){
                        System.out.println("Error: El ID del libro ya existe!");
                    }else{
                        System.out.println("Ingrese el nombre del libro: ");
                        String nombreLibro = entrada.nextLine();
                        System.out.println("Ingrese el autor del libro: ");
                        String autorLibro = entrada.nextLine();
                        libros.add(new String[]{idLibro, nombreLibro, autorLibro});
                        System.out.println("Libro agregado exitosamente!!!");
                    }
                    break;
                case 2:
                    System.out.println("Ingrese la cedula del usuario (solo numero): ");
                    
                    while(!entrada.hasNextInt()){
                        System.out.println("Error: ingrese un numero valido!!");
                        entrada.next();
                        System.out.println("Ingrese la cedula del usuario (SOLO CON NUMERO): ");
                    }
                    int cedulaUsuario = entrada.nextInt();
                    entrada.nextLine();
                    System.out.println("Ingrese el nombre del usuario: ");
                    String nombreUsuario = entrada.nextLine();
                    System.out.println("Ingrese los apellidos del usuario: ");
                    String apellidoUsuario = entrada.nextLine();
                    
                    boolean cedulaDuplicado = false;
                    for(String[] usuario: usuarios){
                        if(usuario[0].equals(String.valueOf(cedulaUsuario))){
                            cedulaDuplicado = true;
                            break;
                        }
                    } 
                    if(cedulaDuplicado){
                        System.out.println("Error: La cedula del usuario ya existe!!");
                    }else{
                            usuarios.add(new String[]{String.valueOf(cedulaUsuario),nombreUsuario,apellidoUsuario});
                            System.out.println("Usuario registrado exitosamente!!!");
                    }
                    break;
                case 3:
                    System.out.println("Ingrese el Id del libro de que desea prestar: ");
                    String idPrestar = entrada.nextLine();
                    System.out.println("Ingrese la cedula del usuario que presta el libro: ");
                      while(!entrada.hasNextInt()){
                        System.out.println("Error: ingrese un numero valido!!");
                        entrada.next();
                        System.out.println("Ingrese la cedula del usuario (SOLO CON NUMERO): ");
                    }
                    int cedulaPrestar = entrada.nextInt();
                    entrada.nextLine();
                    
                    boolean usuarioRegistrado = false;
                    for(String[] usuario: usuarios){
                        if(usuario[0].equals(String.valueOf(cedulaPrestar))){
                            usuarioRegistrado = true;
                            break;
                        }
                    }
                    if(!usuarioRegistrado){
                        System.out.println("Error el usuario con la cedula digitada"+cedulaPrestar 
                                + " No esta registrado, no se puede prestar el libro");
                    }else{
                        boolean libroEncontrado = false;
                        for(String[] libro: libros){
                            if(libro[0].equals(idPrestar)){
                                librosPrestados.push(new String[]{idPrestar, libro[1], libro[2], String.valueOf(cedulaPrestar)});
                                libros.remove(libro);
                                libroEncontrado = true;
                                System.out.println("Libro prestado con exito!!");
                            break;
                        }
                    } 
                    if(!libroEncontrado){
                        System.out.println("Libro no disponble. Desea agregar a la cola de espera? (si/no)");
                        String respuesta = entrada.nextLine();
                        if(respuesta.equalsIgnoreCase("si")){
                            colaEspera.add(new String[]{idPrestar, String.valueOf(cedulaPrestar)});
                            System.out.println("Agregado a la cola de espera");
                        }
                    }
                }
                    break;
                case 4:
                    if(!librosPrestados.isEmpty()){
                        String [] libroDevuelto = librosPrestados.pop();
                        libros.add(new String[]{libroDevuelto[0], libroDevuelto[1], libroDevuelto[2]});
                        System.out.println("Libro devuelto exitosamente");
                    }
                    if(!colaEspera.isEmpty()){
                        String[] proximosEnCola = colaEspera.poll();
                        System.out.println("El usuario con cedula"+ proximosEnCola[1] + 
                                "esta en cola y ahora prestara el libro con ID: "+ proximosEnCola[0] );
                        librosPrestados.push(proximosEnCola);
                    }else{
                        System.out.println("No hay libros prestados");
                    }
                    break;
                case 5:
                    if(libros.isEmpty()){
                        System.out.println("No hay libros disponibles");
                    }else{
                        System.out.println("----Libros disponibles----");
                        System.out.printf("%-10s %-15s %-20s %-20s%n", "ID", "Nombre", "Autor");
                        for(String[] libro: libros){
                            System.out.printf("%-10s %-15s %-20s %-20s%n", libro[0], libro[1], libro[2]);
                        }
                    }
                    break;
                case 6:
                     if(usuarios.isEmpty()){
                        System.out.println("No hay usuarios disponibles");
                    }else{
                        System.out.println("----Usuarios disponibles----");
                        System.out.printf("%-10s %-15s %-20s %-20s%n", "ID", "Nombre", "Autor");
                        for(String[] usuario: usuarios){
                            System.out.printf("%-10s %-15s %-20s %-20s%n", usuario[0], usuario[1], usuario[2]);
                        }
                    }
            
                    break;
                case 7:
                    System.out.println("Vuelva pronto!!");
                    break;
                default:
                    System.out.println("Opcion no valida. Intetente de nuevo");
                    break;
            }
        }while(opcion != 7);
    }
}