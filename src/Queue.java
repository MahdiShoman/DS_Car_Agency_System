public class Queue {

    int size;
    LinkedList l ;

    Queue(){
        l = new LinkedList();
    }

    boolean isEmpty(){
        return l.isEmpty();
    }

    void enqueue(Object x){
        l.addLast(x);
        size++;
    }
    Object dequeue(){

        if(isEmpty()){
            System.out.println("Empty");
            return null;
        }


        Object o = l.getFirst();
        l.removeFirst();
        size--;
        return o  ;

    }
    Object objectOut (){
        if(isEmpty()){
            System.out.println("Empty");
            return null;
        }
        return  l.getFirst() ;
    }

    void printQueue(){
        int sSize =size;
        while (sSize!=0){
        Object s= dequeue();
        System.out.println(s);
        enqueue(s);
        sSize--;
        }
        if (isEmpty())
            System.out.println("the queue is empty");
       // l.displaylist();
    }
    void printQueueToFile(){
        int sSize =size;
        while (sSize!=0){
            Object s= dequeue();
            System.out.println(s);
            enqueue(s);
            sSize--;
        }
        if (isEmpty())
            System.out.println ("the queue is empty");
        // l.displaylist();
    }


}