package sample.menu;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import sample.Journal;
import sample.Note;
import sample.Tank;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MenuController {

    @FXML
    public Label error;
    public Button activeOne;
    public Button activeTwo;
    public Button activeThree;
    public Button activeFour;
    public Button activeFive;
    public Button pumpinOne;
    public Button pumpinTwo;
    public Button pumpinThree;
    public Button pumpinFour;
    public Button pumpinFive;
    public Button pumpoutOne;
    public Button pumpoutTwo;
    public Button pumpoutThree;
    public Button pumpoutFour;
    public Button pumpoutFive;
    public Button history;
    public TextField SpeedInOne;
    public TextField SpeedInTwo;
    public TextField SpeedInThree;
    public TextField SpeedInFour;
    public TextField SpeedInFive;
    public TextField SpeedOutOne;
    public TextField SpeedOutTwo;
    public TextField SpeedOutThree;
    public TextField SpeedOutFour;
    public TextField SpeedOutFive;
    public TextField MinOne;
    public TextField MinTwo;
    public TextField MinThree;
    public TextField MinFour;
    public TextField MinFive;
    public TextField MaxOne;
    public TextField MaxTwo;
    public TextField MaxThree;
    public TextField MaxFour;
    public TextField MaxFive;
    public Label minInfoOne;
    public Label minInfoTwo;
    public Label minInfoThree;
    public Label minInfoFour;
    public Label minInfoFive;
    public Label maxInfoOne;
    public Label maxInfoTwo;
    public Label maxInfoThree;
    public Label maxInfoFour;
    public Label maxInfoFive;
    public TextField levelOne;
    public TextField levelTwo;
    public TextField levelThree;
    public TextField levelFour;
    public TextField levelFive;
    public TextArea journalArea;

    public static Tank tankOne = new Tank();
    public static Tank tankTwo = new Tank();
    public static Tank tankThree = new Tank();
    public static Tank tankFour = new Tank();
    public static Tank tankFive = new Tank();

    Note note = new Note();
    String[] errors = new String[]{
            " ",
            "Введите целое число",
            "Нижний допустимый уровень выше максимума",
            "Резервуар заполнен",
            "Резервуар пуст",
            "Введите положительное число"
    };

    String[] objName = {"Резервуар №1:","Резервуар №2:","Резервуар №3:","Резервуар №4:","Резервуар №5:"};

    Journal journal1 = new Journal();
    Journal journal2 = new Journal();

    public void active(Tank tank, int i, Button active, Button pumpin, Button pumpout, TextField speedInfoIn, TextField speedInfoOut) throws IOException {
        //Имя резервуара, номер резервуара, кнопка состояния, кнопка насоса закачки, кнопка насоса откачки
        mainTimer.start();
        if(!ishistory) {
            refreshToday.start();
        }
        else refresh.start();
        if (tank.getAction()) {
            tank.setAct(false);
            active.setText("Выкл");
            tank.pumpIn.setActive(false);
            speedInfoIn.setText(String.valueOf(tank.pumpIn.getSpeed()));
            pumpin.setText("Выкл");
            tank.pumpOut.setActive(false);
            speedInfoOut.setText(String.valueOf(tank.pumpOut.getSpeed()));
            pumpout.setText("Выкл");
            Date date = new Date();
            SimpleDateFormat formatForDate = new SimpleDateFormat("MM.dd '|' hh:mm:ss '|'");
            note.setNote(formatForDate.format(date), objName[--i], "выключен");
        } else {
            //timer1.start();
            tank.setAct(true);
            active.setText("Вкл");
            Date date = new Date();
            SimpleDateFormat formatForDate = new SimpleDateFormat("MM.dd '|' hh:mm:ss '|'");
            note.setNote(formatForDate.format(date), objName[--i], "включен");
        }
        journal1.setList(note);
        journal1.Save();
        journal2.setList(note);
        journal2.SaveToday();
    }

    public void activeOne() throws IOException {
        active(tankOne, 1, activeOne, pumpinOne, pumpoutOne, SpeedInOne, SpeedOutOne);
    }

    public void activeTwo() throws IOException {
        active(tankTwo, 2, activeTwo, pumpinTwo, pumpoutTwo, SpeedInTwo, SpeedOutTwo);
    }

    public void activeThree() throws IOException {
        active(tankThree, 3, activeThree, pumpinThree, pumpoutThree, SpeedInThree, SpeedOutThree);
    }

    public void activeFour() throws IOException {
        active(tankFour, 4, activeFour, pumpinFour, pumpoutFour, SpeedInFour, SpeedOutFour);
    }

    public void activeFive() throws IOException {
        active(tankFive, 5, activeFive, pumpinFive, pumpoutFive, SpeedInFive, SpeedOutFive);
    }

    public void pumpInActive(Tank tank, int i, Button pumpin, TextField speedInfo) throws IOException {
        //Имя резервуара, номер резервуара, кнопка насоса закачки
        if (tank.pumpIn.getActive()) {
            tank.pumpIn.setActive(false);
            pumpin.setText("Выкл");
            speedInfo.setText(String.valueOf(tank.pumpIn.getSpeed()));
            Date date = new Date();
            SimpleDateFormat formatForDate = new SimpleDateFormat("MM.dd '|' hh:mm:ss '|'");
            note.setNote(formatForDate.format(date), objName[--i], "Насос закачки: выключен");
        } else {
            tank.pumpIn.setActive(true);
            pumpin.setText("Вкл");
            Date date = new Date();
            SimpleDateFormat formatForDate = new SimpleDateFormat("MM.dd '|' hh:mm:ss '|'");
            note.setNote(formatForDate.format(date), objName[--i], "Насос закачки: включен");
        }
        journal1.setList(note);
        journal1.Save();
        journal2.setList(note);
        journal2.SaveToday();
    }

    public void pumpinOne() throws IOException {
        pumpInActive(tankOne, 1, pumpinOne, SpeedInOne);
    }

    public void pumpinTwo() throws IOException {
        pumpInActive(tankTwo, 2, pumpinTwo, SpeedInTwo);
    }

    public void pumpinThree() throws IOException {
        pumpInActive(tankThree, 3, pumpinThree, SpeedInThree);
    }

    public void pumpinFour() throws IOException {
        pumpInActive(tankFour, 4, pumpinFour, SpeedInFour);
    }

    public void pumpinFive() throws IOException {
        pumpInActive(tankFive, 5, pumpinFive, SpeedInFive);
    }

    void pumpOutActive(Tank tank, int i, Button pumpout, TextField speedInfo) throws IOException {
        //Имя резервуара, номер резервуара, кнопка насоса откачки
        if (tank.pumpOut.getActive()) {
            tank.pumpOut.setActive(false);
            pumpout.setText("Выкл");
            speedInfo.setText(String.valueOf(tank.pumpIn.getSpeed()));
            Date date = new Date();
            SimpleDateFormat formatForDate = new SimpleDateFormat("MM.dd '|' hh:mm:ss '|'");
            note.setNote(formatForDate.format(date), objName[--i], "Насос откачки: выключен");
        } else {
            tank.pumpOut.setActive(true);
            pumpout.setText("Вкл");
            Date date = new Date();
            SimpleDateFormat formatForDate = new SimpleDateFormat("MM.dd '|' hh:mm:ss '|'");
            note.setNote(formatForDate.format(date), objName[--i], "Насос откачки: включен");
        }
        journal1.setList(note);
        journal1.Save();
        journal2.setList(note);
        journal2.SaveToday();
    }

    public void pumpoutOne() throws IOException {
        pumpOutActive(tankOne,1,pumpoutOne, SpeedOutOne);
    }

    public void pumpoutTwo() throws IOException {
        pumpOutActive(tankTwo,2,pumpoutTwo, SpeedOutTwo);
    }

    public void pumpoutThree() throws IOException {
        pumpOutActive(tankThree,3,pumpoutThree, SpeedOutThree);
    }

    public void pumpoutFour() throws IOException {
        pumpOutActive(tankFour,4,pumpoutFour, SpeedOutFour);
    }

    public void pumpoutFive() throws IOException {
        pumpOutActive(tankFive,5,pumpoutFive, SpeedOutFive);
    }

    void setSpeedIn(Tank tank, int i, Button pumpIn, TextField speedIn){
        //Имя резервуара, номер резервуара, кнопка насоса закачки, поле скорости
        try {
            if(Integer.parseInt(speedIn.getText())<0){error.setText(errors[5]);}
            else {
                error.setText(errors[0]);
                tank.pumpIn.setSpeed(Integer.parseInt(speedIn.getText()));
                tank.pumpIn.setActive(true);
                pumpIn.setText("Вкл");
                Date date = new Date();
                SimpleDateFormat formatForDate = new SimpleDateFormat("MM.dd '|' hh:mm:ss '|'");
                note.setNote(formatForDate.format(date), objName[--i], "Насос закачки: скорость изменена на " + tank.pumpIn.getSpeed());
                journal1.setList(note);
                journal1.Save();
                journal2.setList(note);
                journal2.SaveToday();
            }
        }
        catch (Exception e){
            error.setText(errors[1]);
        }
    }

    public void setSpeedInOne(){
        setSpeedIn(tankOne, 1, pumpinOne, SpeedInOne);
    }

    public void setSpeedInTwo(){
        setSpeedIn(tankTwo, 2, pumpinTwo, SpeedInTwo);
    }

    public void setSpeedInThree(){
        setSpeedIn(tankThree, 3, pumpinThree, SpeedInThree);
    }

    public void setSpeedInFour(){
        setSpeedIn(tankFour, 4, pumpinFour, SpeedInFour);
    }

    public void setSpeedInFive(){
        setSpeedIn(tankFive, 5, pumpinFive, SpeedInFive);
    }

    void setSpeedOut(Tank tank, int i, Button pumpOut, TextField speedOut){
        try {
            if(Integer.parseInt(speedOut.getText())<0){error.setText(errors[5]);}
            else {
                error.setText(errors[0]);
                tank.pumpOut.setSpeed(Integer.parseInt(speedOut.getText()));
                tank.pumpOut.setActive(true);
                pumpOut.setText("Вкл");
                Date date = new Date();
                SimpleDateFormat formatForDate = new SimpleDateFormat("MM.dd '|' hh:mm:ss '|'");
                note.setNote(formatForDate.format(date), objName[--i], "Насос откачки: скорость изменена на " + tank.pumpOut.getSpeed());
                journal1.setList(note);
                journal1.Save();
                journal2.setList(note);
                journal2.SaveToday();
            }
        }
        catch (Exception e){
            error.setText(errors[1]);
        }
    }

    public void setSpeedOutOne(){
        setSpeedOut(tankOne, 1, pumpoutOne, SpeedOutOne);
    }

    public void setSpeedOutTwo(){
        setSpeedOut(tankTwo, 2, pumpoutTwo, SpeedOutTwo);
    }

    public void setSpeedOutThree(){
        setSpeedOut(tankThree, 3, pumpoutThree, SpeedOutThree);
    }

    public void setSpeedOutFour(){
        setSpeedOut(tankFour, 4, pumpoutFour, SpeedOutFour);
    }

    public void setSpeedOutFive(){
        setSpeedOut(tankFive, 5, pumpoutFive, SpeedOutFive);
    }

    void setMin(Tank tank, int i, TextField Min, Label min){
        //Имя резервуара, номер резервуара, поле минимума
        try {
            if(Integer.parseInt(Min.getText())<0){error.setText(errors[5]);}
            else
            if (Integer.parseInt(Min.getText()) >= tank.max) {
                error.setText(errors[2]);
            } // если max<min -> ошибка
            else
                error.setText(errors[0]);
            tank.setMin(Integer.parseInt(Min.getText())); //если нижний уровень меньше текущего уровня, текущий = нижний
            min.setText(tank.getMin());
            if (Integer.parseInt(tank.getMin())>tank.level) {
                if(i==1)minTimer1.start();
                else if(i==2)minTimer2.start();
                else if(i==3)minTimer3.start();
                else if(i==4)minTimer4.start();
                else if(i==5)minTimer5.start();
            }
            Date date = new Date();
            SimpleDateFormat formatForDate = new SimpleDateFormat("MM.dd '|' hh:mm:ss '|'");
            note.setNote(formatForDate.format(date), objName[--i], "нижний допустимый уровень изменен на " + tank.getMin());
            journal1.setList(note);
            journal1.Save();
            journal2.setList(note);
            journal2.SaveToday();
        }
        catch (Exception e){
            error.setText(errors[1]);
        }
    }

    public void checkMin(Tank tank, TextField speedIn, TextField speedOut, int i){
        if(tank.getLvl()<Integer.parseInt(tank.getMin())){
            tank.pumpIn.setSpeed(1);
            speedIn.setText(String.valueOf(tank.pumpIn.getSpeed()));
            tank.pumpOut.setSpeed(0);
            speedOut.setText(String.valueOf(tank.pumpOut.getSpeed()));
        }
        else{
            tank.pumpIn.setSpeed(0);
            speedIn.setText(String.valueOf(tank.pumpIn.getSpeed()));
            tank.pumpOut.setSpeed(0);
            speedOut.setText(String.valueOf(tank.pumpOut.getSpeed()));
            if(i==1)minTimer1.stop();
            else if(i==2)minTimer2.stop();
            else if(i==3)minTimer3.stop();
            else if(i==4)minTimer4.stop();
            else if(i==5)minTimer5.stop();
        }
    }

    Timer minTimer1 = new Timer(1000, new ActionListener(){
        public void actionPerformed(ActionEvent e) {
            checkMin(tankOne,SpeedInOne,SpeedOutOne,1);
        }
    });
    Timer minTimer2 = new Timer(1000, new ActionListener(){
        public void actionPerformed(ActionEvent e) {
            checkMin(tankTwo,SpeedInTwo,SpeedOutTwo,2);
        }
    });
    Timer minTimer3 = new Timer(1000, new ActionListener(){
        public void actionPerformed(ActionEvent e) {
            checkMin(tankThree,SpeedInThree, SpeedOutThree,3);
        }
    });
    Timer minTimer4 = new Timer(1000, new ActionListener(){
        public void actionPerformed(ActionEvent e) {
            checkMin(tankFour, SpeedInFour, SpeedOutFour,4);
        }
    });
    Timer minTimer5 = new Timer(1000, new ActionListener(){
        public void actionPerformed(ActionEvent e) {
            checkMin(tankFive, SpeedInFive, SpeedOutFive,5);
        }
    });

    public void setMinOne() {
        setMin(tankOne,1,MinOne,minInfoOne);
    }

    public void setMinTwo() {
        setMin(tankTwo,2,MinTwo,minInfoTwo);
    }

    public void setMinThree() {
        setMin(tankThree,3,MinThree,minInfoThree);
    }

    public void setMinFour() {
        setMin(tankFour,4,MinFour,minInfoFour);
    }

    public void setMinFive() {
        setMin(tankFive,5,MinFive,minInfoFive);
    }

    boolean checkerMax = false;

    public void setMax(Tank tank, int i, TextField Max, Label max){
        //Имя резервуара, номер резервуара, поле максимума, скорость насоса закачки, скорость насоса откачки
        try {
            if(Integer.parseInt(Max.getText())<0){error.setText(errors[5]);}
            else
            if(Integer.parseInt(Max.getText())<=tank.min){error.setText(errors[2]);} // если max<min -> ошибка
            else {
                error.setText(errors[0]);
                tank.setMax(Integer.parseInt(Max.getText()));
                max.setText(tank.getMax());
                if(tank.max<tank.level){ // Если верхний уровень ниже текущего, изменить текущий до верхнего
                    if(i==1)maxTimer1.start();
                    else if(i==2)maxTimer2.start();
                    else if(i==3)maxTimer3.start();
                    else if(i==4)maxTimer4.start();
                    else if(i==5)maxTimer5.start();
                }
                Date date = new Date();
                SimpleDateFormat formatForDate = new SimpleDateFormat("MM.dd '|' hh:mm:ss '|'");
                note.setNote(formatForDate.format(date), objName[--i], "верхний допустимый уровень изменен на " + tank.getMax());
                journal1.setList(note);
                journal1.Save();
                journal2.setList(note);
                journal2.SaveToday();
            }
        }
        catch (Exception e){
            error.setText(errors[1]);
        }
    }

    public void checkMax(Tank tank, TextField speedIn, TextField speedOut, int i){
        if(tank.getLvl()>Integer.parseInt(tank.getMax())){
            tank.pumpIn.setSpeed(0);
            speedIn.setText(String.valueOf(tank.pumpIn.getSpeed()));
            tank.pumpOut.setSpeed(1);
            speedOut.setText(String.valueOf(tank.pumpOut.getSpeed()));
        }
        else{
            tank.pumpIn.setSpeed(0);
            speedIn.setText(String.valueOf(tank.pumpIn.getSpeed()));
            tank.pumpOut.setSpeed(0);
            speedOut.setText(String.valueOf(tank.pumpOut.getSpeed()));
            if(i==1)maxTimer1.stop();
            else if(i==2)maxTimer2.stop();
            else if(i==3)maxTimer3.stop();
            else if(i==4)maxTimer4.stop();
            else if(i==5)maxTimer5.stop();
        }
    }

    Timer maxTimer1 = new Timer(1000, new ActionListener(){
        public void actionPerformed(ActionEvent e) {
            checkMax(tankOne,SpeedInOne,SpeedOutOne,1);
        }
    });
    Timer maxTimer2 = new Timer(1000, new ActionListener(){
        public void actionPerformed(ActionEvent e) {
            checkMax(tankTwo,SpeedInTwo,SpeedOutTwo,2);
        }
    });
    Timer maxTimer3 = new Timer(1000, new ActionListener(){
        public void actionPerformed(ActionEvent e) {
            checkMax(tankThree,SpeedInThree, SpeedOutThree,3);
        }
    });
    Timer maxTimer4 = new Timer(1000, new ActionListener(){
        public void actionPerformed(ActionEvent e) {
            checkMax(tankFour, SpeedInFour, SpeedOutFour,4);
        }
    });
    Timer maxTimer5 = new Timer(1000, new ActionListener(){
        public void actionPerformed(ActionEvent e) {
            checkMax(tankFive, SpeedInFive, SpeedOutFive,5);
        }
    });

    public void setMaxOne() {
        setMax(tankOne,1,MaxOne, maxInfoOne);
    }

    public void setMaxTwo() {
        setMax(tankTwo,2,MaxTwo, maxInfoTwo);
    }

    public void setMaxThree() {
        setMax(tankThree,3,MaxThree, maxInfoThree);
    }

    public void setMaxFour() {
        setMax(tankFour,4,MaxFour, maxInfoFour);
    }

    public void setMaxFive() {
        setMax(tankFive,5,MaxFive, maxInfoFive);
    }

    public void timer(Tank tank){
        // Название резервуара, номер резервуара,
        int k = tank.pumpIn.getSpeed() - tank.pumpOut.getSpeed();
        tank.changeLvl(k);
    }

    public void refresh() throws Exception {
        Path path = Paths.get("src/sample/demo.txt");
        String str = Files.readString(path);
       // String str2 = str.substring(2, str.length());
        journal1.download();
        journalArea.setText("");
        journalArea.setText(str.replace("\r\n\r\n","\n"));
    }

    public void refreshToday() throws Exception {
        Path path2 = Paths.get("src/sample/today.txt");
        String str = Files.readString(path2);
        journal2.downloadToday();
        journalArea.setText("");
        journalArea.setText(str.replace("\r\n\r\n","\n"));
    }

@FXML
    void delete() throws IOException {
        journal1.removeList();
        Path path = Paths.get("src/sample/demo.txt");
        PrintWriter writer = new PrintWriter("src/sample/demo.txt");
        writer.print("");
        String str = Files.readString(path, StandardCharsets.UTF_8);
        journalArea.setText(str);
    }

    Timer refreshToday = new Timer(1000, new ActionListener(){
        public void actionPerformed(ActionEvent e) {
            try {
                refreshToday();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    });

    Timer refresh = new Timer(1000, new ActionListener(){
        public void actionPerformed(ActionEvent e) {
            try {
                refresh();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    });

    Timer mainTimer = new Timer(1000, new ActionListener(){
        public void actionPerformed(ActionEvent e) {
            timer(tankOne);
            timer(tankTwo);
            timer(tankThree);
            timer(tankFour);
            timer(tankFive);
            levelOne.setText(tankOne.getLvl() +"%");
            levelTwo.setText(tankTwo.getLvl() +"%");
            levelThree.setText(tankThree.getLvl() +"%");
            levelFour.setText(tankFour.getLvl() +"%");
            levelFive.setText(tankFive.getLvl() +"%");
        }
    });
    boolean ishistory=false;
    public void history(){
        if(!ishistory) {
            ishistory=true;
            history.setText("Скрыть");
            refreshToday.stop();
            refresh.start();
        }
        else{
            ishistory=false;
            history.setText("История");
            refresh.stop();
            refreshToday.start();
        }
    }

}
