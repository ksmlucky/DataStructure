public class MySingleLinkedList<T> {
    public Node<T> head = null;

    public class Node<T> {
        T data;
        Node<T> next = null;

        public Node(T data) {
            this.data = data;
        }

    }

    public void addNode(T data) {
        if (head == null) {
            head = new Node<T>(data);
        } else {
            Node<T> node = this.head;   //node는 현재 헤드로 놓고
            while (node.next != null) {
                node = node.next;
            }
            node.next = new Node<T>(data);
        }
    }

    public  void printAll() {
        if (head != null) {
            Node<T> node = this.head;
            System.out.println(node.data);

            while (node.next != null) {
                node = node.next;
                System.out.println(node.data);

            }
        }
    }

    public Node<T> search(T data) {
        if (this.head == null) {
            return null;
        } else {
            Node<T> node = this.head;
            while (node != null) {
                if (node.data == data) {
                    return node;
                } else {
                    node = node.next;
                }
            }
            return null;
        }
    }

    public void addNodeInside(T data, T isData) {
        Node<T> searchedNode = this.search(isData);

        if (searchedNode == null) {
            this.addNode(data);

        } else {
            Node<T> nextNode = searchedNode.next;
            searchedNode.next = new Node<T>(data);
            searchedNode.next.next = nextNode;
        }
    }

    public boolean delNode(T isData) {
        if (this.head == null) {
            return false;
        } else {
            Node<T> node = this.head;
            if (node.data == isData) {
                this.head = this.head.next; //헤더 가리키는 포인터만 다음 노드로 옮겨주면
                //자바의 가비지 컬렉션이 알아서 주워감
                //따로 삭제하거나 할당 해제?하거나 할 필요 없음
                return true;
            } else {
                while (node.next != null) {
                    if (node.next.data == isData) {
                        node.next = node.next.next; //여기까지 하면 삭제가 된거임
                        return true;
                    }
                    node = node.next;
                }
                return false;   //일치하는 데이터가 없다(삭제할 데이터가 없다)는 뜻에서 false 반환

            }
        }
    }


    public static void main(String[] args) {
        MySingleLinkedList<Integer> msll = new MySingleLinkedList<Integer>();
        msll.addNode(1);
        msll.addNode(2);
        msll.addNode(3);
        msll.addNode(4);
        msll.addNode(5);
        msll.printAll();

        System.out.println();

        msll.addNodeInside(5, 1);
        msll.printAll();

        System.out.println();

        msll.delNode(5);
        msll.printAll();
    }
}
