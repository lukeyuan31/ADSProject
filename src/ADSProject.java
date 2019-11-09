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

            public void insert(int element){
                if(current_size>=Max_size) {
                    System.out.println("The size exceeds the max size");
                    return;
                }
                min_heap[current_size]=element;
                int pos=current_size+1;
                current_size++;

                while(pos!=1 && element<min_heap[getParent(pos)-1]){
                    int indexOfParent=getParent(pos)-1;
                    int temp=min_heap[indexOfParent];
                    min_heap[indexOfParent]=min_heap[pos-1];
                    min_heap[pos-1]=temp;
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
