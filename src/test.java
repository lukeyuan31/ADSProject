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
        String filename="input.txt";
        File dir = new File(filename);
        String directory=dir.getAbsolutePath();
        RBTreeNode nil =new RBTreeNode();
        rbTree.setRoot(rbTree,nil);
        String message;


        FileInputStream fileInputStream=new FileInputStream(directory);
        Scanner scanner=new Scanner(fileInputStream);

        while (globalTime!=0 || minHeap.getCurrent_size()!=0){
            String [] inputMessage = new String[0];
            if(scanner.hasNextLine()){
                message=scanner.nextLine();
                inputMessage = message.split("[:(),]");
                for (int i=0; i<inputMessage.length; i++){
                    inputMessage[i]=inputMessage[i].trim();
                }

            }

            if(Integer.parseInt(inputMessage[0])==globalTime){
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
            else {
                int oldTime=minHeap.getRoot().getExecuted_time();
                minHeap.modifyRoot(oldTime+5);
                globalTime++;
            }


        }
        rbTree.traverse(rbTree.getRoot(rbTree));


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
