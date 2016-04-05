package mx.itesm.ladiesnight;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
/**
 * Created by beeme on 03/04/2016.
 */
public class ValueListHandler<T> implements InterfaceValueListHandler {

    protected ArrayList<T> list;
    protected ListIterator<T> listIterator;

    public ValueListHandler() {
    }


    @Override
    public void setList( ArrayList list) {
        this.list = list;
        if(list != null)
            listIterator =  list.listIterator();
        else
            System.out.println("Vacía");
    }

    public  ArrayList<T> getList() {
        return this.list;
    }

    public int getSize(){
        int size = 0;

        if (list != null) {
            size = this.list.size();
        } else {
            System.out.println("No data");
        }

        return size;
    }

    public T getCurrentElement() {

        T obj = null;
        if (this.list != null) {
            int currIndex = listIterator.nextIndex();
            obj = this.list.get(currIndex);
        } else {
            System.out.println("No element");
        }
        return obj;

    }

    public  ArrayList<T> getPreviousElements(int count) {
        int i = 0;
        T object = null;
         ArrayList<T> linkedList = new  ArrayList<T>();
        if (this.listIterator != null) {
            while (this.listIterator.hasPrevious() && (i < count)) {
                object = this.listIterator.previous();
                linkedList.add(object);
                i++;
            }
        } else {
            System.out.println("No data");
        }

        return linkedList;
    }

    public ArrayList<T> getNextElements(int count){
        int i = 0;
        T object = null;
         ArrayList<T> linkedList = new  ArrayList<T>();
        if (this.listIterator != null) {
            while (this.listIterator.hasNext() && (i < count)) {
                object = this.listIterator.next();
                linkedList.add(object);
                i++;
            }
        } else {
            System.out.println("No data");
        }

        return linkedList;
    }

    public void resetIndex() {
        if (listIterator != null) {
            listIterator = list.listIterator();
        } else {
            System.out.println("No data");
        }
    }
}