class RBTreeNode {
        private int key;
        private RBTreeNode leftChild;
        private RBTreeNode rightChild;
        private RBTreeNode parent;
        private String color;
        private int executed_time;
        private int total_time;
        //private int length;

        //Construct nil nodes
        public RBTreeNode(){
            this.color="black";
            this.leftChild=null;
            this.rightChild=null;
            this.parent=null;
            this.key=0;

        }

        public RBTreeNode(int key,int executed_time, int total_time,RBTreeNode leftChild,RBTreeNode rightChild,RBTreeNode parent,String color){
            this.leftChild=leftChild;
            this.rightChild=rightChild;
            this.parent=parent;
            this.color=color;
            this.key=key;
            this.executed_time=executed_time;
            this.total_time=total_time;
        }

    public int getTotal_time() {
        return total_time;
    }

    public int getExecuted_time() {
        return executed_time;
    }

    public void setTotal_time(int total_time) {
        this.total_time = total_time;
    }

    public void setExecuted_time(int executed_time) {
        this.executed_time = executed_time;
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
