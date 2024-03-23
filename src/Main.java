import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {

        Scanner s = new Scanner(new File("D:\\КФУ(Java)\\Programming\\Programming_19_03\\src\\schedule.txt"));

        //4. Создать и наполнить данными из файла структуру Map<канал, List<программа>>
        List<Program> allPrograms = new ArrayList<Program>();
        Map<String, List<Program>> programsMap = new HashMap<>();
        String read = s.nextLine();
        while (s.hasNextLine()){
            String channel = read;
            read = s.nextLine();
            List<Program> channelList = new ArrayList<Program>();
            while ((read.charAt(0) != '#') && (s.hasNextLine())) {
                byte hour = Byte.parseByte("" + read.charAt(0) + read.charAt(1));
                byte minutes = Byte.parseByte("" + read.charAt(3) + read.charAt(4));
                BroadcastsTime data = new BroadcastsTime(hour,  minutes);
                String name = s.nextLine();
                allPrograms.add(new Program(channel, data, name));
                channelList.add(new Program(channel, data, name));
                if (s.hasNextLine())
                    read = s.nextLine();
            };
            programsMap.put(channel, channelList);
        }

        // 5. Создать List<программа> со всеми программами всех каналов
        for (List<Program> programs : programsMap.values()) {
            allPrograms.addAll(programs);
        }

        //6. вывести все программы в порядке возрастания времени показа
        Collections.sort(allPrograms, Comparator.comparing(program -> program.getTime()));

        //7. вывести все программы, которые идут сейчас
        System.out.println("все программы, которые идут сейчас");
        BroadcastsTime currentTime = new BroadcastsTime( 15,  10); // Пример текущего времени
        for (Program program : allPrograms) {
            if (program.getTime().equals(currentTime)) {
                System.out.println(program.getName());
            }
        };


        //8. Найти все программы по некоторому названию:
        String searchName = "Доброе утро. Суббота";
        List<Program> foundPrograms = allPrograms.stream()
                .filter(program -> program.getName().equals(searchName))
                .collect(Collectors.toList());

        //9 Найти все программы определенного канала, которые идут сейчас:
        System.out.println("все программы определенного канала, которые идут сейчас");
        BroadcastsTime currentTime1 = new BroadcastsTime( 15,  10); // Пример текущего времени1
        String channelName = "#Первый";
        List<Program> channelPrograms = programsMap.get(channelName);
        if (channelPrograms != null) {
            for (Program program : channelPrograms) {
                if (program.getTime().equals(currentTime1)) {
                    System.out.println(program.getName());
                }
            }
        }

        //10 найти все программы определенного канала, которые будут идти в некотором промежутке времени
        System.out.println("все программы определенного канала, которые будут идти в некотором промежутке времени");
        BroadcastsTime startTime = new BroadcastsTime( 5,  0); // Example start time
        BroadcastsTime endTime = new BroadcastsTime( 9,  0); // Example end time
        for (Program program : channelPrograms) {
            if (program.getTime().between(startTime, endTime)) {
                System.out.println(program.getName());
            }
        }
    }

}