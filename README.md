# AStarMaker
Java Terrain Maker that gets solved with A*.

DESCRIPTION

The algorithm is demonstrated through the use of an ant trying to get to a block of cheese. The heuristic used is Euclidean distance and I've tried to pre-compute everything I can prior to the search so that it is as time efficient as possible (although I'm sacrificing memory). This includes calculating all heuristic values, finding all neighbours, and adding all obstacles (water) to the closed list. I have also purposely added time delays so that the algorithm's inner workings are made more apparent for the user. A 5s delay is made before the search (so the user can examine the grid) and 0.25s delays are made before visiting each cell (so the search evolution is visible).

The cost of moving are as follows:

	      Gravel - 1 (Gray)
        
        Grass  - 3 (Green)
        
        Swamp  - 4 (Brown)
        
        Water  - Impossible (Blue)
        

The grid can be setup automatically or manually. Gravel is the default for automatic configuration. For manual configuration, please give the coordinate in the form "row,column" where the top left is 0,0.



INSTRUCTIONS:

Please note that the program has ONLY been tested on Terminal in OSX Sierra. Follow these steps to get it working:

	1) cd to just outside the "astar" folder
  
	2) enter "javac astar/AStarMain.java" to compile
  
	3) enter "java astar.AStarMain" to run
  
	4) follow command prompts

Usage video: https://youtu.be/ICxpfUf71XM
