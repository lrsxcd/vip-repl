# client-server architecture -- concept #1 

This is a very basic example of sharing state across clients through one central server.

Currently, only the set of markers is shared across leaflet maps.

## Inspiration

[withshadow](../withshadow) for using React Leaflet.

[snakelake](https://github.com/timothypratley/snakelake) for client-server architecture using [Sente](https://github.com/ptaoussanis/sente).

## Usage

Server:
``` shell
lein run
```

Client:
``` shell
yarn install
yarn watch
```

Open two browsers at `localhost:8700`, try clicking them, and see them synchronize.

## License

Copyright © 2017 LRS

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
