# Using [Shadow](http://shadow-cljs.org/)

## WORK IN PROGRESS

This project was createed with the shadow npx tool

## Prerequisites

Install [nvm](https://gist.github.com/d2s/372b5943bce17b964a79)

Install latest stabel node 

```shell 
nvm install v10.15.3
nvm alias default v10.15.3
nvm list
```

## Install shadow tools with npm

```shell
npm install -g shadow-cljs
```

## Development

Run the application in development mode:

```shell
cd withshadow
npm install
npm run dev
```

### Add a React library

This is how you add a React library:

```shell
npm install --save libname
```

Add the library code to the main page:

```shell
npm install
npm run dev
```

#### Example of using React-Leaflet

Adding the leaflet to the packages.json

```shell
npm add --save leaflet
npm add --save react-leaflet
```
