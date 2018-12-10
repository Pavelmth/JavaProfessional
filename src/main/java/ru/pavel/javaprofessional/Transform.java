package ru.pavel.javaprofessional;

public class Transform {
    public int[] transform(int[] array) {
        boolean check = true;
        for (int o: array) {
            if (o == 4) {
                check = false;
            }
        }
        if (check) {
            throw new RuntimeException("в массиве нет цифры 4");
        }

        int numb = 0;
        for (int i = 0; i < array.length; i++ ) {
            if (array[i] == 4) {
                numb = i + 1;
            }
        }
        int[] arrayCopy = new int[array.length - numb];
        System.arraycopy(array, numb, arrayCopy, 0, array.length - numb);

        return arrayCopy;
    }
}
