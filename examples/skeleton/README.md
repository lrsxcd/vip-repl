# Initial skeleton for vip-repl

This is a very basic skeleton of vip-repl

## Task

To make it simple we will start with filters

Filter will currently be simple html input using one of the types

* number
* range
* date
* time


Createing a filter should do the following 
Initilize  - see issues
Create the change dispacher 
Can we create a single dispacher for all filter - simply add filter id and value?
Create the co-effect - change the db
Create the co-effect/interceptor - update the server - We can alternatively use re-frame Interceptors as this is valid for all events, we need to create a messge with message-type and new value






## issues and questions 

how to do multiple initilizers
(https://github.com/Day8/re-frame/blob/master/docs/CodeWalkthrough.md#initialize)
In react/redus you would have seprate initilizer to each branch of the db tree
Maybe in order to solve it we do one initilize of the global structure and later creating the keys of each filter with assoc-in




##Structure


## Adding a new visual element