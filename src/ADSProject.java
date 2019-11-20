    class building{
        private int buildingNum;
        private int executed_time;
        private int total_time;

        public building(int buildingNum,int total_time){
            this.buildingNum=buildingNum;
            this.total_time=total_time;
        }

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

            public void deleteNode(RBTree tree, RBTreeNode node){
                RBTreeNode temp=node;
                String storeColor=node.getColor();
                RBTreeNode pointer=null;
                //if node has no left child, swap with right child
                if(node.getLeftChild()==nil){
                    pointer=node.getRightChild();
                    swap_root(tree,node,node.getRightChild());
                }

                //if node has no right child, swap with left child
                else if(node.getRightChild()==nil){
                    pointer=node.getLeftChild();
                    swap_root(tree,node,node.getLeftChild());
                }

                //if the node has both left child and right child
                else {
                    temp=tree_minimum(node.getRightChild());
                    storeColor=temp.getColor();
                    pointer=temp.getRightChild();
                    if(temp.getParent()==node){
                        pointer.setParent(temp);
                    }
                    else {
                        swap_root(tree,temp,temp.getRightChild());
                        temp.setRightChild(node.getRightChild());
                        temp.getRightChild().setParent(temp);
                    }
                    swap_root(tree,node,temp);
                    temp.setLeftChild(node.getLeftChild());
                    temp.getLeftChild().setParent(pointer);
                    temp.setColor(node.getColor());

                }
                if(storeColor.equals("black")){
                    delete_adjust(tree,pointer);
                }
            }

            public void delete_adjust(RBTree tree, RBTreeNode node){
                RBTreeNode temp=null;
                //while not a root node and is black
                while( node !=tree.root && node.getColor().equals("black")){
                    //node is the left child of its parent
                    if(node==node.getParent().getLeftChild()){
                        //temp becomes brother
                        temp=node.getParent().getRightChild();
                        //case1: brother is red
                        if(temp.getColor().equals("red")){
                            temp.setColor("black");
                            node.getParent().setColor("red");
                            LeftRotation(tree,node.getParent());
                            temp=node.getParent().getRightChild();
                        }
                        //case2: brother and both children are black
                        if(temp.getLeftChild().getColor().equals("black") && temp.getRightChild().getColor().equals("black")){
                            temp.setColor("red");
                            node=node.getParent();
                        }
                        //case3: brother is black, left child is red, right child is black
                        else if (temp.getRightChild().getColor().equals("black")){
                            temp.getLeftChild().setColor("black");

                            temp.setColor("red");

                            RightRotation(tree,temp);

                            temp=node.getParent().getRightChild();
                        }
                        //case4: brother is black, right child is red
                        if (temp.getRightChild().getColor().equals("red")){
                            temp.setColor(node.getParent().getColor());
                            node.getParent().setColor("black");
                            temp.getRightChild().setColor("black");
                            LeftRotation(tree,node.getParent());
                            node=tree.root;
                        }
                    }
                    else {
                        temp=node.getParent().getLeftChild();
                        if(temp.getColor().equals("red")){
                            temp.setColor("black");
                            node.getParent().setColor("red");
                            RightRotation(tree,node.getParent());
                            temp=node.getParent().getLeftChild();
                        }
                        //case2: brother and both children are black
                        if(temp.getRightChild().getColor().equals("black") && temp.getLeftChild().getColor().equals("black")){
                            temp.setColor("red");
                            node=node.getParent();
                        }
                        //case3: brother is black, left child is red, right child is black
                        else if (temp.getLeftChild().getColor().equals("black")){
                            temp.getRightChild().setColor("black");

                            temp.setColor("red");

                            LeftRotation(tree,temp);

                            temp=node.getParent().getRightChild();
                        }
                        //case4: brother is black, right child is red
                        if (temp.getLeftChild().getColor().equals("red")){
                            temp.setColor(node.getParent().getColor());
                            node.getParent().setColor("black");
                            temp.getLeftChild().setColor("black");
                            RightRotation(tree,node.getParent());
                            node=tree.root;
                        }

                    }
                }
                node.setColor("black");
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


            //this is used in deleting a node. replace root of the subtree of oldRoot by newRoot
            public void swap_root(RBTree tree, RBTreeNode oldRoot,  RBTreeNode newRoot){
                //if the old root is the root of the tree
                if(oldRoot.getParent()==nil){
                    tree.root=newRoot;
                }
                //if the oldRoot is the left subtree
                else if(oldRoot==oldRoot.getParent().getLeftChild()){
                    oldRoot.getParent().setLeftChild(newRoot);
                }
                else {
                    oldRoot.getParent().setRightChild(newRoot);
                }

                newRoot.setParent(oldRoot.getParent());

            }

            //find the deepest left child of node
            public RBTreeNode tree_minimum(RBTreeNode node){
                while (node.getLeftChild()!=nil){
                    node=node.getLeftChild();
                }
                return node;
            }


            public void traverse(RBTreeNode node){
                 if(node!=nil){
                     System.out.println(node.getKey());
                     System.out.println(node.getColor());
                     traverse(node.getLeftChild());
                     traverse(node.getRightChild());
                }
            }

            public RBTreeNode findNode(RBTree tree, int key,RBTreeNode pointer){
                if(tree.getRoot(tree)==nil){
                    return null;
                }
                if(key==pointer.getKey()){
                    return pointer;
                }
                else if(key<pointer.getKey()){
                    if(pointer.getLeftChild()!=nil){
                        return findNode(tree,key,pointer.getLeftChild());
                    }
                }
                else if(key>pointer.getKey()){
                    if(pointer.getRightChild()!=nil){
                        return findNode(tree,key,pointer.getRightChild());
                    }
                }

                return null;

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
                 //System.out.println(node9.getLeftChild().getKey());

                 tree.traverse(tree.root);

                 //System.out.println(tree.findNode(tree,6,tree.root).getKey());
                 System.out.println("\n\n");
                 //tree.deleteNode(tree,tree.findNode(tree,6,tree.root));
                 //tree.deleteNode(tree,node3);
                 //RBTreeNode tobedeleted=tree.findNode(tree,5,tree.root);

                 tree.deleteNode(tree,node4);
                 tree.traverse(tree.root);


                 tree.deleteNode(tree,node6);
                 System.out.println("\n\n");
                 tree.traverse(tree.root);
        }

            
            

    }

    class min_heap {
            private building[] min_heap;
            private int Max_size;
            private int current_size=0;
            private int leftChild;
            private int rightChild;

        public int getCurrent_size() {
            return current_size;
        }

        public min_heap(int max_size){
                this.Max_size=max_size;
                min_heap= new building[max_size + 1];
            }

            public building getRoot(){
                return min_heap[0];
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

            public void print(){
                for (int i =0;i<current_size;i++){
                    System.out.println(min_heap[i].getExecuted_time());
                }
            }

            public void modifyRoot(int newTime){
                min_heap[0].setExecuted_time(newTime);
                heapify(0);
            }

            public void insert(building newBuilding) {
                if (current_size >= Max_size) {
                    System.out.println("The size exceeds the max size");
                    return;
                }
                min_heap[current_size] = newBuilding;
                int pos = current_size + 1;
                current_size++;

                while (pos != 1 && newBuilding.getExecuted_time() < min_heap[getParent(pos) - 1].getExecuted_time()) {
                    int indexOfParent = getParent(pos) - 1;
                    building temp = min_heap[indexOfParent];
                    min_heap[indexOfParent] = min_heap[pos - 1];
                    min_heap[pos - 1] = temp;
                }
                //current_size++;
            }
            //check if the node at position pos is a leaf node
            private boolean isLeaf(int pos){
                return pos >= current_size / 2 && pos < current_size;
            }

            private void swap(int a,int b){
                building temp;
                temp =min_heap[a];
                min_heap[a]=min_heap[b];
                min_heap[b]=temp;
            }

            public void removeMin(){
                min_heap[0]=min_heap[current_size-1];
                //min_heap[current_size-1]=null;
                current_size--;
                heapify(0);
            }

            //make a heapify operation at position pos after updating the root or deleting the root

            //first heapify the node at pos, then recursively call this function to heapify the leaves
            public void heapify(int pos){
                if(!isLeaf(pos)){
                    int leftchild=getLeftChild(pos+1)-1;
                    int rightchild=getRightChild(pos+1)-1;
                    if (min_heap[pos].getExecuted_time() > min_heap[leftchild].getExecuted_time()
                            || min_heap[pos].getExecuted_time() > min_heap[rightchild].getExecuted_time()
                    ){
                        if (min_heap[leftchild].getExecuted_time() < min_heap[rightchild].getExecuted_time()){
                            swap(pos,leftchild);
                            heapify(leftchild);
                        }
                        else {
                            swap(pos,rightchild);
                            heapify(rightchild);
                        }
                    }
                }
            }


            public static void main (String args[]){
                min_heap minHeap = new min_heap(15);
                building building1=new building(10,50);
                building1.setExecuted_time(3);
                building building2=new building(10,50);
                building2.setExecuted_time(17);
                building building3=new building(10,50);
                building3.setExecuted_time(10);
                building building4=new building(10,50);
                building4.setExecuted_time(84);
                building building5=new building(10,50);
                building5.setExecuted_time(19);
                building building6=new building(10,50);
                building6.setExecuted_time(6);
                building building7=new building(10,50);
                building7.setExecuted_time(22);
                minHeap.insert(building1);
                //System.out.println(building1.getExecuted_time());
                minHeap.insert(building2);
                //System.out.println(building2.getExecuted_time());
                minHeap.insert(building3);
                minHeap.insert(building4);
                minHeap.insert(building5);
                minHeap.insert(building6);
                minHeap.insert(building7);

                minHeap.print();

                System.out.println("\n\n");

                minHeap.removeMin();

                minHeap.print();





                /*
                minHeap.insert(3);
                minHeap.insert(17);
                minHeap.insert(10);
                minHeap.insert(84);
                minHeap.insert(19);
                minHeap.insert(6);
                minHeap.insert(22);
                minHeap.insert(9);
                */
                //System.out.println("insert complete");
                //minHeap.print();

        }


    }


