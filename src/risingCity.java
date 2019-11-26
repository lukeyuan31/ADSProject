import java.io.*;
import java.util.Scanner;


public class risingCity {

    public static void main(String args[]) throws IOException {
        int globalTime=0;
        min_heap minHeap=new min_heap(2000);
        RBTree rbTree=new RBTree();
        //String filename="1000days.txt";
        String filename=args[0];
        //String filename="Sample_input1.txt";
        //String filename="input.txt";
        File dir = new File(filename);
        String directory=dir.getAbsolutePath();
        RBTreeNode nil =rbTree.getRoot(rbTree);
        //rbTree.setRoot(rbTree,nil);
        String message = null;
        File output=new File("output_file.txt");
        output.createNewFile();
        BufferedWriter bufferedWriter=new BufferedWriter(new FileWriter(output));
        String temp=";";


        //inputMessage is used to store the command that scanner read in
        String [] inputMessage = new String[0];

        FileInputStream fileInputStream=new FileInputStream(directory);
        Scanner scanner=new Scanner(fileInputStream);

        //currentBuild is the building that is being building.
        building currentBuild = null;
        //buildDay is used to store the time that the build has been built
        int buildDay=0;

        while (scanner.hasNextLine() || rbTree.getLength()>0){
            //while(globalTime<413){
            if (inputMessage.length==0){
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
                int oldTime;
                if (currentBuild!=null){
                    RBTreeNode currentNode=rbTree.findNode(rbTree,currentBuild.getBuildingNum(),rbTree.getRoot(rbTree));
                    oldTime=currentBuild.getExecuted_time();
                    currentBuild.setExecuted_time(oldTime+1);
                    currentNode.setExecuted_time(oldTime+1);
                    buildDay++;
                }
                switch (inputMessage[1]){
                    case "Insert" : {
                        int buildingNum=Integer.parseInt(inputMessage[2]);
                        int totalTime = Integer.parseInt(inputMessage[3]);
                        building newBuilding = new building(buildingNum,totalTime);
                        minHeap.insert(newBuilding);
                        RBTreeNode newNode=rbTree.createRBTnode(buildingNum,0,totalTime);
                        rbTree.insertNode(rbTree,newNode);
                        break;
                    }
                    case "PrintBuilding" : {
                        //System.out.println("Print test");
                        int buildingNum1 = Integer.valueOf(inputMessage[2]);
                        if (inputMessage.length == 3) {
                            //int buildingNum=Integer.valueOf(inputMessage[2]);
                            RBTreeNode display=rbTree.findNode(rbTree,buildingNum1,rbTree.getRoot(rbTree));
                            if (display!=null) {
                                //System.out.println("(" + display.getKey() + "," + display.getExecuted_time() + "," + display.getTotal_time() + ")");
                                temp="(" + display.getKey() + "," + display.getExecuted_time() + "," + display.getTotal_time() + ") \n";
                                bufferedWriter.write(temp);
                                bufferedWriter.flush();
                            }
                            else {
                                //System.out.println("(0,0,0)");
                                temp="(0,0,0) \n";
                                bufferedWriter.write(temp);
                                bufferedWriter.flush();
                            }
                        }
                        if (inputMessage.length == 4) {
                            int buildingNum2 = Integer.valueOf(inputMessage[3]);
                            rbTree.setPrintString("");
                            rbTree.printBuilding(rbTree.getRoot(rbTree),buildingNum1,buildingNum2);
                            String tempResult=rbTree.getPrintString();
                            if (tempResult.length()>0) {
                                //System.out.println(tempResult.substring(0, tempResult.length() - 1));
                                temp=tempResult.substring(0, tempResult.length() - 1);
                                temp=temp+"\n";
                                bufferedWriter.write(temp);
                                bufferedWriter.flush();

                            }
                            else {
                                //System.out.println("(0,0,0)");
                                temp="(0,0,0) \n";
                                bufferedWriter.write(temp);
                                bufferedWriter.flush();
                            }

                        }
                        break;
                    }
                }
                inputMessage=new String[0];
                if (currentBuild!=null) {
                    oldTime = currentBuild.getExecuted_time();
                    if (oldTime == currentBuild.getTotal_time()) {
                        //System.out.println("(" + currentBuild.getBuildingNum() + "," + (globalTime) + ")");
                        temp="(" + currentBuild.getBuildingNum() + "," + (globalTime) + ") \n";
                        bufferedWriter.write(temp);
                        bufferedWriter.flush();
                        RBTreeNode tobeDelete = rbTree.findNode(rbTree, currentBuild.getBuildingNum(), rbTree.getRoot(rbTree));
                        rbTree.deleteNode(rbTree, tobeDelete);
                        if (minHeap.getCurrent_size() > 0) {
                            currentBuild = minHeap.removeMin();
                            buildDay = 0;
                        } else {
                            currentBuild = null;
                            buildDay = 0;
                        }
                    } else if (buildDay == 5) {
                        minHeap.insert(currentBuild);
                        currentBuild = minHeap.removeMin();
                        buildDay = 0;
                    }
                }
            }else {
                if (currentBuild!=null){
                    RBTreeNode currentNode=rbTree.findNode(rbTree,currentBuild.getBuildingNum(),rbTree.getRoot(rbTree));
                    int oldTime=currentBuild.getExecuted_time();
                    //if current building is finished, select a new building to build for one day
                    if ((oldTime+1)==currentBuild.getTotal_time()){
                        //System.out.println("a building is finished");
                        //System.out.println("("+currentBuild.getBuildingNum()+","+(globalTime)+")");
                        temp="("+currentBuild.getBuildingNum()+","+(globalTime)+") \n";
                        bufferedWriter.write(temp);
                        bufferedWriter.flush();
                        RBTreeNode tobeDelete=rbTree.findNode(rbTree,currentBuild.getBuildingNum(),rbTree.getRoot(rbTree));
                        rbTree.deleteNode(rbTree,tobeDelete);
                        if (minHeap.getCurrent_size()>0) {
                            currentBuild = minHeap.removeMin();
                            buildDay=0;
                        }
                        else {
                            currentBuild=null;
                            buildDay=0;
                        }


                    }

                    //if current building has been built for 5 days, re-insert it and select a new building
                    else if(buildDay==4){
                        //System.out.println("reach 5 days");
                        oldTime=currentBuild.getExecuted_time();
                        currentBuild.setExecuted_time(oldTime+1);
                        currentNode.setExecuted_time(oldTime+1);
                        if (currentBuild.getExecuted_time()==currentBuild.getTotal_time()){
                            //System.out.println("("+currentBuild.getBuildingNum()+","+(globalTime)+")");
                            temp="("+currentBuild.getBuildingNum()+","+(globalTime)+") \n";
                            bufferedWriter.write(temp);
                            bufferedWriter.flush();
                            RBTreeNode tobeDelete=rbTree.findNode(rbTree,currentBuild.getBuildingNum(),rbTree.getRoot(rbTree));
                            rbTree.deleteNode(rbTree,tobeDelete);
                            if (minHeap.getCurrent_size()>0) {
                                currentBuild = minHeap.removeMin();
                            }else {
                                currentBuild=null;
                            }
                        }
                        else {
                            minHeap.insert(currentBuild);
                            currentBuild = minHeap.removeMin();
                        }
                        //currentBuild.setExecuted_time(currentBuild.getExecuted_time()+1);
                        buildDay=0;
                    }
                    else {
                        //System.out.println(globalTime+"building day");
                        currentBuild.setExecuted_time(oldTime+1);
                        currentNode.setExecuted_time(oldTime+1);
                        buildDay++;
                    }
                }
                else if(currentBuild==null){
                    if (minHeap.getCurrent_size()==0){
                        //System.out.println("heap is empty");
                        //do nothing
                    }
                    else if (minHeap.getCurrent_size()>0){

                        currentBuild=minHeap.removeMin();
                        int oldTime=currentBuild.getExecuted_time();
                        currentBuild.setExecuted_time(oldTime+1);
                        RBTreeNode currentNode=rbTree.findNode(rbTree,currentBuild.getBuildingNum(),rbTree.getRoot(rbTree));
                        currentNode.setExecuted_time(oldTime+1);

                        buildDay=1;
                    }
                }

            }



            //check if current building is finished or reach 5 days



            globalTime++;
            //System.out.println(globalTime);
        }
    }
}
