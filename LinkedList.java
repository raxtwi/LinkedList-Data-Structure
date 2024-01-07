package datastructureshw;

// Enis Buğra BULUT 
public class LinkedList<AnyType> {

    private Node<AnyType> head;
    private Node<AnyType> tail;
    private int theSize;

    public LinkedList() {
        clear();
    }

    public AnyType getData(int index) {
        if (index < 0 || index >= theSize) {
            throw new IndexOutOfBoundsException("Index " + index + "; size " + theSize);
        }

        return getNode(index).data;
    }

    public AnyType setData(int index, AnyType newValue) {
        if (index < 0 || index >= theSize) {
            throw new IndexOutOfBoundsException("Index " + index + "; size " + theSize);
        }

        AnyType oldValue = getNode(index).data;
        getNode(index).setData(newValue);
        return oldValue;
    }

    public boolean add(AnyType x) {
        if (theSize == 0) {
            Node<AnyType> firstNode = new Node<>(null, x, null);
            head = firstNode;
            tail = firstNode;
            theSize++;
        } else {
            Node<AnyType> n = new Node<>(tail, x, null);
            n.prev.next = n;
            tail = n;
            theSize++;
        }

        return true;
    }

    public void add(int index, AnyType x) {
        if (index < 0 || index > theSize) {
            throw new IndexOutOfBoundsException("Index " + index + "; size " + theSize);
        }

        if (index == 0) {
            if (theSize == 0) {
                Node<AnyType> firstNode = new Node<>(null, x, null);
                head = firstNode;
                tail = firstNode;
                theSize++;
            } else {
                Node<AnyType> a = new Node<>(null, x, head);
                head.prev = a;
                head = a;
                theSize++;
            }
        } else if (index == theSize) {
            Node<AnyType> a = new Node<>(tail, x, null);
            tail.next = a;
            tail = a;
            theSize++;
        } else {
            Node<AnyType> a = new Node<>(getNode(index).prev, x, getNode(index));
            getNode(index).prev.next = a;
            getNode(index).prev = a;
            theSize++;
        }
    }

    public void remove(int index) {
        if (index < 0 || index >= theSize) {
            throw new IndexOutOfBoundsException("Index " + index + "; size " + theSize);
        }

        if (index == 0) {
            Node<AnyType> temp = head;
            head = temp.next;
            head.prev = null;
            temp.next = null;
            theSize--;
        } else if (index == theSize - 1) {
            Node<AnyType> temp = tail;
            tail = temp.prev;
            tail.next = null;
            temp.prev = null;
            theSize--;
        } else {
            getNode(index).prev.next = getNode(index).next;
            getNode(index).next.prev = getNode(index).prev;
            theSize--;
        }
    }

    public int size() {
        return theSize;
    }

    public boolean isEmpty() {
        return theSize == 0;
    }

    public void clear() {
        theSize = 0;
    }

    @Override
    public String toString() {
        String rStr = "[ ";

        Node<AnyType> temp = head;
        for (int i = 0; i < theSize; i++) {
            rStr = rStr + temp.data + " ";
            temp = temp.next;
        }
        rStr = rStr + "]";

        return rStr;
    }

    private Node<AnyType> getNode(int index) {
        Node<AnyType> p;

        if (index <= theSize / 2) {
            p = head;
            for (int i = 0; i < index; i++) {
                p = p.next;
            }
        } else {
            p = tail;
            for (int i = theSize - 1; i > index; i--) {
                p = p.prev;
            }
        }

        return p;
    }

    public void removeDuplicate() {
        Node current = head, index = null, temp = null;
        if (head == null) {
            System.out.println("List is empty !");
        } else {
            while (current != null) {
                temp = current;
                index = current.next;
                while (index != null) {
                    if (temp.data == index.data) {
                        if(temp.next == index){
                            if(index.next == null){
                                temp.next = null;
                                temp = tail;
                                theSize--;
                            } else {
                                temp.next = index.next;
                                index.next.prev = temp;
                                theSize--;
                            }
                        } else {
                            if(index.next == null){
                                index.prev.next = null;
                                index.prev = tail;
                                theSize--;
                                
                            } else {
                                index.prev.next = index.next;
                                index.next.prev = index.prev;
                                theSize--;
                            }
                        }
                    } 
                    index = index.next;
                }
                current = current.next;
            }
        }
    }

    private class Node<AnyType> {

        private Node<AnyType> prev;
        private AnyType data;
        private Node<AnyType> next;

        public Node(Node<AnyType> p, AnyType d, Node<AnyType> n) {
            prev = p;
            data = d;
            next = n;
        }

        private void setData(AnyType newData) {
            this.data = newData;
        }
    }
}
