

boating:- find(Solution), write(Solution), fail. 


initial( state( farmer(west), goat(west), cabbage(west), wolf(west) ) ).
goal( state( farmer(east), goat(east), cabbage(east), wolf(east) ) ).

different(west, east).
different(east, west).

eat( state( farmer(Side_f), goat(Side_g), cabbage(Side_c), wolf(Side_w) ) ) :- 
    ( Side_g=Side_w, different(Side_f,Side_g)); (Side_g=Side_c, different(Side_f,Side_g)).

find(Solution):- initial(State), result(State, Solution).    
   
from( state( farmer(Side_f), goat(Side_f), cabbage(Side_c), wolf(Side_w) ),
      state( farmer(Side_f_different), goat(Side_f_different), cabbage(Side_c), wolf(Side_w) ) ) :- different(Side_f,Side_f_different). 

 
 
from( state( farmer(Side_f), goat(Side_g), cabbage(Side_f), wolf(Side_w)),
      state( farmer(Side_f_different), goat(Side_g), cabbage(Side_f_different), wolf(Side_w))):-different(Side_f, Side_f_different). 

 

from(state(farmer(Side_f),goat(Side_g),cabbage(Side_c),wolf(Side_f)),
     state(farmer(Side_f_different),goat(Side_g),cabbage(Side_c),wolf(Side_f_different))):-different(Side_f, Side_f_different). 

 

from(state(farmer(Side_f),goat(Side_g),cabbage(Side_c),wolf(Side_w)),
     state(farmer(Side_f_different),goat(Side_g),cabbage(Side_c),wolf(Side_w))):-different(Side_f, Side_f_different).
     
     


result(State, Solution) :- search(State, [], Solution).

search(State, Sofar, [State | Sofar]) :- goal(State), ! .

search(State, Sofar, Solution) :- from(State, Newstate), not(eat(Newstate)), not(member(Newstate, Sofar)), search(Newstate, [State | Sofar], Solution).






