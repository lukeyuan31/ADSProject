    class building{
        private int buildingNum;
        private int executed_time;
        private int total_time;

        public int getBuildingNum() {
            return buildingNum;
        }

        public int getExecuted_time() {
            return executed_time;
        }

        public int getTotal_time() {
            return total_time;
        }

        public void setBuildingNum(int buildingNum) {
            this.buildingNum = buildingNum;
        }

        public void setExecuted_time(int executed_time) {
            this.executed_time = executed_time;
        }

        public void setTotal_time(int total_time) {
            this.total_time = total_time;
        }
    }

    class RBTreeNode {
            private int key;
            private RBTreeNode leftChild;
            private RBTreeNode rightChild;
            private RBTreeNode parent;
            private String color;

            //Construct nil nodes
            public RBTreeNode(){
                this.color="black";
                this.leftChild=null;
                this.rightChild=null;
                this.parent=null;
                this.key=0;

            }

            public RBTreeNode(int key,RBTreeNode leftChild,RBTreeNode rightChild,RBTreeNode parent,String color){
                this.leftChild=leftChild;
                this.rightChild=rightChild;
                this.parent=parent;
                this.color=color;
                this.key=key;
            }

        public RBTreeNode getLeftChild() {
            return leftChild;
        }

        public RBTreeNode getParent() {
            return parent;
        }

        public RBTreeNode getRightChild() {
            return rightChild;
        }

        public String getColor() {
            return color;
        }

        public int getKey(){
            return key;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public void setLeftChild(RBTreeNode leftChild) {
            this.leftChild = leftChild;
        }

        public void setParent(RBTreeNode parent) {
            this.parent = parent;
        }

        public void setRightChild(RBTreeNode rightChild) {
            this.rightChild = rightChild;
        }

        public void setKey(int key){
                this.key=key;
        }
    }

    class RBTree {
            private RBTreeNode nil=new RBTreeNode();
            private RBTreeNode root=new RBTreeNode();

            public RBTree(){
                 root=nil;
            }

            public RBTreeNode getRoot(RBTree tree){
                return tree.root;
            }

            public void setRoot(RBTree tree,RBTreeNode node){
                tree.root=node;
            }

            public RBTreeNode createRBTnode(int key){
                RBTreeNode newNode=new RBTreeNode(key,nil,nil,nil,"red");
                return newNode;
            }

            public void insertNode(RBTree tree,RBTreeNode newNode){
                //find the correct place to insert the new node
                RBTreeNode pointer=getRoot(tree);
                RBTreeNode insertPointer=nil;
                if (pointer==nil){
                    tree.root=newNode;
                }
                else {
                    while(pointer!=nil){
                        insertPointer=pointer;
                       if(newNode.getKey()<pointer.getKey()){
                            pointer=pointer.getLeftChild();
                       }
                       else {
                           pointer=pointer.getRightChild();
                       }
                    }
                }
                newNode.setParent(insertPointer);
                if(newNode.getKey()<insertPointer.getKey()){
                    insertPointer.setLeftChild(newNode);
                }
                else {
                    insertPointer.setRightChild(newNode);
                }
                
            }

            public void traverse(RBTreeNode node){
                 if(node!=nil){
                     System.out.println(node.getKey());
                     traverse(node.getLeftChild());
                     traverse(node.getRightChild());
                }
            }

            public static void main(String args[]){
                 RBTree tree=new RBTree();
                 RBTreeNode node1=tree.createRBTnode(3);
                 RBTreeNode node2=tree.createRBTnode(1);
                 RBTreeNode node3=tree.createRBTnode(4);
                 RBTreeNode node4=tree.createRBTnode(2);
                 RBTreeNode node5=tree.createRBTnode(7);
                 RBTreeNode node6=tree.createRBTnode(8);
                 RBTreeNode node7=tree.createRBTnode(5);
                 tree.insertNode(tree,node1);
                 tree.insertNode(tree,node2);
                 tree.insertNode(tree,node3);
                 tree.insertNode(tree,node4);
                 tree.insertNode(tree,node5);
                 tree.insertNode(tree,node6);
                 tree.insertNode(tree,node7);
                 tree.traverse(tree.root);
        }

            
            

    }

    class min_heap {
            private int[] min_heap;
            private int Max_size;
            private int current_size=0;
            private int leftChild;
            private int rightChild;
            
            public min_heap(int max_size){
                this.Max_size=max_size;
                min_heap=new int[max_size+1];
            }

            private int getParent(int index){
                return index / 2;
            }

            private int getLeftChild(int index){
                return index *2;
            }

            private int getRightChild(int index){
                return index*2+1;
            }

            private void print(){
                for (int i =0;i<current_size;i++){
                    System.out.println(min_heap[i]);
                }
            }

            public void insert(int element) {
                if (current_size >= Max_size) {
                    System.out.println("The size exceeds the max size");
                    return;
                }
                min_heap[current_size] = element;
                int pos = current_size + 1;
                current_size++;

                while (pos != 1 && element < min_heap[getParent(pos) - 1]) {
                    int indexOfParent = getParent(pos) - 1;
                    int temp = min_heap[indexOfParent];
                    min_heap[indexOfParent] = min_heap[pos - 1];
                    min_heap[pos - 1] = temp;
                }
                //current_size++;
            }


            public static void main (String args[]){
                min_heap minHeap = new min_heap(15);
                minHeap.insert(5);
                minHeap.insert(3);
                minHeap.insert(17);
                minHeap.insert(10);
                minHeap.insert(84);
                minHeap.insert(19);
                minHeap.insert(6);
                minHeap.insert(22);
                minHeap.insert(9);

                System.out.println("insert complete");
                minHeap.print();

        }


    }


