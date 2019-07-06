# client-server architecture -- concept #1 

This is a very basic example of a simple web app managing client(s) states in the serverd, used as a tool for a toy data exploration task.

It is work in progress -- more will come.

The APIs and implementation details are experimental, and require much more thought. The important thing here, for now, is demonstrating the usage example.

## Inspiration

[Withshadow](../withshadow) for using [React Leaflet](https://react-leaflet.js.org) with [shadow-cljs](https://github.com/thheller/shadow-cljs).

[Snake Lake](https://github.com/timothypratley/snakelake) for client-server architecture using [Sente](https://github.com/ptaoussanis/sente).

[Shiny](https://shiny.rstudio.com/) is a whole web framework for data science usage. We try to mimic the essense of its architecture here, with some differences.

## Installation
```shell
npm install
```

## Usage

Run the client by
```shell
npm run watch
```
at your shell, then open `localhost:8700` at the browsser. Note that you can open it in several browsers (or browser tabs), and they will share the same state.

Open a REPL, open [example1](./examples/example1.clj), then evaluate it one form after another, looking at the results in the browser.

## TODO

- add a slider component

- create a more complete leaflet API

- add a more complete example (consider cases of slow computation)

- think about consistent API, probably using [Hanami](https://github.com/jsa-aerial/hanami) templates

- think about efficiency of the server-side reactive mechanism

- consider [freactive.core](https://github.com/aaronc/freactive.core) for server-side reactivity

- document implementation details

## License

Copyright © 2017 LRS

Distributed under the Eclipse Public License either version 1.0 or (at your option) any later version.
