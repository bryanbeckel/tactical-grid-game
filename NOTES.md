# Development Notes

10/7/2026: I need to first create the grid and set the size of the scene. Adding characters and the game system can come later. A grid should be represented by an array of arrays of tile objects. Also, anything related directly to JavaFX imports should not be placed in the main method.

10/8/2026: I should plan out the best way to customize the grid. Should it be edited one tile at a time, one row at a time, or one column at a time? How can I avoid this part of the code being several pages long? Can I shorten it with iteration or recursion?

10/13/2026: I should replace if statements that check Color objects with an independent Java object so that my work is cleaner. I shall use my knowledge of enums from 198C for that and hope that it works similarly in Java. Lastly, I also want to find an easier way to read and set up maps than doing it piece by piece. If I can learn to implement CSV files like I did with Python, that would help a lot.

11/23/2026: I need to create a heap to hold the turn order for the characters in the game. This way I can create a system because on the initiative of each character.