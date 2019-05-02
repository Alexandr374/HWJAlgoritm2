package com.company;
//1. Создать массив большого размера (миллион элементов).
//2. Написать методы удаления, добавления, поиска элемента массива.
//3. Заполнить массив случайными числами.
//4. Написать методы, реализующие рассмотренные виды сортировок, и проверить скорость выполнения каждой.

import java.util.Comparator;

public class MyArray<Item> {
    private int size = 0;
    private Object [] array = new Object[1];

    public MyArray(){
    }

    public MyArray(MyArray<Item> myArray) {
        array = new Object[myArray.size];
        System.arraycopy(myArray.array, 0,this.array,0,myArray.size);
        this.size = myArray.size;
    }

    public Item get (int index){
        if(index < 0 || index > (size -1)){
            throw new IndexOutOfBoundsException();
        }
        return (Item) array[index];
    }

    public void set(int index, Item item) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException();
        }
        array[index] = item;
    }

    private void resize(int capacity){
        Object [] temp = new Object[capacity];
        for(int i = 0; i < array.length; i++){
            temp[i] = array[i];
        }
        array = temp;
    }

    public void add(Item item) {
        if (size == array.length){
            resize(2*array.length);
        }
        array[size] = item;
        size++;
    }

    public boolean remove (Item item){
        int index = indexOf(item);
        if(index == -1){
            return false;
        }
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        array[size - 1] = null;
        size--;
        if(size == array.length/4 && size > 0){
            resize(array.length/2);
        }
        return true;
    }

    public int indexOf (Item item){
        for (int i = 0; i < size; i++) {
            if (array[i].equals(item)){
                return i;
            }
        }
        return -1;
    }

    public boolean contains (Item item){
        return indexOf(item) != -1;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < size; i++) {
            s.append(array[i] + ", ");
        }
        return s.toString();
    }

    public int size(){
        return size;
    }

    public int length(){
        return array.length;
    }

    private void exch (int i, int j){
        Object temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private boolean less (Item item1, Item item2, Comparator<Item> cmp){
        return cmp.compare(item1, item2) < 0;
    }

    public void selectionSort (Comparator<Item> cmp){
        for (int i = 0; i < size - 1; i++) {
            int min = i;
            for (int j = i + 1; j < size; j++) {
                if (less((Item) array[j],(Item) array[min],cmp)){
                    min = j;
                }
            }
            exch(i,min);
        }
    }

    public void insertSort (Comparator<Item> cmp){
        for (int i = 0; i < size; i++) {
            for (int j = i; j > 0 ; j--) {
                if(less((Item)array[j],(Item)array[j-1],cmp)){
                    exch(j,j-1);
                }
                else {
                    break;
                }
            }
        }
    }
}
