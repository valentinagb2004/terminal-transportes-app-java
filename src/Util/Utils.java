/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

import Excepciones.EmailValidoException;
import Excepciones.FormatoFechaException;
import Excepciones.TelefonoValidoException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

/**
 *
 * @author Valentina
 */
public class Utils {
    
    /*public static String obtenerFechaSistema() {
        // Obtener la fecha actual
        LocalDate currentDate = LocalDate.now();
        
        // Definir el formato deseado
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        // Formatear la fecha y retornar como String
        return currentDate.format(formatter);
    }*/
     
     // Método que compara una fecha con la fecha del sistema
    public static boolean fechaMayorAlSistema(String dateStr, String hora) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        dateStr = dateStr+ " "+hora + ":00";

        try {
            // Parsear la fecha de entrada
            LocalDateTime inputDate = LocalDateTime.parse(dateStr, formatter);
            // Obtener la fecha actual
            LocalDateTime currentDate = LocalDateTime.now();
            
            // Comparar las fechas
            return inputDate.isAfter(currentDate);
        } catch (DateTimeParseException e) {
            // Manejar el caso de un formato de fecha inválido
            System.err.println("Fecha inválida: " + dateStr);
            return false;
        }
    }
    
    public static void validarEmail(String email) throws EmailValidoException {
        // Expresión regular para validar un correo electronico
        final String EMAIL_PATTERN =
                "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        // Compilar la expresión regular en un patrón
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        if (email == null || !pattern.matcher(email).matches()) {
            throw new EmailValidoException();
        }
    }
    
    public static void validarTelefono(String s) throws TelefonoValidoException{
        // Verificar que la longitud del string sea 10
        if (s.length() != 10) {
            throw new TelefonoValidoException();
        }
        
        // Verificar que todos los caracteres sean dígitos
        for (char c : s.toCharArray()) {
            if (!Character.isDigit(c)) {
               throw new TelefonoValidoException();
            }
        }
    }
    
    public static void validarFecha(String fechaString) throws FormatoFechaException {
        try {
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate.parse(fechaString, formato);
        } catch (Exception e) {
          throw new FormatoFechaException();
        }
    }
    
    public static boolean esFechaInicioMenor(String fechaInicio, String horaInicio, String fechaFin, String horaFin) {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        fechaInicio = fechaInicio+ " "+horaInicio + ":00";
        fechaFin = fechaFin+ " "+horaFin + ":00";
        
        try {
            LocalDateTime inicio = LocalDateTime.parse(fechaInicio, formato);
            LocalDateTime fin = LocalDateTime.parse(fechaFin, formato);
            return fin.isAfter(inicio);
        } catch (DateTimeParseException e) {
            System.out.println("Error: Formato de fecha u hora inválido");
            return false;
        }
    }
}
