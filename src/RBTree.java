class RBTree {
            private RBTreeNode nil=new RBTreeNode();
            private RBTreeNode root=nil;
            private int length=0;
            private String printString;


            public RBTree(){
                 root=nil;
            }

            public RBTreeNode getRoot(RBTree tree){
                return tree.root;
            }

            public void setRoot(RBTree tree,RBTreeNode node){
                tree.root=node;
            }

            public RBTreeNode createRBTnode(int key, int executed_time, int total_time){
                RBTreeNode newNode=new RBTreeNode(key,executed_time,total_time,nil,nil,nil,"red");
                return newNode;
            }

            public int getLength() {
                 return length;
            }

            public String getPrintString() {
            return printString;
            }

            public void setPrintString(String printString) {
            this.printString = printString;
            }


            //perform the left rotate at node in tree.
            void LeftRotation(RBTree tree, RBTreeNode node){
                if (node.getParent()!=nil){
                    if (node==node.getParent().getLeftChild()){
                        node.getParent().setLeftChild(node.getRightChild());
                    }
                    else {
                        node.getParent().setRightChild(node.getRightChild());
                    }
                    node.getRightChild().setParent(node.getParent());
                    node.setParent(node.getRightChild());
                    if (node.getRightChild().getLeftChild()!=nil){
                        node.getRightChild().getLeftChild().setParent(node);
                    }
                    node.setRightChild(node.getRightChild().getLeftChild());
                    node.getParent().setLeftChild(node);
                }
                else {
                    RBTreeNode right = root.getRightChild();
                    root.setRightChild(right.getLeftChild());
                    right.getLeftChild().setParent(root);
                    root.setParent(right);
                    right.setLeftChild(root);
                    right.setParent(nil);
                    root=right;
                }
            }

            //perform the right rotate for node in tree
            void RightRotation(RBTree tree, RBTreeNode node){
                if (node.getParent()!=nil){
                    if (node==node.getParent().getLeftChild()){
                        node.getParent().setLeftChild(node.getLeftChild());
                    }
                    else {
                        node.getParent().setRightChild(node.getLeftChild());
                    }

                    node.getLeftChild().setParent(node.getParent());
                    node.setParent(node.getLeftChild());

                    if (node.getLeftChild().getRightChild()!=nil){
                        node.getLeftChild().getRightChild().setParent(node);
                    }

                    node.setLeftChild(node.getLeftChild().getRightChild());
                    node.getParent().setRightChild(node);
                }
                else {
                    RBTreeNode left=root.getLeftChild();
                    root.setLeftChild(root.getLeftChild().getRightChild());
                    left.getRightChild().setParent(root);
                    root.setParent(left);
                    left.setRightChild(root);
                    left.setParent(nil);
                    root=left;
                }
            }

            /*
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

             */
            //delete the select node
            public void deleteNode(RBTree tree, RBTreeNode node){
                length--;
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
                    //node.getLeftChild().setParent(temp);
                    temp.setLeftChild(node.getLeftChild());

                    temp.getLeftChild().setParent(temp);
                    temp.setColor(node.getColor());

                }
                //if the color of the deleted node is black, delete_adjust should be performed
                if(storeColor.equals("black")){
                    delete_adjust(tree,pointer);
                }
            }


            //after deleting the node, in some situations the tree should be adjusted to maintain red black tree
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
                            continue;
                        }
                        //case3: brother is black, left child is red, right child is black
                        else if (temp.getRightChild().getColor().equals("black")){
                            temp.getLeftChild().setColor("black");

                            temp.setColor("red");

                            RightRotation(tree,temp);

                            temp=node.getParent().getRightChild();
                        }
                        //case4: brother is black, right child is red
                        if (temp.getRightChild().getColor().equals("red")) {
                            temp.setColor(node.getParent().getColor());
                            node.getParent().setColor("black");
                            temp.getRightChild().setColor("black");
                            LeftRotation(tree, node.getParent());
                            node = tree.getRoot(tree);
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
                            continue;
                        }
                        //case3: brother is black, left child is red, right child is black
                        else if (temp.getLeftChild().getColor().equals("black")){
                            temp.getRightChild().setColor("black");

                            temp.setColor("red");

                            LeftRotation(tree,temp);

                            temp=node.getParent().getLeftChild();
                        }
                        //case4: brother is black, right child is red
                        if (temp.getLeftChild().getColor().equals("red"))
                            temp.setColor(node.getParent().getColor());
                            node.getParent().setColor("black");
                            temp.getLeftChild().setColor("black");
                            RightRotation(tree,node.getParent());
                            node=tree.getRoot(tree);


                    }
                }
                node.setColor("black");
            }



            //insert the newNode into the tree
            public void insertNode(RBTree tree,RBTreeNode newNode){
                //find the correct place to insert the new node
                length++;
                RBTreeNode pointer=root;
                RBTreeNode insertPointer=nil;
                if (pointer==nil){
                    tree.root=newNode;
                    newNode.setColor("black");
                    newNode.setParent(nil);
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

            //after inserting the newNode, the tree should be adjusted
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
                              continue;
                        }
                        //if the new node is the right child of its parent, perform a left rotation on its parent
                        if (newNode==newNode.getParent().getRightChild()){
                            newNode=newNode.getParent();
                            LeftRotation(tree,newNode);
                        }

                            //new node is the left child of its parent, set father into black, grandfather into red,
                            //perform a rotation on grandfather
                            //System.out.println(newNode.getKey());
                            RBTreeNode grandfather = newNode.getParent().getParent();
                            newNode.getParent().setColor("black");
                            grandfather.setColor("red");
                            RightRotation(tree, grandfather);

                    }
                    //father is the right child of grandfather
                    else {
                        RBTreeNode uncle=newNode.getParent().getParent().getLeftChild();
                        if(uncle.getColor().equals("red")){
                            newNode.getParent().setColor("black");
                            uncle.setColor("black");
                            newNode.getParent().getParent().setColor("red");
                            newNode=newNode.getParent().getParent();
                            continue;
                        }
                        if(newNode==newNode.getParent().getLeftChild()){
                            newNode=newNode.getParent();
                            RightRotation(tree,newNode);
                        }

                            RBTreeNode grandfather = newNode.getParent().getParent();
                            newNode.getParent().setColor("black");
                            grandfather.setColor("red");
                            LeftRotation(tree, grandfather);

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
            /*
            public int count(RBTreeNode node){

                if (node!=nil){
                    length++;
                    count(node.getLeftChild());
                    count(node.getRightChild());
                }
                return length;
            }

             */
            //find and return the certain node for int key. if not found, null is returned
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


            //print the triplet from num1 to num2
            public void printBuilding(RBTreeNode pointer,int num1, int num2){
                //printString="";
                if (pointer!=nil){
                    printBuilding(pointer.getLeftChild(),num1,num2);
                    if (num1<=pointer.getKey() && pointer.getKey()<=num2){
                        printString=printString+"("+pointer.getKey()+","+pointer.getExecuted_time()+","+pointer.getTotal_time()+"),";
                    }
                    printBuilding(pointer.getRightChild(),num1,num2);
                }
                //return result;
            }



            //this main method is used to test the correctness when implementing red black tree.
            public static void main(String args[]){
                 RBTree tree=new RBTree();
                 RBTreeNode node1=tree.createRBTnode(1,0,40);

                 RBTreeNode node2=tree.createRBTnode(4,0,23);
                 RBTreeNode node3=tree.createRBTnode(3,0,43);
                 RBTreeNode node4=tree.createRBTnode(6,0,2);
                 RBTreeNode node5=tree.createRBTnode(9,0,4);
                 RBTreeNode node6=tree.createRBTnode(7,0,23);
                 RBTreeNode node7=tree.createRBTnode(2,0,1);
                 RBTreeNode node8=tree.createRBTnode(5,0,12);
                 RBTreeNode node9=tree.createRBTnode(8,0,12);
                RBTreeNode node10=tree.createRBTnode(10,0,12);
                RBTreeNode node11=tree.createRBTnode(11,0,12);
                RBTreeNode node12=tree.createRBTnode(12,0,12);
                RBTreeNode node13=tree.createRBTnode(13,0,12);

                 tree.insertNode(tree,node1);
                 tree.insertNode(tree,node2);
                 tree.insertNode(tree,node3);
                 tree.insertNode(tree,node4);
                 tree.insertNode(tree,node5);
                 tree.insertNode(tree,node6);
                 tree.insertNode(tree,node7);
                 tree.insertNode(tree,node8);
                 tree.insertNode(tree,node9);
                tree.insertNode(tree,node10);
                tree.insertNode(tree,node11);
                tree.insertNode(tree,node12);
                tree.insertNode(tree,node13);
                 //System.out.println(node9.getLeftChild().getKey());

                 tree.traverse(tree.root);
                System.out.println("\n\n");
                 System.out.println(tree.length);

                 //System.out.println(tree.findNode(tree,6,tree.root).getKey());
                 System.out.println("\n\n");
                 //tree.deleteNode(tree,tree.findNode(tree,6,tree.root));
                 //tree.deleteNode(tree,node3);
                 //RBTreeNode tobedeleted=tree.findNode(tree,5,tree.root);

                 tree.deleteNode(tree,node6);
                 tree.traverse(tree.root);
                System.out.println(tree.length);


                // tree.deleteNode(tree,node6);
                 System.out.println("\n\n");
                 tree.traverse(tree.root);
        }

            
            

    }


