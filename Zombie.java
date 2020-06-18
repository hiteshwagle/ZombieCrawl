import java.util.*; 
public class Arraylist { 
	public static void main(String[] args) 
	{ 
		int n = 4; 
		String moves = "DLUURR";
      	List<Integer> initPosition =new ArrayList<Integer>();
      	initPosition.add(2);
      	initPosition.add(1);
     
		List<List<Integer> > aList = new ArrayList<List<Integer> >(); 

		ArrayList<Integer> a1 = new ArrayList<Integer>(); 
		a1.add(0); 
		a1.add(1); 
		aList.add(a1); 
      
      	a1 = new ArrayList<Integer>(); 
      	a1.add(1); 
		a1.add(2); 
		aList.add(a1); 
      
      	a1 = new ArrayList<Integer>(); 
      	a1.add(3); 
		a1.add(1); 
		aList.add(a1); 

		findZombieScore(n, moves, initPosition, aList);

	} 
  
  
	public static void findZombieScore(int n, String moves, List<Integer> initPosition, List<List<Integer> > creaturesPosition){
        
		Map<Integer, List<Integer>> poorCreatures = new HashMap<Integer, List<Integer>>();
		Map<Integer, List<Integer>> zombiesPos = new HashMap<Integer, List<Integer>>(); 
		List<Integer> tempList = new ArrayList<Integer>();
		List<Integer> poorCreatureCounter = new ArrayList<Integer>();
		int score = 0;
		int count = 0;
		boolean shouldRun = true;
		int movesLen = moves.length();
		zombiesPos.put(0, initPosition);  

		for(int i = 0; i < creaturesPosition.size(); i++){
			poorCreatures.put(i, creaturesPosition.get(i));
		}

		while(shouldRun){
			for(int i = 0; i < movesLen; i++){
				tempList = zombiesPos.get(count);

				for(int j = 0; j < poorCreatures.size(); j++){
					if(!poorCreatureCounter.contains(j)){
						if(poorCreatures.get(j).get(0) == tempList.get(0) && poorCreatures.get(j).get(1) == tempList.get(1)){
							int tempCount = count + 1;	
							zombiesPos.put(tempCount, poorCreatures.get(j));
							poorCreatureCounter.add(j);
							score++;
						}
					}
				}

				int posX = tempList.get(0);
				int posY = tempList.get(1);

				if(moves.charAt(i) == 'U'){
					if(posY == 0){ 
						posY = (n-1); 
					} else {
						posY--;
					}
				} else if (moves.charAt(i) == 'R'){
					if(posX == (n-1)){ 
						posX = 0; 
					} else {
						posX++;
					}
				} else if (moves.charAt(i) == 'D'){
					if(posY == (n-1)){ 
						posY = 0; 
					} else {
						posY++;
					}
				} else {
					if(posX == 0){ 
						posX = (n-1);
					} else {
						posX--;
					}
				}
				
				tempList.clear();
				tempList.add(posX);
				tempList.add(posY);
				zombiesPos.put(count, tempList);
				
			}
			
			count++;
			if(count > (zombiesPos.size()-1)){
				shouldRun = false;
			}
		}

		//Output
      	//Score
		System.out.println(score);
      	//Zombie Positions
      	for (int i = 0; i < zombiesPos.size(); i++) { 
            System.out.print(zombiesPos.get(i) + " "); 
        }               
	}
} 
