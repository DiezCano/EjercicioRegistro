import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class EjercicioClase {


    static void main() {

        List<Registro> lista = new ArrayList<>();
        LocalDateTime ahora = LocalDateTime.now();
        Random r = new Random();

        for (int i = 0; i < 100; i++) {
            lista.add(new Registro(
                    ahora.minusMinutes(100 - i),
                    15 + r.nextDouble() * 30,
                    40 + r.nextDouble() * 60
            ));
        }

        // 1º 1. Filtrar los registros de temperatura que sean mayores a 25 grados, la humedad sea menor a 70 y la
        //fecha sea anterior a la fecha actual, y mostrarlos.
        IO.println("--- 1 ---");
        lista.stream()
                .filter(reg ->reg.getTemperatura() > 25 )
                .filter(reg -> reg.getHumedad() < 70)
                .filter(reg ->reg.getFechahora().isBefore(LocalDateTime.now()))
                .forEach(System.out::println);

        //2. Encontrar el registro con la temperatura más alta y mostrar el registro completo.
        IO.println("--- 2 ---");


        //3. Obtener una lista con las fechas/horas de todas las tomas de datos.
        IO.println("--- 3 ---");
        List<LocalDateTime> listaFecha = lista.stream()
                .map(reg -> reg.getFechahora())
                .toList();
        listaFecha.forEach(System.out::println);

        //4. Incrementar en 5 unidades la humedad de todos los registros y mostrar las temperaturas,
        //humedades y fechas/horas actualizadas.
        IO.println("--- 4 ---");
        lista.stream()
            .map(reg -> new Registro(
                reg.getFechahora(),
                reg.getTemperatura(),
                reg.getHumedad() + 5 ))
                .forEach(System.out::println);
        //5. Encontrar el registro con la temperatura más baja que tenga una humedad mayor a 80 y mostrar la
        //temperatura, humedad y fecha.
        IO.println("--- 5 ---");
        lista.stream()
                .filter(e -> e.getHumedad() > 80)
                .min(Comparator.comparing(e -> e.getTemperatura()))
                .ifPresent(System.out::println);
        //6. Verificar si algún registro tiene una temperatura mayor a 30 grados, humedad mayor a 90 y la fecha
        //es de hoy. Mostrar un mensaje indicando si hay algún registro que cumple esta condición o no.
        IO.println("--- 6 ---");
        lista.stream()
                .filter(e -> e.getTemperatura() > 30)
                .filter(e -> e.getHumedad() > 90)
                .forEach(System.out::println);
        //7. Muestra 10 registros saltándote los 5 primeros.
        IO.println("--- 7 ---");
        lista.stream()
                .skip(5)
                .limit(10)
                .forEach(System.out::println);
        //8. Muestra los registros ordenados por fecha (sorted(Comparator))
        IO.println("--- 8 ---");
        lista.stream()
                .sorted(Comparator.comparing(Registro::getFechahora))
                .forEach(System.out::println);
        //9. Cuenta los registros que tengan temperatura mayor a 35 grados (count()).
        IO.println("--- 9 ---");
        long cantidad = lista.stream()
                .filter(e -> e.getTemperatura() > 35)
                .count();
        IO.println(cantidad);
        //10. Calcular la temperatura promedio de todos los registros (transformarlo en Stream<Double> y
        //llamar a average()).
        IO.println("--- 10 ---");
        OptionalDouble doble = lista.stream()
                .mapToDouble(Registro::getTemperatura)
                .average();
        doble.ifPresent(IO::println);
    }

}
