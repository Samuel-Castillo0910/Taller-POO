package Main;

public final class GameUtils {

public static Object[] resizeArray(Object[] array, int newSize){
    Object[] newArray = new Object[newSize];

    for(int i=0; i < array.length; i++){
        newArray[i] = array[i];
    }

    return newArray;
}

}
