# Scala loan calculator

This project is a small loan calculator that will compute the optimal payback scenario given user defined loans and chosen strategy.

This will be the first larger project I undertake in Scala and is entirely a learning experience.


##Parts:
  * linear programming algorithm
    * though the problem is simple enough to not need a full linear programming algorithm, I would like to phrase the problem to use a custom linear programming algorithm and one using one found in a common library.
  * multiple strategies
    * two coined strategies are:
      * snowball - pay off smallest loan first
      * avalanche - pay off highest interest rate first
    * fastest payoff - this could require a linear programming algorithm
    * pay the lowest amount - this can be shown to be equivalent to the avalanche method via the linear programming algorithm.
  * nice looking front end 
  * comprehensive testing 

## Learning I want out of this project
  * Get a grip on Scala - actually the smallest part of this project
  * Get a grip on functional programming 
  * Get a grip on testing - this is difficult to incorporate when I'm learning so many other aspects.  
  * Get a grip on a web framework - Use lift




## TODO
  * Develop forms in lift
    * how to get access to data?
    * restructure page so it looks better
    * dynamically allow more loan inputs
  * figure out this testing - choose a library - ask at PDXScala 













