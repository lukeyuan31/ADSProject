# ADS Project

**UFID 2144-2855**  
*Keyuan Lu*  
keyuan.lu@ufl.edu

## How to Run the Code
- Unzip the file.  
In the directory of this project, run the command below

```
$ make
```
This will compile the code and generate several .class files.  
Then run the command:

```
$ java risingCity *inputfile_name*
```
The **inputfile_name** will be passed into the program.  

Output is in **output_file.txt** under same directory.

## Structure

Below is the structure of my code.

```
.
├── RBTree.java
├── RBTreeNode.java
├── building.java
├── makefile
├── min_heap.java
├── risingCity.java
```

#### RBTree

Variables:
```java
 private RBTreeNode nil;
 //nil is the nil node introduced in lectures. Construct it as a 
 //node with null leftchild and null rightchild.
 private RBTreeNode root=nil;
 private int length;
```


Methods:

```java
void LeftRotation(RBTree tree, RBTreeNode node){...}
void RightRotation(RBTree tree, RBTreeNode node){...}
public void deleteNode(RBTree tree, RBTreeNode node){...}
public void delete_adjust(RBTree tree, RBTreeNode node){...}
public void insertNode(RBTree tree,RBTreeNode newNode){...}
public void insert_adjust(RBTree tree, RBTreeNode newNode){...}
public void swap_root(RBTree tree, RBTreeNode oldRoot,  RBTreeNode newRoot){...}
public RBTreeNode tree_minimum(RBTreeNode node){...}
public RBTreeNode findNode(RBTree tree, int key,RBTreeNode pointer){...}
public void printBuilding(RBTreeNode pointer,int num1, int num2){...}
```
- **LeftRotation** performs a left rotate at **node** in **tree**.
- **RightRotation** performs a right rotate at **node** in **tree**.
- **deleteNode** deletes the select node.
- **delete_adjust** adjusts the tree after some deletions.
- **insertNode** inserts the selected node into the tree.
- **insert_adjust** fixes the tree after inserting new node.
- **swap_root** replace root of the subtree of oldRoot by newRoot.
- **tree_minimum** return the child node with smallest key of given **node**
- **findNode** finds the node with given key.
- **printBuilding** prints the output all **(buildingNum,executed_time,total_time)** triplets separated by commas in a single line including buildingNum1 and buildingNum2; if they exist. If there is no building in the specified range, output (0,0,0).

#### RBTreeNode
Variables:

```java
private int key;
private RBTreeNode leftChild;
private RBTreeNode rightChild;
private RBTreeNode parent;
private String color;
private int executed_time;
private int total_time;
private building building;
```
**building** is the pointer from red black tree to the building in min heap.

Two types of node are constructed in this class. **nil** and normal node. **nil** is the nil node introduced in lectures.

```
//nil
public RBTreeNode(){}
//normal node
public RBTreeNode(int key,int executed_time, int total_time,RBTreeNode leftChild,RBTreeNode rightChild,RBTreeNode parent,String color){...}

```

#### building
The data structure for each building in this project.

```
private int buildingNum;
private int executed_time;
private int total_time;
```

#### min_heap
The min heap used in this project. Implemented as an array.

```
private building[] min_heap;
private int Max_size;
private int current_size=0;
```

Methods:

```java
public void insert(building newBuilding) {...}
private boolean isLeaf(int pos){...}
private void swap(int a,int b){...}
public building removeMin(){...}
public void heapify(int pos){...}
```
- **insert** inserts the newBuilding into the min heap. The building with smallest **executed_time** is always the root of the min heap. If there is a tie, **building_num** is then used to decide.
- **isLeaf** checks the node at position pos if it is a leaf node.
- **swap** swaps the two nodes at position a and position b
- **removeMin** removes the root from the min heap and returns the root to make build actions.
- **heapify** is used to maintain the property of min heap. If a tie appears in the process, **buildingNum** is considered.


#### risingCity
risingCity is the main loop for this project.
Here is the flow chart of the main loop:
![image](https://raw.githubusercontent.com/lukeyuan31/ADSProject/master/risingCity.png?token=AHHGAOC75FHJOCMWD523UMC54Z36W)
Here is the pseudo code:

```pseudo code
currentBuilding: The building which is being built is temporarily removed from minHeap, 
if it reaches the 5 day period, re-insert into minHeap. If it finished, there's no need to re-insert.
buildDay: The number of days that current building has been built

while (input has next line || rbtree is not null){
    if input is not null{
        read in a line
    }
    
    if (input is not null && globalTime matches){
        if(currentBuilding is being built){
            build for one day
        }
        execute the input message(insert/print)
        if(currentBuilding finished){
            print result
            delete node from rbtree
            removeMin from minHeap if minHeap !=null
        }else if(buildDay==5){
            insert currentBuilding into minheap
            removeMin from minHeap if minHeap !=null
        }
    }
    else{
        if(currentBuilding is not null){
            if(currentBuilding finished){
            print result
            delete node from rbtree
            removeMin from minHeap if minHeap !=null
        }else if(buildDay==5){
            insert currentBuilding into minheap
            removeMin from minHeap if minHeap !=null
         }
        }
        else{
            removeMin from minHeap if minHeap !=null
            build for one day
        }
        
    }
    globalTime++
}
```

## Reference

- https://cloud.tencent.com/developer/article/1392338