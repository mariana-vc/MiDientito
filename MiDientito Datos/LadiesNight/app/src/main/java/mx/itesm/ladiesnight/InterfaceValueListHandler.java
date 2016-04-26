package mx.itesm.ladiesnight;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by beeme on 03/04/2016.
 */
public interface InterfaceValueListHandler<T> {

    public void setList(ArrayList<T> list);
    public ArrayList<T> getList();
    public int getSize();
    public T getCurrentElement();
    public ArrayList<T> getPreviousElements(int count);
    public ArrayList<T> getNextElements(int count);
    public void resetIndex();

}
