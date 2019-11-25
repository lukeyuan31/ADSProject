import java.io.*;
import java.util.Scanner;


public class test2{
    int globalTime;
    //currentBuild is the building that is being building.
    building currentBuild;
    //buildDay is used to store the time that the build has been built
    int buildDay;

    RBTree rbTree;

    min_heap minHeap;

    public void buildABuilding(){
        if (currentBuild!=null){
            RBTreeNode currentNode=rbTree.findNode(rbTree,currentBuild.getBuildingNum(),rbTree.getRoot(rbTree));
            int oldTime=currentBuild.getExecuted_time();
            if (buildDay<5){
                currentBuild.setExecuted_time(oldTime+1);
                currentNode.setExecuted_time(oldTime+1);
                buildDay++;
            }
        }
        else {
            if (minHeap.getCurrent_size()>0){
                currentBuild = minHeap.removeMin();
            }
            else {
                currentBuild=null;
            }
        }
    }

    public void checkBuildingFinish(){
        if (currentBuild.getExecuted_time()==currentBuild.getTotal_time()){
            System.out.println("("+currentBuild.getBuildingNum()+","+(globalTime)+")");
            RBTreeNode tobeDelete=rbTree.findNode(rbTree,currentBuild.getBuildingNum(),rbTree.getRoot(rbTree));
            rbTree.deleteNode(rbTree,tobeDelete);
        }
        if (minHeap.getCurrent_size()>0){
            currentBuild = minHeap.removeMin();
        }else {
            currentBuild=null;
        }
    }

    public void FiveDaysCheck(){
        if (buildDay==5 && currentBuild!=null){
            minHeap.insert(currentBuild);
            currentBuild = minHeap.removeMin();
        }
    }

    public static void main(String args[]) throws FileNotFoundException {
        test2 test=new test2();
        test.globalTime=0;
        test.minHeap=new min_heap(2000);
        test.rbTree=new RBTree();

        String filename="Sample_input2.txt";
        File dir = new File(filename);
        String directory=dir.getAbsolutePath();
        RBTreeNode nil=test.rbTree.getRoot(test.rbTree);
        String message=null;
        String [] inputMessage = new String[0];

        FileInputStream fileInputStream=new FileInputStream(directory);
        Scanner scanner=new Scanner(fileInputStream);
        test.currentBuild = null;

        test.buildDay = 0;

        while(scanner.hasNextLine() || test.rbTree.getLength()>0){
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

            if (inputMessage.length>0 && test.globalTime==Integer.parseInt(inputMessage[0])){
                test.buildABuilding();
                switch (inputMessage[1]){
                    case "Insert":{
                        int buildingNum=Integer.parseInt(inputMessage[2]);
                        int totalTime = Integer.parseInt(inputMessage[3]);
                        building newBuilding = new building(buildingNum,totalTime);
                        test.minHeap.insert(newBuilding);
                        RBTreeNode newNode=test.rbTree.createRBTnode(buildingNum,0,totalTime);
                        test.rbTree.insertNode(test.rbTree,newNode);
                        //System.out.println("insert complete!");
                        //minHeap.print();
                        break;
                    }
                    case "PrintBuilding" : {
                        //System.out.println("Print test");
                        int buildingNum1 = Integer.valueOf(inputMessage[2]);
                        if (inputMessage.length == 3) {
                            //int buildingNum=Integer.valueOf(inputMessage[2]);
                            RBTreeNode display=test.rbTree.findNode(test.rbTree,buildingNum1,test.rbTree.getRoot(test.rbTree));
                            if (display!=null) {
                                System.out.println("(" + display.getKey() + "," + display.getExecuted_time() + "," + display.getTotal_time() + ")");
                            }
                            else {
                                System.out.println("(0,0,0)");
                            }
                        }
                        if (inputMessage.length == 4) {
                            int buildingNum2 = Integer.valueOf(inputMessage[3]);
                            test.rbTree.setPrintString("");
                            test.rbTree.printBuilding(test.rbTree.getRoot(test.rbTree),buildingNum1,buildingNum2);
                            String tempResult=test.rbTree.getPrintString();
                            System.out.println(tempResult.substring(0,tempResult.length()-1));

                        }
                        break;
                    }
                }
                inputMessage=new String[0];
                test.checkBuildingFinish();
                test.FiveDaysCheck();
                test.globalTime++;
            }
            else {
                test.buildABuilding();
                test.checkBuildingFinish();
                test.FiveDaysCheck();
            }
        }
    }

}