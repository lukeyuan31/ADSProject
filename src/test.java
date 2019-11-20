import java.io.*;

import java.util.ArrayList;
import java.util.Scanner;


public class test {
    /*
    public ArrayList readIn(String filename){
        File dir = new File(filename);
        String directory=dir.getAbsolutePath();
        String message;
        ArrayList messages=new ArrayList();
        try{
            FileInputStream fileInputStream=new FileInputStream(directory);
            Scanner scanner=new Scanner(fileInputStream);
            while (scanner.hasNextLine()){
                message=scanner.nextLine();
                String[] inputMessage = message.split("[:(),]");
                messages.add(inputMessage);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return messages;
    }

     */

    public static void main(String[] args) throws FileNotFoundException {

        int globalTime=0;
        min_heap minHeap=new min_heap(2000);
        RBTree rbTree=new RBTree();
        String filename="Sample_input1.txt";
        //String filename="input.txt";
        File dir = new File(filename);
        String directory=dir.getAbsolutePath();
        RBTreeNode nil =rbTree.getRoot(rbTree);
        //rbTree.setRoot(rbTree,nil);
        String message;


        FileInputStream fileInputStream=new FileInputStream(directory);
        Scanner scanner=new Scanner(fileInputStream);

        int buildingDay=0;

        //while (globalTime==0 || rbTree.getRoot(rbTree)!=nil){
          while (globalTime==0 || rbTree.getRoot(rbTree)!=nil){
            String [] inputMessage = new String[0];
            if(scanner.hasNextLine()){
                message=scanner.nextLine();
                inputMessage = message.split("[:(),]");
                for (int i=0; i<inputMessage.length; i++){
                    inputMessage[i]=inputMessage[i].trim();
                }

            }

            while (inputMessage.length>0 && globalTime<Integer.parseInt(inputMessage[0])){
                //System.out.println(globalTime+"building day");
                if(minHeap.getCurrent_size()!=0) {
                    int oldTime = minHeap.getRoot().getExecuted_time();
                    if (oldTime < minHeap.getRoot().getTotal_time() && buildingDay < 5) {
                        //minHeap.modifyRoot(oldTime+1);
                        if ((oldTime + 1) == minHeap.getRoot().getTotal_time()) {
                            System.out.println("(" + minHeap.getRoot().getBuildingNum() + "," + globalTime + ")");
                            minHeap.removeMin();
                            buildingDay = 0;
                        } else {
                            buildingDay++;
                            minHeap.getRoot().setExecuted_time(oldTime + 1);
                        }
                    } else if (buildingDay == 5) {
                        //buildingDay=0;
                        minHeap.heapify(0);
                        building newBuilding = minHeap.getRoot();
                        int newTime = newBuilding.getExecuted_time();
                        newBuilding.setExecuted_time(newTime + 1);
                        buildingDay = 1;
                    }
                }
                //minHeap.print();
                //System.out.println();
                globalTime++;
            }

            if (inputMessage.length>0 && Integer.parseInt(inputMessage[0])==globalTime){
                //System.out.println(globalTime+"command day");
                if (minHeap.getCurrent_size()!=0) {
                    int oldTime = minHeap.getRoot().getExecuted_time();
                    if (oldTime < minHeap.getRoot().getTotal_time() && buildingDay < 5) {
                        //minHeap.modifyRoot(oldTime+1);
                        if ((oldTime + 1) == minHeap.getRoot().getTotal_time()) {
                            System.out.println("(" + minHeap.getRoot().getBuildingNum() + "," + globalTime + ")");
                            minHeap.removeMin();
                            buildingDay = 0;
                        } else {
                            buildingDay++;
                            minHeap.getRoot().setExecuted_time(oldTime + 1);
                        }
                    } else if (buildingDay == 5) {
                        //buildingDay=0;
                        minHeap.heapify(0);
                        building newBuilding = minHeap.getRoot();
                        int newTime = newBuilding.getExecuted_time();
                        newBuilding.setExecuted_time(newTime + 1);
                        buildingDay = 1;
                    }
                    //minHeap.print();
                    //System.out.println();
                }
                switch (inputMessage[1]){
                    case "Insert":{
                        int buildingNum=Integer.parseInt(inputMessage[2]);
                        int totalTime = Integer.parseInt(inputMessage[3]);
                        building newBuilding = new building(buildingNum,totalTime);
                        minHeap.insert(newBuilding);
                        RBTreeNode newNode=rbTree.createRBTnode(buildingNum);
                        rbTree.insertNode(rbTree,newNode);
                        //System.out.println("insert complete!");
                        break;
                    }
                    case "PrintBuilding":{
                        int buildingNum1 = Integer.valueOf(inputMessage[2]);
                        if (inputMessage.length == 3) {
                            //int buildingNum=Integer.valueOf(inputMessage[2]);

                        }
                        if (inputMessage.length == 4) {
                            int buildingNum2 = Integer.valueOf(inputMessage[3]);

                        }
                        //int startTime=Integer.valueOf(inputMessage[0]);
                        //System.out.println("print complete!");
                        break;
                    }
                }
                globalTime++;
            }

            while (inputMessage.length==0 && rbTree.getRoot(rbTree)!=nil){
                //System.out.println("final days"+globalTime);
                int oldTime=minHeap.getRoot().getExecuted_time();
                if (oldTime<minHeap.getRoot().getTotal_time() && buildingDay<5){
                    //minHeap.modifyRoot(oldTime+1);
                    if ((oldTime+1)==minHeap.getRoot().getTotal_time()){
                        System.out.println("("+minHeap.getRoot().getBuildingNum()+","+globalTime+")");
                        RBTreeNode pointer=rbTree.findNode(rbTree,minHeap.getRoot().getBuildingNum(),rbTree.getRoot(rbTree));
                        minHeap.removeMin();

                        rbTree.deleteNode(rbTree,pointer);
                        buildingDay=0;
                    }else {
                        buildingDay++;
                        minHeap.getRoot().setExecuted_time(oldTime + 1);
                    }
                }
                else if(buildingDay==5){
                    //buildingDay=0;
                    minHeap.heapify(0);
                    building newBuilding=minHeap.getRoot();
                    int newTime=newBuilding.getExecuted_time();
                    //newBuilding.setExecuted_time(newTime+1);
                    //buildingDay=1;
                    if ((newTime+1)==minHeap.getRoot().getTotal_time()){
                        System.out.println("("+minHeap.getRoot().getBuildingNum()+","+globalTime+")");
                        RBTreeNode pointer=rbTree.findNode(rbTree,minHeap.getRoot().getBuildingNum(),rbTree.getRoot(rbTree));
                        minHeap.removeMin();

                        rbTree.deleteNode(rbTree,pointer);
                        buildingDay=0;
                    }else {
                        buildingDay=1;
                        minHeap.getRoot().setExecuted_time(newTime + 1);
                    }
                }
                //minHeap.print();
                //System.out.println();
                globalTime++;
            }

            /*
            if(inputMessage.length>0 && Integer.parseInt(inputMessage[0])==globalTime){
                System.out.println(globalTime);
                System.out.println("entering switch");
                switch (inputMessage[1]){
                    case "Insert":{
                        int buildingNum=Integer.parseInt(inputMessage[2]);
                        int totalTime = Integer.parseInt(inputMessage[3]);
                        building newBuilding = new building(buildingNum,totalTime);
                        minHeap.insert(newBuilding);
                        //RBTreeNode newNode=new RBTreeNode(buildingNum,nil,nil,nil,"red");
                        RBTreeNode newNode=rbTree.createRBTnode(buildingNum);
                        rbTree.insertNode(rbTree,newNode);
                        System.out.println("insert complete!");
                        break;
                    }
                    case "PrintBuilding": {
                        int buildingNum1 = Integer.valueOf(inputMessage[2]);
                        if (inputMessage.length == 3) {
                            //int buildingNum=Integer.valueOf(inputMessage[2]);

                        }
                        if (inputMessage.length == 4) {
                            int buildingNum2 = Integer.valueOf(inputMessage[3]);

                        }
                        //int startTime=Integer.valueOf(inputMessage[0]);
                        System.out.println("print complete!");
                        break;
                    }
                }
            }
            else {
                while (globalTime<Integer.parseInt(inputMessage[0])) {
                    System.out.println(globalTime);
                    int oldTime = minHeap.getRoot().getExecuted_time();
                    int newTime = oldTime + 5;
                    if (newTime >= minHeap.getRoot().getExecuted_time()) {
                        int buildingNum = minHeap.getRoot().getBuildingNum();
                        RBTreeNode tobeDeleted = rbTree.findNode(rbTree, buildingNum, rbTree.getRoot(rbTree));
                        rbTree.deleteNode(rbTree, tobeDeleted);
                        minHeap.removeMin();
                        //rbTree.deleteNode();
                    } else {
                        minHeap.modifyRoot(oldTime + 5);
                    }
                    globalTime++;
                }
            }*/

        //rbTree.traverse(rbTree.getRoot(rbTree));
           //minHeap.print();
        }
        //rbTree.traverse(rbTree.getRoot(rbTree));


        /*
        String message;
        int startTime;
        Scanner input=new Scanner(System.in);
        System.out.println("start testing");
        String filename=input.nextLine();
        //File file=new File(filepath);
        File dir = new File(filename);
        String directory =dir.getAbsolutePath();
        RBTreeNode nil = new RBTreeNode();
        */

        /*
        try {
            FileInputStream fileInputStream=new FileInputStream(directory);
            Scanner scanner=new Scanner(fileInputStream);
            min_heap minHeap = new min_heap(2000);
            RBTree rbTree = new RBTree();
            while (scanner.hasNextLine()) {
                message = scanner.nextLine();
                String[] inputMessage = message.split("[:(),]");



                for (int i = 0; i < inputMessage.length; i++) {
                    inputMessage[i] = inputMessage[i].trim();
                    System.out.println(inputMessage[i]);
                }

                startTime = Integer.valueOf(inputMessage[0]);
                switch (inputMessage[1]) {
                    case "Insert": {
                        //int startTime=Integer.valueOf(inputMessage[0]);
                        int buildingNum = Integer.valueOf(inputMessage[2]);
                        int totalTime = Integer.valueOf(inputMessage[3]);
                        building newBuilding = new building(buildingNum,totalTime);
                        minHeap.insert(newBuilding);
                        //RBTreeNode newNode=new RBTreeNode(buildingNum,nil,nil,nil,"red");
                        RBTreeNode newNode=rbTree.createRBTnode(buildingNum);
                        rbTree.insertNode(rbTree,newNode);
                        //System.out.println(buildingNum);
                    }
                    case "PrintBuilding": {
                        int buildingNum1 = Integer.valueOf(inputMessage[2]);
                        if (inputMessage.length == 3) {
                            //int buildingNum=Integer.valueOf(inputMessage[2]);

                        }
                        if (inputMessage.length == 4) {
                            int buildingNum2 = Integer.valueOf(inputMessage[3]);

                        }
                        //int startTime=Integer.valueOf(inputMessage[0]);

                    }

                }
            }
            rbTree.traverse(rbTree.getRoot(rbTree));
            scanner.close();
            fileInputStream.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        */

    }

}
