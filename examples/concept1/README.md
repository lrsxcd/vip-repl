# client-server architecture -- concept #1 

This is a very basic example of sharing state across clients through one central server.

At the moment, we only the set of markers across leaflet maps, nothing more.

This is work in progress -- more will come.

## Inspiration

[Withshadow](../withshadow) for using [React Leaflet](https://react-leaflet.js.org).

[Snake Lake](https://github.com/timothypratley/snakelake) for client-server architecture using [Sente](https://github.com/ptaoussanis/sente).

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

Open `localhost:8700` at two distinct browsers, try clicking the maps, and see them synchronize.

## License

Copyright © 2017 LRS

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
