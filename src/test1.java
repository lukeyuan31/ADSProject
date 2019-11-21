import java.io.*;
import java.util.Scanner;


public class test1 {

    public static void main(String args[]) throws FileNotFoundException {
        int globalTime=0;
        min_heap minHeap=new min_heap(2000);
        RBTree rbTree=new RBTree();
        //String filename="Sample_input1.txt";
        String filename="input.txt";
        File dir = new File(filename);
        String directory=dir.getAbsolutePath();
        RBTreeNode nil =rbTree.getRoot(rbTree);
        //rbTree.setRoot(rbTree,nil);
        String message = null;

        //inputMessage is used to store the command that scanner read in
        String [] inputMessage = new String[0];

        FileInputStream fileInputStream=new FileInputStream(directory);
        Scanner scanner=new Scanner(fileInputStream);

        //currentBuild is the building that is being building.
        building currentBuild = null;
        //buildDay is used to store the time that the build has been built
        int buildDay=0;

        while (scanner.hasNextLine() || globalTime<100){
            if (inputMessage.length==0){
                //System.out.println(globalTime+"getting input");
                if (scanner.hasNextLine()) {
                    message = scanner.nextLine();
                }
                inputMessage = message.split("[:(),]");
                //use .trim() to delete the empty space near every word
                for (int i=0; i<inputMessage.length; i++){
                    inputMessage[i]=inputMessage[i].trim();
                }
            }
            if (inputMessage.length>0 && globalTime==Integer.parseInt(inputMessage[0])){
                //System.out.println(globalTime+"executing command");
                switch (inputMessage[1]){
                    case "Insert" : {
                        int buildingNum=Integer.parseInt(inputMessage[2]);
                        int totalTime = Integer.parseInt(inputMessage[3]);
                        building newBuilding = new building(buildingNum,totalTime);
                        minHeap.insert(newBuilding);
                        RBTreeNode newNode=rbTree.createRBTnode(buildingNum);
                        rbTree.insertNode(rbTree,newNode);
                        //System.out.println("insert complete!");
                        //minHeap.print();
                        break;
                    }
                    case "Print" : {
                        System.out.println("Print test");
                    }
                }
                inputMessage=new String[0];
            }

            //check if current building is finished or reach 5 days
            if (currentBuild!=null){
                int oldTime=currentBuild.getExecuted_time();
                //if current building is finished, select a new building to build for one day
                if (oldTime==currentBuild.getTotal_time()){
                    //System.out.println("a building is finished");
                    System.out.println("("+currentBuild.getBuildingNum()+","+(globalTime-1)+")");
                    RBTreeNode tobeDelete=rbTree.findNode(rbTree,currentBuild.getBuildingNum(),rbTree.getRoot(rbTree));
                    rbTree.deleteNode(rbTree,tobeDelete);
                    currentBuild=minHeap.removeMin();
                    currentBuild.setExecuted_time(oldTime+1);

                    System.out.println(currentBuild.getExecuted_time());
                    minHeap.print();
                    System.out.println();
                    //minHeap.print();
                    buildDay=1;
                }

                //if current building has been built for 5 days, re-insert it and select a new building
                else if(buildDay==5){
                    //System.out.println("reach 5 days");
                    minHeap.insert(currentBuild);
                    currentBuild=minHeap.removeMin();
                    currentBuild.setExecuted_time(currentBuild.getExecuted_time()+1);
                    buildDay=1;
                    System.out.println(currentBuild.getExecuted_time());
                    minHeap.print();
                    System.out.println();
                }
                else {
                    //System.out.println(globalTime+"building day");
                    currentBuild.setExecuted_time(oldTime+1);
                    buildDay++;
                    System.out.println(currentBuild.getExecuted_time());
                    minHeap.print();
                    System.out.println();
                }
            }
            else if(currentBuild==null){
                if (minHeap.getCurrent_size()==0){
                    //System.out.println("heap is empty");
                    //do nothing
                    break;
                }
                else if (minHeap.getCurrent_size()>0){

                    currentBuild=minHeap.removeMin();
                    System.out.println(currentBuild.getExecuted_time());
                    minHeap.print();
                    System.out.println();
                }
            }
            globalTime++;
        }
    }
}
