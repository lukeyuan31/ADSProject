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

            public void LeftRotation(RBTree tree, RBTreeNode nodex){
                RBTreeNode nodey=nodex.getRightChild();
                nodex.setRightChild(nodey.getLeftChild());
                nodey.getLeftChild().setParent(nodex);
                nodey.setParent(nodex.getParent());
                if(nodey.getParent()==nil){
                    tree.setRoot(tree,nodey);
                }
                else if(nodex.getParent().getLeftChild()==nodex){
                    nodex.getParent().setLeftChild(nodey);
                    }
                else {
                    nodex.getParent().setRightChild(nodey);
                }
                //nodex.setParent(nodey);
                nodey.setLeftChild(nodex);
                nodex.setParent(nodey);
                
            }

            public void RightRotation(RBTree tree, RBTreeNode nodey){
                 RBTreeNode nodex=nodey.getLeftChild();
                 nodey.setLeftChild(nodex.getRightChild());
                 nodex.getRightChild().setParent(nodey);
                 nodex.setParent(nodey.getParent());
                 if(nodey.getParent()==nil){
                     tree.setRoot(tree,nodex);
                 }
                 else if (nodey.getParent().getLeftChild()==nodey){
                     nodey.getParent().setLeftChild(nodex);
                 }
                 else {
                     nodey.getParent().setRightChild(nodex);
                 }

                 nodex.setRightChild(nodey);
                 nodey.setParent(nodex);
                 
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
                    newNode.setParent(insertPointer);
                    if(newNode.getKey()<insertPointer.getKey()){
                        insertPointer.setLeftChild(newNode);
                    }
                    else {
                        insertPointer.setRightChild(newNode);
                    }
                }


                insert_adjust(tree,newNode);

            }

            public void insert_adjust(RBTree tree, RBTreeNode newNode){
                while (newNode.getParent().getColor().equals("red")) {
                    //father is the left child of grandfather
                    if (newNode.getParent()==newNode.getParent().getParent().getLeftChild()){
                        //get the uncle node of new node
                        RBTreeNode uncle=newNode.getParent().getParent().getRightChild();

                        //if the color of the uncle node is red, turn both the parent node and uncle node into black,
                        //turn the color of grandfather node into red.
                        if(uncle.getColor().equals("red")){
                              newNode.getParent().setColor("black");
                              uncle.setColor("black");
                              newNode.getParent().getParent().setColor("red");
                              newNode=newNode.getParent().getParent();
                        }
                        //if the new node is the right child of its parent, perform a left rotation on its parent
                        else if (newNode==newNode.getParent().getRightChild()){
                            newNode=newNode.getParent();
                            LeftRotation(tree,newNode);
                        }
                        else {
                            //new node is the left child of its parent, set father into black, grandfather into red,
                            //perform a rotation on grandfather
                            //System.out.println(newNode.getKey());
                            RBTreeNode grandfather = newNode.getParent().getParent();
                            newNode.getParent().setColor("black");
                            grandfather.setColor("red");
                            RightRotation(tree, grandfather);
                        }
                    }
                    //father is the right child of grandfather
                    else {
                        RBTreeNode uncle=newNode.getParent().getParent().getLeftChild();
                        if(uncle.getColor().equals("red")){
                            newNode.getParent().setColor("black");
                            uncle.setColor("black");
                            newNode.getParent().getParent().setColor("red");
                            newNode=newNode.getParent().getParent();
                        }
                        else if(newNode==newNode.getParent().getLeftChild()){
                            newNode=newNode.getParent();
                            RightRotation(tree,newNode);
                        }
                        else {
                            RBTreeNode grandfather = newNode.getParent().getParent();
                            newNode.getParent().setColor("black");
                            grandfather.setColor("red");
                            LeftRotation(tree, grandfather);
                        }
                    }
                }
                tree.getRoot(tree).setColor("black");
            }

            public void traverse(RBTreeNode node){
                 if(node!=nil){
                     System.out.println(node.getKey());
                     System.out.println(node.getColor());
                     traverse(node.getLeftChild());
                     traverse(node.getRightChild());
                }
            }

            public static void main(String args[]){
                 RBTree tree=new RBTree();
                 RBTreeNode node1=tree.createRBTnode(1);
                 RBTreeNode node2=tree.createRBTnode(4);
                 RBTreeNode node3=tree.createRBTnode(3);
                 RBTreeNode node4=tree.createRBTnode(6);
                 RBTreeNode node5=tree.createRBTnode(9);
                 RBTreeNode node6=tree.createRBTnode(7);
                 RBTreeNode node7=tree.createRBTnode(2);
                 RBTreeNode node8=tree.createRBTnode(5);
                 RBTreeNode node9=tree.createRBTnode(8);
                 tree.insertNode(tree,node1);
                 tree.insertNode(tree,node2);
                 tree.insertNode(tree,node3);
                 tree.insertNode(tree,node4);
                 tree.insertNode(tree,node5);
                 tree.insertNode(tree,node6);
                 tree.insertNode(tree,node7);
                 tree.insertNode(tree,node8);
                 tree.insertNode(tree,node9);
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


