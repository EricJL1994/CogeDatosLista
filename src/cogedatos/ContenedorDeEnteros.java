package cogedatos;

public class ContenedorDeEnteros {

    class Nodo {

        Nodo next;
        int value;

        public Nodo(int dato) {
            value = dato;
            next = null;
        }

    }

    private Nodo inicio;
    private int tamaño;

    //Constructor que inicializa el objeto vacío
    public ContenedorDeEnteros() {
    }

    //Devuelve el tamaño de la lista encadenada
    public int cardinal() {
        return tamaño;
    }

    //Método que inserta un nuevo elemento a la lista
    public boolean insertar(int nuevo) {
        Nodo mag = new Nodo(nuevo);
        Nodo current = inicio; //Nodo actual
        Nodo previo = null;   //Nodo anterior actual. Nos hace falta para poder insertar

        if (buscar(nuevo) == true) { //Si el elemento ya está en la lista, no se inserta
            return false;
        }

        while (current != null) { //Recorremos la lista hasta el final
            previo = current;  //Siempre apuntará a la posición anterior a la actual
            current = current.next; //la actual avanza
        }

        if (previo == null) { //En caso de que la lista esté vacía, el elemento pasará a ser el primer
            inicio = mag;  //Por lo tanto el nodo inicio apunta hacia él
        } else {
            previo.next = mag; //En caso contrario, el nodo next del anterior al actual apuntará al nuevo nodo
        }

        mag.next = null; //Como se trata del último nodo insertado, su nodo next deberá apuntar a null
        tamaño++; //Se incrementa el tamaño
        return true;
    }

    //Método para buscar un elemento en la lista
    public boolean buscar(int elem) {
        Nodo current = inicio;
                //Aquí no es necesario el nodo previo

        while (current != null) { //Recorremos la lista
            if (current.value == elem) { //Si encuentra el elemento devuelve verdadero
                return true;
            }

            current = current.next; //Avanza el nodo actual
        }
        return false;
    }

    //Devuelve un vector con los elementos de la lista
    public int[] elementos() {
        Nodo current = inicio;
        int[] vec = new int[tamaño]; //Creamos el vector con la dimensión de la lista
        for (int i = 0; i < tamaño; i++) { //Recorremos el vector y lo rellenamos
            vec[i] = current.value;
            current = current.next;
        }
        return vec;
    }

    //Extrae el elemento pasado por parámetro de la lista encadenada
    public boolean extraer(int elem) {
        Nodo current = inicio;
        Nodo previo = null;

        if (buscar(elem) == false) { //Si el elemento no existe, devuelve falso
            return false;
        }

        if (tamaño == 1 && current.value == elem) { //Comprobamos el caso "tamaño 1"
            inicio = null;
            tamaño--;
            return true;
        }

        if (current.value == elem && current == inicio) { //Comprobamos el caso "eliminar primer elemento de la lista"
            inicio = inicio.next; //Si inicio apunta al siguiente, el primero nodo se pierde
            tamaño--;
            return true;
        }

        while (current.value != elem) {
            previo = current;
            current = current.next;
        }

        previo.next = current.next; //El nodo next del anterior al elemento a eliminar apunta al siguente de dicho elemento
        current = previo.next;     // De esta forma el nodo se pierde
        tamaño--; //Se decrementa el tamaño
        return true;
    }

    //Método para vaciar la lista
    public void vaciar() {
        inicio = null; //De esta manera se pierden todos los nodos
        tamaño = 0;    //El tamaño vuelve a ser 0
    }
}
