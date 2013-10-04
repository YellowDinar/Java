import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;


//     |1|
//     / \
//   2/   \3
//   /	   \   3
// |0|     |3|---|4|
//   \	   /
//	 3\   /1
//	   \ /
//     |2|
//
//
//	   0_1_2_3_4
//	0| 0 2 3 0 0
//	1| 0 0 0 3 0
//	2| 0 0 0 1 0
//	3| 0 0 0 0 3
//	4| 0 0 0 0 0

public class Main {
	static int MAX_VAL=0;  //ìàêñèìàëüíîå çíà÷åíèå ïîòîêà êîòîðûé ìîæåò âûõîäèòü èç íà÷àëüíîé òî÷êè s
	static int C[][]={{0,2,3,0,0},{0,0,0,3,0},{0,0,0,1,0},{0,0,0,0,3},{0,0,0,0,0}};    // Ìàòðèöà "ïðîïóñêíûõ ñïîñîáíîñòåé"
	static int F[][]={{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0}};    // Ìàòðèöà "òåêóùåãî ïîòîêà â ãðàôå"
	static int push[];        // Ïîòîê â âåðøèíó [v] èç íà÷àëüíîé òî÷êè
	static int mark[];        // Îòìåòêè íà âåðøèíàõ, â êîòîðûõ ïîáûâàëè
	static int pred[];        // Îòêóäà ïðèøëè â âåðøèíó [v] (ïðåäîê)
	static int N=5, M=5;         // Êîë-âî âåðøèí, ðåáåð, íà÷àëüíàÿ è êîíå÷íûå òî÷êè
	static int s=0;
	static int t=4;
	static int max_flow;

	static void init() //îáíóëÿåì ìàññèâû
	 {
		mark=new int[N];
		push=new int[N];
		pred=new int[N];
		for(int i = 0; i < N; i++){
			mark[i] = 0;
	        push[i] = 0;
	        pred[i] = 0;
	    }
	 }

	// Àëãîðèòì Áåëëìàíà-Ôîðäà

	static int bfs(int s, int t)
	 {
	     init();
	     Queue<Integer> Q = new LinkedList<Integer>();
	     mark[s] = 1; //ñòàâèò ìàðêó 1, çíà÷èò ïîñåòèëè âåðøèíó ïîñåòèëè
	     pred[s] = s; //ïðåäûäóùèé ýëåìåíò
	     
	     for(int i=0;i<N;i++){
	    	MAX_VAL+=C[s][i];  //ñ÷èòàåì ñóììó ìàêñèìàëüíîãî ïîòîêà èç íà÷àëüíîé âåðøèíû
	     }
	     push[s] = MAX_VAL;  //ïóñêàåì ìàêñèìàëüíûé ïîòîê â ñòàðòîâóþ âåðøèíó
	     
	     Q.add(s);  //äîáàâëÿåì íîìåð âåðøèíû â î÷åðåäü
	     while( mark[t]==0 && !Q.isEmpty() ){ 
	         int u = Q.peek(); //áåðåì ïåðâóþ âåðøèíó
	         Q.poll(); //óäàëÿåì åå èç î÷åðåäè
	         for(int v=1; v<N; v++)
	             if(mark[v]==0 && (C[u][v]-F[u][v] > 0)){  //åñëè íåò ìàðêè è ðàçíîñòü ìåæäó ïðîïóñêíîé ñïîñîáíîñòüþ è ïîòîêîì ïîëîæèòåëüíà
	                 push[v] = Math.min(push[u], C[u][v]-F[u][v]); //âûáèðàåì ìèíèìàëüíóþ âåëè÷èíó ìåæäó âõîäÿùèì ïîòîêîì è âîçìîæíîñòüþ èñõîäÿùåãî ïîòîêà
	                 mark[v] = 1; //ñòàâèì ìàðêó
	                 pred[v] = u; //ïðèñâàèâàåì ïðåäûäóùóþ
	                 Q.add(v); //äîâàâëÿåì â î÷åðåäü
	             }
	     }
	     return mark[t]; //âîçâðàùàåì ìàðêó ñòîêà, íàõóÿ íå ïîíÿòíî:D
	     
	 }

	// Àëãîðèòì Ôîðäà-Ôàëêåðñîíà

	static void max_flow_ff(){
	     int u,v,flow = 0; //îáíóëÿåì ïîòîê è äâå âåðøèíû ñ êîòîðûìè ðàáîòàåì
	     
         int add = push[t];  //äîáàâëÿåì ïîòîê

         v = t; u = pred[v]; //èäåì â îáðàòíóþ ñòîðîíó îò ñòîêà ê èñòî÷íèêó
         while( v != s )
         {
             F[u][v]+=add;
             F[v][u]-=add;
             v=u; u=pred[v];
         }
         flow+=add;

	     max_flow = flow; 
	}
	

	public static void main(String args[]){
	   bfs(s,t);
	   max_flow_ff();
	   System.out.println();
	   for(int i=0;i<N;i++){
		   for(int j=0;j<N;j++){
			   System.out.print(F[i][j]+" ");
		   }
		   System.out.println();
	   }
	}
}
