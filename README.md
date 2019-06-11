# VIP REPL

*VIP REPL* stands for "View-Interact-Pull REPL". It is a Clojure library
for visualizing data in an interactive mode while working in a
Clojure REPL (Read Eval Print Loop).

## Overview

*VIP REPL* is intended to be used by users who use the REPL for data
exploration, adding the ability to visualize data sets graphically,
interact with graphs and modify them, then pull the modified data back
to the REPL, thus enabling working on it in the on-going REPL session.

*VIP REPL* works in asynchronous mode, allowing the user to switch
between VIP REPL windows and the REPL session.

Describing a typical scenario might make this more tangible:

1. A user starts working in a REPL. She loads some data and starts
   manipulating it.
2. At some point, she invokes VIP REPL for visualizing some specific
   data set she has created; the type of visualization and other
   parameters are defined by the user when calling the VIP REPL
   visualization code.
3. VIP REPL opens a graphical window displaying the data
   visualization.
4. Using controls in the VIP REPL window, the user
   manipulates the data; for instance, she selects a subset of the
   data (rows and/or columns), creates some new computed columns and
   so on.
5. The user can "commit" her modifications to the data set, thus
   creating a modified copy of the original data set that can be
   retrieved in the REPL for further processing.
6. Since VIP REPL is asynchronous, the user can get back to the REPL
   and continue her work without closing the VIP REPL window. She
   might even open additional VIP REPL windows for displaying other
   data sets.
   


