package ru.pavel.javaprofessional;

public class Check {
    public boolean check(int[] array) {
        boolean check = false;
        for (int o: array) {
            if(o == 1 || o == 4) {
                check = true;
            }
        }

        return check;
    }
}
