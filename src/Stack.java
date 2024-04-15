public class Stack {


     int size ;
    LinkedList l;

    Stack(){
      l=new LinkedList();
      size=0;
    }

    void push ( Object element ){
        l.addFirst(element);
        size++;
    }


    Object pop (){

        if(isEmpty()){
            System.out.println("Empty");
            return null;
        }else {
            Object first = l.getFirst();
            l.removeFirst();
            size--;
            return first;
        }
    }

    Object top (){
        return l.getFirst();
    }

    boolean isEmpty(){
        return size==0 ;
    }

    void printStack(){
  /*     int s =size;
       Stack temp = new Stack();
        while (s!=0){
             Object p = pop();
             temp.push(p);
            System.out.println(p);
            push(p);
            s--;
        }
        s = size;
        while (s!=0){
            push(temp.pop());
            s--;
        }*/
        l.displaylist();
    }

}
