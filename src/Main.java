import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader bf = new BufferedReader(new FileReader("file.txt"))) {
            int N = Integer.parseInt(bf.readLine()); // Считываем количество занятых мест
            Map<Integer, Set<Integer>> seatsMap = new HashMap<>();

            // Считываем занятые места
            for (int i = 0; i < N; i++) {
                String[] temp = bf.readLine().split(" ");
                int row = Integer.parseInt(temp[0]);
                int seat = Integer.parseInt(temp[1]);

                // Добавляем место в соответствующий ряд
                seatsMap.computeIfAbsent(row, k -> new HashSet<>()).add(seat);
            }

            int maxRow = 1;
            int minSeat = 1;

            // Ищем ряды с подходящими местами
            for (Map.Entry<Integer, Set<Integer>> entry : seatsMap.entrySet()) {
                int row = entry.getKey();
                Set<Integer> occupiedSeats = entry.getValue();
                Integer lastSeat = null;

                // Проверяем все занятые места в ряду
                for (int seat : occupiedSeats) {
                    if (lastSeat != null && seat == lastSeat + 3) {
                        int freeSeatCandidate = lastSeat + 1;
                        if (row > maxRow || (row == maxRow && freeSeatCandidate < minSeat)) {
                            maxRow = row;
                            minSeat = freeSeatCandidate;
                        }
                    }
                    lastSeat = seat;
                }
            }

            // Выводим результат
            System.out.println(maxRow + " " + minSeat);

        } catch (IOException e) {
            System.out.println("Ошибка чтения файла: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Ошибка формата числа: " + e.getMessage());
        }
    }
}
