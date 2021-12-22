/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cpcs324_phase2;


import java.util.Scanner;
import java.util.Random;


public class CPCS324_Phase2 {

    
    
    //constant =infinity
     final static int INFT=  1000000000;
    //helps in printing verices label
    static char[] vertexLabel = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};
    
    
    
    public static void main(String[] args) {
        
      //  int graph[][];
        //reading user choice
        Scanner input=new Scanner (System.in);
        
       //vetrice number variable  
       int  Vnumber;
     
       
        // graph matrix 
                         //A     B     C    D    E   F     G    H    I    J              
       int graph[][] ={{ 0  , 10 ,INFT,INFT,INFT, 5  ,INFT,INFT,INFT,INFT},//A
                          {INFT,  0 , 3  ,INFT,  3 ,INFT,INFT,INFT,INFT,INFT},//B
                          {INFT,INFT, 0  ,  4 ,INFT,INFT,INFT,  5 ,INFT,INFT},//C
                          {INFT,INFT,INFT,  0 ,INFT,INFT,INFT,INFT, 4  ,INFT},//D
                          {INFT,INFT, 4  ,INFT,  0 ,INFT, 2  ,INFT,INFT,INFT},//E
                          {INFT,  3 ,INFT,INFT,INFT, 0  ,INFT,INFT,INFT,  2 },//F
                          {INFT,INFT,INFT,  7 ,INFT,INFT, 0  ,INFT,INFT,INFT},//G
                          {INFT,INFT,INFT,  4 ,INFT,INFT,INFT,  0 , 3  ,INFT},//H
                          {INFT,INFT,INFT,INFT,INFT,INFT,INFT,INFT, 0  ,INFT},//I
                          {INFT, 6  ,INFT,INFT,INFT,INFT, 8  ,INFT,INFT,  0 } //J
                        };
                         
           Vnumber=10;   

            
            //for run time analysis
         /*  int c [][]=  generateGraph(Vnumber);

             for (int i = 0; i < Vnumber; i++) {
                 for (int j = 0; j < Vnumber; j++) {
                     if(c[i][j]== 0 && i!=j)
                         c[i][j]=INFT;



                 }

             }

            graph=c;*/
                         
                         
       
       
      
        
        
        
        System.out.println("********************WELCOME ***************************");
        System.out.println("TO RUN FloyedWarshall ALGOROTHM ENTER 1  ");
        System.out.println("TO RUN DIJKSTRA ALGOROTHM ENTER 2  ");
         int choice=input.nextInt();
         
         
         
         
        switch(choice) {
             
             case 1:{
                ////////////////////////////////////////////////////////////////////////////floyed algorithm 
               //print weight graph befor applyin the alg
                    System.out.println("\t\tThe Weight Matrix D(0)\t\t");  
                    System.out.println();        
                     printDistanceMatrix (graph, Vnumber);
                    System.out.println();   

                    //start the timer
                   long sTime = System.currentTimeMillis();

                    FloyedWarshall(graph , Vnumber);
                    //end the timer
                    long fTime = System.currentTimeMillis();

                   System.out.println("Time in milllis =" +(fTime-sTime)+"ms");
        
              break;
             }
              case 2:{
                ////////////////////////////////////////////////////////////////////////////dijkstra algorit
                     //start the timer
                    double sTime  = System.currentTimeMillis();
                    dijkstrasShortestPath(graph, 0,Vnumber);
                     //end the timer
                    double fTime = System.currentTimeMillis();
                    System.out.println();
                    System.out.println("Time in milllis =" +(fTime-sTime)+"ms");
                 break;
              }
                  
         
         }
       
        
        
    
    
    
        }
    
 //  FloyedWarshall algorithm 
    public static int [][] FloyedWarshall (int distanceMatrix [][] ,int Vnumber){
        int i, j, k ,x=0;
           
            //  FloyedWarshall algorithm 
           for (k = 0; k < Vnumber; k++){
           
                  for (i = 0; i < Vnumber; i++){

                       for (j = 0; j < Vnumber; j++){
                           
                             if (distanceMatrix[i][k] +distanceMatrix[k][j] < distanceMatrix[i][j])
                              distanceMatrix[i][j] = distanceMatrix[i][k] + distanceMatrix[k][j];
                         }
                       
                       
                    }
                  //for printing purposes
                  if(k == Vnumber-1){
                      System.out.println("\t\tThe Distance Matrix  D(" +(Vnumber) +")\t\t");
                      System.out.println();
                      printDistanceMatrix (distanceMatrix , Vnumber);
                  
                  }
                  else{
                          System.out.println("\t\tIntermediate Matrix For iteration D(" +(++x) +")\t\t");
                          System.out.println();
                          printDistanceMatrix (distanceMatrix,  Vnumber);
                      
                  }
                 
                   System.out.println();
                   System.out.println();
             }
           
           
            return distanceMatrix;
    }
    
    
    
    
    
    //print the matrix
    public static void printDistanceMatrix (int [][] distanceMatrix, int Vnumber){
        
        //print veretex label horizantlly
        for (int i=0; i<Vnumber; ++i){
            System.out.print( "\t"+ vertexLabel[i] );   
           }
        System.out.println();
        
        
         for (int i=0; i<Vnumber; ++i)
        {//print veretex label vertically
            System.out.print(vertexLabel[i] + "\t" );
            //print matrix elements
            for (int j=0; j<Vnumber; ++j)
            { 
                if (distanceMatrix[i][j]==INFT){
                    System.out.print("∞" + (Vnumber-1 == j? "\n" :"\t" ));}
                    
                else
                    System.out.print(distanceMatrix[i][j] + (Vnumber-1 == j? "\n" :"\t" ));
            }
        
        }
    
    }
    
    //return the index of smallest vertex
    public static int getClosestVertex(int[] distance, boolean[] visited){

	int min = INFT;
	int minIdx = -1;
	for(int i=0; i<distance.length; i++)
	{
          
		if(distance[i] < min){
               
			if(visited[i] == false){
			
				min = distance[i];
				minIdx = i;
                 }	}
                 
	}
        
	return minIdx;
}
    
    
   public static void dijkstrasShortestPath(int [][] g, int src,int Vnumber)
{
	// shortest distance array
	int[] distance = new int[Vnumber];
	//array to tell whether shortest distance of vertex has been found
	boolean[] visited = new boolean[Vnumber];
        //store shortest path
        int parent []=new int [Vnumber];
        
	parent[src]=-1;	
	//initializing the arrays
	for(int i=0; i<Vnumber; i++){
            distance[i] = INFT;//initial distance is infinite
            visited[i] = false;//shortest distance for any node has not been found yet
	}
        
        //source vertex
	distance[src] = 0;
		
	for(int i=0; i<Vnumber; i++){
                //get the closest vertex
		int closestVertex = getClosestVertex(distance, visited);
              
		
                if(closestVertex ==-1 )
                    break;
		
                //mark as visited
		visited[closestVertex] = true;
		for(int j=0; j<Vnumber; j++){
                        
                        
			if(visited[j] == false){
			
				if(g[closestVertex][j] != 0){
				
					int d = distance[closestVertex] + g[closestVertex][j];
                                        //distance via closestVertex is less than the initial distance
					if(d < distance[j]){
						distance[j] = d;
                                                parent[j]=closestVertex;
                                        }
                                
                                
                                }
			}		
		}
                
     
	}
        
        
    printSolution(src,distance, parent);
}
   
   private static void printSolution(int source,  int[] distances,int[] parents){
                                     
        int nVertices = distances.length;
        System.out.print("Vertex\t\tDistance\tPath");
         
        for (int vertexIndex = 0;vertexIndex < nVertices;vertexIndex++){
            if (vertexIndex != source){
            //just to print orginal graph with labels
              if(distances.length==10){
                System.out.print("\n" + vertexLabel[source] + " -> ");
                System.out.print(vertexLabel[vertexIndex] + " \t\t ");
              
              }
              //  System.out.print("\n" + source + " -> ");
              //  System.out.print(vertexIndex + " \t\t ");
                if(distances[vertexIndex]!=INFT){
                    System.out.print(distances[vertexIndex] + "\t\t");
                    printPath(vertexIndex, parents);
                }
                else{
                    System.out.print("∞" + "\t\t");
                    System.out.print("no path" + "\t\t");
                
                  }
            
            }
        }
    }
   
   //print path 
    private static void printPath(int currentVertex, int[] parents){
           
        // Base case : Source node has
        // been processed
        if (currentVertex == -1)
            return;
        printPath(parents[currentVertex], parents);
        System.out.print(vertexLabel[currentVertex] + " ");
       // System.out.print(currentVertex + " ");
    }
    
    
    
    //randomly generate graph with give vertices #
    public static int [][] generateGraph(int vNum){
        //graph matrix
        int [][] graph =new int [vNum][vNum];
        //generate random number
        Random random=new Random ();
        //v=source ,u=destnation ,w=weight
        int v,u,w,eNum;
        //number of edges 
        eNum=vNum*3;
        
        
        for (int i = 0; i < eNum; i++) {
            v=random.nextInt(vNum);
            
            u=random.nextInt(vNum);
         
            w=random.nextInt(vNum);
            //if self loop =0
            if(u ==  v)
                    graph[v][u]=0;
            
            else {
                  //adding edge will not create cycle  
                  if(! CheckCycle(graph,v,u,vNum))  
                    graph[v][u]=w;
                  //to make directed graph
                    graph[u][v]=INFT;
                    
                    
                
                }    
        }
    
    
    
    
    return graph;
    }
    //chrck if adding edge will create cycle
    public static boolean CheckCycle(int [][] graph,int v,int u,int vNum){
    
        for (int i = 0; i < vNum; i++) {
            //if there is edge between destnation vert
            if(graph[u][i] !=0 && graph[v][i] != INFT){
                //if the edge connected to destnation is connected to source 
                if(graph[i][v] !=0 && graph[v][i] != INFT )
                    //edge will create cycle 
                    return true;
            
            
            
            }
                
            
        }
    
    
    
    return false;
    }
    
    
    
    
    

}


    
       
 











