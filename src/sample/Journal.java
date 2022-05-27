package sample;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public class Journal {
    public ArrayList<String> list = new ArrayList<>();


    public void setList(Note n){
        this.list.add(n.getNote());
    }

    public void removeList(){
        this.list.clear();
    }

    public void Save() throws IOException {
        Files.write(Paths.get("src/sample/demo.txt"), this.list, StandardCharsets.UTF_8, StandardOpenOption.CREATE); // Сохранение в текстовик
    }

    public void SaveToday() throws IOException {
        Files.write(Paths.get("src/sample/today.txt"), this.list, StandardCharsets.UTF_8, StandardOpenOption.CREATE); // Сохранение в текущую
    }

    public void download() throws Exception {
        Path path = Paths.get("src/sample/demo.txt");
        this.list.clear();
        this.list.add(Files.readString(path, StandardCharsets.UTF_8));
    }

    public void downloadToday() throws Exception {
        Path path = Paths.get("src/sample/today.txt");
        this.list.clear();
        this.list.add(Files.readString(path, StandardCharsets.UTF_8));
    }

    public ArrayList getList(){
        return this.list;
    }
}
