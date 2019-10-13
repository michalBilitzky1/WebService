Web Service for addition and multiplication

Running instructions:
1. download application from git and run it.
2. choose a mathematical operation addition or multiplication.
3. choose 2 numbers
4. open a browser and write in the search bar the following line: "localhost:8085/operation/number1/number2"
the opearation is the one chosen in instruction 2, the numbers are those chosen in instruction 3.
example:"localhost:8085/multiplication/999/999"
5. the result of the mathematical operation will appear on the screen. in the example above the number 998001 will appear on the screen.

Challenges:

Creating a web service by myself.
I had to learn how develope and design a web service using different sources from the Enternet.
There were some difficulties since i worked on intellij and i never developed a web application in this platform before.
I have read about different servers to install like glassfish and apache and eventually found the site  start.spring.io 
which helped me bootstrap my application.

Things i would have added if I had more time:

I would split the web service to 2 webservices instead of one.
one that is encharged of the multiplication and the other of the addition so that the project will conatain 2 web services.
I didnt do that because althugh i looked in the Enternet i didnt find how to make 2 webservices under the same project. 
if i had more time i would have find the way to do that. 
the reason i decided to develope one project and not 2 projects with 2 web services is that the 2 functions of the addition and
multiplication use the same helper functions and i didn't want to split them to 2 projects and then have a duplicated code.
If i had more time i would have find a better way like maybe making an interface of the controller since both services
receive 2 numbers and work on them.   

  
