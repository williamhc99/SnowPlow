# SnowPlow

Snowplow is a simple algorithm that navigates through a randomly generated 2D grid of 1s, and 2s. 

Starting from the topmost, leftmost 0, the algorithm will 'plow' through the grid. Only 1's can be 'plowed' and once 'plowed' they turn to 0.

The rules for the next grid to plow is a 1 that is adjacent or diagonal to a 0 until there all possible 1s that can be 'plowed' are exhausted.

The src folder contains 2 java files, one where Snowplow is displayed on stdout and one where Snowplow is displayed on a GUI. 

Example:

Grid before snowplow:

1 1 1 1 1 <br />
1 1 1 1 1 <br />
1 1 1 2 2 <br />
1 1 2 1 2 <br />
2 1 2 1 2 <br />

Grid after snowplow:

0 0 0 0 0 <br />
0 0 0 0 0 <br />
0 0 0 2 2 <br />
0 0 2 0 2 <br />
2 0 2 0 2 <br />
