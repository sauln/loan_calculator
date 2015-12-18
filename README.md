# Scala loan calculator

This project is a small loan calculator that will compute the optimal payback scenario given user defined loans and chosen strategy.

This will be the first larger project I undertake in Scala and is entirely a learning experience.


##Parts:
  * linear programming algorithm
    * though the problem turned out to be simple enough to not need linear programming, it was in pursuit of learning the simplex algorithm that I chose this project
  * multiple payoff strategies
    * snowball - pay off smallest loan first
    * avalanche - pay off highest interest rate first
    * fastest payoff - this could require a linear programming algorithm
    * pay the lowest amount - 
  * nice looking front end
    * def the hardest part
  * comprehensive testing
  * visualization dashboard 
    * dynamic pie chart of loans
    * visualizations relevant to different strategies


## Learning I want out of this project
  * Gain experience with Scala 
  * Get comfortable with functional programming design
  * Understand view-first and use lift


## TODO
  * Add visualization support
    * what libraries?
      * wookietreiber/scala-chart is a wrapper for JFreeChart

  * Add option to remove/edit loans
  * Use widgets? based on jQuery widgets
    * TableSorter could be good display of loans
  * Integrate calculations!
  * Buttons to select strategy












