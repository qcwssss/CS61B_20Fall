# Project 3 Prep

**For tessellating hexagons, one of the hardest parts is figuring out where to place each hexagon/how to easily place hexagons on screen in an algorithmic way.
After looking at your own implementation, consider the implementation provided near the end of the lab.
How did your implementation differ from the given one? What lessons can be learned from it?**

Answer:
I choose the upper left corner of a single hexagon as the input initial position. In order to create a tesselation, first I create a method which draws a columns of hexagons from top to bottom.
Next, I need to figure out the upper left corner of next column. It's like one column from left to right each time, and by calculating the right start point of the next column, we can fulfill our goal.
-----

**Can you think of an analogy between the process of tessellating hexagons and randomly generating a world using rooms and hallways?
What is the hexagon and what is the tesselation on the Project 3 side?**

Answer:
Yes. Creating one hexagon is like generating a room, a hall etc. In order to build a world, we need to connect each piece seamlessly, just like the tesselation.

-----
**If you were to start working on world generation, what kind of method would you think of writing first? 
Think back to the lab and the process used to eventually get to tessellating hexagons.**

Answer:
I think it's generating a line of wall, because this is the most basic piece, I can use it to build a room, a half way, basically any type of things.

-----
**What distinguishes a hallway from a room? How are they similar?**

Answer:
The room is surrounded by walls, usually four, and at least one entrance
