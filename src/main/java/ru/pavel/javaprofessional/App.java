package ru.pavel.javaprofessional;

//Прочитать файл (около 50 байт) в байтовый массив и вывести этот массив в консоль;
//
//Последовательно сшить 5 файлов в один (файлы примерно 100 байт). Может пригодиться следующая конструкция: ArrayList<InputStream>
//al = new ArrayList<>(); ... Enumeration<InputStream> e = Collections.enumeration(al);
//
//Написать консольное приложение, которое умеет постранично читать текстовые файлы (размером > 10 mb).
//Вводим страницу (за страницу можно принять 1800 символов), программа выводит ее в консоль.
//Контролируем время выполнения: программа не должна загружаться дольше 10 секунд, а чтение – занимать свыше 5 секунд.

import java.io.*;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class App
{
    public static void main( String[] args ) {

        readFile1();

        try {
            gather();
        } catch (IOException e) {
            e.printStackTrace();
        }

        reader();
    }

    public static void readFile1() {
        try {
            FileInputStream in = new FileInputStream("classic/Griboedov_A._Klassikavshkole._Gore_Ot_Uma-2.txt");
            byte[] bytes = new byte[512];
            int x;
            while ((x = in.read(bytes)) != -1 ) {
                System.out.print(new String(bytes, 0, x, "UTF-8"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readFile2() {
        try {
            InputStreamReader in = new InputStreamReader(new FileInputStream("classic/Defo_D._Robinzonkruzo1._Robinzon_Kruzo.txt"));
            int y;
            while ((y = in.read()) != -1) {
                System.out.print((char) y);
            }
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void gather() throws IOException {
        ArrayList<InputStream> in = new ArrayList<>();

        try {
            in.add(new FileInputStream("classic/Defo_D._Robinzonkruzo1._Robinzon_Kruzo.txt"));
            in.add(new FileInputStream("classic/Griboedov_A._Klassikavshkole._Gore_Ot_Uma-2.txt"));
            in.add(new FileInputStream("classic/Grin_A._Alyie_Parusa.txt"));
            in.add(new FileInputStream("classic/London_D._Martin_IdenII-2.txt"));
            in.add(new FileInputStream("classic/Pushkin_A._Spisokshkolnoy._Dubrovskiyi-2.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        SequenceInputStream al = new SequenceInputStream(Collections.enumeration(in));

        byte[] bytes = new byte[1000];
        int x;

        while ((x = al.read(bytes)) != -1) {
            System.out.print(new String(bytes, 0, x, "UTF-8"));
        }

        try {
            al.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void reader() {
        try {
            long t = System.currentTimeMillis();

            FileInputStream in = new FileInputStream("classic/Defo_D._Robinzonkruzo1._Robinzon_Kruzo.txt");

            FileChannel fileChannel = FileChannel.open(Paths.get("classic/Defo_D._Robinzonkruzo1._Robinzon_Kruzo.txt"));
            long fileSize = fileChannel.size();

            if (fileSize < 10_000_000) {
                int x;
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                while ((x = br.read()) != -1) {
                    System.out.print((char) x);
                }
            } else {
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                String strLine;
                while ((strLine = br.readLine()) != null) {
                    System.out.println(strLine);
                }
            }

            LinkedList<String> sheets = new LinkedList<>();
            String sheet = null;
            char ch;
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            while (br.read() != -1) {
                ch = (char) in.read();
                sheet += ch;
                if (sheet.length() > 1800) {
                    sheets.add(sheet);
                    sheet = null;
                }
            }

            System.out.print(System.currentTimeMillis() - t);

        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}






