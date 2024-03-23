import java.io.File;
import java.io.IOException;
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
                BroadcastsTime data = new BroadcastsTime(read);
                String name = s.nextLine();
                allPrograms.add(new Program(channel, data, name));
                channelList.add(new Program(channel, data, name));
                if (s.hasNextLine())
                    read = s.nextLine();
            };
            programsMap.put(channel, channelList);
        }
        s.close();

        // 5. Создать List<программа> со всеми программами всех каналов
        for (List<Program> programs : programsMap.values()) {
            allPrograms.addAll(programs);
        }


        //6. вывести все программы в порядке возрастания времени показа

        System.out.println(6 + " вывести все программы в порядке возрастания времени показа");
        Collections.sort(allPrograms, Comparator.comparing(program -> program.getTime()));
        System.out.println(allPrograms);


        //7. вывести все программы, которые идут сейчас
        System.out.println(7 + " вывести все программы, которые идут сейчас ");
        BroadcastsTime currentTime = new BroadcastsTime( "15:10"); // Пример текущего времени
        for (Program program : allPrograms) {
            if (program.getTime().equals(currentTime)) {
                System.out.println(program.getName());
            }
        };



        //8. Найти все программы по некоторому названию:
        System.out.println(8 + " найти все программы по некоторому названию:");
        String searchName = "Умницы и умники";
        List<Program> foundPrograms = allPrograms.stream()
                .filter(program -> program.getName().equals(searchName))
                .collect(Collectors.toList());
        System.out.println(foundPrograms);


        //9 Найти все программы определенного канала, которые идут сейчас:
        System.out.println(9);
        BroadcastsTime currentTime1 = new BroadcastsTime( (byte) 13,  (byte) 10); // Пример текущего времени1
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
        System.out.println(10);
        BroadcastsTime startTime = new BroadcastsTime((byte) 5, (byte) 0); // Example start time
        BroadcastsTime endTime = new BroadcastsTime((byte) 9, (byte) 0); // Example end time
        for (Program program : channelPrograms) {
            if (program.getTime().between(startTime, endTime)) {
                System.out.println(program.getName());
            }
        }
    }

}