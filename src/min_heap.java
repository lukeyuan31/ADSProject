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
            min_heap= new building[max_size ];
            building dummy=new building(Integer.MAX_VALUE,Integer.MIN_VALUE);
            min_heap[0]=dummy;
        }

        public building getRoot(){
            return min_heap[1];
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
            for (int i =1;i<=current_size;i++){
                System.out.println("("+min_heap[i].getExecuted_time()+","+min_heap[i].getBuildingNum()+","+min_heap[i].getTotal_time()+")");
                //System.out.println(min_heap[i].getBuildingNum());
            }
        }

        public void modifyRoot(int newTime){
            min_heap[1].setExecuted_time(newTime);
            heapify(1);
        }

        public void insert(building newBuilding) {
            if (current_size >= Max_size) {
                System.out.println("The size exceeds the max size");
                return;
            }
            current_size++;
            min_heap[current_size] = newBuilding;
            int pos = current_size;

            //the parent of new building
            //building parentBuilding=min_heap[getParent(pos)-1];

            while (pos!=1 &&newBuilding.getExecuted_time() <= min_heap[getParent(pos)].getExecuted_time()) {

                if (newBuilding.getExecuted_time() == min_heap[getParent(pos)].getExecuted_time()) {
                    if (newBuilding.getBuildingNum() < min_heap[getParent(pos)].getBuildingNum()) {
                        int indexOfParent = getParent(pos) ;
                        building temp = min_heap[indexOfParent];
                        min_heap[indexOfParent] = min_heap[pos];
                        min_heap[pos] = temp;
                        pos = getParent(pos);
                    } else {
                        //pos = getParent(pos);
                        break;
                    }
                } else {
                    int indexOfParent = getParent(pos);
                    building temp = min_heap[indexOfParent];
                    min_heap[indexOfParent] = min_heap[pos];
                    min_heap[pos] = temp;
                    pos = getParent(pos);

                }
            }
            //System.out.println("insert complete");
            }
            //current_size++;

        //check if the node at position pos is a leaf node
        private boolean isLeaf(int pos){
            return ((pos > (current_size / 2)) && (pos) <= current_size);
        }

        private void swap(int a,int b){
            building temp;
            temp =min_heap[a];
            min_heap[a]=min_heap[b];
            min_heap[b]=temp;
        }

        public building removeMin(){
            building currentBuilding=min_heap[1];
            min_heap[1]=min_heap[current_size];
            //min_heap[current_size-1]=null;
            current_size--;
            if (current_size>0) {
                heapify(1);
            }
            return currentBuilding;
        }

        //make a heapify operation at position pos after updating the root or deleting the root

        //first heapify the node at pos, then recursively call this function to heapify the leaves
        public void heapify(int pos){

            if(current_size==0){
                //do nothing
            }
            //System.out.println(min_heap.length);
            else if(current_size==2){
                if(min_heap[1].getExecuted_time()>min_heap[2].getExecuted_time()){
                    swap(1,2);
                }
                else if (min_heap[1].getExecuted_time()==min_heap[2].getExecuted_time()){
                    if (min_heap[1].getBuildingNum()>min_heap[2].getBuildingNum()){
                        swap(1,2);
                    }
                }
            }
            else if(!isLeaf(pos)){
                int leftchild=getLeftChild(pos);
                int rightchild=getRightChild(pos);
                /*
                if (min_heap[rightchild]==null ){
                    if (min_heap[pos].getExecuted_time()>min_heap[leftchild].getExecuted_time()){
                        swap(pos,leftchild);
                    }
                }

                 */
                if (min_heap[pos].getExecuted_time() > min_heap[leftchild].getExecuted_time()
                        || min_heap[pos].getExecuted_time() > min_heap[rightchild].getExecuted_time())
                {
                    /*
                    if(min_heap[rightchild]==null){
                        swap(pos,leftchild);
                    }
                    */
                     if (min_heap[leftchild].getExecuted_time() < min_heap[rightchild].getExecuted_time()){
                        swap(pos,leftchild);
                        heapify(leftchild);
                    }
                    else {
                        swap(pos,rightchild);
                        heapify(rightchild);
                    }
                }
                else if (min_heap[pos].getExecuted_time()==min_heap[leftchild].getExecuted_time()
                        || min_heap[pos].getExecuted_time()==min_heap[rightchild].getExecuted_time()){
                    if (min_heap[leftchild].getExecuted_time()>min_heap[rightchild].getExecuted_time()){
                        if (min_heap[rightchild].getBuildingNum() < min_heap[pos].getBuildingNum()){
                            swap(pos,rightchild);
                            heapify(rightchild);
                        }
                    }
                    else if (min_heap[leftchild].getExecuted_time() < min_heap[rightchild].getExecuted_time()){
                        if (min_heap[leftchild].getBuildingNum() < min_heap[pos].getBuildingNum()){
                            swap(pos,leftchild);
                            heapify(leftchild);
                        }
                    }
                    else {
                        if (min_heap[pos].getBuildingNum() > min_heap[leftchild].getBuildingNum()
                        ||  min_heap[pos].getBuildingNum() > min_heap[rightchild].getBuildingNum()){
                            if (min_heap[leftchild].getBuildingNum()< min_heap[rightchild].getBuildingNum()){
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
            }
        }


        public static void main (String args[]){


            min_heap minHeap = new min_heap(15);
            building building1=new building(76,50);
            building1.setExecuted_time(10);
            building building2=new building(4,50);
            building2.setExecuted_time(10);
            building building3=new building(56,50);
            building3.setExecuted_time(10);
            building building4=new building(34,50);
            building4.setExecuted_time(10);
            building building5=new building(1,50);
            building5.setExecuted_time(10);
            building building6=new building(67,50);
            building6.setExecuted_time(10);
            building building7=new building(22,50);
            building7.setExecuted_time(10);
            building building8=new building(5765,50);
            building8.setExecuted_time(10);
            building building9=new building(3,50);
            building9.setExecuted_time(10);
            building building10=new building(45,50);
            building10.setExecuted_time(10);




            minHeap.insert(building1);
            //System.out.println(building1.getExecuted_time());
            minHeap.insert(building2);
            //System.out.println(building2.getExecuted_time());
            minHeap.insert(building3);
            minHeap.insert(building4);
            minHeap.insert(building5);
            minHeap.insert(building6);
            minHeap.insert(building7);
            minHeap.insert(building8);
            minHeap.insert(building9);
            minHeap.insert(building10);
            //minHeap.removeMin();
            //minHeap.min_heap[4]=null;
            //minHeap.swap(0,1);
            minHeap.print();
            System.out.println();
            //minHeap.heapify(0);
            minHeap.removeMin();

            minHeap.print();
            System.out.println();
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
